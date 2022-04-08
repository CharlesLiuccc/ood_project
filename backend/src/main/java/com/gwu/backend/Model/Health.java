package com.gwu.backend.Model;

public class Health {
    private String token;
    private String clientID;
    private String clientSecrete;

    public Health(String id, String secrete){
        this.token="";
        this.clientID=id;
        this.clientSecrete=secrete;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientSecrete(String clientSecrete) {
        this.clientSecrete = clientSecrete;
    }

    public String getClientSecrete() {
        return clientSecrete;
    }
}
