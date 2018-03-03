package com.struts.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.struts.model.User;

public class LoginInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("初始化");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		User currentUser = (User)session.get("currentUser");
		if(currentUser!=null) {
			result = invocation.invoke();
		}else {
			result = "login";
		}
		return result;
	}

}
