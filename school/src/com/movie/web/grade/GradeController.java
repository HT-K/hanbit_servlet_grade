package com.movie.web.grade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.web.global.Command;
import com.movie.web.global.CommandFactory;
import com.movie.web.global.DispatcherServlet;
import com.movie.web.member.MemberBean;

/**
 * Servlet implementation class GradeController
 */
@WebServlet({"/grade/my_grade.do", "/grade/grade_list.do", "/grade/grade_add.do", "/grade/grade_insert.do"})
public class GradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = CommandFactory.getCommand(request, response);
		GradeService service = GradeServiceImpl.getInstance(); // 싱글톤 패턴을 사용하여 이미 생성된 GradeServiceImpl 객체를 가져와서 사용함
		List<GradeBean> list = new ArrayList<GradeBean>();
		GradeBean grade = new GradeBean(); 
		MemberBean member = new MemberBean();
		
		switch (command.getAction()) {
		case "my_grade":
			grade = service.getGradesById(request.getParameter("id")); // detail.jsp 에서 my_grade.do 를 호출할 떄 id 값이 같이 넘어온다.
			request.setAttribute("score", grade); // id를 이용하여 데이터베이스에서 성적을 담아서 온 GradeBean객체를 score라는 이름으로 참조할 수 있도록 request 객체에 담는다.
			command.setView(command.getDirectory(), "my_grade");
			break;
		case "grade_list" :
			list = service.getList();
			request.setAttribute("list", list);
			command.setView(command.getDirectory(), "grade_list");
			break;
		case "grade_add" :
			member.setId(request.getParameter("id"));
			member.setName(request.getParameter("name"));
			request.setAttribute("member", member);
			break;
		case "grade_insert" :
			grade.setId(request.getParameter("id"));
			grade.setJava(Integer.parseInt(request.getParameter("java")));
			grade.setSql(Integer.parseInt(request.getParameter("sql")));
			grade.setJsp(Integer.parseInt(request.getParameter("jsp")));
			grade.setSpring(Integer.parseInt(request.getParameter("spring")));
			service.input(grade);
			command.setView("admin", "admin_form");
			break;
		default:
			break;
		}
		DispatcherServlet.goNextPage(request, response, command.getView());
	}
}
