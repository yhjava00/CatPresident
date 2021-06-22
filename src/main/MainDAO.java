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

	public List<GoodsVO> mainGoodsList(int idx) {
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.goodsList",idx);
		}finally {
			session.close();
		}
		return list;
	}
	
	public List<GoodsVO> mainRankList(int idx) {
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.goodsRank",idx);
		}finally {
			session.close();
		}
		return list;
	}
	
	public List<GoodsVO> searchResult(String name){
		List<GoodsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.searchResult",name);
		}finally {
			session.close();
		}				
		return list;
	}
	
	public List<GoodsDetailsVO> goodsDetails(int idx){
		List<GoodsDetailsVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.goodsDetails",idx);
		}finally {
			session.close();
		}
		
		return list;
	}

	public List<String> goodsImgList(int idx){
		List<String> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			list = session.selectList("main.DetailsImg",idx);
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
	
}
