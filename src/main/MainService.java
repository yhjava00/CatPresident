package main;

public class MainService {

	private static final MainService mainService = new MainService();
	
	private MainDAO mainDAO;
	
	private MainService() {
		mainDAO = MainDAO.getMainDAO();
	}
	
	public static MainService getMainService() {
		return mainService;
	}
	
}
