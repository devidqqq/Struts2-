package com.struts.model;

import java.util.Date;

public class Student {

	
	private String studentId;
	private String stuNo;
	private String stuName;
	private String stuSex="-1";
	private Date stuBirthday;
	private Date stuRxsj;
	private String stuNation="-1";
	private String stuZzmm="-1";
	private int classId=-1;
	private String className;
	private String gradeName = "";
	private String stuDesc;
	private String stuPic;
	
	private String bstuBirthday;
	private String estuBirthday;
	private String bRxsj;
	private String eRxsj;
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public Date getStuBirthday() {
		return stuBirthday;
	}
	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}
	public Date getStuRxsj() {
		return stuRxsj;
	}
	public void setStuRxsj(Date stuRxsj) {
		this.stuRxsj = stuRxsj;
	}
	public String getStuNation() {
		return stuNation;
	}
	public void setStuNation(String stuNation) {
		this.stuNation = stuNation;
	}
	public String getStuZzmm() {
		return stuZzmm;
	}
	public void setStuZzmm(String stuZzmm) {
		this.stuZzmm = stuZzmm;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getStuDesc() {
		return stuDesc;
	}
	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}
	public String getStuPic() {
		return stuPic;
	}
	public void setStuPic(String stuPic) {
		this.stuPic = stuPic;
	}
	public String getBstuBirthday() {
		return bstuBirthday;
	}
	public void setBstuBirthday(String bstuBirthday) {
		this.bstuBirthday = bstuBirthday;
	}
	public String getEstuBirthday() {
		return estuBirthday;
	}
	public void setEstuBirthday(String estuBirthday) {
		this.estuBirthday = estuBirthday;
	}
	public String getbRxsj() {
		return bRxsj;
	}
	public void setbRxsj(String bRxsj) {
		this.bRxsj = bRxsj;
	}
	public String geteRxsj() {
		return eRxsj;
	}
	public void seteRxsj(String eRxsj) {
		this.eRxsj = eRxsj;
	}
}
