package com.yousns.controller;

import javax.servlet.http.HttpSession;

import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;

@RequestMapping("/posts")
public class PostController implements Controller{




	//게시물 리스트
	@RequestMapping(method=HTTPMETHOD.GET)
	public String posts_list(@RequestParam("page")String page,@RequestParam("rows")String rows, HttpSession session,Model model){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("뉴스피드");
			model.addAttribute("page", page);
			model.addAttribute("rows", rows);
			return "Dispatch:/jsp/posts/posts_list.jsp";
		}
	}

	//게시물 상세보기
	@RequestMapping(value="/{id}", method=HTTPMETHOD.GET)
	public String posts_view(@RequestParam("id")String postkey, Model model){
		System.out.println("상세보기");
		model.addAttribute("postkey", postkey);
		return "Dispatch:/jsp/posts/posts_view.jsp";
	}

	//게시물 작성을 위한 페이지
	@RequestMapping(value = "/new", method = HTTPMETHOD.GET)
	public String posts_newpage(){
		System.out.println("게시물 생성페이지");
		return "Dispatch:/jsp/posts/posts_newpage.jsp"; 
	}
	//게시물생성
	@RequestMapping( method = HTTPMETHOD.POST)
	public String posts_new(){
		System.out.println("게시물 생성");
		return "Dispatch:/jsp/posts/posts_new.jsp"; 
	}
	//게시물 수정양식
	@RequestMapping(value="/{id}/edit", method = HTTPMETHOD.GET)
	public String posts_editpage(@RequestParam("id")String postkey, Model model){
		System.out.println("게시물 수정페이지");
		model.addAttribute("postKey", postkey);
		return "Dispatch:/jsp/posts/posts_editpage.jsp"; 
	}
	//게시물 수정
	@RequestMapping(value="/{id}", method = HTTPMETHOD.PUT)
	public String posts_edit(@RequestParam("id")String postkey, Model model){
		System.out.println("게시물 수정");
		model.addAttribute("postKey", postkey);
		return "Dispatch:/jsp/posts/posts_edit.jsp"; 
	}

	//게시물 삭제
	@RequestMapping(value ="/{id}", method = HTTPMETHOD.DELETE)
	public String posts_delete(@RequestParam("id")String postkey, Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("게시물 삭제");
			model.addAttribute("postKey", postkey);
			return "Dispatch:/jsp/posts/posts_delete.jsp"; 
		}
	}

	//게시물 신고 양식
	@RequestMapping(value ="/{id}/reports/new", method = HTTPMETHOD.GET)
	public String posts_reportpage(@RequestParam("id")String postkey, Model model,  HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("게시물 신고양식");
			model.addAttribute("postKey", postkey);
			return "Dispatch:/jsp/posts/posts_reportpage.jsp"; 
		}
	}

	//게시물 신고
	@RequestMapping(value="/reports", method = HTTPMETHOD.POST)
	public String posts_report(@RequestParam("id")String postkey, Model model,  HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("게시물 신고");
			model.addAttribute("postKey", postkey);
			return "Dispatch:/jsp/posts/posts_report.jsp"; 
		}
	}


	//게시물 좋아요
	@RequestMapping(value="/{id}", method = HTTPMETHOD.PATCH)
	public String posts_like(@RequestParam("id")String postkey, Model model,  HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("게시물 좋아요");
			model.addAttribute("postKey", postkey);
			return "Dispatch:/jsp/posts/posts_like.jsp"; 
		}
	}
}
