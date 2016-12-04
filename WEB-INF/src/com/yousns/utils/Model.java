package com.yousns.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Model {

	private HttpServletRequest req;
	private HttpServletResponse res;
	
	public Model(HttpServletRequest req, HttpServletResponse res){
		this.req=req;
		this.res= res;
	}
	
	public void addAttribute(String name, Object obj){
		if(req!=null){
			req.setAttribute(name, obj);
		}		
	}
	
}
