package com.struts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.struts.model.PageBean;
import com.struts.model.Student;
import com.struts.util.DateUtil;
import com.struts.util.StringUtil;
import com.struts.util.UUIDUtil;

public class StudentDao {

	
	public List<Student> stuList(Connection con, Student s_student,PageBean pageBean) throws Exception {
		List<Student> stuList = new ArrayList<Student>();
		StringBuffer sb = new StringBuffer("select * from t_student t1,t_class t2,t_grade t3 where t1.classId = t2.classId and t2.gradeId = t3.gradeId");
		if(StringUtil.isNotEmpty(s_student.getStuNo())) {
			sb.append(" and t1.stuNo like '"+s_student.getStuNo()+"'");
		}
		if(StringUtil.isNotEmpty(s_student.getStuName())) {
			sb.append(" and t1.stuName like '"+s_student.getStuName()+"'");
		}
		if(!"-1".equals(s_student.getStuSex())) {
			sb.append(" and t1.stuSex like '"+s_student.getStuSex()+"'");
		}
		if(!"-1".equals(s_student.getStuNation())) {
			sb.append(" and t1.stuNation like '"+s_student.getStuNation()+"'");
		}
		if(StringUtil.isNotEmpty(s_student.getGradeName())) {
			sb.append(" and t3.gradeName like '"+s_student.getGradeName()+"'");
		}
		if(s_student.getClassId()!=-1) {
			sb.append(" and t1.classId = "+s_student.getClassId());
		}
		if(StringUtil.isNotEmpty(s_student.getBstuBirthday())) {
			sb.append(" and TO_DAYS(t1.stuBirthday) >= TO_DAYS('"+s_student.getBstuBirthday()+"')");
		}
		if(StringUtil.isNotEmpty(s_student.getEstuBirthday())) {
			sb.append(" and TO_DAYS(t1.stuBirthday) <= TO_DAYS('"+s_student.getEstuBirthday()+"')");
		}
		if(StringUtil.isNotEmpty(s_student.getbRxsj())) {
			sb.append(" and TO_DAYS(t1.stuRxsj) >= TO_DAYS('"+s_student.getbRxsj()+"')");
		}
		if(StringUtil.isNotEmpty(s_student.geteRxsj())) {
			sb.append(" and TO_DAYS(t1.stuRxsj) <= TO_DAYS('"+s_student.geteRxsj()+"')");
		}
		if(pageBean!=null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		System.out.println(sb.toString());
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Student student = new Student();
			student.setStudentId(rs.getString("studentId"));
			student.setStuNo(rs.getString("stuNo"));
			student.setStuName(rs.getString("stuName"));
			student.setStuSex(rs.getString("stuSex"));
			student.setStuBirthday(DateUtil.formatStringToDate(rs.getString("stuBirthday"), "yyyy-MM-dd"));
			student.setStuRxsj(DateUtil.formatStringToDate(rs.getString("stuRxsj"), "yyyy-MM-dd"));
			student.setStuNation(rs.getString("stuNation"));
			student.setStuZzmm(rs.getString("stuZzmm"));
			student.setClassId(rs.getInt("classId"));
			student.setClassName(rs.getString("className"));
			student.setGradeName(rs.getString("gradeName"));
			student.setStuDesc(rs.getString("stuDesc"));
			student.setStuPic(rs.getString("stuPic"));
			stuList.add(student);
		}
		return stuList;
	}
	
