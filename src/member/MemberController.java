package member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartFilter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import vo.MemberVO;


@WebServlet("*.member")
public class MemberController extends HttpServlet {
	
	private MemberService memberService;
	
	private static final String PROFILE_PATH = "C:\\java_study\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CatPresident\\resources\\profile\\";
	
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
			String checkbox=request.getParameter("rememberMe");
			String loginPw=request.getParameter("user_pw");
			int loginRs= memberService.login(loginId, memberService.encryptionPw(loginPw));
			if(loginRs==1) {
				HttpSession session=request.getSession();
				session.setAttribute("loginUser", loginId);
				if(checkbox.equals("true")) {
					Cookie c= new Cookie("loginUser",loginId);
					c.setMaxAge(365*24*60*60);
					response.addCookie(c);
				}else {
					Cookie c=new Cookie("loginUser","");
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
			response.getWriter().write(loginRs + "");
			return;
		case "/logout":
			request.getSession().removeAttribute("loginUser");
	         Cookie c=new Cookie("loginUser","");
	         c.setMaxAge(0);
	         response.addCookie(c);
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
			mVo.setPw(memberService.encryptionPw(userpw));
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
		case "/checkPhone" :
			String userPhone=request.getParameter("user_phone");
			int rs=memberService.checkPhone(userPhone);
			response.getWriter().write(rs+"");
			return;
		case "/search_idForm" :
			nextPage = "member/search_id.jsp";
			break;
		case "/search_id" :
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			MemberVO mVo2=memberService.search_id_rs(name, phone);
			JSONObject json = new JSONObject();
			if(mVo2==null) {
				json.put("id", "nothing");
				json.put("joindate", "nothing");
			}else {
				json.put("id", mVo2.getId());
				json.put("joindate", mVo2.getJoindate());
			}
			try {
				response.getWriter().write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		case "/search_id_rs" :
			nextPage = "member/search_id_rs.jsp";
			break;
		case "/search_pwForm" :
			nextPage="member/search_pw.jsp";
			break;
		case "/search_pw_rs" :
			String searchPw_id=request.getParameter("id");
			int searchPw=memberService.changePw(searchPw_id);
			response.getWriter().write(searchPw+"");
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
