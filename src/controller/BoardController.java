package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.BoardService;


/* 가장 완벽한 형태 (여지껏 우리가 작성했던 것 중에서...) */


@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");	// ajax 작성 시, 달라질 수 있는 부분임.
		String requestURI = request.getRequestURI();																		//   /BATCH/student/list.do       
		String contextPath = request.getContextPath();																		// 	 /BATCH
		String command = requestURI.substring(contextPath.length() + 1);  						// contextPath + 1 == contextPath를 제외한 값 	--> student/list.do
		
		ModelAndView mav = null;
		BoardService service = null; 													// 모든 model들의 type을 잡아두고 아래 switch문에서 method를 채워준다.
		switch(command) {
		}
		
		if (service != null) {
			try {														// Exception을 직접 받기 위한 try를 작성할 것 ---> 100% 예외 처리됨. IOE or Servlet E 는 위에서 해주지만 다른 예외 처리를 위해 작성.(interface에서 던지는 exception임)
				mav = service.execute(request, response);		// 모든 실행의 결과는 mav이다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//command의 실행 결과로 mav가 반환됨.
		if (mav != null) {				// mav 가 null이 아니면
			if(mav.isRedirect()) {		// redirect 여부 확인
					response.sendRedirect(mav.getView());										// mav가 getView(이동할 장소)를 가지고 redirect response를 한다.
			} else {
					request.getRequestDispatcher(mav.getView()).forward(request, response);		// 아니면 forward한다 request와 response를 들고.
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
