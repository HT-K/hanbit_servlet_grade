package com.movie.web.admin;

import java.util.List;

import com.movie.web.grade.GradeBean;
import com.movie.web.grade.MemberGradeBean;
import com.movie.web.member.MemberBean;

public interface AdminDAO {
	public AdminBean selectAdmin(AdminBean admin); // 관리자 정보를 가져온다.
	public int insertScore(GradeBean gradeBean); 
	public MemberBean selectMemID(String id); // ID를 이용해서 학생 검색
	public MemberBean selectMemName(String name); //이름을 이용해서 학생 검색
}
