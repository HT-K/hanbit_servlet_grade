package com.movie.web.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class MemberServiceImpl implements MemberService {
	MemberDAO dao = MemberDAOImpl.getInstance(); // 싱글톤패턴으로 생성한 객체를 가져온다 (이렇게 호출하면 생성한 하나의 객체를 가지고 다른 곳에서도 호출할 수 있다.)
	private static MemberService instance = new MemberServiceImpl(); // 싱글톤 패턴으로 객체를 사용하기 위함.	
	
	public static MemberService getInstance() {
		return instance;
	}

	@Override
	public MemberBean login(String id, String password) {
		// 로그인
		return dao.login(id,password); // 해당 id와 password를 가진 사용자의 모든 정보를 가져와서 bean 객체에 넣는다.
	}
		
	@Override
	public int join(MemberBean member) {
		// 회원가입
		return dao.insert(member); // 입력받은 회원양식의 값들을 MemberBean 객체인 member에 set 한 후 이곳으로 가져와서 다시 dao에 넘긴다.
	}
	
	@Override
	public MemberBean detail(String id) {
		// 내 정보보기
		return dao.selectMember(id); // MemberDAOImpl 클래스에 구현한 selectMember 메소드를 호출하여 데이터베이스에서 MemberBean 객체에 내용을 넣어서 받아온다.
	}

	@Override
	public int update(MemberBean member) {
		// 수정
		return dao.update(member);
	}

	@Override
	public int remove(String id) {
		// 삭제
		return dao.delete(id);
	}

	@Override
	public boolean isMember(String id) {
		// 회원인지 아닌지 검사
		return dao.isMember(id);
	}

	@Override
	public List<MemberBean> getMemList() {
		// 모든 회원 리스트
		return dao.selectAllMem();
	}
}
