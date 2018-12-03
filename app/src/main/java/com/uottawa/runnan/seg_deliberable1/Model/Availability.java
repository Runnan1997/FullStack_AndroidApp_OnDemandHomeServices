package com.uottawa.runnan.seg_deliberable1.Model;

public class Availability {
    public Availability(String id,String dates, String time, String name) {
        this.id = id;
        this.dates = dates;
        this.time = time;
        this.name = name;
    }


    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String dates;
    private  String time;
    private String id;
    private String name;


    public Availability(String dates, String time) {
        this.dates = dates;
        this.time = time;
    }

    public Availability() {
    }
}
