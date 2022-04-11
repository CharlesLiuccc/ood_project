package com.gwu.backend.Model;

public class Catalog {
    private int catalog_id;
    private int user_id;
    private int amount;
    private int risk;

    public Catalog(){
        this.catalog_id=-1;
        this.user_id = -1;
        this.amount=0;
        this.risk=0;
    }
    public Catalog(int user_id){
        this.catalog_id=-1;
        this.user_id=user_id;
        this.amount=0;
        this.risk=0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public int getRisk() {
        return risk;
    }

    public boolean addInfo(){
        this.amount++;
        return true;
    }

    public boolean removeInfo(){
        if(this.amount==0){
            return false;
        }
        else{
            this.amount--;
            return true;
        }
    }
}
