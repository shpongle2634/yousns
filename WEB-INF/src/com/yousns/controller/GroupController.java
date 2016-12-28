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
@RequestMapping("/groups")
public class GroupController implements Controller{

	//그룹리스트
	@RequestMapping(method = HTTPMETHOD.GET)
	public String groups_view(HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 리스트");
			return "Dispatch:/jsp/groups/groups_newgroup.jsp"; 
		}
	}
	//그룹뉴스피드
	@RequestMapping(value="/{id}",method = HTTPMETHOD.GET)
	public String groups_newspeed(@RequestParam("id") String id, HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 뉴스피드");
			model.addAttribute("groupkey", id);
			return "Dispatch:/jsp/posts/posts_list.jsp"; 
		}
	}
	//그룹 생성
	@RequestMapping(method=HTTPMETHOD.POST)
	public String groups_new(HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 생성");
			return "Dispatch:/jsp/groups/groups_new.jsp"; }
	}


	//그룹 삭제
	@RequestMapping(value="/{id}/delete",method=HTTPMETHOD.GET)
	public String groups_delete(@RequestParam("id") String id,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 삭제");
			model.addAttribute("groupKey", id);
			return "Dispatch:/jsp/groups/groups_delete.jsp"; 
		}
	}
	//그룹 가입요청
	@RequestMapping(value="/{id}/users/{uid}",method=HTTPMETHOD.GET)
	public String groups_join(@RequestParam("id") String id, @RequestParam("uid") String uid,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 가입요청");
			model.addAttribute("groupKey", id);
			model.addAttribute("memberkey", uid);
			return "Dispatch:/jsp/groups/groups_members_join.jsp"; 
		}
	}
	//그룹 가입 승인
	@RequestMapping(value="/{id}/users/{uid}/approve",method=HTTPMETHOD.GET)
	public String gruops_approve(@RequestParam("id") String id, @RequestParam("uid") String uid,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 가입승인");
			model.addAttribute("groupKey", id);
			model.addAttribute("memberkey", uid);
			return "Dispatch:/jsp/groups/groups_members_approve.jsp"; 
		}
	}
	//그룹 가입 거절/삭제
	@RequestMapping(value="/{id}/users/{uid}/reject",method=HTTPMETHOD.GET)
	public String groups_reject(@RequestParam("id") String id, @RequestParam("uid") String uid,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 거절");
			model.addAttribute("groupKey", id);
			model.addAttribute("memberkey", uid);
			return "Dispatch:/jsp/groups/groups_members_reject.jsp"; 
		}
	}
	//가입자 리스트
	@RequestMapping(value="/{id}/users",method=HTTPMETHOD.GET)
	public String groups_members( @RequestParam("id") String id ,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 가입자 보기");
			model.addAttribute("groupKey", id);
			return "Dispatch:/jsp/groups/groups_members_list.jsp"; 
		}
	}
	//가입자 요청자 리스트
	@RequestMapping(value="/{id}/requests/",method=HTTPMETHOD.GET)
	public String groups_members_request(@RequestParam("id") String id ,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 가입요청자 보기");
			model.addAttribute("groupKey", id);
			return "Dispatch:/jsp/groups/groups_members_request_list.jsp"; 
		}
	}

	//그룹 탈퇴
	@RequestMapping(value="/{id}/users/{uid}/delete",method=HTTPMETHOD.GET)
	public String members_delete(@RequestParam("id") String id ,@RequestParam("uid") String uid ,HttpSession session, Model model){
		if(session.getAttribute("token") ==null)
			return "Redirect:/yousns";
		else{
			System.out.println("그룹 탈퇴");
			model.addAttribute("groupKey", id);
			model.addAttribute("memberkey", uid);
			return "Dispatch:/jsp/groups/groups_members_delete.jsp"; 
		}
	}

}
