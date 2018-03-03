package com.struts.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.dao.DataDicDao;
import com.struts.dao.DataDicTypeDao;
import com.struts.model.DataDic;
import com.struts.model.DataDicType;
import com.struts.model.PageBean;
import com.struts.util.DbUtil;
import com.struts.util.NavCodeUtil;
import com.struts.util.PaginationUtil;
import com.struts.util.PropertiesUtil;
import com.struts.util.ResponseUtil;
import com.struts.util.StringUtil;

import net.sf.json.JSONObject;

public class DataDicAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	DataDicTypeDao dataDicTypeDao = new DataDicTypeDao();
	private HttpServletRequest request;
	private DataDicDao dataDicDao = new DataDicDao();
	private List<DataDic> dataDicList = new ArrayList<DataDic>();
	private List<DataDicType> dataDicTypeList = new ArrayList<DataDicType>();
	private String navCode;
	private String mainPage;
	private String ddTypeName;
	private String ddId;
	private DataDic dataDic;
	private String pageCode;
	private int page = 0;
	
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
	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}
	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}
	public List<DataDic> getDataDicList() {
		return dataDicList;
	}
	public void setDataDicList(List<DataDic> dataDicList) {
		this.dataDicList = dataDicList;
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
	public String getDdTypeName() {
		return ddTypeName;
	}
	public void setDdTypeName(String ddTypeName) {
		this.ddTypeName = ddTypeName;
	}
	public String getDdId() {
		return ddId;
	}
	public void setDdId(String ddId) {
		this.ddId = ddId;
	}
	public DataDic getDataDic() {
		return dataDic;
	}
	public void setDataDic(DataDic dataDic) {
		this.dataDic = dataDic;
	}
	
	public String dataDicList() {
		Connection con = null;
		DataDic dataDic = new DataDic();
		if(StringUtil.isNotEmpty(ddTypeName)) {
			dataDic.setDdTypeName(ddTypeName);
		}
		if(page == 0) {
			page = 1;
		}
		PageBean pageBean = new PageBean(page, Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		try {
			con=dbUtil.getCon();
			dataDicList = dataDicDao.dataDicList(con,dataDic,pageBean);
			navCode = NavCodeUtil.genNavigetion("系统管理", "数据字典维护");
			int total = dataDicDao.dataDicCount(con, dataDic);
			if(ddTypeName==null) {
				ddTypeName="";
			}
			pageCode = PaginationUtil.genPagination(total, Integer.parseInt(PropertiesUtil.getValue("pageSize")), page,request.getContextPath()+"/dataDic!dataDicList","&ddTypeName="+ddTypeName);
			mainPage = "dataDic/dataDicList.jsp";
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
			dataDicTypeList = dataDicTypeDao.dataDicTypeList(con);
			if(StringUtil.isNotEmpty(ddId)) {
				dataDic = dataDicDao.dataDicById(con, ddId);
				navCode = NavCodeUtil.genNavigetion("系统管理", "数据字典修改");
			}else {
				navCode = NavCodeUtil.genNavigetion("系统管理", "数据字典添加");
			}
			mainPage = "dataDic/dataDicSave.jsp";
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
		int num = 0;
		try {
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(ddId)) {
				dataDic.setDdId(Integer.parseInt(ddId));
				num = dataDicDao.updateDataDic(con, dataDic);
			}else {
				num = dataDicDao.addDataDic(con, dataDic);
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
		int num = 0;
		try {
			con=dbUtil.getCon();
			JSONObject result = new JSONObject();
				num = dataDicDao.deleteDataDic(con, ddId);
				result.put("success", true);
				result.put("message", "删除成功！");
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
}