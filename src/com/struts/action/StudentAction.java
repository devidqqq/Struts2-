package com.struts.action;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.dao.DataDicDao;
import com.struts.dao.GradeDao;
import com.struts.dao.SclassDao;
import com.struts.dao.StudentDao;
import com.struts.model.DataDic;
import com.struts.model.Grade;
import com.struts.model.PageBean;
import com.struts.model.Sclass;
import com.struts.model.Student;
import com.struts.util.DateUtil;
import com.struts.util.DbUtil;
import com.struts.util.NavCodeUtil;
import com.struts.util.PaginationUtil;
import com.struts.util.PropertiesUtil;
import com.struts.util.ResponseUtil;
import com.struts.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * @author JackDu
 * StudentAction.java
 * 2018年1月27日
 */
public class StudentAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DbUtil dbUtil = new DbUtil();
	private StudentDao studentDao = new StudentDao();
	private DataDicDao dataDicDao = new DataDicDao();
	private GradeDao gradeDao = new GradeDao();
	private SclassDao sclassDao = new SclassDao();
	private List<Student> stuList = new ArrayList<Student>();
	private List<DataDic> sexlist = new ArrayList<>();
	private List<DataDic> nationlist = new ArrayList<>();
	private List<Grade> gradeList = new ArrayList<>();
	private List<Sclass> sclassList = new ArrayList<Sclass>();
	private String navCode;
	private String studentId;
	private Student student;
	private Student s_student;
	private String mainPage;
	private List<DataDic> zzmmlist;
	private List<Sclass> classList;
	private HttpServletRequest request;
	/*
	 * struts2文件上传
	 */
	
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	
	//分页
	private Object pageCode;
	private int page;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public List<Student> getStuList() {
		return stuList;
	}
	
	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}
	public Student getS_student() {
		return s_student;
	}

	public void setS_student(Student s_student) {
		this.s_student = s_student;
	}
	public List<DataDic> getSexlist() {
		return sexlist;
	}

	public void setSexlist(List<DataDic> sexlist) {
		this.sexlist = sexlist;
	}

	public List<DataDic> getNationlist() {
		return nationlist;
	}

	public void setNationlist(List<DataDic> nationlist) {
		this.nationlist = nationlist;
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
	public List<DataDic> getZzmmlist() {
		return zzmmlist;
	}

	public void setZzmmlist(List<DataDic> zzmmlist) {
		this.zzmmlist = zzmmlist;
	}
	public List<Sclass> getClassList() {
		return classList;
	}

	public void setClassList(List<Sclass> classList) {
		this.classList = classList;
	}

	/*
	 * 文件上传
	 */
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public String getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public Object getPageCode() {
		return pageCode;
	}

	public void setPageCode(Object pageCode) {
		this.pageCode = pageCode;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String list() {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if(page == 0) {
				page = 1;
			}
			/*if(StringUtil.isNotEmpty(list)) {
				s_student = new Student();
				HttpSession session = request.getSession();
				session.setAttribute("s_student", s_student);
			}*/
			HttpSession session = request.getSession();
			if(s_student!=null) {
				session.setAttribute("s_student", s_student);
			}else {
				if(session.getAttribute("s_student")==null) {
					s_student = new Student();
				}else {
					s_student = (Student) session.getAttribute("s_student");
				}
			}
			PageBean pageBean = new PageBean(page, Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			stuList = studentDao.stuList(con,s_student,pageBean);
			int total = studentDao.studentCount(con,s_student);
			navCode = NavCodeUtil.genNavigetion("学生信息管理", "学生信息维护");
			DataDic s_dataDic = new DataDic();
			s_dataDic.setDdTypeName("性别");
			sexlist = dataDicDao.dataDicList(con, s_dataDic, null);
			
			s_dataDic.setDdTypeName("名族");
			nationlist = dataDicDao.dataDicList(con, s_dataDic, null);
			
			gradeList = gradeDao.gradeList(con);
			
			if(s_student!=null&&StringUtil.isNotEmpty(s_student.getGradeName())) {
				Sclass cls = new Sclass();
				cls.setGradeName(s_student.getGradeName());
				sclassList = sclassDao.classList(con, cls, null);
			}
			
			pageCode = PaginationUtil.genPagination(total, Integer.parseInt(PropertiesUtil.getValue("pageSize")),page,request.getContextPath()+"/student!list","");
			mainPage = "student/studentList.jsp";
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
	
	public String preSave(){
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if(StringUtil.isNotEmpty(studentId)) {
				student = studentDao.getStudentById(con, studentId);
			}
			
			DataDic s_dataDic = new DataDic();
			s_dataDic.setDdTypeName("性别");
			sexlist = dataDicDao.dataDicList(con, s_dataDic, null);
			
			s_dataDic.setDdTypeName("名族");
			nationlist = dataDicDao.dataDicList(con, s_dataDic, null);
			s_dataDic.setDdTypeName("政治面貌");
			zzmmlist = dataDicDao.dataDicList(con, s_dataDic, null);
			
			classList = sclassDao.classList(con, new Sclass(), null);
			mainPage = "student/studentSave.jsp";
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
	
	
	public String save(){
		Connection con = null;
		try {
			if(uploadFile!=null) {
				String imageName = DateUtil.getCurrentDate();
				String realPath = ServletActionContext.getServletContext().getRealPath("stuImage");
				String imageFileName = imageName+"."+uploadFileFileName.split("\\.")[1];
				File saveFile = new File(realPath, imageFileName);
				FileUtils.copyFile(uploadFile, saveFile);
				student.setStuPic(imageFileName);
			}else if(StringUtil.isEmpty(student.getStuPic())){
				student.setStuPic("");
			}
			
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(studentId)) {
				student.setStudentId(studentId);
				studentDao.updateStudent(con,student);
			}else {
				studentDao.addStudent(con,student);
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
			con = dbUtil.getCon();
			int num = studentDao.deleteStudent(con, studentId);
			JSONObject result = new JSONObject();
			if(num>0) {
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

	public String allInfo() {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			student = studentDao.getStudentById(con, studentId);
			navCode = NavCodeUtil.genNavigetion("学生信息管理", "学生信息详情");
			mainPage = "student/studentDetails.jsp";
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
