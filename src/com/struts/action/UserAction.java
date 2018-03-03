package com.struts.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.struts.dao.UserDao;
import com.struts.model.User;
import com.struts.util.DbUtil;
import com.struts.util.NavCodeUtil;
import com.struts.util.ResponseUtil;
import com.struts.util.StringUtil;

import net.sf.json.JSONObject;

public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private User user;
	private String error;
	private String imageCode;
	private String navCode;
	private String mainPage;
	private int userId;
	private String password;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	



	public String login(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(user.getUserName())||StringUtil.isEmpty(user.getPassword())){
			error="用户名或密码为空！";
			return ERROR;
		}
		if(StringUtil.isEmpty(imageCode)){
			error="验证码不能为空！";
			return ERROR;
		}
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误！";
			return ERROR;
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				error="用户名或密码错误！";
				return ERROR;
			}else{
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	public String preSave() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			navCode = NavCodeUtil.genNavigetion("系统管理", "修改密码");
			mainPage = "user/modifyPassword.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	public String update() {
		Connection con=null;
		try{
			con=dbUtil.getCon();
			user = new User();
			user.setUserId(userId);
			user.setPassword(password);
			int num = userDao.updateUser(con, user);
			JSONObject result = new JSONObject();
			if(num>0) {
				result.put("success", true);
			}else {
				result.put("errorMsg", "修改失败！");
			}
			ResponseUtil.write(result, ServletActionContext.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String logOut() {
		HttpSession session=request.getSession();
		session.invalidate();
		return "logOut";
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
