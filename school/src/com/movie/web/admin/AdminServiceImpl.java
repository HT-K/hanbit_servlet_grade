package com.movie.web.admin;

import java.util.List;

import com.movie.web.grade.GradeBean;
import com.movie.web.grade.MemberGradeBean;
import com.movie.web.member.MemberBean;

public class AdminServiceImpl implements AdminService {
	private static AdminService service = new AdminServiceImpl();
	AdminDAO dao = AdminDAOImpl.getInstance(); // 싱글톤 패턴으로 dao 객체 가져오기
	
	public static AdminService getService() {
		return service;
	}

	@Override
	public AdminBean getAdmin(AdminBean admin) { // 관리자 정보 가져오기
		// TODO Auto-generated method stub
		return dao.selectAdmin(admin);
	}
	
	@Override
	public int addScore(GradeBean gradeBean) { // 학생 성적 입력하기
		return dao.insertScore(gradeBean);
	}

	@Override
	public MemberBean getMemUseId(String id) { // 아이디로 학생검색
		// TODO Auto-generated method stub
		return dao.selectMemID(id);
	}

	@Override
	public MemberBean getMemUseName(String name) { // 이름으로 학생 검색
		// TODO Auto-generated method stub
		return dao.selectMemName(name);
	}
}
