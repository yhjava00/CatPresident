package myPage;

import org.apache.ibatis.session.SqlSessionFactory;

import common.MySqlSessionFactory;

public class MyPageDAO {

	private static final MyPageDAO myPageDAO = new MyPageDAO();
	
	private SqlSessionFactory sqlSessionFactory;
	
	private MyPageDAO() {
		sqlSessionFactory = MySqlSessionFactory.getSqlSessionFactory();
	}
	
	public static MyPageDAO getMyPageDAO() {
		return myPageDAO;
	}
	
}
