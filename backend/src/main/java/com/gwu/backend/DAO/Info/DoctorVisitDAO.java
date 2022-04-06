package com.gwu.backend.DAO.Info;

import com.gwu.backend.Model.Info.DoctorVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DoctorVisitDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //find information by id
    public DoctorVisit findById(int info_id){
        DoctorVisit result = new DoctorVisit();
        String sql = "SELECT * FROM doctor_visit_info WHERE info_id = ?";
        jdbcTemplate.query(sql, new Object[]{info_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setInfo_id(info_id);
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setInfo_timestamp(rs.getString("info_timestamp"));
                result.setDoctor_name(rs.getString("doctor_name"));
                result.setVisit_date(rs.getString("visit_date"));
                result.setVisit_detail(rs.getString("visit_detail"));
            }
        });
        return result;
    }

    //find all the doctor visit information under certain catalog with catalog_id
    public ArrayList<DoctorVisit> findByCatalog(int catalog_id){
        ArrayList<DoctorVisit> result = new ArrayList<>();
        String sql = "SELECT * FROM doctor_visit_info WHERE catalog_id = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                DoctorVisit visit = new DoctorVisit();
                visit.setInfo_id(rs.getInt("info_id"));
                visit.setCatalog_id(catalog_id);
                visit.setInfo_timestamp(rs.getString("info_timestamp"));
                visit.setDoctor_name(rs.getString("doctor_name"));
                visit.setVisit_date(rs.getString("visit_date"));
                visit.setVisit_detail(rs.getString("visit_detail"));
                result.add(visit);
            }
        });
        return result;
    }

    //add new doctor visit information
    public boolean addInfo(DoctorVisit visit){
        String sql = "INSERT INTO doctor_visit_info(catalog_id,info_timestamp,doctor_name,visit_date,visit_detail) values(?,?,?,?,?)";
        jdbcTemplate.update(sql,visit.getCatalog_id(),visit.getInfo_timestamp(),visit.getDoctor_name(),visit.getVisit_date(),visit.getVisit_detail());
        return true;
    }

    //delete doctor visit information
    public boolean deleteInfo(int info_id){
        if(findById(info_id).getCatalog_id()==-1){
            return false;
        }
        else{
            String sql = "DELETE FROM doctor_visit_info WHERE info_id = ?";
            jdbcTemplate.update(sql,info_id);
            return true;
        }
    }
}
