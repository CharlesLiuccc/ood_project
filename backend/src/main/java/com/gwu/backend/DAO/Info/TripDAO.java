package com.gwu.backend.DAO.Info;

import com.gwu.backend.Model.Info.Takeout;
import com.gwu.backend.Model.Info.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class TripDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArrayList<Trip> findByCatalog(int catalog_id){
        ArrayList<Trip> result = new ArrayList<>();
        String sql = "SELECT * FROM trip_info WHERE catalog_id = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Trip current = new Trip();
                current.setInfo_id(rs.getInt("info_id"));
                current.setCatalog_id(catalog_id);
                current.setInfo_timestamp(rs.getString("info_timestamp"));
                current.setStart_time(rs.getString("start_time"));
                current.setEnd_time(rs.getString("end_time"));
                current.setTrip_destination(rs.getString("trip_destination"));
                current.setTrip_detail(rs.getString("trip_detail"));
                result.add(current);
            }
        });
        return result;
    }

    public Trip findById(int info_id){
        Trip result = new Trip();
        String sql = "SELECT * FROM trip_info WHERE info_id = ?";
        jdbcTemplate.query(sql, new Object[]{info_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setInfo_id(info_id);
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setInfo_timestamp(rs.getString("info_timestamp"));
                result.setStart_time(rs.getString("start_time"));
                result.setEnd_time(rs.getString("end_time"));
                result.setTrip_destination(rs.getString("trip_destination"));
                result.setTrip_detail(rs.getString("trip_detail"));
            }
        });
        return result;
    }

    public boolean addInfo(Trip trip){
        String sql = "INSERT INTO trip_info(catalog_id,info_timestamp,start_time,end_time,trip_destination,trip_detail) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,trip.getCatalog_id(),trip.getInfo_timestamp(),trip.getStart_time(),trip.getEnd_time(),trip.getTrip_destination(),trip.getTrip_detail());
        return true;
    }

    public boolean deleteInfo(int info_id){
        if(findById(info_id).getCatalog_id()==-1){
            return false;
        }
        else{
            String sql = "DELETE FROM trip_info WHERE info_id = ?";
            jdbcTemplate.update(sql,info_id);
            return true;
        }
    }
}
