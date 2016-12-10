package com.yousns.dispatcher;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yousns.utils.Controller;
import com.yousns.utils.Model;
import com.yousns.utils.RequestMapping;
import com.yousns.utils.RequestParam;


/*
 * 
 * Spring에서와 같이 DispatcherServlet클래스는 모든 요청을 service함수로 가로챈후, 적절한 컨트롤러에 미리 지정한 RESTful API에 따라 서비스를 분기한다.
 * */

public class DispatcherServlet extends HttpServlet {

	/**
	 * Created by	: Seo Taehoon
	 * Last updated : 2016-12-09
	 */
	private static final long serialVersionUID = 1L;
	private static final HashMap<String, Controller> dispatchMap= new HashMap<>();

	private void controllerMapping() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String pack = "com.yousns.controller"; //package 이름
		//		System.out.println(pack);
		String root ="C:\\Users\\TaeHoon\\git\\yousns\\WEB-INF\\src\\";
		String urlpath =root+ pack.replace(".","\\");//실제 파일경로
		//		System.out.println(urlpath);

		File url = new File(urlpath); 
		if(url.exists()){
			File [] arrFile = url.listFiles();
			for( int i = 0; i < arrFile.length; ++i ) {
				//				System.out.println(arrFile[i].getName());
				if(arrFile[i].getName().contains(".java")){
					String controller = pack+"." + arrFile[i].getName().substring(0,arrFile[i].getName().indexOf('.'));//컨트롤러 클래스 이름   
					//									System.out.println(controller);
					Class clz= Class.forName(controller); //컨트롤러에서 라우트할 경로와, 싱글톤 인스턴스를 생성.
					RequestMapping servicePath = (RequestMapping) clz.getAnnotation(RequestMapping.class);
					dispatchMap.put(servicePath.value(), (Controller) clz.newInstance()) ; //private 생성자가 아니어도 싱글톤으로 관리. 따라서 컨트롤러에 전역변수 절대금지.
				}
			}
		}
	}


	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//		dispatchMap.put("/users", );
		try {
			controllerMapping();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Object invokeMethod(Controller controller,Method m, Object[] parameters){
		Object obj= null;
		try {
			obj= m.invoke(controller,parameters);
			if(obj !=null){
				//System.out.println(obj);
			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	private Object getParam(Parameter params,HttpServletRequest req, HttpServletResponse res, Model model){
		Object value= null;
		RequestParam paramName= params.getAnnotation(RequestParam.class);
		if(paramName !=null){
			value = req.getParameter(paramName.value());
		}else if(paramName ==null){
			if(params.getType().equals(HttpSession.class)){
				//				System.out.println("session");
				value= req.getSession();
			}
			else if(params.getType().equals(HttpServletResponse.class)){
				//				System.out.println("response");
				value= res;
			}else if(params.getType().equals(HttpServletRequest.class)){
				//				System.out.println("response");
				value= req;
			}else if(params.getType().equals(Model.class)){
				//				System.out.println("response");
				value= model;
			}

		}
		return value;

	}
	
	private boolean findpath(String[] requestpath, String[] temppath){
		boolean flag = false, flag2=true;
		flag= requestpath.length== temppath.length;
		if(flag){
			for(int i =0; i<requestpath.length; i++){
				if(temppath[i].contains("{") && temppath[i].contains("}"));//skip
				else {
					if(!requestpath[i].equals(temppath[i])){
						flag2=false;
					}
				}
				
			}
			
		}
		
		return flag&&flag2;
	}
	
	private void flowControll(HttpServletRequest req,HttpServletResponse res, String result) throws IOException, ServletException{
		if(result !=null){
			String s =(String)result;
			String path= null;
			boolean isRedirect = s.contains("Redirect:");
			boolean isDispatch= s.contains("Dispatch:");
			if(isRedirect) {
				path= s.substring("Redirect:".length());
				System.out.println(s+ "  " +path);
				res.sendRedirect(path);
			}
			else if(isDispatch){
				path= s.substring("Dispatch:".length());
				System.out.println(s+ "  " +path);
				RequestDispatcher dispatcher= req.getRequestDispatcher(path);
				dispatcher.forward(req, res);//디스패치
			}
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI= req.getRequestURI();
		String contextPath = req.getContextPath();

		System.out.println(requestURI);
		String requesturl = requestURI.substring(contextPath.length());
		//		System.out.println("requesturl : " +requesturl);
		int next =requesturl.indexOf('/',1);
		//		System.out.println(next);
		String routePath = (next!=-1) ? requesturl.substring(0, next) : requesturl;
		requesturl= requesturl.substring(routePath.length());

		//		System.out.println("routePath : "+ routePath);
		Controller controller = dispatchMap.get(routePath); //Controller 객체를 가져온다. 
		if(controller!=null){
			Class mapperClass = controller.getClass();
			Method[] methods = mapperClass.getDeclaredMethods();
			Model model= new Model(req,res);

			//메소드에서 path와 HTTP METHOD를 매칭
			String[] targetURLs = requesturl.split("/");
			HashMap<Integer,Object> invokeParams=new HashMap<>();
			ArrayList<Object> args= new ArrayList<>();
			for(Method m : methods){
				//				System.out.println(m.getName() + req.getMethod());
				RequestMapping r = m.getAnnotation(RequestMapping.class);
				Parameter[] params = m.getParameters();
				if(r !=null) {
					String pathform =r.value();
					String[] tempRoute = pathform.split("/");

					if(pathform.equals(requesturl) && r.method().equals(req.getMethod())){//완전히 찾은경우.
						System.out.println("normal");
						if( params ==null ||params.length==0){
							String result=(String) invokeMethod(controller, m, null);
							flowControll(req,res,result);
						}
						else {
							for(int i =0; i< params.length; i++){
								//System.out.println(params[i].getType());
								Object value =null;
								value = getParam(params[i],req,res,model);
								if(!invokeParams.containsKey(i))
									invokeParams.put(i, value);
							}
							if(invokeParams.size() == params.length){ //파라미터 매핑 완료.
								for(int j = 0; j< params.length; j++){
									//System.out.println(invokeParams.get(j));
									args.add(invokeParams.get(j));
								}
								String result = (String)invokeMethod(controller,m, args.toArray());
								flowControll(req,res,result);
							}							
						}

					}

					else if(findpath(targetURLs,tempRoute)&& r.method().equals(req.getMethod())){//HTTP 메소드가 같고,Path 길이가 같은경우.
//						System.out.println(Arrays.toString(tempRoute));
						//반복문으로 path의 context를 비교하여 정확한 경로를 찾아낸다.
						//2. 파라미터가 존재. {}가 있으면 괄호를 풀어서 해당 변수이름에 해당하는 파라미터에 실제 url 값을 넣는다.
						for(int i=0; i< targetURLs.length; i++){
							if(tempRoute[i].equals(targetURLs[i]));
							else{
								if(tempRoute[i].contains("{") && tempRoute[i].contains("}")){
									String targetParam = tempRoute[i].substring(1, tempRoute[i].length()-1);
									//System.out.println(targetParam);

									for(int j = 0; j< params.length; j++){
										if(params[j].getName().equals(targetParam)){//RequestParam 파라미터 매핑
											//System.out.println(j + " " +params[j].getName() + " : " +targetURLs[i]);
											if(!invokeParams.containsKey(j))
												invokeParams.put( j, targetURLs[i]);
										}else{
											Object value =null;
											value = getParam(params[j],req,res,model);
											if(value !=null){
												//System.out.println(j + " " +params[j].getName() + " : " +value);
												if(!invokeParams.containsKey(j))
													invokeParams.put(j, value);
											}
										}
									}
								} 
							}
						}

						if(invokeParams.size() == params.length){ //파라미터 매핑 완료.
							for(int j = 0; j< params.length; j++){
								//								System.out.println(invokeParams.get(j));
								args.add(invokeParams.get(j));
							}
							String result = (String)invokeMethod(controller,m, args.toArray());
							flowControll(req,res,result);
						}
					}

				}

			}
		}
		else{
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/index.jsp");
			dispatcher.forward(req, res);
		}

	}


}
