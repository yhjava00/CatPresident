package myPage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.myPage")
public class MyPageController extends HttpServlet {
	
	private MyPageService myPageService;
	
	@Override
	public void init() throws ServletException {
		myPageService = MyPageService.getMyPageService();
	}
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		String nextPage = "myPage/myPage.jsp";
		
		switch (path) {
		case "/available_reviews":
			nextPage = "myPage/myPage_available_reviews.jsp";
			break;
		case "/often_seen":
			nextPage = "myPage/myPage_often_seen.jsp";
			break;
		case "/orders":
			nextPage = "myPage/myPage_orders.jsp";
			break;
		case "/review":
			nextPage = "myPage/myPage_review.jsp";
			break;
		case "/update":
			nextPage = "myPage/myPage_update.jsp";
			break;
		default:
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
