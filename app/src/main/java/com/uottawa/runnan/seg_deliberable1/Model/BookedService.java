package com.uottawa.runnan.seg_deliberable1.Model;

public class BookedService {
    public BookedService(String hoName, String spName, String id) {
        this.hoName = hoName;
        this.spName = spName;
        this.id = id;
    }

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

}
