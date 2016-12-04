package com.yousns.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;
import com.yousns.vo.StudentVo;

import testDAO.StudentDAO;

@RequestMapping("/users")
public class UserController implements Controller{
	
	@RequestMapping(value = "/login/{id}/{password}", method = HTTPMETHOD.POST)
	public String login( @RequestParam("id") String id,  @RequestParam("password") String password,
			@RequestParam("query") String query , @RequestParam("body") String body,Model model,HttpSession session){
		System.out.println(id + " " + password + " " + query + " " + body); //body, queryString, path Param 매핑 확인용
		model.addAttribute("body", body); //model .add 확인용
		model.addAttribute("id", id);
		
		//DB작업
		StudentDAO dao= new StudentDAO();
		StudentVo vo= dao.getStudent(id, password);
		
		if(vo !=null) {
			model.addAttribute("StudentVO", vo);
			session.setAttribute("name", vo.getS_name());
		}
		
		
		return "Dispatch:/jsp/main.jsp"; //상대경로 /yousns 에서 시작
	}
	
	@RequestMapping(value = "/apitest/test", method = HTTPMETHOD.POST)
	public String apitest(@RequestParam("id") String id, Model model, HttpSession session){
		model.addAttribute("id", id);
		return "Redirect:../../jsp/main.jsp"; //절대경로
	}
	
	
}
