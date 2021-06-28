package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberService;

@WebServlet("/")
public class StartPage extends HttpServlet {
	private MemberService memberService;
	@Override
	public void init() throws ServletException {
		memberService = MemberService.getMemberService();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberService.autologin(request.getSession(), request.getCookies());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/common/baseLayout.jsp");
		dispatcher.forward(request, response);
	}
}
