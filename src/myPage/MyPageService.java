package myPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.GoodsVO;
import vo.ReviewVO;

public class MyPageService {

	private static final MyPageService myPageService = new MyPageService();
	
	private MyPageDAO myPageDAO;
	
	private MyPageService() {
		myPageDAO = MyPageDAO.getMyPageDAO();
	}
	
	public static MyPageService getMyPageService() {
		return myPageService;
	}
	
	public Map<String, Integer> getTotCount() {
		Map<String, Integer> totCount = new HashMap<String, Integer>();
		
		return totCount;
	}
	
	public List<GoodsVO> getCollectionList(String id, String type) {

		Map<String, String> info = new HashMap<>();
		
		info.put("id", id);
		info.put("type", type);
		
		return myPageDAO.selectCollectionList(info);
	}

	public String insertReview(ReviewVO vo) {
		int state = myPageDAO.insertReview(vo);
		if (state == 0) {
			return "error";
		}
		return "success";
	}
	
	public String updatePhone(String id, String phone) {

		Map<String, String> info = new HashMap<>();
		
		info.put("id", id);
		info.put("phone", phone);
		
		int state = myPageDAO.updatePhone(info);
		
		if(state!=1) 
			return "error";
		
		return "success";
	}
	
	public String updatePw(String id, String pw) {
		
		Map<String, String> info = new HashMap<>();
		
		info.put("id", id);
		info.put("pw", pw);
		
		int state = myPageDAO.updatePw(info);
		
		if(state!=1) 
			return "error";
		
		return "success";
	}
	
}
