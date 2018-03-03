package com.struts.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.dao.SclassDao;
import com.struts.dao.GradeDao;
import com.struts.model.Sclass;
import com.struts.model.Grade;
import com.struts.model.PageBean;
import com.struts.util.DbUtil;
import com.struts.util.NavCodeUtil;
import com.struts.util.PaginationUtil;
import com.struts.util.PropertiesUtil;
import com.struts.util.ResponseUtil;
import com.struts.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClassAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	private GradeDao gradeDao = new GradeDao();
	private HttpServletRequest request;
	private SclassDao sclassDao = new SclassDao();
	private List<Sclass> sclassList = new ArrayList<Sclass>();
	private List<Grade> gradeList = new ArrayList<Grade>();
	private String navCode;
	private String mainPage;
	private String pageCode;
	private int page = 0;
	private String gradeName;
	private Sclass sclass;
	private String classId;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	public List<Sclass> getSclassList() {
		return sclassList;
	}
	public void setSclassList(List<Sclass> sclassList) {
		this.sclassList = sclassList;
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
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Sclass getSclass() {
		return sclass;
	}
	public void setSclass(Sclass sclass) {
		this.sclass = sclass;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public String classList() {
		Connection con = null;
		Sclass sclass = new Sclass();
		if(StringUtil.isNotEmpty(gradeName)) {
			sclass.setGradeName(gradeName);
		}
		if(page == 0) {
			page = 1;
		}
		PageBean pageBean = new PageBean(page, Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		try {
			con=dbUtil.getCon();
			sclassList = sclassDao.classList(con,sclass,pageBean);
			navCode = NavCodeUtil.genNavigetion("班级信息管理", "班级信息维护");
			int total = sclassDao.classCount(con, sclass);
			if(gradeName==null) {
				gradeName="";
			}
			pageCode = PaginationUtil.genPagination(total, Integer.parseInt(PropertiesUtil.getValue("pageSize")), page,request.getContextPath()+"/class!classList","&gradeName="+gradeName);
			mainPage = "class/classList.jsp";
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
	
	public String preSave() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			gradeList = gradeDao.gradeList(con);
			if(StringUtil.isNotEmpty(classId)) {
				sclass = sclassDao.classById(con, classId);
				navCode = NavCodeUtil.genNavigetion("班级信息管理", "班级信息修改");
			}else {
				navCode = NavCodeUtil.genNavigetion("班级信息管理", "班级信息添加");
			}
			mainPage = "class/classSave.jsp";
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
	
	public String save() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(classId)) {
				sclass.setClassId(Integer.parseInt(classId));
				sclassDao.updateClass(con, sclass);
			}else {
				sclassDao.addClass(con, sclass);
			}
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
		return "yes";
	}
	
	public String delete() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			JSONObject result = new JSONObject();
			if(sclassDao.existsStuAtClass(con, classId)) {
				result.put("error", "班级下面有学生，不能删除！");
			}else {
				sclassDao.deleteClass(con, classId);
				result.put("success", true);
				result.put("message", "删除成功！");
			}
			ResponseUtil.write(result, ServletActionContext.getResponse());
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
		return null;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	public String getClassByGradeName() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			Sclass s_sclass = new Sclass();
			s_sclass.setGradeName(gradeName);
			List<Sclass> classList = sclassDao.classList(con, s_sclass, null);
			JSONArray result = JSONArray.fromObject(classList);
			ResponseUtil.write(result, ServletActionContext.getResponse());
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
		return null;
	}
}