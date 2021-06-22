package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.ChattingVO;
import vo.GoodsDetailsVO;
import vo.GoodsVO;

public class MainService {

	private static final MainService mainService = new MainService();
	
	private MainDAO mainDAO;
	
	private MainService() {
		mainDAO = MainDAO.getMainDAO();
	}
	
	public static MainService getMainService() {
		return mainService;
	}
	
	public Map<String , Object> main(){
		
		Map<String, Object> info = new HashMap<String, Object>();
		
		List<GoodsVO> goodsList1 = mainDAO.mainGoodsList(1);
		List<GoodsVO> goodsList2 = mainDAO.mainGoodsList(7);
		List<GoodsVO> goodsList3 = mainDAO.mainRankList(13);
		
		info.put("goodsList1", goodsList1);
		info.put("goodsList2", goodsList2);
		info.put("goodsList3", goodsList3);
			
		return info;
	}
	
	public Map<String , Object> details(int idx){
		
		Map<String, Object> info = new HashMap();
		
		List<GoodsDetailsVO> detail = mainDAO.goodsDetails(idx);
		List<String> imgList = mainDAO.goodsImgList(idx);
		info.put("details", detail);
		info.put("imgList", imgList);
		
		return info;
	}
	
	public Map<String , Object> search(String name){
		Map<String, Object> info = new HashMap();
		
		List<GoodsVO> resultList = mainDAO.searchResult(name);
		info.put("result", resultList);
		
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
