package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import dao.LogPreparedStatement;

import view.LoginPage;
import vo.CustomerVO;

public class MemberDAO {
	
	// Field
	LoginPage mList;
	
	private Connection 			 conn   = null;
	private LogPreparedStatement lpstmt = null;
	private ResultSet 			 rs     = null;
	
	// 생성자
	public MemberDAO() {
	}	
	public MemberDAO(LoginPage mList) {
		this.mList = mList;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(lpstmt != null) lpstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 메소드
	// 한 회원의 정보를 검색
	public CustomerVO getMember(String id) {
		CustomerVO mem = null; // 한사람 정보 : 1 row
		
		try {
			conn = DBConn.getConnection();
			String sql = "SELECT fname, lname, email, csid FROM CUSTOMER";
			sql += " WHERE csid = ?"; // ? 뒤에 + 로 연결하지 않도록 한다
			
			lpstmt = new LogPreparedStatement(conn, sql);
			lpstmt.setString(1, id);
//			System.out.println(sql);
//			System.out.println(lpstmt.getQueryString());
			
			rs = lpstmt.executeQuery();
			if(rs.next()) {
				String fname		= rs.getString("fname");
				String lname		= rs.getString("lname");
				String email		= rs.getString("email");
				String csid		    = rs.getString("csid");
				String cspwd		= rs.getString("cspwd");
				mem = new CustomerVO(csid, cspwd, lname, fname, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return mem;
	}
	
	// 여러명의 회원정보를 검색
	public Vector getMemberList() {
		// out Vector : row
		Vector v   = new Vector();
		try {
			conn       = DBConn.getConnection();
			String sql = "SELECT fname, lname, email, csid FROM CUSTOMER";
			lpstmt     = new LogPreparedStatement(conn, sql);
			System.out.println(lpstmt);
			rs		   = lpstmt.executeQuery();
			while(rs.next()) {
				String fname		= rs.getString("fname");
				String lname		= rs.getString("lname");
				String email		= rs.getString("email");
				String csid		    = rs.getString("csid");
				String cspwd		= rs.getString("cspwd");
				
				// Inner vector : columns 역할
				Vector m = new Vector();
				m.add(fname);
				m.add(lname);
				m.add(email);
				m.add(csid);
				m.add(cspwd);
				v.add(m);	// out vector 안에 inner vector 저장
			}
			// while문 종료 후 결과(ResultSet → out vector 에 저장)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close();
		}
		
//		Vector v   = null;
		return v;
	}
	
	// 회원 정보 수정
	public int updateMember(CustomerVO mem) {
		int cnt = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "update CUSTOMER set ";
			sql += " fname=?, ";
			sql += " lname=?, ";
			sql += " email=? ";
			sql += " where csid = ? ";
			sql += " and   cspwd = ? ";
			lpstmt = new LogPreparedStatement(conn, sql);
			lpstmt.setString(1, mem.getFname());
			lpstmt.setString(2, mem.getLname());
			lpstmt.setString(3, mem.getEmail());
			lpstmt.setString(4, mem.getId());
			lpstmt.setString(5, mem.getPwd());
			System.out.println("updateMember 오류 :" + lpstmt );
			cnt = lpstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
