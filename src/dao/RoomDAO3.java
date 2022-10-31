package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import vo.RoomVO;
//import com.pknu.room.dao.LogPreparedStatement;

public class RoomDAO3 {
	
	// Field
	private Connection 			 conn   = null;
	private LogPreparedStatement lpstmt = null;
	private ResultSet 			 rs     = null;
	
	// 생성자
	public RoomDAO3() {
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
	
	// 메소
	// 객실 보기
	public Vector getMemberList() {
		Vector v3   = new Vector();
		try {
			conn       = DBConn.getConnection();
			String sql = "select roomnum, roomtype, roomfloor, ppnum"; 
			sql += " from ROOMS";
			lpstmt     = new LogPreparedStatement(conn, sql);

			// sql문 확인
//			System.out.println(lpstmt);
			rs		   = lpstmt.executeQuery();
			while(rs.next()) {
				int    roomnum    = rs.getInt("roomnum");
				String roomtype   = rs.getString("roomtype");
				int    roomfloor  = rs.getInt("roomfloor");
				int    ppnum      = rs.getInt("ppnum");
				
				// Inner vector : columns 역할
				Vector m = new Vector();
				m.add(roomnum);
				m.add(roomtype);
				m.add(roomfloor);
				m.add(ppnum);
				v3.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close();
		}
		
//		Vector v   = null;
		return v3;
	}
}