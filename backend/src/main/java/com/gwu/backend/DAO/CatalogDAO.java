package com.gwu.backend.DAO;

import com.gwu.backend.Model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CatalogDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Catalog findByUser(int user_id){
        Catalog result = new Catalog();
        String sql = "SELECT * FROM catalog WHERE user_id = ?";
        jdbcTemplate.query(sql,new Object[]{user_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setCatalog_id(rs.getInt("catalog_id"));
                result.setUser_id(user_id);
                result.setAmount(rs.getInt("amount"));
            }
        });
        return result;
    }
    public Catalog findById(int catalog_id){
        Catalog result = new Catalog();
        String sql = "SELECT * FROM catalog WHERE catalog_id = ?";
        jdbcTemplate.query(sql,new Object[]{catalog_id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setCatalog_id(catalog_id);
                result.setUser_id(rs.getInt("user_id"));
                result.setAmount(rs.getInt("amount"));
            }
        });
        return result;
    }

    public boolean updateAmount(int catalog_id,int amount){
        String sql = "UPDATE catalog set amount = ? WHERE catalog_id = ?";
        jdbcTemplate.update(sql,amount,catalog_id);
        return true;
    }

    public boolean addCatalog(int user_id){
        Catalog current_catalog = new Catalog(user_id);
        String sql = "INSERT INTO catalog(user_id,amount) VALUES(?,?)";
        jdbcTemplate.update(sql,current_catalog.getUser_id(),current_catalog.getAmount());
        return true;
    }

    public boolean deleteCatalog(int user_id){
        String sql = "DELETE FROM catalog WHERE user_id = ?";
        jdbcTemplate.update(sql,user_id);
        return true;
    }
}
