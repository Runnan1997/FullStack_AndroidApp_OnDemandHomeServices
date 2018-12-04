package com.uottawa.runnan.seg_deliberable1.Model;

public class HomeOwner extends User {
    public HomeOwner(String hoName, String spName, String id) {
        this.hoName = hoName;
        this.spName = spName;
        this.id = id;
    }
    public HomeOwner(String hoName, String spName, String service,String id) {
        this.hoName = hoName;
        this.spName = spName;
        this.id = id;
        this.service = service;
    }
    public HomeOwner(){};

    public String getHoName() {
        return hoName;
    }

    public void setHoName(String hoName) {
        this.hoName = hoName;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    private String hoName;
    private String spName;
    private String id;
    private String service;


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}