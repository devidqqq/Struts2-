package com.struts.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.struts.dao.DataDicTypeDao;
import com.struts.model.DataDicType;
import com.struts.util.DbUtil;
import com.struts.util.NavCodeUtil;
import com.struts.util.ResponseUtil;
import com.struts.util.StringUtil;

import net.sf.json.JSONObject;

public class DataDicTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	DataDicTypeDao dataDicTypeDao = new DataDicTypeDao();
	private List<DataDicType> dataDicTypeList = new ArrayList<DataDicType>();
	private String navCode;
	private String mainPage;
	private String ddTypeId;
	private DataDicType dataDicType;
	
	public String getDdTypeId() {
		return ddTypeId;
	}
	public void setDdTypeId(String ddTypeId) {
		this.ddTypeId = ddTypeId;
	}
	public DataDicType getDataDicType() {
		return dataDicType;
	}
	public void setDataDicType(DataDicType dataDicType) {
		this.dataDicType = dataDicType;
	}
	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}
	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
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
	
	public String ddTypeList() {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			dataDicTypeList = dataDicTypeDao.dataDicTypeList(con);
			navCode = NavCodeUtil.genNavigetion("系统管理", "数据字典类别维护");
			mainPage = "dataDicType/dataDicTypeList.jsp";
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
			if(StringUtil.isNotEmpty(ddTypeId)) {
				dataDicType = dataDicTypeDao.dataDicTypeById(con, ddTypeId);
				navCode = NavCodeUtil.genNavigetion("系统管理", "数据字典类别修改");
			}else {
				navCode = NavCodeUtil.genNavigetion("系统管理", "数据字典类别添加");
			}
			mainPage = "dataDicType/dataDicTypeSave.jsp";
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
			if(StringUtil.isNotEmpty(ddTypeId)) {
				dataDicType.setDdTypeId(Integer.parseInt(ddTypeId));
				num = dataDicTypeDao.updateDataDicType(con, dataDicType);
			}else {
				num = dataDicTypeDao.addDataDicType(con, dataDicType);
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
			if(dataDicTypeDao.existDataDicAtddType(con, ddTypeId)) {
				result.put("error", "数据字典类别下面有数据字典，不能删除！");
			}else {
				num = dataDicTypeDao.deleteDataDicType(con, ddTypeId);
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
