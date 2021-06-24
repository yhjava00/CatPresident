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
	
	public GoodsVO getGoods(int idx) {
		return mainDAO.selectGoods(idx);
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
	
	public void recently(Map param) {
		if(param.get("id") == null) {
			return;
		}
				
		if(mainDAO.selectRecently(param) == null) {// db검색 결과 없으면
			mainDAO.insertRecently(param); // 입력
		}else if(mainDAO.selectRecently(param).equals("recently")) {// db검색 결과 최근 본 상품이 있으면
			mainDAO.deleteRecently(param); // 삭제 후
			mainDAO.insertRecently(param); // 다시 입력
		}
	}
	public Map<String, Object> search(Map keywordMap, String keyword){ //검색결과, 페이징
		
		Map<String, Object> info = new HashMap<String, Object>();
		List<GoodsVO> searchList = mainDAO.searchResult(keywordMap); //검색 상품 리스트
		int count = mainDAO.searchCount(keyword); //총 검색 상품 수
		int pageCount = (count%10==0 ? count/10 : (count/10)+1); //총 몇 페이지 나왔는지
		info.put("searchList", searchList);
		info.put("searchCount", count);
		info.put("pageCount", pageCount);	
		return info;		
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
