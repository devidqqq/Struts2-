package com.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.struts.model.DataDic;
import com.struts.model.PageBean;
import com.struts.util.StringUtil;

public class DataDicDao {

	
	public List<DataDic> dataDicList(Connection con, DataDic s_dataDic,PageBean pageBean) throws SQLException{
		List<DataDic> dataDicList = new ArrayList<DataDic>();
		StringBuffer sql = new StringBuffer("select * from t_datadic t1,t_datadictype t2 where t1.ddTypeId = t2.ddTypeId");
		if(StringUtil.isNotEmpty(s_dataDic.getDdTypeName())) {
			sql.append(" and t2.ddTypeName like '%"+s_dataDic.getDdTypeName()+"%'");
		}
		if(pageBean!=null) {
			sql.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DataDic dataDic = new DataDic();
			dataDic.setDdId(rs.getInt("ddId"));
			dataDic.setDdTypeId(rs.getInt("ddTypeId"));
			dataDic.setDdTypeName(rs.getString("ddTypeName"));
			dataDic.setDdValue(rs.getString("ddValue"));
			dataDic.setDdDesc(rs.getString("ddDesc"));
			dataDicList.add(dataDic);
		}
		return dataDicList;
	}
	
	public int dataDicCount(Connection con, DataDic s_dataDic) throws SQLException{
		StringBuffer sql = new StringBuffer("select count(*) as total from t_datadic t1,t_datadictype t2 where t1.ddTypeId = t2.ddTypeId");
		if(StringUtil.isNotEmpty(s_dataDic.getDdTypeName())) {
			sql.append(" and t2.ddTypeName like '%"+s_dataDic.getDdTypeName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		}else {
			return 0;
		}
	}
	
	public int addDataDic(Connection con, DataDic dataDic) throws Exception {
		String sql = "insert into t_datadic values(null,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dataDic.getDdTypeId());
		pstmt.setString(2, dataDic.getDdValue());
		pstmt.setString(3, dataDic.getDdDesc());
		return pstmt.executeUpdate();
	}
	
	public int updateDataDic(Connection con, DataDic dataDic) throws Exception {
		String sql = "update t_datadic set ddTypeId=?,ddValue=?,ddDesc=? where ddId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dataDic.getDdTypeId());
		pstmt.setString(2, dataDic.getDdValue());
		pstmt.setString(3, dataDic.getDdDesc());
		pstmt.setInt(4, dataDic.getDdId());
		return pstmt.executeUpdate();
	}
	
	public DataDic dataDicById(Connection con,String ddId) throws Exception{
		String sql = "select * from t_datadic where ddId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ddId);
		ResultSet rs = pstmt.executeQuery();
		DataDic dataDic = new DataDic();
		if(rs.next()) {
			dataDic.setDdId(rs.getInt("ddId"));
			dataDic.setDdTypeId(rs.getInt("ddTypeId"));
			dataDic.setDdValue(rs.getString("ddValue"));
			dataDic.setDdDesc(rs.getString("ddDesc"));
		}
		return dataDic;
	}
	
	public int deleteDataDic(Connection con,String ddId) throws Exception{
		String sql = "delete from t_datadic where ddId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ddId);
		return pstmt.executeUpdate();
	}
}
