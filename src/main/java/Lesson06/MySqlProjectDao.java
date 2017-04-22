package Lesson06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {

	DataSource dataSource;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Project> selectList() throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			res = stmt.executeQuery(
				"select pno, pname, content, sta_date, end_date, state, cre_date, " +
				"end_date, state, cre_date, tags " +
				"from projects " +
				"order by pno desc"
			);
			
			List<Project> projectList = new ArrayList<>();
			
			while(res.next()) {
				
				projectList.add(new Project()
						.setNo(res.getInt("pno"))
						.setTitle(res.getString("pname"))
						.setContent(res.getString("content"))
						.setStartDate(res.getDate("sta_date"))
						.setEndDate(res.getDate("end_date"))
						.setState(res.getInt("state"))
						.setCreatedDate(res.getDate("cre_date"))
						.setTags(res.getString("tags"))
				);
			}
			
			return projectList;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
            try { if (res != null) res.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}

            try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	@Override
	public int insert(Project project) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(
				"insert into projects(pname, content, sta_date, end_date, state, cre_date, tags)" +
				" values(?, ?, ?, ?, ?, now(), ?)"
			);
			
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, java.sql.Date.valueOf(project.getStartDate().toString()));
			stmt.setDate(4, java.sql.Date.valueOf(project.getEndDate().toString()));
			stmt.setInt(5,  project.getState());
			stmt.setString(6, project.getTags());
			
			int result = stmt.executeUpdate();
			
			return result;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	@Override
	public int delete(int no) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(
				"delete from projects" +
				" where pno = ?"
			);
			
			stmt.setInt(1, no);
			
			int result = stmt.executeUpdate();
			return result;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	@Override
	public Project selectOne(int no) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		
		try {
			
			connection = dataSource.getConnection();
			stmt = connection.prepareStatement(
				"select pno, pname, content, sta_date, end_date, state, cre_date, " +
				"end_date, state, cre_date, tags " +
				"from projects " +
				" where pno = ?"
			);
			stmt.setInt(1, no);
			
			res = stmt.executeQuery();
			res.next();
			
			Project project = new Project()
				.setNo(res.getInt("pno"))
				.setTitle(res.getString("pname"))
				.setContent(res.getString("content"))
				.setStartDate(res.getDate("sta_date"))
				.setEndDate(res.getDate("end_date"))
				.setState(res.getInt("state"))
				.setCreatedDate(res.getDate("cre_date"))
				.setTags(res.getString("tags"));
			
			return project;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
            try { if (res != null) res.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}

            try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	@Override
	public int update(Project project) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			stmt = connection.prepareStatement(
				"update projects set pname = ?, content = ?, sta_date = ?, end_date = ?, tags = ?" +
				" ,state = ?" +
				" where pno = ?"
			);
			
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, java.sql.Date.valueOf(project.getStartDate().toString()));
			stmt.setDate(4, java.sql.Date.valueOf(project.getEndDate().toString()));
			stmt.setString(5, project.getTags());
			stmt.setInt(6, project.getState());
			stmt.setInt(7, project.getNo());
			
			int result = stmt.executeUpdate();
			return result;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
}
