package com.uottawa.runnan.seg_deliberable1.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rating{

    private double avgRating;
    private String name;

    public Rating(String name, double avgRating){
        this.avgRating=avgRating;
        this.name=name;

    }

    public Rating() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public double getAvgRating() {
        return avgRating;
    }




       // avgRating = Math.round( ((double) count)/rateList.size() *10 )/10.0;


}
