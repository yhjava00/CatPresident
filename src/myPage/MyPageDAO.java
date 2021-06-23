package myPage;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.GoodsVO;
import vo.ReviewVO;

public class MyPageDAO {

	private static final MyPageDAO myPageDAO = new MyPageDAO();

	private static SqlSessionFactory sqlSessionFactory;
	
	private MyPageDAO() {
		String resource = "resources/config/config.xml";
        
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MyPageDAO getMyPageDAO() {
		return myPageDAO;
	}
	
	public List<GoodsVO> selectCollectionList(Map<String, String> info) {
		SqlSession session = sqlSessionFactory.openSession();
		List<GoodsVO> collectionList = null;
		
		try {
			collectionList = session.selectList("myPage.selectCollectionList", info); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            session.close();
        }
		
		return collectionList;
	}

	public int insertReview(ReviewVO vo) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.insert("myPage.insertReview", vo);
			session.commit();
		} finally {
			session.close();
		}
		return state;
	}
	
	public int updatePhone(Map<String, String> info) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.update("myPage.updatePhone", info);
			session.commit();
		} finally {
			session.close();
		}
		return state;
	}
	
	public int updatePw(Map<String, String> info) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.update("myPage.updatePw", info);
			session.commit();
		} finally {
			session.close();
		}
		return state;
	}
	
}
