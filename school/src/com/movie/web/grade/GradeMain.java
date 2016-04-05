package com.movie.web.grade;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class GradeMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GradeService service = new GradeServiceImpl(); // 기차역 같은 존재, 한개만 생성되어 가져다 쓰면 된다.
		ArrayList<MemberGradeBean> tmpList = new ArrayList<MemberGradeBean>();
		
		while (true) {
			System.out.println("[메뉴] 1.등록 2.수정 3.삭제 4.조회(전체) 5.조회(이름) 6.조회(학번) 7.회원수 0.종료");
			switch (scanner.nextInt()) {
			case 1:
				System.out.println("score_seq,ID,학번,자바,SQL,JSP,스프링 점수 입력");
				// GradeBean객체를 생성해서 그 객체에 각각에 맞는 데이터 값을 넣는다. , 값에 따라 객체를 계속 생성해야 하므로 무한루프 안에 있어야함.
				service.input(new GradeBean(scanner.nextInt(),scanner.next(),scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt())); // 데이터를 입력받아 값을 set한 gradeBean객체를 넘긴다. 이 객체는 DAO로 보내지고 마지막에 DB로 보내져야한다.
				break;
			case 2:
				/*System.out.println("수정하려는 성적표의 학번,자바,SQL,JSP,스프링 점수를 입력하세요");
				int hak = scanner.nextInt();
				String id = service.getGradeByHak(hak).getId();
				System.out.println(service.update(new GradeBean(id, hak, scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt())));*/
				break;
			case 3:
				System.out.println("삭제할 성적을 가진 학생의 학번을 입력하세요.");
				System.out.println(service.delete(scanner.nextInt()));
				break;
			case 4:
				System.out.println(service.getList());
				break;
			case 5:
				System.out.println("조회할 이름 입력");
				//Vector<GradeBean> gradeVec = service.getGradesById(scanner.next());
				tmpList = service.getGradesByName(scanner.next());

				System.out.println(tmpList);
				/*if (gradeVec.size() == 0) { // 리턴 받은 벡터 객체의 사이즈가 0이면 성적 객체를 아무것도 저장하지 못했다는 뜻이다.
					System.out.println("입력한 이름의 학생이 없습니다.");
				} else {
					System.out.println(gradeVec); // 벡터는 toString() 메소드가 오버라이드 되어있어서 그냥 써주기만하면 println이 된다.
				}*/
				break;
			case 6:
				System.out.println("조회할 학번 입력");
				MemberGradeBean memGre = service.getGradeByHak(scanner.nextInt());
				
				System.out.println(memGre);
				/*if (gradeBean.equals(null)) {
					System.out.println("입력한 학번의 학생이 없습니다.");
				} else {
					System.out.println(gradeBean.toString());
				}*/
				break;
			case 7:
				System.out.println("전체 수 = " + service.getCount());
				break;
			case 0:
				System.out.println("프로그램 종료");
				System.exit(0);
			default:
				System.out.println("메뉴를 잘못입력하셨습니다.");
				break;
			}
		}
	}
}