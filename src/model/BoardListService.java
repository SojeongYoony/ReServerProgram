package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class BoardListService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		BoardDTO boardDTO = (BoardDTO) session.getAttribute("boardDTO");
		if(boardDTO != null) {
			session.removeAttribute("boardDTO");
		}
		
		if (session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		List<BoardDTO> list = BoardDAO.getInstance().selectBoardList();
		int totalCount = BoardDAO.getInstance().getTotalCount();
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		return new ModelAndView("views/listBoard.jsp", false);
		
	}

}
