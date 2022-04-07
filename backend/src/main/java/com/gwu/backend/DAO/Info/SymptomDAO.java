package com.gwu.backend.DAO.Info;

import com.gwu.backend.Model.Info.RelatedNews;
import com.gwu.backend.Model.Info.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class SymptomDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArrayList<Symptom> findByCatalog(int catalog_id){
        ArrayList<Symptom> result = new ArrayList<>();
        String sql = "SELECT * FROM symptom_info WHERE catalog_id = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Symptom current = new Symptom();
                current.setInfo_id(rs.getInt("info_id"));
                current.setCatalog_id(catalog_id);
                current.setInfo_timestamp(rs.getString("info_timestamp"));
                current.setSymptom_type(rs.getInt("symptom_type"));
                current.setStart_time(rs.getString("start_time"));
                current.setSymptom_detail(rs.getString("symptom_detail"));
                result.add(current);
            }
        });
        return result;
    }

    public Symptom findById(int info_id){
        Symptom result = new Symptom();
        String sql = "SELECT * FROM symptom_info WHERE info_id = ?";
        jdbcTemplate.query(sql, new Object[]{info_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setInfo_id(info_id);
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setInfo_timestamp(rs.getString("info_timestamp"));
                result.setStart_time(rs.getString("start_time"));
                result.setSymptom_type(rs.getInt("symptom_type"));
                result.setSymptom_detail(rs.getString("symptom_detail"));
            }
        });
        return result;
    }

    public boolean addInfo(Symptom symptom){
        String sql = "INSERT INTO symptom_info(catalog_id,info_timestamp,start_time,symptom_type,symptom_detail) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,symptom.getCatalog_id(),symptom.getInfo_timestamp(),symptom.getSymptom_type(),symptom.getSymptom_type(),symptom.getSymptom_detail());
        return true;
    }

    public boolean deleteInfo(int info_id){
        if(findById(info_id).getCatalog_id()==-1){
            return false;
        }
        else{
            String sql = "DELETE FROM symptom_info WHERE info_id = ?";
            jdbcTemplate.update(sql,info_id);
            return true;
        }
    }
}
