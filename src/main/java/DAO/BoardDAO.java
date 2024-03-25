package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Board;

public class BoardDAO {
	//DB connection
	final static String JDBC_DRiVER = "oracle.jdbc.driver.OracleDriver";
	final static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//DB(데이터베이스) 연결 메소드
	public Connection open() { 
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRiVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; //데이터베이스 연결 객체를 리턴
	}
	
	//게시판 리스트 가져오는 메소드
	public ArrayList<Board> getList() throws Exception {
		//connection 열어주기
		Connection conn = open(); //DB 커넥션 열기
		ArrayList<Board> boardList = new ArrayList<>(); //Board 객체를 저장할 ArrayList
		
		String sql = "SELECT board_no, title, USER_id, to_char(reg_date, 'yyyy.mm.dd') REG_DATE, views FROM board"; //쿼리문, 세미콜론은 제거 필요
		PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록
		ResultSet rs = pstmt.executeQuery(); //쿼리문 실행 -> 데이터베이스 결과 저장		
		
		/*
		 - 리소스 자동 닫기하지 않을 경우
		 try {
		 ...} catch(Exception e) {
		 ...} 
		 finally {
		 	conn.close();
		 	pstmt.close();
		 	rs.close();		 
		*/
		
		//Exception에서 사용하는 리소스 자동 닫기 (try-with-resource)
		try (conn; pstmt; rs) {
			while(rs.next()) { //DB에서 한 행씩 가져옴
				Board b = new Board();
				b.setBoard_no(rs.getInt("board_no"));
				b.setTitle(rs.getString("title"));
				b.setUser_id(rs.getString("user_id"));
				b.setReg_date(rs.getString("reg_date"));
				b.setViews(rs.getInt("views"));
				
				boardList.add(b);
			}
			
			return boardList;
		}
	}
}