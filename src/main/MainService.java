package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.BasketVO;
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
		List<GoodsVO> goodsList3 = mainDAO.mainRankList();
		List<ReviewVO> reviewList = mainDAO.showMainReview();
		info.put("goodsList1", goodsList1);
		info.put("goodsList2", goodsList2);
		info.put("goodsList3", goodsList3);
		info.put("reviewList", reviewList);
		
		return info;
	}

	
	public Map<String , Object> details(Map param){// 상품 상세
		
		Map<String, Object> info = new HashMap<String, Object>();
		
		List<GoodsDetailsVO> detail = mainDAO.goodsDetails(param);
		List<String> imgList = mainDAO.goodsImgList((Integer)param.get("idx"));
		List<ReviewVO> reviewList = mainDAO.ReviewInProduct((Integer)param.get("idx"));
		info.put("reviewList", reviewList);
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
	public Map<String, Object> search(Map keywordMap){ //search		
		Map<String, Object> info = new HashMap<String, Object>();
		List<GoodsVO> searchList = null;
		int count;
		
		if(keywordMap.get("keyword").equals("(score/vote_num)") || keywordMap.get("keyword").equals("vote_num") 
				|| keywordMap.get("keyword").equals("quantity")) {
			searchList = mainDAO.descList(keywordMap);
			count = mainDAO.allGoodsCount();
		}else {
			searchList = mainDAO.searchResult(keywordMap);
			count = mainDAO.searchCount((String) keywordMap.get("keyword"));
		}		
		int pageCount = (count%10==0 ? count/10 : (count/10)+1);
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
	
	public List<BasketVO> basket(String id) {
		return mainDAO.selectbasket(id);
	}
	
	public int insertbasket(Map param) {/////////////////////
		String check = mainDAO.checkbasket(param);
		if(check == null) {
		}
		else {
			mainDAO.delbasket(Integer.parseInt(check));
		}
		return mainDAO.insertbasket(param);		
	}

	public String updatebasket(String id, String type, int idx){
	
	Map<String, Object> info = new HashMap<>();
	info.put("id", id);
	info.put("idx", idx);
	
	int state = 0;
	List<BasketVO> list = mainDAO.quantityCheck(info);
	for(BasketVO li : list) {
	int quantity =0; 
		quantity = li.getQuantity();
	if(type.equals("minus")) {
		if(quantity>1) {
			state = mainDAO.minusBasket(info);				
		}
	}else
		state = mainDAO.plusBasket(info);
	}
	if(state!=1) {
		return "error";
	}
	
	return "success";
}

public String deleteBasket(String id, int idx){
	int state = 0;
	Map<String, Object> info = new HashMap<>();
	info.put("id", id);
	info.put("idx", idx);
	
	state = mainDAO.deleteBasket(info);
	if(state == 0) {
		return "error";
	}
	return "success";
}
}
