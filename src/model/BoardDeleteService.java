package model;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class BoardDeleteService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long no = Long.parseLong(request.getParameter("no"));
		
		// 삭제할 게시글에 댓글 유무 확인하기
		List<ReplyDTO> replyList = BoardDAO.getInstance().selectReplyList(no);

		PrintWriter out = response.getWriter();
		// 댓글이 없으면 삭제 진행
		if(replyList.size() == 0) {	// replyList == null  ==> reply가 없다. == 삭제 진행		*** 주의 *** replyList가 null이 아니고 size로 해야 함 	**개선version : query로 작업하는 게 좋음
			int result = BoardDAO.getInstance().deleteBoard(no);	// dto 호출, db로 보내는 code
			// 댓글이 있으면 못 지운다 작업이 필요함 (삭제에서는)
			if (result > 0) {
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='selectBoardList.do'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} else {	
			out.println("<script>");
			out.println("alert('댓글이 달린 게시글은 삭제할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return null;
	}
}
