package com.yousns.controller;

import javax.servlet.http.HttpSession;

import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;

/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-09
 */
@RequestMapping("/comments")
public class CommentController implements Controller{


	//댓글좋아요
	@RequestMapping(value = "/{id}", method = HTTPMETHOD.PATCH)
	public String comments_like(@RequestParam("id") String commentkey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("댓글 좋아요");
			model.addAttribute("commentkey", commentkey);
			return "Dispatch:/jsp/comments/comments_like.jsp"; 
		}
	}

	//댓글생성
	@RequestMapping(method = HTTPMETHOD.POST)
	public String comments_new(@RequestParam("postKey") String postKey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("댓글 생성");
			return "Dispatch:/jsp/comments/comments_new.jsp"; 
		}
	}
	//대댓글생성
	@RequestMapping(value= "/{id}",method = HTTPMETHOD.POST)
	public String ofcomments_new(@RequestParam("id") String commentkey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("대댓글생성");
			model.addAttribute("commentkey", commentkey);
			return "Dispatch:/jsp/comments/ofcomments_new.jsp"; 
		}
	}
	
	//댓글수정
	@RequestMapping(value= "/{id}",method = HTTPMETHOD.PUT)
	public String comments_edit(@RequestParam("id") String commentkey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("댓글수정");
			model.addAttribute("commentkey", commentkey);
			return "Dispatch:/jsp/comments/comments_edit.jsp"; 
		}
	}
	
	//댓글삭제
	@RequestMapping(value= "/{id}",method = HTTPMETHOD.DELETE)
	public String comments_delete(@RequestParam("id") String commentkey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("댓글삭제");
			model.addAttribute("commentkey", commentkey);
			return "Dispatch:/jsp/comments/comments_delete.jsp"; 
		}
	}
	
	//댓글신고양식
	@RequestMapping(value= "/{id}/reports/new",method = HTTPMETHOD.GET)
	public String comments_reportpage(@RequestParam("id") String commentkey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("댓글신고양식");
			model.addAttribute("commentkey", commentkey);
			return "Dispatch:/jsp/comments/comments_reportpage.jsp"; 
		}
	}
	
	//댓글 신고
	@RequestMapping(value= "/{id}/reports",method = HTTPMETHOD.POST)
	public String comments_report(@RequestParam("id") String commentkey, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("댓글신고");
			model.addAttribute("commentkey", commentkey);
			return "Dispatch:/jsp/comments/comments_report.jsp"; 
		}
	}
	
}
