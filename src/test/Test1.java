package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Test1 extends HttpServlet {
//	private DataSource dataFactory;
//	Connection conn;
//	PreparedStatement pstmt;
//
//    public Test1() {
//    	try {
//			Context ctx = new InitialContext();
//			Context envContext = (Context) ctx.lookup("java:/comp/env");
//			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
    
	private  MemberDAO memberDAO;
	
	@Override
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nextPage = "/view/main/main.jsp";
//		
//		String path = request.getRequestURI();
//		
//		String action = path.substring(path.lastIndexOf("CatPresident/")+13);
//		System.out.println("action:" + action);
//		
//		request.setAttribute("page", action);
//		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
//		
		
	}

}
