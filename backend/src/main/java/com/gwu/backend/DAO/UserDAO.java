package com.gwu.backend.DAO;

import com.gwu.backend.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public User findByMail(String mail){
        User result = new User();
        String sql = "SELECT * FROM user WHERE user_mail = ?";
        jdbcTemplate.query(sql, new Object[]{mail}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setUser_id(rs.getInt("user_id"));
                result.setUser_name(rs.getString("user_name"));
                result.setUser_mail(mail);
                result.setUser_pwd(rs.getString("user_pwd"));
                result.setUser_state(rs.getInt("user_state"));
                result.setUser_risk(rs.getInt("user_risk"));
            }
        });
        return result;
    }

    public User findByID(int id){
        User result = new User();
        String sql = "SELECT * FROM user WHERE user_id = ?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setUser_id(id);
                result.setUser_name(rs.getString("user_name"));
                result.setUser_mail(rs.getString("user_mail"));
                result.setUser_pwd(rs.getString("user_pwd"));
                result.setUser_state(rs.getInt("user_state"));
                result.setUser_risk(rs.getInt("user_risk"));
            }
        });
        return result;
    }

    public boolean addUser(User user){

        return true;
    }

    public boolean editUser(User user){

        return true;
    }

    public boolean deleteUser(String mail){

        return true;
    }
}
