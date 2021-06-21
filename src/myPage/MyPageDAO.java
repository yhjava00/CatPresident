package myPage;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyPageDAO {

	private static final MyPageDAO myPageDAO = new MyPageDAO();

	private static SqlSessionFactory sqlSessionFactory;
	
	private MyPageDAO() {
		String resource = "resources/config/config.xml";
        
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MyPageDAO getMyPageDAO() {
		return myPageDAO;
	}
	
}
