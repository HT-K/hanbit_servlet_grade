package com.movie.web.grade;

import java.util.ArrayList;
import java.util.List;

public class GradeServiceImpl implements GradeService{
	// 멤버 필드 (속성을 모아놓은 부분) 
	GradeBean grade;
	GradeDAO dao = GradeDAOImpl.getInstance();
	ArrayList<MemberGradeBean>  gradeList; // 메소드들이 공유를 해야해서 멤버 필드 영역에 선언해야 한다. (인스턴스 변수라 한다) , 인스턴스 변수 멤버 필드 영역에서 초기화를 하지 않는다. 따라서 아래 생성자와 같이 메소드에서 초기화해주는게 좋다!
	
	private static GradeService instance = new GradeServiceImpl(); // 싱글톤 패턴을 사용하여 하나의 객체로 사용할 수 있도록 만듦
	
	
	/*GradeDAO dao = new GradeDAOImpl(); // 싱글톤 패턴 적용 전에는 이런방식으로 사용하였음*/	
	
	public static GradeService getInstance() { // 이 GradeServiceImpl 클래스가 필요한 곳에서 이 static메소드를 호출하면 이미 생성된 하나의 서비스임플객체를가지고 모든 곳에서 쓸 수 있다.
		return instance;
	}

	public GradeServiceImpl() {
		gradeList = new ArrayList<MemberGradeBean>(); // 초기화
	}
	
	// 멤버 메소드 에어리어
	@Override
	public void input(GradeBean gradeBean) { // gradeBean -> 지역변수의 일종으로 매개변수 혹은 파라미터라고 부름
		// C 성적표 등록
		dao.insert(gradeBean);
		//gradeList.add(gradeBean);
	}

	@Override
	public List<GradeBean> getList() { // 서비스에서 syso를 하는게 아니라 Controller로 gradeList를 던져주면 된다!
		// R 성적표 리스트 출력
		return dao.selectAll();
	}

	@Override
	public MemberGradeBean getGradeByHak(int hak) {
		// R 성적표 조회(학번)
		return dao.selectGradeByHak(hak);
	}

	@Override
	public ArrayList<MemberGradeBean> getGradesByName(String name) {
		// R 성적표 조회(아이디)
		ArrayList<MemberGradeBean> tmpList = dao.selectGradesByName(name);
		
		return tmpList;
		
	/*	Vector<GradeBean> tmpVec = new Vector<GradeBean>();
	 * 
		for (GradeBean temp : gradeList) {
			if (temp.getId().equals(name)) {
				tmpVec.add(temp); // 매개변수로 넘어온 name 값을 가진 GradeBean객체를 tmpVec벡터에 저장한다.
			}
		}
		
		tmpVec.add(grade);
		return tmpVec;*/
	}
	
	@Override
	public GradeBean getGradesById(String id) {
		return dao.selectGradesById(id);
	}

	@Override
	public int getCount() {
		// R 카운트 조회
		return dao.count();
	}

	@Override
	public String update(GradeBean grade) {
		// U 성적표 수정
		
		/*for (int i = 0; i < gradeList.size(); i++) {
			if (gradeList.get(i).getHak() == grade.getHak()) {
				gradeList.get(i).setJava(grade.getJava());
				gradeList.get(i).setSql(grade.getSql());
				gradeList.get(i).setJsp(grade.getJsp());
				gradeList.get(i).setSpring(grade.getSpring());
				return "수정성공";
			}
		}
		return "수정하려는 학번이 존재하지 않습니다.";*/
		
		
		/*//for - each 문을 이용한 수정
		for (GradeBean temp : gradeList) {
			if (temp.getHak() == grade.getHak()) {
				gradeList.remove(temp);
				gradeList.add(grade);
				return "수정성공";
			}
		}
		return "수정실패";*/
		
		//for - each 방법2
		/*for (GradeBean temp : gradeList) {
			if (temp.getHak() == grade.getHak()) {
				int index = gradeList.indexOf(temp); // 매개변수로 받아온 grade의 학번과 같은 학번을 가진 객체를 찾으면 그 객체의 index번호를 찾아낸다. 그 후 그 객체의 성적을 수정!
				gradeList.get(index).setJava(grade.getJava());
				gradeList.get(index).setSql(grade.getSql());
				gradeList.get(index).setJsp(grade.getJsp());
				gradeList.get(index).setSpring(grade.getSpring());
				return "수정성공";
			}
		}*/
		return "수정실패";
	}

	@Override
	public String delete(int hak) {
		// D 성적표 삭제
		/*for (GradeBean temp : gradeList) {
			if (temp.getHak() == hak) {
				gradeList.remove(temp);
				return "삭제성공";
			}
		}*/
		return "삭제실패";
	}
}