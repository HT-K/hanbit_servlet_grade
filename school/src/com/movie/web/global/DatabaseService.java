package com.movie.web.global;

import java.sql.Connection;

public interface DatabaseService { // 인터페이스 생성~
	public Connection getConnection();
}
