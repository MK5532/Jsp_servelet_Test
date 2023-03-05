package org.comstudy.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.comstudy.myweb.dbcp.JdbcUtil;

public class BoardDAO {
	Connection conn = null; 
	PreparedStatement stmt = null;
	ResultSet rs = null;

	final String FIND_ALL = "SELECT * FROM BOARD";
	final String FIND_ONE = "SELECT * FROM BOARD WHERE SEQ=?";
	final String SAVE = "insert into BOARD(title, content, writeDate, writer, cnt) values(?,?,?,?,?)";
	final String UPDATE = "update BOARD set title=?, content=?, writer=? WHERE seq=?";
	final String DELETE = "DELETE from BOARD WHERE seq=?";
	
	// 검색
	public List<BoardDTO> findAll() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(FIND_ALL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Long seq = rs.getLong("seq");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("writeDate");
				String writer = rs.getString("writer");
				Long cnt = rs.getLong("cnt");
				list.add(new BoardDTO(seq, title, content, writeDate, writer, cnt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return list;
	}
	public BoardDTO findOne(BoardDTO dto) {
		BoardDTO board = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(FIND_ONE);
			stmt.setLong(1, dto.getSeq());
			rs = stmt.executeQuery();
			if (rs.next()) {
				Long seq = rs.getLong("seq");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("writeDate");
				String writer = rs.getString("writer");
				Long cnt = rs.getLong("cnt");
				board = new BoardDTO(seq, title, content, writeDate, writer, cnt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return board;
	}

	// 입력
	public void save(BoardDTO dto) {
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(SAVE);
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getContent());
			
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = simpleDateFormat.format(dto.getWriteDate());
	        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
			stmt.setDate(3, date1) ;
			// java.util.Date를 java.sql.Date로
			
			stmt.setString(4, dto.getWriter());
			stmt.setLong(5, dto.getCnt());

			// select문 외에는 모두 executeUpdate
			int cnt = stmt.executeUpdate();
			if(cnt>0) {
				System.out.println("입력 성공!");
				conn.commit();
			} else {
				System.out.println("입력 실패!");
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}

	public void update(BoardDTO dto) {
		System.out.println("Saram update ... " + dto);
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getContent());			
			stmt.setString(3, dto.getWriter());
			stmt.setLong(4, dto.getSeq());

			int cnt = stmt.executeUpdate();
			if(cnt>0) {
				System.out.println("수정 성공!");
				conn.commit();
			} else {
				System.out.println("수정 실패!");
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
	}
	// 삭제
	public void remove(BoardDTO dto) {
		try {
			System.out.println("삭제 시도함");

			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setLong(1, dto.getSeq());
			int cnt = stmt.executeUpdate();
			if(cnt>0) {
				System.out.println("삭제 성공!");
				conn.commit();
			} else {
				System.out.println("삭제 실패!");
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}

	}

}
