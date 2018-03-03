package com.struts.util;

public class NavCodeUtil {

	
	public static String genNavigetion(String mainPage, String subPage) {
		StringBuffer navCode = new StringBuffer();
		navCode.append("当前位置：&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("projectName")+"/main.jsp'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
		navCode.append("<a href='#'>"+mainPage+"</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
		navCode.append("<a href='#'>"+subPage+"</a>");
		return navCode.toString();
	}
}
