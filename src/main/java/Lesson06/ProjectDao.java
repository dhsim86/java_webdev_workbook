package Lesson06;

import java.util.List;

import javax.sql.DataSource;

public interface ProjectDao {
	
	public void setDataSource(DataSource dataSource);
	
	List<Project> selectList() throws Exception;
	public int insert(Project project) throws Exception;
	public int delete(int no) throws Exception;
	public Project selectOne(int no) throws Exception;
	public int update(Project project) throws Exception;
}
