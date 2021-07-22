package main;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.BasketVO;
import vo.ChattingVO;
import vo.GoodsDetailsVO;
import vo.GoodsVO;
import vo.ReviewVO;

public class MainDAO {

	private static final MainDAO mainDAO = new MainDAO();
	
	private static SqlSessionFactory sqlSessionFactory;
	
	private MainDAO() {
		String resource = "resources/config/config.xml";
        
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MainDAO getMainDAO() {
		return mainDAO;
	}

	public GoodsVO selectGoods(int idx){
		GoodsVO goods = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			goods = session.selectOne("main.selectGoods", idx);
		}finally {
			session.close();
		}
		return goods;
	}
	
	public List<GoodsVO> scoreDescList(){//별점 높은순으로 메인에 뿌려줌
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.scoreDescList");
		}finally {
			session.close();
		}
		return list;		
	}
	
	public List<GoodsVO> voteNumDescList() {//투표 많은순으로 메인에 뿌려줌
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.voteNumDescList");
		}finally {
			session.close();
		}
		return list;
	}
	
	public List<GoodsVO> mainRankList() {// 랭킹 : 판매량 순으로 9개 메인에 뿌림
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.goodsRank");
		}finally {
			session.close();
		}
		return list;
	}
	
	public List<GoodsDetailsVO> goodsDetails(Map param){// 상품 상세 정보 페이지
		List<GoodsDetailsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.goodsDetails",param);
		}finally {
			session.close();
		}		
		return list;
	}

	public List<String> goodsImgList(int idx){// 상품 상세 정보 페이지 이미지 5장
		List<String> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.DetailsImg",idx);
		}finally {
			session.close();
		}			
		return list;
	}
	
	public void insertLike(Map param) {// 상품 좋아요 
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("insertLike", param);
		session.commit();
		session.close();
	}
	
	public void deleteLike(Map param) {// 상품 좋아요 취소 
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("deleteLike", param);
		session.commit();
		session.close();
	}
	
	public List<GoodsVO> searchResult(Map keywordMap) {
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.selectSearch",keywordMap);
		}finally {
			session.close();
		}
		return list;
	}
	
	public int searchCount(String keyword) {// 총 검색된 결과 수
		int count;
		SqlSession session = sqlSessionFactory.openSession();		
		try {
			 count = session.selectOne("main.selectSearchCount", keyword);
		}finally {
			session.close();
		}
		return count;
		
	}
	
	public void insertRecently(Map param) {// 최근 본 insert
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("main.insertRecently", param);
		session.commit();
		session.close();
	}
	
	public void deleteRecently(Map param) {// 최근 본 delete
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("main.deleteRecently", param);
		session.commit();
		session.close();
	}
	
	public String selectRecently(Map param) {// 최근에 본 입력 전 db에 있는 데이터인지 체크위해 select 		
		String result;
		SqlSession session = sqlSessionFactory.openSession();		
		try {
			 result = session.selectOne("main.selectRecently", param);
		}finally {
			session.close();
		}
		return result;
	}
	
	public List<GoodsVO> descList(Map param) { //
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.selectSearchDesc",param);
		}finally {
			session.close();
		}
		return list;
	}
	
	public int allGoodsCount() { //
		int count;
		SqlSession session = sqlSessionFactory.openSession();		
		try {
			 count = session.selectOne("main.selectAllGoodsCount");
		}finally {
			session.close();
		}
		return count;		
	}

	public List<ReviewVO> ReviewInProduct(int goods_idx) {	//////	
		List<ReviewVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.selectReviewInProduct",goods_idx);
		}finally {
			session.close();
		}	
		return list;
	}
	
	public int insertChatting(Map<String, Object> info){
		int state = 0;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			state = session.insert("main.insertChatting", info);
			session.commit();
		}finally {
			session.close();
		}
		
		return state;
	}
	
	public List<ChattingVO> selectChattingList(String id){
		List<ChattingVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.selectChattingList", id);
		}finally {
			session.close();
		}
		
		return list;
	}
	
	public int insertbasket(Map param) {//////
		int insertResult = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			insertResult = session.insert("main.insertBasket", param);
			session.commit();
		} finally {
			session.close();
		}
		return insertResult;
	}
	
	public String checkbasket(Map param) { ////
		String checkResult = "";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			checkResult = session.selectOne("main.checkInbasket", param);
		}finally {
			session.close();
		}
		return checkResult;
	}
	
	public int delbasket(int idx) { /////
		int delResult = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			delResult = session.delete("main.delBasket", idx);
			session.commit();
		}finally {
			session.close();
		}
		return delResult;
	}
	
	public List<ReviewVO> showMainReview(){
		List<ReviewVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
	    try {
	        list = session.selectList("main.selectMainReview");
	        
	    } finally {
	        session.close();
	    }
	    return list;
	}
	
	public List<BasketVO> selectbasket(String id) {
		List<BasketVO> list = null;
    SqlSession session = sqlSessionFactory.openSession();
    try {
        list = session.selectList("main.selectBasket", id);
        
    } finally {
        session.close();
    }
    return list;
}

