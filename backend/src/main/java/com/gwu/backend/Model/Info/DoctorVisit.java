package com.gwu.backend.Model.Info;

public class DoctorVisit {
    private int info_id;
    private int catalog_id;
    private String info_timestamp;
    private String doctor_name;
    private String visit_date;
    private String visit_detail;

    public DoctorVisit(){
        this.info_id=-1;
        this.catalog_id=-1;
        this.info_timestamp = "";
        this.doctor_name="";
        this.visit_date="";
        this.visit_detail="";
    }

    public DoctorVisit(int catalog_id,String timestamp,String name,String date,String detail){
        this.info_id=-1;
        this.catalog_id=catalog_id;
        this.info_timestamp=timestamp;
        this.doctor_name=name;
        this.visit_date=date;
        this.visit_detail=detail;
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

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }

    public String getVisit_detail() {
        return visit_detail;
    }
}
