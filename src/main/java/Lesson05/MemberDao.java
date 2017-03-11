package Lesson05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dongho on 2017. 3. 11..
 */
public class MemberDao {

    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Member> selectList() throws Exception {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                "select mno, mname, email, cre_date" +
                " from members" +
                " order by mno asc"
            );

            List<Member> memberList = new ArrayList<Member>();

            while(rs.next()) {
                memberList.add(new Member()
                    .setNo(rs.getInt("mno"))
                    .setName(rs.getString("mname"))
                    .setEmail(rs.getString("email"))
                    .setCreatedDate(rs.getDate("cre_date"))
                );
            }

            return memberList;
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }

    public int insert(Member member) throws Exception {

        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(
                "insert into members(email, pwd, mname, cre_date, mod_date)" +
                " values(?, ?, ?, now(), now())"
            );
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            int result = stmt.executeUpdate();

            return result;
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }

    public int delete(int no) throws Exception {

        PreparedStatement stmt = null;

        try {

            stmt = connection.prepareStatement(
                "delete from members" +
                " where mno = ?"
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
        }
    }

    public Member selectOne(int no) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = connection.prepareStatement(
                "select mno, mname, email, cre_date" +
                " from members" +
                " where mno = ?"
            );
            stmt.setInt(1, no);

            rs = stmt.executeQuery();
            rs.next();

            Member member = new Member()
                .setNo(rs.getInt("mno"))
                .setName(rs.getString("mname"))
                .setEmail(rs.getString("email"))
                .setCreatedDate(rs.getDate("cre_date"));

            return member;
        }
        catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            throw e;
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }

    public int update(Member member) throws Exception {

        PreparedStatement stmt = null;

        try {

            stmt = connection.prepareStatement(
                "update members set email = ?, mname = ?, mod_date = now()" +
                " where mno = ?"
            );
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getName());
            stmt.setInt(3, member.getNo());

            int result = stmt.executeUpdate();
            return result;
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }

    public Member exist(String email, String password) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = connection.prepareStatement(
                " select mno, mname, email, cre_date" +
                " from members" +
                " where email = ? and pwd = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {

                Member member = new Member()
                    .setNo(rs.getInt("mno"))
                    .setName(rs.getString("mname"))
                    .setEmail(rs.getString("email"))
                    .setCreatedDate(rs.getDate("cre_date"));

                return member;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }
}