public int minusBasket(Map<String, Object> info){
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.update("main.minusBasket", info);
		session.commit();
	} finally {
		session.close();
	}
	return state;
}

public int plusBasket(Map<String, Object> info){
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.update("main.plusBasket", info);
		session.commit();
	} finally {
		session.close();
	}
	return state;
}

public int deleteBasket(Map<String, Object> info) {
	int state = 0;
	
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.delete("main.deleteBasket", info);
		session.commit();
	} finally{
		session.close();
	}
	
	return state;
}

public List<BasketVO> quantityCheck(Map<String, Object> info) {
	List<BasketVO> list = null;
	
	SqlSession session = sqlSessionFactory.openSession();
	try {
		list = session.selectList("main.quantityCheck", info);
	} finally {
		session.close();
	}
	return list;
}

public int insertOrder(Map<String, Object> orderList){
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.insert("main.insertOrder", orderList);
		session.commit();
		} finally {
		session.close();
	}
	return state;
}

public int insertAvailableReviews(Map<String, Object> info){
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.insert("main.insertAvailableReviews", info);
		session.commit();
	} finally {
		session.close();
	}
	return state;
}

public int updateGoodsQuantity(Map<String, Object> info) {
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.update("main.updateGoodsQuantity", info);
		session.commit();
	} finally {
		session.close();
	}
	return state;
}

public int clearBasket(String id) {
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.delete("main.clearBasket", id);
		session.commit();
	} finally {
		session.close();
	}
	return state;
	}

public int updateMemberInfo(Map<String, Object> memberInfoList) {
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.update("main.updateMemberInfo", memberInfoList);
		session.commit();
	} finally {
		session.close();
	}
	return state;
}

public int ReviewCnt() {
	int reviewCnt = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		reviewCnt = session.selectOne("main.ReviewCnt");
	} finally {
		session.close();
	}
	return reviewCnt;
}
public static List<ReviewVO> paging(Map page) {
	List<ReviewVO>list = null;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		list = session.selectList("main.paging", page);		
	} finally {
		session.close();
	}
	return list;
}

public static List<ReviewVO> showReview(){
	List<ReviewVO>reviewList = null;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		reviewList = session.selectList("main.showReview");
	} finally {
		session.close();
	}
	return reviewList;
}
public List<GoodsVO> selectDirectGoods(int idx) {
	List<GoodsVO> list = null;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		list = session.selectList("main.selectOrder", idx);
	} finally {
		session.close();
	}
	return list;
}

public int directInsertOrder(Map<String, Object> directOrderMap){
	int state = 0;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		state = session.insert("main.directInsertOrder", directOrderMap);
		session.commit();
		} finally {
		session.close();
	}
	return state;
}

}
