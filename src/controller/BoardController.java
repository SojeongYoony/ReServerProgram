package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.BoardDeleteService;
import model.BoardDetailService;
import model.BoardInsertService;
import model.BoardListService;
import model.MemberService;
import model.ReplyInsertService;
import model.ReplyListService;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String requestURI = request.getRequestURI();																		 
		String contextPath = request.getContextPath();																	
		String command = requestURI.substring(contextPath.length() + 1);  					
		
		ModelAndView mav = null;
		MemberService service = null; 													
		switch(command) {
		/* board List */
		case "selectBoardList.do" : 
			service = new BoardListService();
			break;
		/* board detail */
		case "selectBoardByNo.do" :
			service = new BoardDetailService();
			break;
		/* form 가기 */
		case "insertBoardForm.do" :
			mav = new ModelAndView("views/boardInsertForm.jsp", false);
			break;
		/* insert board */	
		case "insertBoard.do" :
			service = new BoardInsertService();
			break;
		/* insert reply */
		case "insertReply.do" :
			service = new ReplyInsertService();
			break;
		/* delete content */	
		case "deleteBoard.do" :
			service = new BoardDeleteService();
			break;
		}
		
		if (service != null) {
			try {														
				mav = service.execute(request, response);		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		if (mav != null) {				
			if(mav.isRedirect()) {		
					response.sendRedirect(mav.getView());										
			} else {
					request.getRequestDispatcher(mav.getView()).forward(request, response);		
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
