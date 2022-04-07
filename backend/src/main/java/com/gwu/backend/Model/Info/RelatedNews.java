package com.gwu.backend.Model.Info;

public class RelatedNews {
    private int info_id;
    private int catalog_id;
    private String info_timestamp;
    private String news_date;
    private String news_detail;

    public RelatedNews(){
        this.info_id=-1;
        this.catalog_id=-1;
        this.info_timestamp="";
        this.news_date="";
        this.news_detail="";
    }

    public RelatedNews(int catalog_id,String timestamp, String date,String detail){
        this.info_id=-1;
        this.catalog_id=catalog_id;
        this.info_timestamp=timestamp;
        this.news_date=date;
        this.news_detail=detail;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setInfo_timestamp(String info_timestamp) {
        this.info_timestamp = info_timestamp;
    }

    public String getInfo_timestamp() {
        return info_timestamp;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_detail(String news_detail) {
        this.news_detail = news_detail;
    }

    public String getNews_detail() {
        return news_detail;
    }
}
