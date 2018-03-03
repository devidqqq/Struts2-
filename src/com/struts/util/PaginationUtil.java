package com.struts.util;

public class PaginationUtil {
	
	public static String genPagination(int total,int pageSize,int currentPage,String url,String s_condition) {
		int totalPage = total%pageSize==0 ? total/pageSize : total/pageSize+1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='"+url+"?page=1"+s_condition+"'>首页</a></li>");
		if(currentPage==1) {
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
		}else {
			pageCode.append("<li><a href='"+url+"?page="+(currentPage-1)+s_condition+"'>上一页</a></li>");
		}
		for(int i=currentPage-2;i<=currentPage+2;i++) {
			if(i>=1&&i<=totalPage) {
				if(i==currentPage) {
					pageCode.append("<li class='active'><a href='"+url+"?page="+i+s_condition+"'>"+i+"</a></li>");
				}else {
					pageCode.append("<li><a href='"+url+"?page="+i+s_condition+"'>"+i+"</a></li>");
				}
			}
		}
		if(currentPage==totalPage) {
			pageCode.append("<li class=\"disabled\"><a href='#'>下一页</a></li>");
		}else {
			pageCode.append("<li><a href='"+url+"?page="+(currentPage+1)+s_condition+"'>下一页</a></li>");
		}
		pageCode.append("<li><a href='"+url+"?page="+totalPage+s_condition+"'>尾页</a></li>");
		return pageCode.toString();
	}

}
