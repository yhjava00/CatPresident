package main;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import common.MySqlSessionFactory;

public class MainDAO {

	private static final MainDAO mainDAO = new MainDAO();
	
	private SqlSessionFactory sqlSessionFactory;
	
	private MainDAO() {
		sqlSessionFactory = MySqlSessionFactory.getSqlSessionFactory();
	}
	
	public static MainDAO getMainDAO() {
		return mainDAO;
	}

	public List<String> selectMember() {
		List<String> list = null;
        SqlSession session = sqlSessionFactory.openSession();
 
        try {
            list = session.selectList("member.selectAll");
        } finally {
            session.close();
        }
 
        return list;
	}
	
}
