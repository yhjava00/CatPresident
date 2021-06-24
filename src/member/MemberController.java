package member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartFilter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import vo.MemberVO;


@WebServlet("*.member")
public class MemberController extends HttpServlet {
	
	private MemberService memberService;
	
	private static final String PROFILE_PATH = "C:\\lyh_java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CatPresident\\resources\\profile\\";
	
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
			}
			response.getWriter().write(loginRs + "");
			return;
		case "/logout":
			request.getSession().removeAttribute("loginUser");
			return;
		case "/withdrawal":
		{
			String id = (String) request.getSession().getAttribute("loginUser");
			memberService.withdrawal(id);
			request.getSession().removeAttribute("loginUser");
		}
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
			mVo.setProfile("profile-empty.svg");
			memberService.insertMember(mVo);			
			return;
		case "/checkId" :
		{
			String id=request.getParameter("user_id");
			int result=memberService.checkId(id);
			response.getWriter().write(result + "");
		}
			return;
		case "/upload_profile" :
			String id = (String) request.getSession().getAttribute("loginUser");
			String fileName = uploadProfile(request, id);
			PrintWriter out = response.getWriter();
			out.println(fileName);
			out.close();
			return;
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/view/" + nextPage);
		dispatcher.forward(request, response);
	}
	
	public String uploadProfile(HttpServletRequest request, String id) {

		int size = 1024 * 1024 * 10;
		String path = PROFILE_PATH + id;
		
		MemberVO member = memberService.getMember(id);
		
		File olderFile = new File(path, member.getProfile());
		
		olderFile.delete();
		
		String originalFileName = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());

			Enumeration files = multi.getFileNames();
	        String str = (String)files.nextElement();
	        originalFileName = multi.getOriginalFileName(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		memberService.changeProfile(id, originalFileName);
		
		return originalFileName;
	}
	


}
