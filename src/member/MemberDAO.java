package member;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.MemberVO;




public class MemberDAO {
	
private static final MemberDAO memberDAO = new MemberDAO();
	
	private static SqlSessionFactory sqlSessionFactory;
	
	private MemberDAO() {
		String resource = "resources/config/config.xml";
        
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDAO getMemberDAO() {
		return memberDAO;
	}
	//아이디 중복check
	//1 중복된 아이디 없음
	//-1중복된 아이디
	//0 서버오류
	public int checkId(String id) {
		List<String> list=null;
		int result=1;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			list= session.selectList("member.check_id", id);
			for(int i=0; i<=list.size()-1; i++) {
				if(list.get(i).equals(id)) { 
					result=-1;
				}else {
					result=1;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            session.close();
        }
		return result;
	}

	public int login(String member_id, String member_pw) {
//		로그인
//		-2:아이디없음
//		-1:서버오류
//		0:비밀번호틀림
//		1:성공
		String pwd=null;
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			pwd=session.selectOne("member.login", member_id);
			
			if(pwd!=null) {
				return pwd.equals(member_pw)? 1:0;
			}else {
				return -2;
			}
	
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public MemberVO getMember(String id) {
		MemberVO mVo=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			mVo=(MemberVO)session.selectOne("member.getMember", id);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mVo;
	}
	
	public void insertMember(MemberVO mVo) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.insert("member.insertMember", mVo);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
