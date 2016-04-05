package com.movie.web.global;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.web.admin.AdminBean;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/global/main.do")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = CommandFactory.getCommand(request, response);
		HttpSession session = request.getSession(); // 세션 객체 생성, header 페이지 설정을 위해 이곳에서 생성이 필요하다.
		AdminBean admin = null; // header.jsp 에서 AdminBean 객체의 role을 검사하는 코드가 있어서, main 가기 전에 세션 객체 생성 후 이것을 저장하는 작업이 한번 필요하다.

		switch (command.getAction()) {
			case "main":
				session.setAttribute("admin", admin);
				command.setView(command.getDirectory(), "main");
				break;
			default:
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(command.getView()); // ~~~.jsp가 파라미터로 넘어감																									// 내용전달
		dis.forward(request, response); // 페이지 이동, 위에서 지정한 URL (~~~~.jsp)로 페이지를 이동한다 (request와 response 객체를 가지고)
	}
}
