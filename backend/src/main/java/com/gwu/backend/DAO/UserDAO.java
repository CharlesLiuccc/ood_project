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
            }
        });
        return result;
    }

    public boolean addUser(User user){
        if(findByMail(user.getUser_mail()).getUser_id()==-1){
            String sql = "INSERT INTO user(user_name,user_mail,user_pwd) VALUES(?,?,?)";
            jdbcTemplate.update(sql,user.getUser_name(),user.getUser_mail(),user.getUser_pwd());
            return true;
        }
        //already exist
        return false;
    }

    public boolean editUser(User user){
        String sql = "UPDATE user set user_name = ?, user_mail = ?, user_pwd = ? WHERE user_id = ?";
        jdbcTemplate.update(sql,user.getUser_name(),user.getUser_mail(),user.getUser_pwd(),user.getUser_id());
        return true;
    }

    public boolean deleteUser(String mail){
        String sql = "DELETE FROM user WHERE user_mail = ?";
        jdbcTemplate.update(sql,mail);
        return true;
    }
}
