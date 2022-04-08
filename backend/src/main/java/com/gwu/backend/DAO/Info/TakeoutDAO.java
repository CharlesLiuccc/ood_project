package com.gwu.backend.DAO.Info;

import com.gwu.backend.Model.Info.Takeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class TakeoutDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArrayList<Takeout> findByCatalog(int catalog_id){
        ArrayList<Takeout> result = new ArrayList<>();
        String sql = "SELECT * FROM takeout_info WHERE catalog_id = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Takeout current = new Takeout();
                current.setInfo_id(rs.getInt("info_id"));
                current.setCatalog_id(catalog_id);
                current.setInfo_timestamp(rs.getString("info_timestamp"));
                current.setTakeout_place(rs.getString("takeout_place"));
                current.setTakeout_date(rs.getString("takeout_date"));
                current.setTakeout_detail(rs.getString("takeout_detail"));
                result.add(current);
            }
        });
        return result;
    }

    public Takeout findById(int info_id){
        Takeout result = new Takeout();
        String sql = "SELECT * FROM takeout_info WHERE info_id = ?";
        jdbcTemplate.query(sql, new Object[]{info_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setInfo_id(info_id);
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setInfo_timestamp(rs.getString("info_timestamp"));
                result.setTakeout_date(rs.getString("takeout_date"));
                result.setTakeout_place(rs.getString("takeout_place"));
                result.setTakeout_detail(rs.getString("takeout_detail"));
            }
        });
        return result;
    }

    public boolean addInfo(Takeout takeout){
        String sql = "INSERT INTO takeout_info(catalog_id,info_timestamp,takeout_place,takeout_date,takeout_detail) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,takeout.getCatalog_id(),takeout.getInfo_timestamp(),takeout.getTakeout_place(),takeout.getTakeout_date(),takeout.getTakeout_detail());
        return true;
    }

    public boolean deleteInfo(int info_id){
        if(findById(info_id).getCatalog_id()==-1){
            return false;
        }
        else{
            String sql = "DELETE FROM takeout_info WHERE info_id = ?";
            jdbcTemplate.update(sql,info_id);
            return true;
        }
    }

}
