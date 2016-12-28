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
@RequestMapping("/search")
public class SearchController implements Controller{

	//통합검색
	@RequestMapping(method = HTTPMETHOD.POST)
	public String comments_delete(@RequestParam("keyword") String keyword, Model model){

			System.out.println("검색");
			model.addAttribute("keyword", keyword);
			return "Dispatch:/jsp/search.jsp"; 
	}

}
