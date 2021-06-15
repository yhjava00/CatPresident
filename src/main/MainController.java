package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.main")
public class MainController extends HttpServlet {
	
	private MainService mainService;
	
	@Override
	public void init() throws ServletException {
		mainService = MainService.getMainService();
	}
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
//		String nextPage = "myPage/myPage.jsp";
		String nextPage = "main.jsp";
		
		switch (path) {
		case "/review":
			nextPage = "myPage/myPage_review.jsp";
			break;
		case "/test":
			
			System.out.println(request.getParameter("test"));
			
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
