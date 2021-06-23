package main;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
	
	public List<GoodsVO> mainRankList(int idx) {// 랭킹 9가지 상품 임의로 뿌림
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.goodsRank",idx);
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
	
}
