package com.yousns.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yousns.dao.PostDAO;
import com.yousns.dao.StudentDAO;
import com.yousns.utils.Controller;
import com.yousns.utils.HTTPMETHOD;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;
import com.yousns.vo.PostVO;
import com.yousns.vo.UserVO;

@RequestMapping("/posts")
public class PostController implements Controller{
	

	
	@RequestMapping(value = "/new", method = HTTPMETHOD.GET)
	public String newpost(){	
		return "Redirect:/posts/jsp/newpostform.jsp"; 
	}
	
	@RequestMapping(method=HTTPMETHOD.POST)
	public String postlist(@RequestParam("page") String page, @RequestParam("rows") String rows , Model model,HttpSession session){
		int pagenum =0;
		int itemnum = 0;
		String userkey=null;

		if(page ==null || rows== null){ //badRequest(파라미터 없음)
			//error
			model.addAttribute("errcode", new Integer(400));
			return "Dispatch:/jsp/errorpage";
		}
		
		pagenum =Integer.parseInt(page);
		itemnum =Integer.parseInt(rows);
		userkey= (String)session.getAttribute("userkey");
		
		if(userkey ==null){ //비 로그인시
			return "Redirect:/yousns/jsp/login.jsp";
		}
			
		PostDAO service = new PostDAO(); //DAO 호출
		ArrayList<PostVO> list = service.getlist(pagenum, itemnum, userkey);
		model.addAttribute("postlist", list);
		//something service
		return "Redirect:/yousns/jsp/postlist.jsp";
	} 
	
	
}
