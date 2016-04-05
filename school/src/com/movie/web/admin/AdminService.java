package com.movie.web.admin;

import java.util.List;

import com.movie.web.grade.GradeBean;
import com.movie.web.grade.MemberGradeBean;
import com.movie.web.member.MemberBean;

public interface AdminService {
	public AdminBean getAdmin(AdminBean admin);
	public int addScore(GradeBean gradeBean); 
	public MemberBean getMemUseId(String id);
	public MemberBean getMemUseName(String name);
}
