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
import javax.websocket.Session;

import vo.ChattingVO;
import vo.ReviewVO;

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
			request.setAttribute("detailsMap", mainService.details(param,idx));
			request.setAttribute("idx", param.get("idx"));
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
			request.setAttribute("searchListMap",mainService.search(param,(String) param.get("keyword")));
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
		default :
			request.setAttribute("goodsListMap", mainService.main());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
