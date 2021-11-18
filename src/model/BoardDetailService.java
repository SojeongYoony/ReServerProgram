package model;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;
import dto.ReplyDTO;

public class BoardDetailService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 게시글 번호
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(opt.orElse("0"));
		HttpSession session = request.getSession();
		
		if (session.getAttribute("open") == null) {
			session.setAttribute("open", true);
			BoardDAO.getInstance().updateBoardHit(no);
		}
		
		BoardDTO boardDTO = BoardDAO.getInstance().selectBoardByNo(no);
		
		
		if(boardDTO != null) {
			session.setAttribute("boardDTO", boardDTO);
			List<ReplyDTO> replyList = BoardDAO.getInstance().selectReplyList(no);
			request.setAttribute("replyList", replyList);
			request.setAttribute("boardDTO", boardDTO);
			return new ModelAndView("views/boardDetail.jsp", false);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일치하는 게시글이 없습니다. 다시 시도하세요.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
	}

}
