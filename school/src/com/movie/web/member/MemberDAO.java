package com.movie.web.member;

import java.util.List;

public interface MemberDAO { // void 타입이 존재하지 않는다. 
	public MemberBean login(String id, String password); // 로그인 체크
	public int insert(MemberBean member); // 회원가입 시 내용 데이터베이스에 저장
	public MemberBean selectMember(String id); // 회원 상세 정보 출력을 위함 (id 이용)
	public int update(MemberBean member); // 업데이트로 내용 변경 시 데이터베이스 내용도 변경
	public int delete(String id); // 회원 탈퇴
	public boolean isMember(String id); //회원인지 아닌지 검사
	public List<MemberBean> selectAllMem(); // 모든 학생을 리스트에 담아야한다. (관리자 페이지에 보여주기 위함)
}
