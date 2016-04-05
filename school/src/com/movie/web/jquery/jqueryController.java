package com.movie.web.jquery;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.web.global.Command;
import com.movie.web.global.CommandFactory;
import com.movie.web.global.DispatcherServlet;


@WebServlet({"/jquery/attr.do","/jquery/core.do","/jquery/event.do","/jquery/selector.do"
	,"/jquery/traversing.do","/jquery/dom.do"})
public class jqueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = CommandFactory.getCommand(request, response); // 받아온 URL을 쪼개는 메소드 호출
		DispatcherServlet.goNextPage(request, response, command.getView());
	}
}
