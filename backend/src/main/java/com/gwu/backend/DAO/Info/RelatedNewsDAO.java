package com.gwu.backend.DAO.Info;

import com.gwu.backend.Model.Info.RelatedNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class RelatedNewsDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public ArrayList<RelatedNews> findByCatalog(int catalog_id){
        ArrayList<RelatedNews> result = new ArrayList<>();
        String sql = "SELECT * FROM related_news_info WHERE catalog_id = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                RelatedNews current = new RelatedNews();
                current.setInfo_id(rs.getInt("info_id"));
                current.setCatalog_id(catalog_id);
                current.setInfo_timestamp(rs.getString("info_timestamp"));
                current.setNews_date(rs.getString("news_date"));
                current.setNews_detail(rs.getString("news_detail"));
                result.add(current);
            }
        });
        return result;
    }

    public RelatedNews findById(int info_id){
        RelatedNews result = new RelatedNews();
        String sql = "SELECT * FROM related_news_info WHERE info_id = ?";
        jdbcTemplate.query(sql, new Object[]{info_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setInfo_id(info_id);
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setInfo_timestamp(rs.getString("info_timestamp"));
                result.setNews_date(rs.getString("news_date"));
                result.setNews_detail(rs.getString("news_detail"));
            }
        });
        return result;
    }

    public boolean addInfo(RelatedNews relatedNews){
        String sql = "INSERT INTO related_news_info(catalog_id,info_timestamp,news_date,news_detail) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,relatedNews.getCatalog_id(),relatedNews.getInfo_timestamp(),relatedNews.getNews_date(),relatedNews.getNews_detail());
        return true;
    }

    public boolean deleteInfo(int info_id){
        if(findById(info_id).getCatalog_id()==-1){
            return false;
        }
        else{
            String sql = "DELETE FROM related_news_info WHERE info_id = ?";
            jdbcTemplate.update(sql,info_id);
            return true;
        }
    }

    public ArrayList<RelatedNews> findTypeNum(int catalog_id,String type){
        ArrayList<RelatedNews> result = new ArrayList<>();
        String sql = "SELECT * FROM related_news_info WHERE catalog_id = ? AND detail = ?";
        jdbcTemplate.query(sql, new Object[]{catalog_id,type}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                RelatedNews current = new RelatedNews();
                current.setInfo_id(rs.getInt("info_id"));
                current.setCatalog_id(catalog_id);
                current.setInfo_timestamp(rs.getString("info_timestamp"));
                current.setNews_date(rs.getString("news_date"));
                current.setNews_detail(rs.getString("news_detail"));
                result.add(current);
            }
        });
        return result;
    }
}
