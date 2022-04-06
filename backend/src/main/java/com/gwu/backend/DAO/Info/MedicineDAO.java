package com.gwu.backend.DAO.Info;

import com.gwu.backend.Model.Info.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class MedicineDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //all information under certain catalog
    public ArrayList<Medicine> findByCatalog(int catalog_id){
        ArrayList<Medicine> result = new ArrayList<>();
        String sql = "SELECT * FROM medicine_info WHERE catalog_id = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Medicine medicine = new Medicine();
                medicine.setCatalog_id(catalog_id);
                medicine.setInfo_id(rs.getInt("info_id"));
                medicine.setInfo_timestamp(rs.getString("info_timestamp"));
                medicine.setMedicine_name(rs.getString("medicine_name"));
                medicine.setMedicine_frequency(rs.getString("medicine_frequency"));
                medicine.setMedicine_dosage(rs.getString("medicine_dosage"));
                medicine.setMedicine_detail(rs.getString("medicine_detail"));
                result.add(medicine);
            }
        });
        return result;
    }

    //find single medicine information by id
    public Medicine findById(int info_id){
        Medicine result = new Medicine();
        String sql = "SELECT * FROM medicine_info WHERE info_id = ?";
        jdbcTemplate.query(sql, new Object[]{info_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setInfo_id(info_id);
                result.setInfo_timestamp(rs.getString("info_timestamp"));
                result.setMedicine_name(rs.getString("medicine_name"));
                result.setMedicine_frequency(rs.getString("medicine_frequency"));
                result.setMedicine_dosage(rs.getString("medicine_dosage"));
                result.setMedicine_detail(rs.getString("medicine_detail"));
            }
        });
        return result;
    }

    //add new medicine information
    public boolean addInfo(Medicine medicine){
        String sql = "INSERT INTO medicine_info(catalog_id,info_timestamp,medicine_name,medicine_frequency,medicine_dosage,medicine_detail) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,medicine.getCatalog_id(),medicine.getInfo_timestamp(),medicine.getMedicine_name(),medicine.getMedicine_frequency(),medicine.getMedicine_dosage(),medicine.getMedicine_detail());
        return true;
    }

    //delete doctor visit information
    public boolean deleteInfo(int info_id){
        if(findById(info_id).getCatalog_id()==-1){
            return false;
        }
        else{
            String sql = "DELETE FROM medicine_info WHERE info_id = ?";
            jdbcTemplate.update(sql,info_id);
            return true;
        }
    }

}
