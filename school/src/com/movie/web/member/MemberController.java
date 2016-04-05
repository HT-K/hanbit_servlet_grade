package com.movie.web.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movie.web.global.Command;
import com.movie.web.global.CommandFactory;
import com.movie.web.global.Constants;
import com.movie.web.global.DispatcherServlet;

@WebServlet({"/member/login_form.do", "/member/join_form.do", "/member/login.do", 
	"/member/join.do","/member/logout.do","/member/detail.do", 
	"/member/update_form.do", "/member/update.do", "/member/delete.do", "/member/member_list.do"}) // xml과 연결됨, 이렇게 배열 형식으로 서블릿을 지정한다.
public class MemberController extends HttpServlet { // HttpServlet 클래스를 상속받아 만들어진 클래스.
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command command = CommandFactory.getCommand(request, response); // 받아온 URL을 쪼개는 메소드 호출, 팩토리 패턴 사용
		MemberService service = MemberServiceImpl.getInstance(); // 싱글톤 패턴으로 MemberServiceImpl 객체를 가져온다.
		MemberBean member = new MemberBean(); // 멤버 빈은 사용자마다 달라야 하므로 doGet 혹은 doPost가 호출될 때 마다 생성되어야 하므로 싱글톤 패턴으로 하면 안된다.
		HttpSession session = request.getSession(); // 쉘 로우 카피 방식으로 request 객체를 이용해서 세션 객체를 생성한다.
		List<MemberBean> memList = new ArrayList<MemberBean>(); // 관리자 페이지의 전체 회원 리스트를 출력하기 위한 그릇 객체
		
		switch (command.getAction()) { // URI에서 action만 받아온다.
		case "login":
			System.out.println("===로그인 버튼 클릭 후===");
			member = service.login(request.getParameter("id"), request.getParameter("password"));
			if (member.getId() == null) { // db에서 입력받은 id와 같은 값을 찾지 못할 경우 null이 리턴된다.
				System.out.println("===로그인 실패===");
				command.setView(command.getDirectory(), "login_form");
			} else { // 입력받은 id와 password를 가진 회원을 db에서 찾은 경우다.
				System.out.println("===로그인 성공===");
				session.setAttribute("user", member); // db 내용을 저장해서 가져온 MemberBean객체를 세션객체에 저장한다 (이후 이 세션을 이용하여 여러 곳에서 사용예정)
				command.setView(command.getDirectory(), "detail"); // id와 비번 둘다 정확할 경우
			}
			break;
		case "logout":
			System.out.println("===로그아웃 버튼 클릭 후===");
			session.invalidate(); // 세션 기본 객체에 저장됐던 속성 목록이 삭제되면서 세션을 종료한다.
			command.setView(command.getDirectory(), "login_form");
			break;
		case "join":
			System.out.println("===회원가입 버튼 클릭 후===");
			member.setId(request.getParameter("id")); // join_form.jsp에서 받아온 값들을 member 객체에 저장해서 service.join에 보낸다.
			member.setPassword(request.getParameter("password"));
			member.setName(request.getParameter("name"));
			member.setAddr(request.getParameter("addr"));
			member.setBirth(Integer.parseInt(request.getParameter("birth")));
			if (service.join(member) == 1) { // 회원 정보를 member객체에 담아 디비에 보낸 후 성공하면 오라클에서 1을 리턴해준다.
				command.setView(command.getDirectory(), "login_form");
			} else {
				command.setView(command.getDirectory(), "join_form");
			}
			break;
		case "update": // 업데이트 폼에서 업데이트 완료 시 다시 디테일로 가서 변경된 정보를 보여줘야 한다.
			System.out.println("===업데이트 완료 버튼 클릭 후===");
			member.setId(request.getParameter("id")); // 업데이트 폼에서 변경된 정보를 가지고 와서 MemberBean 객체에 저장
			member.setPassword(request.getParameter("password"));
			member.setName(request.getParameter("name"));
			member.setAddr(request.getParameter("addr"));
			member.setBirth(Integer.parseInt(request.getParameter("birth")));

			if (service.update(member) == 1) { // update 가 성공하면 오라클에서 1을 리턴해준다.
				session.setAttribute("user", service.detail(request.getParameter("id"))); // 업데이트 하고 나서 다시 회원의 상세 정보를 session객체(user)에 저장한다.
				command.setView(command.getDirectory(), "detail");
			} else { // update 실패 시 기존의 내용을 update_form에 띄운다.
				command.setView(command.getDirectory(), "update_form");
			}
			break;
		case "delete":
			System.out.println("===탈퇴 버튼 클릭 후===");
			member = (MemberBean) session.getAttribute("user"); // 세션에 저장된 회원의 내용을 member 객체에 저장.
			if (service.remove(member.getId()) == 1) { // 탈퇴하려는 회원의 id를 매개변수로 보내서 회원 탈퇴 실행, 성공하면 오라클에서 1을 리턴해줌
				System.out.println("탈퇴성공");
				command.setView("global", "main");
			} else {
				System.out.println("탈퇴 실패");
				command.setView(command.getDirectory(), "detail");
			}
			break;
		case "member_list" : // 관리자 페이지에서 모든 회원의 정보를 보여주기 위한 서블릿이다.
			memList = service.getMemList();
			request.setAttribute("list", memList); // 결과를 member_list.jsp 에 보내기 위해 request객체(list)에 담는다.
			command.setView(command.getDirectory(), "member_list");
			break;
		default:
			System.out.println("오류");
		}

		DispatcherServlet.goNextPage(request, response, command.getView());
	}
}
