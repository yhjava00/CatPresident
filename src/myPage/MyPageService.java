package myPage;

public class MyPageService {

	private static final MyPageService myPageService = new MyPageService();
	
	private MyPageDAO myPageDAO;
	
	private MyPageService() {
		myPageDAO = MyPageDAO.getMyPageDAO();
	}
	
	public static MyPageService getMyPageService() {
		return myPageService;
	}
	
}
