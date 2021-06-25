package myPage;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.GoodsCollectionVO;
import vo.GoodsVO;
import vo.OrderVO;
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
	
	public List<OrderVO> selectOrderList(Map<String, Object> info) {
		SqlSession session = sqlSessionFactory.openSession();
		List<OrderVO> orderList = null;
		
		try {
			orderList = session.selectList("myPage.selectOrderList", info); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            session.close();
        }
		
		return orderList;
	}

	public int totCollection(String id, String type) {
		
		Map<String, Object> info = new HashMap<String, Object>();

		info.put("id", id);
		info.put("type", type);
		
		int tot = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			tot = session.selectOne("myPage.totCollection", info);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return tot;
	}
	
	public int totMyReview(String id) {
		int tot = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			tot = session.selectOne("myPage.totMyReview", id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return tot;
	}
	
	public List<GoodsVO> selectCollectionList(Map<String, Object> info) {
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
	
	public List<GoodsVO> delAfterFoundCollection(Map<String, Object> info) {
		SqlSession session = sqlSessionFactory.openSession();
		List<GoodsVO> collectionList = null;
		
		try {
			collectionList = session.selectList("myPage.delAfterFoundCollection", info); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return collectionList;
	}
	
	public List<GoodsVO> selectMyReviewList(Map<String, Object> info) {
		SqlSession session = sqlSessionFactory.openSession();
		List<GoodsVO> collectionList = null;
		
		try {
			collectionList = session.selectList("myPage.selectMyReviewList", info); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return collectionList;
	}
	
	public GoodsCollectionVO selectCollection(int idx) {
		SqlSession session = sqlSessionFactory.openSession();
		GoodsCollectionVO goodsCollection = null;
		
		try {
			goodsCollection = session.selectOne("myPage.selectCollection", idx); 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return goodsCollection;
	}
	
	public int deleteCollection(int goodsIdx, String memberId, String type) {
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("goodsIdx", goodsIdx);
		info.put("memberId", memberId);
		info.put("type", type);
		
		SqlSession session = sqlSessionFactory.openSession();
		int state = 0;
		
		try {
			state = session.delete("myPage.deleteCollection", info);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return state;
	}

	public int insertReview(ReviewVO vo) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.insert("myPage.insertReview", vo);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return state;
	}
	
	public int updateGoodsStar(ReviewVO vo) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.update("myPage.updateGoodsStar", vo);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return state;
	}
	
	public int updateGoodsStarOnly(ReviewVO vo) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.update("myPage.updateGoodsStarOnly", vo);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return state;
	}
	
	public int updateReview(ReviewVO vo) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.update("myPage.updateReview", vo);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return state;
	}
	
	public ReviewVO selectReview(int idx) {
		ReviewVO review = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			review = session.selectOne("myPage.selectReview", idx);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}	
		return review;
	}
	
	public int updatePhone(Map<String, String> info) {
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			state = session.update("myPage.updatePhone", info);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
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
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return state;
	}
	
}
