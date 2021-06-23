package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.ChattingVO;
import vo.GoodsDetailsVO;
import vo.GoodsVO;
import vo.ReviewVO;

public class MainService {

	private static final MainService mainService = new MainService();
	
	private MainDAO mainDAO;
	
	private MainService() {
		mainDAO = MainDAO.getMainDAO();
	}
	
	public static MainService getMainService() {
		return mainService;
	}
	
	public Map<String , Object> main(){ //메인에 뿌려줌
		
		Map<String, Object> info = new HashMap<String, Object>();
		
		List<GoodsVO> goodsList1 = mainDAO.scoreDescList(); //별점 높은순 리스트
		List<GoodsVO> goodsList2 = mainDAO.voteNumDescList(); //투표 인원 많은순 리스트
		List<GoodsVO> goodsList3 = mainDAO.mainRankList(13);

		info.put("goodsList1", goodsList1);
		info.put("goodsList2", goodsList2);
		info.put("goodsList3", goodsList3);
		
		return info;
	}
	
	public Map<String , Object> details(Map param ,int idx){// 상품 상세
		
		Map<String, Object> info = new HashMap<String, Object>();
		
		List<GoodsDetailsVO> detail = mainDAO.goodsDetails(param);
		List<String> imgList = mainDAO.goodsImgList(idx);
		info.put("details", detail);
		info.put("imgList", imgList);
		
		return info;
	}
		
	public void like(Map param) {//좋아요 
		mainDAO.insertLike(param);
	}
	
	public void unlike(Map param) {//좋아요 취소
		mainDAO.deleteLike(param);
	}
	
	public int insertChatting(String sender, String recipient, String content) {
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("sender", sender);
		info.put("recipient", recipient);
		info.put("content", content);
		
		return mainDAO.insertChatting(info);
	}
	
	public List<ChattingVO> getChattingList(String id) {
		return mainDAO.selectChattingList(id);
	}
}
