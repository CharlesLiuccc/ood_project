package com.gwu.backend.Model.Info;

public class Medicine {
    private int catalog_id;
    private int info_id;
    private String info_timestamp;
    private String medicine_name;
    private String medicine_frequency;
    private String medicine_dosage;
    private String medicine_detail;

    public Medicine(){
        this.catalog_id=-1;
        this.info_id=-1;
        this.info_timestamp="";
        this.medicine_name="";
        this.medicine_frequency="";
        this.medicine_dosage="";
        this.medicine_detail="";
    }

    public Medicine(int catalog_id,String timestamp,String name,String frequency,String dosage,String detail){
        this.catalog_id=catalog_id;
        this.info_id=-1;
        this.info_timestamp=timestamp;
        this.medicine_name=name;
        this.medicine_frequency=frequency;
        this.medicine_dosage=dosage;
        this.medicine_detail=detail;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
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

    public void setInfo_timestamp(String info_timestamp) {
        this.info_timestamp = info_timestamp;
    }

    public String getMedicine_detail() {
        return medicine_detail;
    }

    public void setMedicine_detail(String medicine_detail) {
        this.medicine_detail = medicine_detail;
    }

    public String getMedicine_dosage() {
        return medicine_dosage;
    }

    public void setMedicine_dosage(String medicine_dosage) {
        this.medicine_dosage = medicine_dosage;
    }

    public String getMedicine_frequency() {
        return medicine_frequency;
    }

    public void setMedicine_frequency(String medicine_frequency) {
        this.medicine_frequency = medicine_frequency;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }
}
