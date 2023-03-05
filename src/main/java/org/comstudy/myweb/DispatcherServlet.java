package org.comstudy.myweb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myweb.board.model.BoardDAO;
import org.comstudy.myweb.board.model.BoardDTO;
import org.comstudy.myweb.saram.model.SaramDAO;
import org.comstudy.myweb.saram.model.SaramDTO;

public class DispatcherServlet extends HttpServlet {
	SaramDAO saramDAO = new SaramDAO();
	BoardDAO boardDAO = new BoardDAO();
	SaramController saramController = new SaramController();
	BoardController boardController = new BoardController();
	
	private String encodingWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		// path 만들기
		String ctxPath = req.getContextPath();
		String reqUri = req.getRequestURI();
		int beginIndex = ctxPath.length();
		String path = reqUri.substring(beginIndex);
		System.out.println("path >>>> " + path);
		// 하위 컨트롤러에서 path를 사용하도록 req에 저장
		req.setAttribute("path", path);
		return path;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet() - DispatcherServlet 요청");
		
		String path = encodingWork(req, resp);
		
		Controller controller = null; // 업캐스팅
		if(path.indexOf("/saram") != -1) {
			controller = saramController;
		} else if(path.indexOf("/board") != -1) {
			controller = boardController;
		}
		String viewName = controller.process(req, resp);
		
		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost() - DispatcherServlet 요청");
		String path = encodingWork(req, resp);
		
		if (req.getParameter("id") != null) {
			int seq = Integer.parseInt(req.getParameter("seq")==null?"0":req.getParameter("seq"));
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age")==null?"0":req.getParameter("age"));
			
			SaramDTO dto = new SaramDTO(seq, id, name, age);
	
			if("/saram/input.do".indexOf(path) != -1) {
				saramDAO.save(dto);
			} else if("/saram/modify.do".indexOf(path) != -1) {
				saramDAO.update(dto);
			} else if("/saram/delete.do".indexOf(path) != -1) {
				saramDAO.remove(dto);
			}

			resp.sendRedirect(req.getContextPath() + "/saram/list.do");
		} else if  (req.getParameter("title") != null) {
			Long seq = Long.parseLong(req.getParameter("seq")==null?"0":req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String dateValue = req.getParameter("writeDate");
			java.util.Date writeDate = null;
			
			if(dateValue != null) {
				java.sql.Date sqlDate = java.sql.Date.valueOf(dateValue);
		        writeDate = new java.util.Date(sqlDate.getTime());
		        // sql로 받은 것을 다시 util로 변경
			} else {
				writeDate = new java.util.Date();
			}
	        
            String writer = req.getParameter("writer");
			Long cnt =Long.parseLong(req.getParameter("cnt")==null?"0":req.getParameter("cnt"));
			
			BoardDTO dto = new BoardDTO(seq, title, content, writeDate, writer, cnt);

			if("/board/input.do".indexOf(path) != -1) {
				boardDAO.save(dto);
			}else if("/board/modify.do".indexOf(path) != -1) {
				boardDAO.update(dto);
			} else if("/board/delete.do".indexOf(path) != -1) {
				boardDAO.remove(dto);
			}

			resp.sendRedirect(req.getContextPath() + "/board/list.do");

		}
	}
}
