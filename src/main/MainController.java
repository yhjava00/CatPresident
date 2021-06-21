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
		
		String nextPage = "main.jsp";
		
		switch (path) {
		case "/header":
			nextPage = "common/header.jsp";
			break;
		case "/product":
			int idx = Integer.parseInt(request.getParameter("idx"));
			request.setAttribute("detailsMap", mainService.details(idx));
			nextPage = "product.jsp";
			break;
		case "/search":
			nextPage = "search.jsp";
			break;
		case "/chat":
			nextPage = "common/chat.jsp";
			break;
		default :
			request.setAttribute("goodsListMap", mainService.main());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
