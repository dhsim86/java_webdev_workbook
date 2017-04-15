package Lesson05;

import java.util.List;

import javax.sql.DataSource;

/**
 * Created by Dongho on 2017. 3. 11..
 */
public interface MemberDao {
	
	public void setDataSource(DataSource dataSource);
	
	List<Member> selectList() throws Exception;
	public int insert(Member member) throws Exception;
	public int delete(int no) throws Exception;
	public Member selectOne(int no) throws Exception;
	public int update(Member member) throws Exception;
	public Member exist(String email, String password) throws Exception;
}