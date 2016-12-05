package com.yousns.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yousns.dao.StudentDAO;
import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;
import com.yousns.vo.StudentVo;
import com.yousns.vo.UserVO;

@RequestMapping("/posts")
public class PostController implements Controller{
	
//	@RequestMapping(value = "/login/{id}/{password}", method = HTTPMETHOD.POST)
//	public String login( @RequestParam("id") String id,  @RequestParam("password") String password,
//			@RequestParam("query") String query , @RequestParam("body") String body,Model model,HttpSession session){
//		System.out.println(id + " " + password + " " + query + " " + body); //body, queryString, path Param 留ㅽ븨 �솗�씤�슜
//		model.addAttribute("body", body); //model .add �솗�씤�슜
//		model.addAttribute("id", id);
//		
//		//DB�옉�뾽
//		StudentDAO dao= new StudentDAO();
//		StudentVo vo= dao.getStudent(id, password);
//		
//		if(vo !=null) {
//			model.addAttribute("StudentVO", vo);
//			session.setAttribute("name", vo.getS_name());
//		}
//		
//		
	@RequestMapping(value = "/new", method = HTTPMETHOD.GET)
	public String login( @RequestParam("id") String id,  @RequestParam("password") String password,
			@RequestParam("query") String query , @RequestParam("body") String body,Model model,HttpSession session){
		System.out.println(id + " " + password + " " + query + " " + body); //body, queryString, path Param 留ㅽ븨 �솗�씤�슜
		model.addAttribute("body", body); //model .add �솗�씤�슜
		model.addAttribute("id", id);
		
		//DB linkage
		StudentDAO dao= new StudentDAO();
		UserVO userVO = new UserVO();
		StudentVo vo= dao.getStudent(id, password);
		
		if(vo !=null) {
			model.addAttribute("StudentVO", vo);
			session.setAttribute("name", vo.getS_name());
		}		
		return "Dispatch:/jsp/main.jsp"; //�긽��寃쎈줈 /yousns �뿉�꽌 �떆�옉
	}
	
//	@RequestMapping(value = "/apitest/test", method = HTTPMETHOD.POST)
//	public String apitest(@RequestParam("id") String id, Model model, HttpSession session){
//		model.addAttribute("id", id);
//		return "Redirect:../../jsp/main.jsp"; //�젅��寃쎈줈
//	}
//	
	
}
