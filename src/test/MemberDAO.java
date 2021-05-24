package test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {

	private SqlSessionFactory sqlSessionFactory;
	
	public MemberDAO() {
		String resource = "config/config.xml";
        
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> selectMember() {
		List<MemberVO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
 
        try {
            list = session.selectList("member.selectAll");
        } finally {
            session.close();
        }
 
        return list;
	}
	
}
