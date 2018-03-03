package com.struts.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.dao.GradeDao;
import com.struts.model.Grade;
import com.struts.util.DbUtil;
import com.struts.util.NavCodeUtil;
import com.struts.util.ResponseUtil;
import com.struts.util.StringUtil;

import net.sf.json.JSONObject;

public class GradeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();
	private List<Grade> gradeList = new ArrayList<Grade>();
	private String navCode;
	private String mainPage;
	private String gradeId;
	private Grade grade;
	
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
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
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	public String gradeList() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			gradeList = gradeDao.gradeList(con);
			navCode = NavCodeUtil.genNavigetion("年级信息管理", "年级信息维护");
			mainPage = "grade/gradeList.jsp";
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
			if(StringUtil.isNotEmpty(gradeId)) {
				grade = gradeDao.gradeById(con, gradeId);
				navCode = NavCodeUtil.genNavigetion("年级信息管理", "年级信息修改");
			}else {
				navCode = NavCodeUtil.genNavigetion("年级信息管理", "年级信息添加");
			}
			mainPage = "grade/gradeSave.jsp";
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
			if(StringUtil.isNotEmpty(gradeId)) {
				grade.setGradeId(Integer.parseInt(gradeId));
				gradeDao.updateGrade(con, grade);
			}else {
				gradeDao.addGrade(con, grade);
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
			if(gradeDao.existClassAtgrade(con, gradeId)) {
				result.put("error", "年级下面有班级字典，不能删除！");
			}else {
				gradeDao.deleteGrade(con, gradeId);
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
}
