package com.movie.web.member;

import java.util.Scanner;

public class MemberMain {
	public static void main(String[] args) {
		MemberService service = new MemberServiceImpl();
		Scanner s = new Scanner(System.in);
		MemberBean member = null;
		
		while (true) {
			System.out.println("[메뉴] 1.회원가입 2.로그인 3.내정보보기 4.내정보수정 5.탈퇴 0.종료");
			switch (s.nextInt()) {
			case 1: 
				System.out.println("아이디, 비번, 이름, 주소, 생년월일 입력");
				System.out.println("Main 회원 가입 결과: " + service.join(new MemberBean(s.next(), s.next(), s.next(), s.next(), s.nextInt())));
				break;
			case 2: 
				System.out.println("아이디와 비밀번호를 입력하세요.");
				try {
					member = service.login(s.next(), s.next());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				System.out.println(member.getName() + "님 로그인에 성공하셨습니다.");
				break;
			case 3: 
				System.out.println("아이디 입력");
				System.out.println(service.detail(s.next()).toString());
				//member = service.detail(member.getId());
				//System.out.println("내정보 ====> " + member.toString());
				break;
			case 4: 
				System.out.println("바꾸실 비밀번호와 생년월일을 입력하세요.");
				System.out.println("Main에서 정보변경 결과 : " + service.update(new MemberBean(member.getId(),s.next(),member.getName(),s.next(),member.getBirth())));
				//member = service.update(new MemberBean(member.getId(),s.next(),s.next(),s.next(),s.nextInt()));
				System.out.println("수정된 내 정보 ===> " + member);
				break;
			case 5: 
				System.out.println("탈퇴하고 싶은 회원의 아이디 =" + member.getId());
				System.out.println(service.remove(member.getId()));
				break;
			case 0: 
				System.out.println("프로그램 종료");
				return;
				//System.exit(0);
			default:
				break;
			}
		}
	}
}
