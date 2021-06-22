package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.MemberVO;


@WebServlet("*.member")
public class MemberController extends HttpServlet {
	
	private MemberService memberService;
	
	@Override
	public void init() throws ServletException {
		memberService = MemberService.getMemberService();
	}
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();

		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));

		String nextPage = "main.jsp";
		
		switch (path) {
		case "/loginForm":
			nextPage = "member/login.jsp";
			break;
		case "/login":
			String loginId=request.getParameter("user_id");

			String loginPw=request.getParameter("user_pw");

			int loginRs= memberService.login(loginId, loginPw);
			if(loginRs==1) {
				HttpSession session=request.getSession();
				session.setAttribute("loginUser", loginId);
				if(loginId.equals("admin@gmail.com")) {
					session.setAttribute("connect", "lee@gmail.com");
				}
			}
			response.getWriter().write(loginRs + "");
			return;
		case "/logout":
			request.getSession().removeAttribute("loginUser");
			return;
		case "/Sign_upForm":
			nextPage = "member/Sign_up.jsp";
			break;
		case "/insertMember" :
			String userid=request.getParameter("user_id");
			String userpw=request.getParameter("user_pw");
			String username=request.getParameter("user_name");
			String userphone=request.getParameter("user_phone");
			MemberVO mVo=new MemberVO();
			mVo.setId(userid);
			mVo.setName(username);
			mVo.setPw(userpw);
			mVo.setPhone(userphone);
			memberService.insertMember(mVo);			
			return;
		case "/checkId" :
			String id=request.getParameter("user_id");
			int result=memberService.checkId(id);
			response.getWriter().write(result + "");
			return;
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	


}
