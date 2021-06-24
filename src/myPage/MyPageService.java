package myPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.MainDAO;
import member.MemberDAO;
import vo.GoodsCollectionVO;
import vo.GoodsVO;
import vo.MemberVO;
import vo.ReviewVO;

public class MyPageService {

	private static final MyPageService myPageService = new MyPageService();
	
	private MyPageDAO myPageDAO;
	private MemberDAO memberDAO;
	private MainDAO mainDAO;
	
	private MyPageService() {
		myPageDAO = MyPageDAO.getMyPageDAO();
		memberDAO = MemberDAO.getMemberDAO();
		mainDAO = MainDAO.getMainDAO();
	}
	
	public static MyPageService getMyPageService() {
		return myPageService;
	}
	
	public Map<String, Object> myPageInfo(String id) {
		Map<String, Object> info = new HashMap<>();
		
		MemberVO member = memberDAO.getMember(id);
		
		int totOftenSeen = myPageDAO.totCollection(id, "oftenSeen");
		int totLike = myPageDAO.totCollection(id, "like");
		int totRecently = myPageDAO.totCollection(id, "recently");
		int totCanWriteReview = myPageDAO.totCollection(id, "review");
		int totWriteReview = myPageDAO.totMyReview(id);

		info.put("member", member);
		
		info.put("totOftenSeen", totOftenSeen);
		info.put("totLike", totLike);
		info.put("totRecently", totRecently);
		info.put("totCanWriteReview", totCanWriteReview);
		info.put("totWriteReview", totWriteReview);
		
		return info;
	}
	
	public List<GoodsVO> getCollectionList(String id, int page, String type) {

		Map<String, Object> info = new HashMap<>();
		
		info.put("id", id);
		info.put("page", page);
		info.put("type", type);
		
		return myPageDAO.selectCollectionList(info);
	}
	
	public List<GoodsVO> getWriteReviewList(String id, int page) {

		Map<String, Object> info = new HashMap<>();
		
		info.put("id", id);
		info.put("page", page);
		
		return myPageDAO.selectMyReviewList(info);
	}
	
	public String insertReview(ReviewVO vo, int reviewIdx) {
		
		GoodsCollectionVO goodsCollection = myPageDAO.selectCollection(reviewIdx);
		
		if(!goodsCollection.getMember_id().equals(vo.getMember_id())||goodsCollection.getGoods_idx()!=vo.getGoods_idx()) {
			return "error";
		}
		
		int state = myPageDAO.insertReview(vo);
		if (state == 0) {
			return "error";
		}
		
		myPageDAO.deleteCollection(reviewIdx);
		
		return "success";
	}
	
	public String updateReview(ReviewVO review) {
		int state = myPageDAO.updateReview(review);
		
		if (state == 0) {
			return "error";
		}
		
		return "success";
	}
	
	public Map<String, Object> reviewModifyForm(int reveiwIdx) {
		Map<String, Object> info = new HashMap<>();
		
		ReviewVO review = myPageDAO.selectReview(reveiwIdx);
		GoodsVO goods = mainDAO.selectGoods(review.getGoods_idx());
		
		info.put("review", review);
		info.put("goods", goods);
		
		return info;
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
