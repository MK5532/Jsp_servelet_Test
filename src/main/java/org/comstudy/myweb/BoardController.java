package org.comstudy.myweb;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myweb.board.model.BoardDAO;
import org.comstudy.myweb.board.model.BoardDTO;

public class BoardController implements Controller {
	BoardDAO dao = new BoardDAO();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = (String)req.getAttribute("path");
		
		String viewName = "/WEB-INF/views/home.jsp";
		if(path.indexOf("/input.do") != -1) {
			viewName = "/WEB-INF/views/board/input.jsp";
		} else if(path.indexOf("/modify.do") != -1) {
			BoardDTO dto = new BoardDTO();
			dto.setSeq(Long.parseLong(req.getParameter("seq")));
			BoardDTO board = dao.findOne(dto);
			req.setAttribute("board", board);
			viewName = "/WEB-INF/views/board/modify.jsp";

		} else if(path.indexOf("/delete.do") != -1){
			BoardDTO dto = new BoardDTO();
			dto.setSeq(Long.parseLong(req.getParameter("seq")));
			BoardDTO board = dao.findOne(dto);
			req.setAttribute("board", board);
			viewName = "/WEB-INF/views/board/delete.jsp";
		}else {
			List<BoardDTO> list = dao.findAll();
			req.setAttribute("list", list);
			viewName = "/WEB-INF/views/board/list.jsp";
		}
		return viewName;
	}

}
