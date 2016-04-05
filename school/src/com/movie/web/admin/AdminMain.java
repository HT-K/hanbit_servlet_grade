package com.movie.web.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.movie.web.grade.GradeBean;
import com.movie.web.grade.MemberGradeBean;

public class AdminMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<MemberGradeBean> listBean = new ArrayList<MemberGradeBean>(); // 1번메뉴에서 필요
		GradeBean gradeBean = new GradeBean();
		AdminService service = AdminServiceImpl.getService();
		
		System.out.println("관리자모드에 오신 것을 환영합니다. 메뉴를 선택해주세요.");
		System.out.println("1. 모든 학생의 정보와 성적 출력 2. 해당 아이디의 성적 입력하세요.");
		
		while (true) {
			switch (sc.nextInt()) {
			case 1:
				//listBean = service.getMemGraList();
				System.out.println("Score_seq \t ID \t JAVA \t SQL \t JSP \t SPRING \t NAME \t PASSWORD \t ADDR \t BIRTH");
				for (MemberGradeBean temp : listBean) {
					System.out.print(temp.getScore_seq() + "\t" + temp.getId() + "\t" + temp.getJava() + "\t" + temp.getSql() + "\t" 
							+ temp.getJsp() + "\t" + temp.getSpring() + "\t" + temp.getName() + "\t" + temp.getPassword() +"\t" + temp.getAddr() + "\t" + temp.getBirth());
					System.out.println();
				}
				break;
			case 2:
				System.out.println("성적을 입력할 학생의 아이디와 점수를 입력하세요.");
				gradeBean.setId(sc.next());
				gradeBean.setJava(sc.nextInt());
				gradeBean.setSql(sc.nextInt());
				gradeBean.setJsp(sc.nextInt());
				gradeBean.setSpring(sc.nextInt());
				
				if (service.addScore(gradeBean) == 1) { //데이터베이스 삽입 성공
					System.out.println("삽입 성공");
				} else {
					System.out.println("삽입 실패");
				}
				break;
			default:
				break;
			}
		}
	}
}
