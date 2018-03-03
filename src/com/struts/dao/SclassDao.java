package com.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.struts.model.PageBean;
import com.struts.model.Sclass;
import com.struts.util.StringUtil;

public class SclassDao {

	
	public List<Sclass> classList(Connection con, Sclass s_sclass,PageBean pageBean) throws SQLException{
		List<Sclass> dataClass = new ArrayList<Sclass>();
		StringBuffer sql = new StringBuffer("select * from t_class t1,t_grade t2 where t1.gradeId = t2.gradeId");
		if(StringUtil.isNotEmpty(s_sclass.getGradeName())) {
			sql.append(" and t2.gradeName like '%"+s_sclass.getGradeName()+"%'");
		}
		if(pageBean!=null) {
			sql.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Sclass sclass = new Sclass();
			sclass.setClassId(rs.getInt("classId"));
			sclass.setClassName(rs.getString("className"));
			sclass.setGradeId(rs.getInt("gradeId"));
			sclass.setGradeName(rs.getString("gradeName"));
			sclass.setClassDesc(rs.getString("classDesc"));
			dataClass.add(sclass);
		}
		return dataClass;
	}
	
	public int classCount(Connection con, Sclass s_sclass) throws SQLException{
		StringBuffer sql = new StringBuffer("select count(*) as total from t_class t1,t_grade t2 where t1.gradeId = t2.gradeId");
		if(StringUtil.isNotEmpty(s_sclass.getGradeName())) {
			sql.append(" and t2.gradeName like '%"+s_sclass.getGradeName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		}else {
			return 0;
		}
	}
	
	public int addClass(Connection con, Sclass s_sclass) throws Exception {
		String sql = "insert into t_class values(null,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, s_sclass.getClassName());
		pstmt.setInt(2, s_sclass.getGradeId());
		pstmt.setString(3, s_sclass.getClassDesc());
		return pstmt.executeUpdate();
	}
	
	public int updateClass(Connection con, Sclass s_sclass) throws Exception {
		String sql = "update t_class set gradeId=?,className=?,classDesc=? where classId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, s_sclass.getGradeId());
		pstmt.setString(2, s_sclass.getClassName());
		pstmt.setString(3, s_sclass.getClassDesc());
		pstmt.setInt(4, s_sclass.getClassId());
		return pstmt.executeUpdate();
	}
	
	public Sclass classById(Connection con,String classId) throws Exception{
		String sql = "select * from t_class where classId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, classId);
		ResultSet rs = pstmt.executeQuery();
		Sclass cls = new Sclass();
		if(rs.next()) {
			cls.setClassId(rs.getInt("classId"));
			cls.setGradeId(rs.getInt("gradeId"));
			cls.setClassName(rs.getString("className"));
			cls.setClassDesc(rs.getString("classDesc"));
		}
		return cls;
	}
	
	public boolean existsStuAtClass(Connection con,String classId) throws Exception{
		String sql = "select * from t_student where classId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, classId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return true;
		}else {
			return false;
		}
	}
	public int deleteClass(Connection con,String classId) throws Exception{
		String sql = "delete from t_class where classId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, classId);
		return pstmt.executeUpdate();
	}
}
