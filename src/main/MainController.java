package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberService;
import vo.BasketVO;
import vo.ChattingVO;
import vo.MemberVO;

@WebServlet("*.main")
public class MainController extends HttpServlet {
	
	private MainService mainService;
	private MemberService memberService;
	
	@Override
	public void init() throws ServletException {
		mainService = MainService.getMainService();
		memberService = MemberService.getMemberService();
	}
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		String nextPage = "main.jsp";
		
		String id = (String) request.getSession().getAttribute("loginUser");
		
		switch (path) {
		case "/header":
			nextPage = "common/header.jsp";
			break;
		case "/product": //수정 됨
		{				
			Map<String, Object> param = new HashMap<String, Object>();
			int idx = Integer.parseInt(request.getParameter("idx"));
			param.put("idx", idx);
			param.put("id", id);
			request.setAttribute("detailsMap", mainService.details(param));
			request.setAttribute("idx", idx);
			mainService.recently(param); //최근 본 추가
			nextPage = "product.jsp";
		}
			break;
			
		case "/search": //검색
		{	
			Map<String, Object> param = new HashMap<String, Object>();	
			String keyword = request.getParameter("keyword");
			int page = Integer.parseInt(request.getParameter("page"));
			param.put("keyword", keyword);
			param.put("page", page);
			request.setAttribute("searchListMap",mainService.search(param));
			request.setAttribute("keyword", keyword);
			request.setAttribute("selectedPage", page);
			request.setAttribute("startPage", ((page-1)/5)*5+1);	
			nextPage = "search.jsp";
		}
			break;	
		case "/like": //좋아요
		{	
			if(id == null) 
				return;
			Map<String, Object> param = new HashMap<String, Object>();
			String like_id = request.getParameter("id");
			int like_idx = Integer.parseInt(request.getParameter("idx"));
			param.put("like_id", like_id);
			param.put("like_idx", like_idx);
			mainService.like(param);
		}
			break;
			
		case "/unlike": //좋아요 취소
		{
			if(id == null) 
				return;
			Map<String, Object> param = new HashMap<String, Object>();
			String unlike_id = request.getParameter("id");
			int unlike_idx = Integer.parseInt(request.getParameter("idx"));
			param.put("unlike_id", unlike_id);
			param.put("unlike_idx", unlike_idx);
			mainService.unlike(param);
		}
			break;

		case "/chat":
			nextPage = "common/chat.jsp";
			
			List<ChattingVO> chatList = mainService.getChattingList(id);
			
			request.setAttribute("chatList", chatList);
			break;
		case "/reviews":
			nextPage = "reviews.jsp";
			break;
		case "/basket":
		{
			nextPage ="basket.jsp";
			List<BasketVO> list = mainService.basket(id);
			request.setAttribute("basketList", list);
			break;
		}
		
		case "/updateQty":
		{
			
			int idx = Integer.parseInt(request.getParameter("idx"));
			String type = request.getParameter("type");

			mainService.updatebasket(id, type, idx);
			
			List<BasketVO> list = mainService.basket(id);
			for(BasketVO vo : list) {
				System.out.println(vo.getQuantity());
			}
			request.setAttribute("basketList", list);

			nextPage = "basket.jsp";
			break;
		}	
		
		case "/deleteBasket":
		{
			int idx = Integer.parseInt(request.getParameter("idx"));
			mainService.deleteBasket(id, idx);
			List<BasketVO> list = mainService.basket(id); 
			request.setAttribute("basketList", list);
			
			nextPage ="basket.jsp";
			break;
		}
		
		case "/insertBasket": ////////
		{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("idx", Integer.parseInt(request.getParameter("idx")));
			param.put("quantity", Integer.parseInt(request.getParameter("quantity")));
			param.put("id", id);
			int insertResult = mainService.insertbasket(param);
			break;
		}
		case "/selectOrderList":
		{	
			System.out.println(id);
			MemberVO member = memberService.getMember(id);
			System.out.println(member.getAddr1());
			System.out.println(member.getName());
			request.setAttribute("addr1", member.getAddr1());
			request.setAttribute("addr2", member.getAddr2());
			request.setAttribute("addr3", member.getAddr3());
			List<BasketVO> list = mainService.basket(id);
			request.setAttribute("orderList", list);
			nextPage ="order.jsp";
			break;
		}
		
		case "/insertOrder":
		{
			String userid = id; 
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr1 = request.getParameter("addr1");
			String addr2 = request.getParameter("addr2");
			String addr3 = request.getParameter("addr3");
			String req = request.getParameter("req"); 
			String total = request.getParameter("total");
			String status = "배송준비";
			Map<String, Object> orderMap = new HashMap<String, Object>();
			List<BasketVO> list2 =mainService.basket(id); 
			for(BasketVO li : list2) {
				orderMap.put("idx",li.getIdx());
				orderMap.put("qty",li.getQuantity());
				orderMap.put("id",userid);
				orderMap.put("name",name);
				orderMap.put("phone",phone);
				orderMap.put("addr1",addr1);
				orderMap.put("addr2",addr2);
				orderMap.put("addr3",addr3);
				orderMap.put("req",req);
				orderMap.put("total",total);
				orderMap.put("status", status);
				mainService.insertOrder(orderMap);
			}
			request.setAttribute("orderList", orderMap);
			mainService.clearBasket(id);
			mainService.updateMemberInfo(orderMap);
			nextPage="order_result.jsp";
		}
		default :
			request.setAttribute("goodsListMap", mainService.main());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
