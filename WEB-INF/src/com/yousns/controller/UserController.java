package com.yousns.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;


@RequestMapping("/users")
public class UserController implements Controller{

	//로그인
	@RequestMapping(value = "/auth", method = HTTPMETHOD.POST)
	public String login( @RequestParam("id") String id,  @RequestParam("password") String password,
			HttpServletRequest req ,HttpSession session){
		req.setAttribute("id", id);
		req.setAttribute("password", password);
		System.out.println("로그인 시도 ID : " + id + " Password : " + password);
		return "Dispatch:/jsp/users/auth.jsp"; 
	}
	//로그아웃
	@RequestMapping(value = "/auth/out", method = HTTPMETHOD.GET)
	public String logout(HttpSession session){
		System.out.println("로그아웃");
		session.invalidate();
		return "Redirect:/yousns"; 
	}
	
	//마이페이지
	@RequestMapping(value = "/{id}", method = HTTPMETHOD.GET)
	public String users_view(@RequestParam("id") String id, Model model, HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{
			System.out.println("마이페이지");
			model.addAttribute("id", id);
			return "Dispatch:/jsp/users/mypage.jsp"; 
		}
	}

	//상세정보 입력
	@RequestMapping(value = "/{id}/details", method = HTTPMETHOD.POST)
	public String details_add( @RequestParam("id") String id,
			HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("상세정보입력");
//			model.addAttribute("id", id);
			return "Dispatch:/jsp/users/details_add.jsp"; 
		}
	}

	//상세정보 보기
	@RequestMapping(value = "/{id}/details", method = HTTPMETHOD.GET)
	public String details_view( @RequestParam("id") String id,
			Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("상세정보보기");
			model.addAttribute("id", id);
			return "Dispatch:/jsp/users/details_view.jsp"; 
		}
	}

	//친구보기
	@RequestMapping(value = "/{id}/friends", method = HTTPMETHOD.GET)
	public String friends_view( @RequestParam("id") String id,
			Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}
		else{
			System.out.println("친구보기");
			model.addAttribute("id", id);
			return "Dispatch:/jsp/users/friends_view.jsp"; 
		}
	}

	//친구 삭제
	@RequestMapping(value = "/{id}/friends/{fid}/delete", method = HTTPMETHOD.GET)
	public String friends_delete(@RequestParam("fid") String fid,
			Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{
			System.out.println("친구삭제");
			model.addAttribute("fid", fid);
			return "Dispatch:/jsp/users/friends_delete.jsp"; 
		}
	}

//	//친구 거절
//	@RequestMapping(value = "/{id}/friends/{fid}/reject", method = HTTPMETHOD.PUT)
//	public String friends_reject( @RequestParam("id") String id,@RequestParam("fid") String fid,
//			Model model,HttpSession session){
//		if(session.getAttribute("token") ==null){			
//			return "Redirect:/yousns";
//		}else{
//
//			System.out.println("친구거절");
//			model.addAttribute("id", id);
//			model.addAttribute("fid", fid);
//			return "Dispatch:/jsp/users/friends_reject.jsp"; 
//		}
//	}

	//친구 수락
	@RequestMapping(value = "/{id}/friends/{fid}/approve", method = HTTPMETHOD.GET)
	public String friends_approve( @RequestParam("id") String id,@RequestParam("fid") String fid,
			Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{

			System.out.println("친구수락");
			model.addAttribute("fid", fid);
			return "Dispatch:/jsp/users/friends_approve.jsp"; 
		}
	}

	//친구 신청
	@RequestMapping(value = "/{id}/friends/{fid}", method = HTTPMETHOD.GET)
	public String friends_request( @RequestParam("id") String id,@RequestParam("fid") String fid,
			Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{
			model.addAttribute("fid", fid);
			return "Dispatch:/jsp/users/friends_request.jsp"; 
		}
	}

//	//회원가입 페이지
//	@RequestMapping( method = HTTPMETHOD.GET)
//	public String users_newpage(){
//		System.out.println("회원가입페이지");
//		return "Redirect:/jsp/users/users_newpage.jsp"; 
//	}

	//회원가입
	@RequestMapping(method = HTTPMETHOD.POST)
	public String users_new(){	
		System.out.println("회원가입");
		return "Dispatch:/jsp/users/join.jsp"; 
	}

//	//회원정보 수정페이지
//	@RequestMapping(value = "/{id}/edit", method = HTTPMETHOD.GET)
//	public String users_view_edit( @RequestParam("id") String id,
//			Model model,HttpSession session){
//		if(session.getAttribute("token") ==null){			
//			return "Redirect:/yousns";
//		}else{
//			model.addAttribute("id", id);
//			return "Dispatch:/jsp/users_view_edit.jsp"; 
//		}
//	}
	
	//회원정보 수정
	@RequestMapping(value = "/{id}/edit", method = HTTPMETHOD.POST)
	public String users_edit( @RequestParam("id") String id,
			HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{
			return "Dispatch:/jsp/users/users_edit.jsp"; 
		}
	}
	
	//내글보기
	@RequestMapping(value = "/{id}/posts", method = HTTPMETHOD.GET)
	public String users_posts( @RequestParam("id") String id,
			Model model,HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{
			model.addAttribute("someonekey", id);
			return "Dispatch:/jsp/posts/posts_list.jsp"; 
		}
	}

	//회원탈퇴
	@RequestMapping(value = "/{id}/delete", method = HTTPMETHOD.GET)
	public String users_delete( @RequestParam("id") String id,
			HttpSession session){
		if(session.getAttribute("token") ==null){			
			return "Redirect:/yousns";
		}else{
			return "Dispatch:/jsp/users/users_delete.jsp"; 
		}
	}
}
