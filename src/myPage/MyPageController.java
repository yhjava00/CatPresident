package myPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberService;
import vo.GoodsVO;
import vo.MemberVO;
import vo.ReviewVO;

@WebServlet("*.myPage")
public class MyPageController extends HttpServlet {
	
	private MyPageService myPageService;
	
	private MemberService memberService;
	
	@Override
	public void init() throws ServletException {
		myPageService = MyPageService.getMyPageService();
		memberService = MemberService.getMemberService();
	}
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = (String) request.getSession().getAttribute("loginUser");
		
		if(id==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.write("<script>alert('로그인 후 이용하실 수 있습니다.'); changeContent('main.main')</script>");
			out.close();
			return;
		}
		
		String uri = request.getRequestURI();
		
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		String nextPage = "myPage/myPage.jsp";
		
		switch (path) {
		case "/available_reviews":
			nextPage = "myPage/myPage_available_reviews.jsp";
			break;
		case "/often_seen":
		{
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, "often_seen");
			
			request.setAttribute("collection", "often_seen");
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection.jsp";
		}
			break;
		case "/like":
		{
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, "like");
			
			request.setAttribute("collection", "like");
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection.jsp";
		}
			break;
		case "/recently_viewed":
		{
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, "recently_viewed");
			
			request.setAttribute("collection", "recently_viewed");
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection.jsp";
		}
			break;
		case "/orders":
			nextPage = "myPage/myPage_orders.jsp";
			break;
		case "/reviewWrite":
			nextPage = "myPage/myPage_review.jsp";
			break;
		case "/insertReview":
		{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int star = Integer.parseInt(request.getParameter("star"));
			ReviewVO vo = new ReviewVO(1, id, star, title, content);
			String state = myPageService.insertReview(vo);
			
			response.setContentType("text/plain");
			
			PrintWriter out = response.getWriter();
			
			out.write(state);
			out.close();
		}
			return;
		case "/update":
		{
			nextPage = "myPage/myPage_update.jsp";
			MemberVO member = memberService.getMember(id);

			request.setAttribute("member", member);
		}
			break;
		case "/updatePhone":
		{
			nextPage = "myPage/myPage_update_phone.jsp";
		}
		break;
		case "/updatePhoneAction":
		{
			nextPage = "myPage/myPage_update.jsp";
			
			String phone = request.getParameter("phone");
			
			String state = myPageService.updatePhone(id, phone);
			
			MemberVO member = memberService.getMember(id);
			
			request.setAttribute("state", state);
			request.setAttribute("member", member);
		}
		break;
		case "/updatePw":
		{
			nextPage = "myPage/myPage_update_pw.jsp";
		}
		break;
		case "/updatePwAction":
		{
			nextPage = "myPage/myPage_update.jsp";
			
			String pw = request.getParameter("pw");

			String state = myPageService.updatePw(id, pw);
			
			MemberVO member = memberService.getMember(id);

			request.setAttribute("state", state);
			request.setAttribute("member", member);
		}
		break;
		default: 
		{
			MemberVO member = memberService.getMember(id);
			Map<String, Integer> totCount = myPageService.getTotCount();
			
			request.setAttribute("member", member);
			request.setAttribute("totCount", totCount);
		}
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
