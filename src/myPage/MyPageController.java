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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import main.MainService;
import member.MemberService;
import vo.GoodsVO;
import vo.MemberVO;
import vo.OrderVO;
import vo.ReviewVO;

@WebServlet("*.myPage")
public class MyPageController extends HttpServlet {
	
	private MyPageService myPageService;
	
	private MemberService memberService;
	
	private MainService mainService;
	
	@Override
	public void init() throws ServletException {
		myPageService = MyPageService.getMyPageService();
		memberService = MemberService.getMemberService();
		mainService = MainService.getMainService();
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
		{
			nextPage = "myPage/myPage_available_reviews.jsp";
			
			int page = 1;
			
			List<GoodsVO> canWriteReviewList = myPageService.getCollectionList(id, page, "review");
			
			request.setAttribute("canWriteReviewList", canWriteReviewList);
		}
			break;
		case "/more_available_reviews":
		{
			int page = Integer.parseInt(request.getParameter("page"));
			
			List<GoodsVO> canWriteReviewList = myPageService.getCollectionList(id, page, "review");
			
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			for(GoodsVO goods : canWriteReviewList) {
				JSONObject obj = new JSONObject();
				
				obj.put("idx", goods.getIdx());
				obj.put("reviewIdx", goods.getReviewIdx());
				obj.put("regDate", goods.getRegDate());
				obj.put("img", goods.getImg());
				obj.put("name", goods.getName());
				obj.put("price", goods.getPrice());
				
				jsonArray.add(obj);
				
			}
			
			json.put("canWriteReviewList", jsonArray);
			
			response.setContentType("application/json;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.print(json);
	        out.close();
		}
			return;
		case "/writeReviews":
		{
			nextPage = "myPage/myPage_write_reviews.jsp";

			int page = 1;
			
			List<GoodsVO> writeReviewList = myPageService.getWriteReviewList(id, page);
			
			request.setAttribute("writeReviewList", writeReviewList);
		}
			break;
		case "/moreWriteReviews":
		{
			int page = Integer.parseInt(request.getParameter("page"));
			
			List<GoodsVO> writeReviewList = myPageService.getWriteReviewList(id, page);
			
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			for(GoodsVO goods : writeReviewList) {
				JSONObject obj = new JSONObject();
				
				obj.put("idx", goods.getIdx());
				obj.put("reviewIdx", goods.getReviewIdx());
				obj.put("regDate", goods.getRegDate());
				obj.put("img", goods.getImg());
				obj.put("name", goods.getName());
				obj.put("price", goods.getPrice());
				
				jsonArray.add(obj);
				
			}
			
			json.put("writeReviewList", jsonArray);
			
			response.setContentType("application/json;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.print(json);
	        out.close();
		}
			return;
		case "/often_seen":
		{	
			int page = 1;
			
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, page, "often_seen");
			
			request.setAttribute("collection", "often_seen");
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection.jsp";
		}
			break;
		case "/like":
		{
			int page = 1;
			
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, page, "like");
			
			request.setAttribute("collection", "like");
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection.jsp";
		}
			break;
		case "/recently_viewed":
		{
			int page = 1;
			
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, page, "recently");
			
			request.setAttribute("collection", "recently");
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection.jsp";
		}
			break;
		case "/moreCollection":
		{
			int page = Integer.parseInt(request.getParameter("page"));
			String type = request.getParameter("type");
			
			List<GoodsVO> collectionList = myPageService.getCollectionList(id, page, type);
			
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection_more.jsp";
		}
		break;
		case "/delCollection":
		{
			int page = Integer.parseInt(request.getParameter("page"));
			int goodsIdx = Integer.parseInt(request.getParameter("goodsIdx"));
			String type = request.getParameter("type");
			
			List<GoodsVO> collectionList = myPageService.deleteCollection(goodsIdx, id, type, page);
			
			request.setAttribute("collectionList", collectionList);
			nextPage = "myPage/myPage_collection_more.jsp";
		}
		break;
		case "/orders":
		{
			nextPage = "myPage/myPage_orders.jsp";
			
			int page = 1;
			
			List<OrderVO> orderList = myPageService.getOrderList(id, page);
			
			request.setAttribute("orderList", orderList);
		}
			break;
		case "/moreOrders":
			nextPage = "myPage/myPage_orders_more.jsp";
			
			int page = Integer.parseInt(request.getParameter("page"));
			
			List<OrderVO> orderList = myPageService.getOrderList(id, page);
			
			request.setAttribute("orderList", orderList);
			break;
		case "/reviewWriteForm":
		{
			nextPage = "myPage/myPage_review.jsp";
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			int ReviewIdx = Integer.parseInt(request.getParameter("reviewIdx"));
			String regDate = request.getParameter("regDate");
			
			GoodsVO goods = mainService.getGoods(idx);
			
			request.setAttribute("goods", goods);
			request.setAttribute("ReviewIdx", ReviewIdx);
			request.setAttribute("regDate", regDate);
		}
			break;
		case "/reviewModifyForm":
		{
			nextPage = "myPage/myPage_review_modify.jsp";
			
			int reviewIdx = Integer.parseInt(request.getParameter("reviewIdx"));

			Map<String, Object> info = myPageService.reviewModifyForm(reviewIdx);
			
			request.setAttribute("info", info);
		}
		break;
		case "/insertReview":
		{
			int idx = Integer.parseInt(request.getParameter("idx"));
			int reviewIdx = Integer.parseInt(request.getParameter("reviewIdx"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int star = Integer.parseInt(request.getParameter("star"));
			
			ReviewVO vo = new ReviewVO(idx, id, star, title, content);
			
			String state = myPageService.insertReview(vo, reviewIdx);
			
			response.setContentType("text/plain");
			
			PrintWriter out = response.getWriter();
			
			out.write(state);
			out.close();
		}
			return;
		case "/updateReview":
		{
			int idx = Integer.parseInt(request.getParameter("idx"));
			int goodsIdx = Integer.parseInt(request.getParameter("goodsIdx")); 
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int star = Integer.parseInt(request.getParameter("star"));
			
			ReviewVO review = new ReviewVO(goodsIdx, id, star, title, content);

			review.setIdx(idx);
			
			String state = myPageService.updateReview(review);
			
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

			String state = myPageService.updatePw(id, memberService.encryptionPw(pw));
			
			MemberVO member = memberService.getMember(id);

			request.setAttribute("state", state);
			request.setAttribute("member", member);
		}
		break;
		default: 
		{
			Map<String, Object> info = myPageService.myPageInfo(id);
			
			request.setAttribute("info", info);
		}
			break;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
