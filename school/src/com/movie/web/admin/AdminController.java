package com.movie.web.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.web.global.Command;
import com.movie.web.global.CommandFactory;
import com.movie.web.global.DispatcherServlet;
import com.movie.web.grade.GradeBean;
import com.movie.web.grade.MemberGradeBean;
import com.movie.web.member.MemberBean;
import com.movie.web.member.MemberService;
import com.movie.web.member.MemberServiceImpl;

@WebServlet({"/admin/login_form.do", "/admin/login.do", "/admin/logout.do", "/admin/search.do"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = CommandFactory.getCommand(request, response);
		AdminService service = AdminServiceImpl.getService(); // 싱글톤 패턴으로 서비스 객체 가져오기
		MemberService mService = MemberServiceImpl.getInstance();
		HttpSession session = request.getSession(); // 쉘로우 카피로 세션 객체 생성
		AdminBean admin = new AdminBean(); // 관리자 정보를 담기위한 AdminBean 객체 생성
		MemberBean member = new MemberBean();
		
		switch (command.getAction()) {
		case "login" :
			String id = request.getParameter("id");
			admin.setId(id);
			admin.setPassword(request.getParameter("password"));
			AdminBean temp = service.getAdmin(admin); // 입력받은 id와 password를 가지고 데이터베이스에 접근해서 관리자 정보를 담은 AdminBean객체를 얻는다.
			if (temp.getId() == null) { // 입력받은 id와 password로 데이터베이스에 접근해서 같은 값을 찾지 못하면 temp.getId()의 값이 null이다!
				System.out.println("관리자 로그인 실패");
				command.setView(command.getDirectory(), "login_form");
			} else { // 입력받은 id와 데이터베이스에서 가져온 id가 일치하면 로그인 성공!
				System.out.println("관리자 로그인 성공");
				session.setAttribute("admin", temp); // 관리자 로그인에 성공하면 세션에 해당 AdminBean객체를 저장한다.
				command.setView(command.getDirectory(), "admin_form");
			}
			break;
		case "logout" :
			session.invalidate(); // 로그아웃 시 세션 객체 해체
			command.setView("global", "main"); // 메인 페이지로 돌아간다.
			break;
		case "search" :
			String keyword = request.getParameter("keyword");
			
			
		default:
			break;
		}	
		DispatcherServlet.goNextPage(request, response, command.getView());
	}
}
