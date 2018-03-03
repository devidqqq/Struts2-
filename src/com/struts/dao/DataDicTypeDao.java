package com.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.struts.model.DataDicType;

public class DataDicTypeDao {
	
	
	public List<DataDicType> dataDicTypeList(Connection con) throws Exception{
		List<DataDicType> dataDicTypeList = new ArrayList<DataDicType>();
		String sql = "select * from t_datadictype";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DataDicType dataDicType = new DataDicType();
			dataDicType.setDdTypeId(rs.getInt("ddTypeId"));
			dataDicType.setDdTypeName(rs.getString("ddTypeName"));
			dataDicType.setDdTypeDesc(rs.getString("ddTypeDesc"));
			dataDicTypeList.add(dataDicType);
		}
		return dataDicTypeList;
 	}
	
	public int addDataDicType(Connection con, DataDicType dataDicType) throws Exception {
		String sql = "insert into t_datadictype values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dataDicType.getDdTypeName());
		pstmt.setString(2, dataDicType.getDdTypeDesc());
		return pstmt.executeUpdate();
	}
	
	public int updateDataDicType(Connection con, DataDicType dataDicType) throws Exception {
		String sql = "update t_datadictype set ddTypeName=?,ddTypeDesc=? where ddTypeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dataDicType.getDdTypeName());
		pstmt.setString(2, dataDicType.getDdTypeDesc());
		pstmt.setInt(3, dataDicType.getDdTypeId());
		return pstmt.executeUpdate();
	}
	
	public DataDicType dataDicTypeById(Connection con,String ddTypeId) throws Exception{
		String sql = "select * from t_datadictype where ddTypeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ddTypeId);
		ResultSet rs = pstmt.executeQuery();
		DataDicType dataDicType = new DataDicType();
		if(rs.next()) {
			dataDicType.setDdTypeId(rs.getInt("ddTypeId"));
			dataDicType.setDdTypeName(rs.getString("ddTypeName"));
			dataDicType.setDdTypeDesc(rs.getString("ddTypeDesc"));
		}
		return dataDicType;
	}
	
	public int deleteDataDicType(Connection con,String ddTypeId) throws Exception{
		String sql = "delete from t_datadictype where ddTypeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ddTypeId);
		return pstmt.executeUpdate();
	}
	
	public boolean existDataDicAtddType(Connection con,String ddTypeId) throws Exception{
		String sql="select * from t_datadic where ddTypeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, ddTypeId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return true;
		}else {
			return false;
		}
	}

}
