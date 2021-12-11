package com.th.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListDao {
	
	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mariadb://localhost:3306/tarr4h";
//		String url = "jdbc:mariadb://tarr4h.cafe24.com:3306/tarr4h";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, "tarr4h", "Gksxodn12");
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public String searchTable() {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		String result = "";
		
		try {
			pstmt = conn.prepareStatement("select * from userinfo");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getString("user_name");
				System.out.println("result : "+result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int insertList(String name, String transport, int extraUser, String comment) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("insert into memberInfo values(?, ?, ?, ?)");
			pstmt.setString(1, name);
			pstmt.setString(2, transport);
			pstmt.setInt(3, extraUser);
			pstmt.setString(4, comment);
			
			
			result = pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {			
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

	public int checkPassword(int password) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("select * from Password where password = ?");
			pstmt.setInt(1, password);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	public List<Map<String, Object>> getList() {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from memberInfo");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Map<String, Object> user = new HashMap<>();
				user.put("name", rset.getString("name"));
				user.put("transport", rset.getString("transport"));
				user.put("extraUser", rset.getInt("extra_user"));
				user.put("comment", rset.getString("comment"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return list;
	}

	public Map<String, Integer> getTotalMember() {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		Map<String, Integer> map = new HashMap<>();
		
		try {
			pstmt = conn.prepareStatement("select count(*), sum(extra_user) from memberInfo");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int totalMember = rset.getInt(1);
				int totalExtra = rset.getInt(2);
				map.put("totalMember", totalMember);
				map.put("totalExtra", totalExtra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return map;
	}

	public int deleteMember(String delUser) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM memberInfo WHERE NAME = ?");
			pstmt.setString(1, delUser);
			
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int checkAdminPassword(int pw) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("select * from Password where admin_password = ?");
			pstmt.setInt(1, pw);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int insertGameInfo(String date, String area) {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("insert into gameInfo values(?, ?)");
			pstmt.setString(1, date);
			pstmt.setString(2, area);
			
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteGameInfo() {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("delete from gameInfo where date like '%%'");
			
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteList() {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement("delete from memberInfo where name like '%%'");
			result = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Map<String, String> getGameInfo() {
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		Map<String, String> map = new HashMap<>();
		
		try {
			pstmt = conn.prepareStatement("select * from gameInfo");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String date = rset.getString(1);
				String area = rset.getString(2);
				map.put("date", date);
				map.put("area", area);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return map;
	}
	
	
}
