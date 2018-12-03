package com.uottawa.runnan.seg_deliberable1.Model;

public class Rate {
    private String spName;
    private String id;
    private int rating;
    private String comment;

    public Rate(String id, String spName, int rating, String comment){
        this.id=id;
        this.spName=spName;
        this.rating=rating;
        this.comment=comment;
    }
    public Rate(){}


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpName() {
        return spName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
