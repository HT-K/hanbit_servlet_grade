package com.movie.web.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.movie.web.global.Constants;
import com.movie.web.global.DatabaseFactory;
import com.movie.web.global.Vendor;

public class MemberDAOImpl implements MemberDAO {
	private Connection conn; // 오라클 연결 객체
	private Statement stmt; // 쿼리 전송 객체1
	private PreparedStatement pstmt; // 쿼리 전송 객체2
	private ResultSet rs; // 쿼리 결과 저장 객체
	private static MemberDAO instance = new MemberDAOImpl(); // 인스턴스 초기화는 원래 룰은 생성자에서 해야하지만 싱글톤 패턴 구현에서는 예외
	
	
	public static MemberDAO getInstance() {
		return instance; // 다른곳에서 이 함수를 호출하면 위에서 생성한 객체하나를 가지고 쓸 수 있게 된다.
	}

	public MemberDAOImpl() {
		conn = DatabaseFactory.getDatabase(Vendor.ORACLE,Constants.ID, Constants.PASSWORD).getConnection(); // 만약 Vendor에 MYSQL을 보내면 MYSQL 데이터베이스에 맞은 값이 넘어오게 된다.
	}

	@Override
	public MemberBean login(String id, String password) {
		MemberBean temp = new MemberBean(); // 데이터베이스에서 내용을 받아와서 저장할 Bean 객체다.
		
		try {
			stmt = conn.createStatement(); // 이것도 팩토리 패턴이다.
			rs = stmt.executeQuery("SELECT * FROM Member WHERE id =" + "'"+id+"' and password = " + "'"+password+"'");

			while (rs.next()) { // rs에 요소가 있는 만큼 돌아라
				temp.setId(rs.getString("id"));
				temp.setName(rs.getString("name"));
				temp.setPassword(rs.getString("password"));
				temp.setAddr(rs.getString("addr"));
				temp.setBirth(rs.getInt("birth"));
			}
		} catch (Exception e) {
			System.out.println("selectById()에서 에러 발생");
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int insert(MemberBean member) { // 회원가입 양식에서 입력받은 값들을 데이터베이스에 삽입
		int result = 0;
		String query = "INSERT INTO Member VALUES (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query); 
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getAddr());
			pstmt.setInt(5, member.getBirth());
			result = pstmt.executeUpdate(); // 오라클은 삽입문이 성공하면 (1 row affected)라고 보내준다, 숫자 1을 보내준다!!!
		} catch (Exception e) {
			System.out.println("insert()에서 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public MemberBean selectMember(String id) {
		MemberBean temp = new MemberBean(); // 데이터베이스에서 내용을 받아와서 저장할 Bean 객체다.
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Member WHERE id =" + "'"+id+"'");

			while (rs.next()) { // rs에 요소가 있는 만큼 돌아라
				temp.setId(rs.getString("id"));
				temp.setName(rs.getString("name"));
				temp.setPassword(rs.getString("password"));
				temp.setAddr(rs.getString("addr"));
				temp.setBirth(rs.getInt("birth"));
			}
		} catch (Exception e) {
			System.out.println("selectMember()에서 에러 발생");
			e.printStackTrace();
		}
		//System.out.println("쿼리 조회 결과 :" + temp.getAddr());
		
		return temp;
	}

	@Override
	public int update(MemberBean member) {
		int result = 0;
		String query = "UPDATE Member SET password = ?, addr = ? WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getAddr());
			pstmt.setString(3, member.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("update()에서 에러 발생");
			e.printStackTrace();
		}
		//System.out.println("쿼리 조회 결과 :" + temp.getAddr());
		
		return result;
		
	}

	@Override
	public int delete(String id) {
		int result = 0;
		String query = "DELETE FROM Member WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete()에서 에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean isMember(String id) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id FROM Member");

			while (rs.next()) { // rs에 요소가 있는 만큼 돌아라
				if (id.equals(rs.getString("id"))) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("isMember()에서 에러 발생");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MemberBean> selectAllMem() {
		List<MemberBean> list = new ArrayList<MemberBean>();
		
		try {
			stmt = conn.createStatement(); // 이것도 팩토리 패턴이다.
			rs = stmt.executeQuery("SELECT * FROM Member");

			while (rs.next()) { // rs에 요소가 있는 만큼 돌아라
				MemberBean temp = new MemberBean();
				temp.setId(rs.getString("id"));
				temp.setName(rs.getString("name"));
				temp.setPassword(rs.getString("password"));
				temp.setAddr(rs.getString("addr"));
				temp.setBirth(rs.getInt("birth"));
				list.add(temp);
			}
		} catch (Exception e) {
			System.out.println("selectMemList() 에서 에러 발생");
			e.printStackTrace();
		}
		return list;
	}
}
