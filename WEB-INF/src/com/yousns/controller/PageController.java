package com.yousns.controller;

import javax.servlet.http.HttpSession;

import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;

/**
 * Created by	: Seo Taehoon
 * Last updated : 2016-12-10
 */
@RequestMapping("/pages")
public class PageController implements Controller{

	//페이지리스트
	@RequestMapping(method = HTTPMETHOD.GET)
	public String pages_view(HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("페이지 리스트");
			return "Dispatch:/jsp/pages/pages_newpage.jsp"; 
		}
	}
	
	//페이지 뉴스피드
	@RequestMapping(value ="/{id}" , method = HTTPMETHOD.GET)
	public String  page_newspeed(@RequestParam("id") String id, HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("페이지 뉴스피드 ");
			model.addAttribute("pagekey", id);
			return "Dispatch:/jsp/posts/posts_list.jsp"; 
		}
	}
	
	
	//페이지 생성
	@RequestMapping(method=HTTPMETHOD.POST)
	public String pages_new(HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("페이지 생성");
			return "Dispatch:/jsp/pages/pages_new.jsp"; }
	}


	//페이지 삭제
	@RequestMapping(value="/{id}/delete",method=HTTPMETHOD.GET)
	public String pages_delete(@RequestParam("id") String id,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("페이지 삭제");
			model.addAttribute("pageKey", id);
			return "Dispatch:/jsp/pages/pages_delete.jsp"; 
		}
	}
	
	//페이지 구독
	@RequestMapping(value="/{id}/users/{uid}",method=HTTPMETHOD.GET)
	public String pages_subscribe(@RequestParam("id")String id, @RequestParam("uid") String uid,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("페이지 구독");
			model.addAttribute("pageKey", id);
			return "Dispatch:/jsp/pages/pages_subscribe.jsp"; 
		}
	}
	
	//구독자 리스트
	@RequestMapping(value="/{id}/users",method=HTTPMETHOD.GET)
	public String pages_subscribers(HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("페이지 구독자");
			return "Dispatch:/jsp/pages/pages_subscribers.jsp"; 
		}
	}
}
