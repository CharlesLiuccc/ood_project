package com.gwu.backend.Model.Info;

public class Symptom {
    private int catalog_id;
    private int info_id;
    private String info_timestamp;
    private String start_time;
    private String symptom_type;
    private String symptom_detail;

    public Symptom(){
        this.catalog_id=-1;
        this.info_id=-1;
        this.info_timestamp="";
        this.start_time="";
        this.symptom_type="";
        this.symptom_detail="";
    }

    public Symptom(int catalog_id,String timestamp,String time,String type,String detail){
        this.catalog_id=catalog_id;
        this.info_id=-1;
        this.info_timestamp=timestamp;
        this.start_time=time;
        this.symptom_type=type;
        this.symptom_detail=detail;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_timestamp(String info_timestamp) {
        this.info_timestamp = info_timestamp;
    }

    public String getInfo_timestamp() {
        return info_timestamp;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getSymptom_type() {
        return symptom_type;
    }

    public void setSymptom_detail(String symptom_detail) {
        this.symptom_detail = symptom_detail;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setSymptom_type(String symptom_type) {
        this.symptom_type = symptom_type;
    }

    public String getSymptom_detail() {
        return symptom_detail;
    }
}
