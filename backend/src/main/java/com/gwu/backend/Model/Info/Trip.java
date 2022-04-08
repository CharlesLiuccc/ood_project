package com.gwu.backend.Model.Info;

public class Trip {
    private int catalog_id;
    private int info_id;
    private String info_timestamp;
    private String start_time;
    private String end_time;
    private String trip_destination;
    private String trip_detail;

    public Trip(){
        this.catalog_id=-1;
        this.info_id=-1;
        this.info_timestamp="";
        this.start_time="";
        this.end_time="";
        this.trip_destination="";
        this.trip_detail="";
    }

    public Trip(int catalog_id,String timestamp,String start_time,String end_time,String destination,String detail){
        this.catalog_id=catalog_id;
        this.info_id=-1;
        this.info_timestamp=timestamp;
        this.start_time=start_time;
        this.end_time=end_time;
        this.trip_destination=destination;
        this.trip_detail=detail;
    }

    public void setInfo_timestamp(String info_timestamp) {
        this.info_timestamp = info_timestamp;
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getInfo_timestamp() {
        return info_timestamp;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setTrip_destination(String trip_destination) {
        this.trip_destination = trip_destination;
    }

    public String getTrip_destination() {
        return trip_destination;
    }

    public void setTrip_detail(String trip_detail) {
        this.trip_detail = trip_detail;
    }

    public String getTrip_detail() {
        return trip_detail;
    }
}
