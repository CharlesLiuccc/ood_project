package com.gwu.backend.Model.Info;

public class Takeout {
    private int info_id;
    private int catalog_id;
    private String info_timestamp;
    private String takeout_place;
    private String takeout_date;
    private String takeout_detail;

    public Takeout(){
        this.info_id=-1;
        this.catalog_id=-1;
        this.info_timestamp="";
        this.takeout_place="";
        this.takeout_date="";
        this.takeout_detail="";
    }

    public Takeout(int catalog_id, String timestamp, String place, String date, String detail){
        this.info_id=-1;
        this.catalog_id=catalog_id;
        this.info_timestamp=timestamp;
        this.takeout_place=place;
        this.takeout_date=date;
        this.takeout_detail=detail;
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

    public String getInfo_timestamp() {
        return info_timestamp;
    }

    public void setInfo_timestamp(String info_timestamp) {
        this.info_timestamp = info_timestamp;
    }

    public String getTakeout_place() {
        return takeout_place;
    }

    public void setTakeout_place(String takeout_place) {
        this.takeout_place = takeout_place;
    }

    public String getTakeout_date() {
        return takeout_date;
    }

    public void setTakeout_date(String takeout_date) {
        this.takeout_date = takeout_date;
    }

    public String getTakeout_detail() {
        return takeout_detail;
    }

    public void setTakeout_detail(String takeout_detail) {
        this.takeout_detail = takeout_detail;
    }
}
