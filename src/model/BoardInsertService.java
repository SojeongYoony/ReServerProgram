package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class BoardInsertService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String author = request.getParameter("author");
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		String ip = request.getRemoteAddr();
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setAuthor(author);
		boardDTO.setContent(content);
		boardDTO.setTitle(title);
		boardDTO.setIp(ip);
		
		PrintWriter out = response.getWriter();
		int result = BoardDAO.getInstance().insertBoard(boardDTO);
		if (result > 0) {
			// 문제에서 게시글이 등록되었습니다 alert 띄우는 부분이 있었는데 못 보고 놓침. -- 수정
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다.')");
			out.println("location.href='selectBoardList.do'");
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('게시글작성 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			// if 조건이 끝날때마다 return null 각각 주지 않아도 됨.
		}
		return null;
		
	}

}