	public int studentCount(Connection con, Student s_student) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_student t1,t_class t2,t_grade t3 where t1.classId = t2.classId and t2.gradeId = t3.gradeId");
		if(StringUtil.isNotEmpty(s_student.getStuNo())) {
			sb.append(" and t1.stuNo like '"+s_student.getStuNo()+"'");
		}
		if(StringUtil.isNotEmpty(s_student.getStuName())) {
			sb.append(" and t1.stuName like '"+s_student.getStuName()+"'");
		}
		if(!"-1".equals(s_student.getStuSex())) {
			sb.append(" and t1.stuSex like '"+s_student.getStuSex()+"'");
		}
		if(!"-1".equals(s_student.getStuNation())) {
			sb.append(" and t1.stuNation like '"+s_student.getStuNation()+"'");
		}
		if(StringUtil.isNotEmpty(s_student.getGradeName())) {
			sb.append(" and t3.gradeName like '"+s_student.getGradeName()+"'");
		}
		if(s_student.getClassId()!=-1) {
			sb.append(" and t1.classId = "+s_student.getClassId());
		}
		if(StringUtil.isNotEmpty(s_student.getBstuBirthday())) {
			sb.append(" and TO_DAYS(t1.stuBirthday) >= TO_DAYS('"+s_student.getBstuBirthday()+"')");
		}
		if(StringUtil.isNotEmpty(s_student.getEstuBirthday())) {
			sb.append(" and TO_DAYS(t1.stuBirthday) <= TO_DAYS('"+s_student.getEstuBirthday()+"')");
		}
		if(StringUtil.isNotEmpty(s_student.getbRxsj())) {
			sb.append(" and TO_DAYS(t1.stuRxsj) >= TO_DAYS('"+s_student.getbRxsj()+"')");
		}
		if(StringUtil.isNotEmpty(s_student.geteRxsj())) {
			sb.append(" and TO_DAYS(t1.stuRxsj) <= TO_DAYS('"+s_student.geteRxsj()+"')");
		}
		System.out.println(sb.toString());
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		}else {
			return 0;
		}
	}
	
	public int addStudent(Connection con, Student student) throws Exception {
		String sql = "insert into t_student values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, UUIDUtil.getUUID());
		pstmt.setString(2, student.getStuNo());
		pstmt.setString(3, student.getStuName());
		pstmt.setString(4, student.getStuSex());
		pstmt.setString(5, DateUtil.formatDateToString(student.getStuBirthday(), "yyyy-MM-dd"));
		pstmt.setString(6, DateUtil.formatDateToString(student.getStuRxsj(), "yyyy-MM-dd"));
		pstmt.setString(7, student.getStuNation());
		pstmt.setString(8, student.getStuZzmm());
		pstmt.setInt(9, student.getClassId());
		pstmt.setString(10, student.getStuDesc());
		pstmt.setString(11, student.getStuPic());
		return pstmt.executeUpdate();
	}

	public int updateStudent(Connection con, Student student) throws Exception {
		String sql = "update t_student set stuNo=?,stuName=?,stuSex=?,stuBirthday=?,stuRxsj=?,stuNation=?,stuZzmm=?,classId=?,stuDesc=?,stuPic=? where studentId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, student.getStuNo());
		pstmt.setString(2, student.getStuName());
		pstmt.setString(3, student.getStuSex());
		pstmt.setString(4, DateUtil.formatDateToString(student.getStuBirthday(), "yyyy-MM-dd"));
		pstmt.setString(5, DateUtil.formatDateToString(student.getStuRxsj(), "yyyy-MM-dd"));
		pstmt.setString(6, student.getStuNation());
		pstmt.setString(7, student.getStuZzmm());
		pstmt.setInt(8, student.getClassId());
		pstmt.setString(9, student.getStuDesc());
		pstmt.setString(10, student.getStuPic());
		pstmt.setString(11, student.getStudentId());
		return pstmt.executeUpdate();
	}
	public Student getStudentById(Connection con, String studentId) throws Exception {
		StringBuffer sql = new StringBuffer("select * from t_student t1,t_class t2,t_grade t3 where t1.classId = t2.classId and t2.gradeId = t3.gradeId");
		if(StringUtil.isNotEmpty(studentId)) {
			sql.append(" and t1.studentId='"+studentId+"'");
		}
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		Student student = new Student();
		if(rs.next()) {
			student.setStudentId(rs.getString("studentId"));
			student.setStuNo(rs.getString("stuNo"));
			student.setStuName(rs.getString("stuName"));
			student.setStuSex(rs.getString("stuSex"));
			student.setStuBirthday(DateUtil.formatStringToDate(rs.getString("stuBirthday"), "yyyy-MM-dd"));
			student.setStuRxsj(DateUtil.formatStringToDate(rs.getString("stuRxsj"), "yyyy-MM-dd"));
			student.setStuNation(rs.getString("stuNation"));
			student.setStuZzmm(rs.getString("stuZzmm"));
			student.setClassId(rs.getInt("classId"));
			student.setClassName(rs.getString("className"));
			student.setGradeName(rs.getString("gradeName"));
			student.setStuDesc(rs.getString("stuDesc"));
			student.setStuPic(rs.getString("stuPic"));
		}
		return student;
	}
	public int deleteStudent(Connection con, String studentId) throws Exception {
		String sql = "delete from t_student where studentId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, studentId);
		return pstmt.executeUpdate();
		
	}
	

}
