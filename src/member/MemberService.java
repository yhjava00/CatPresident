package member;

import vo.MemberVO;

public class MemberService {
private static final MemberService memberService = new MemberService();
	
	private static MemberDAO memberDAO;
	
	private MemberService() {
		memberDAO = MemberDAO.getMemberDAO();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}
	
	public int checkId(String id) {
		int a =memberDAO.checkId(id);
		return a;
	}
	public int login(String id, String pw) {
		int result=memberDAO.login(id, pw);

		return result;
//		로그인
//		-2:아이디없음
//		-1:서버오류
//		0:비밀번호틀림
//		1:성공
	}
	public void insertMember(MemberVO mVo) {
		memberDAO.insertMember(mVo);
	}
}
