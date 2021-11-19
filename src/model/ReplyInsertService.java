package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class ReplyInsertService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		Long board_no = Long.parseLong(request.getParameter("board_no"));
		String ip = request.getRemoteAddr();
		
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setAuthor(author);
		replyDTO.setContent(content);
		replyDTO.setBoard_no(board_no);
		replyDTO.setIp(ip);
		
		int result = BoardDAO.getInstance().insertReply(replyDTO);
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.println("<script>");
			out.println("alert('댓글이 등록되었습니다.')");
			out.println("location.href='selectBoardByNo.do?no="+ board_no + "'");	// redirect 이동
			out.println("</script>");
			out.close();
			return null;
		} else {
			out.println("<script>");
			out.println("alert('댓글 달기 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}
