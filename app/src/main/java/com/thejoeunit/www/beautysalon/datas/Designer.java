package com.thejoeunit.www.beautysalon.datas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by the on 2017-07-26.
 */

public class Designer implements Serializable {
    private String name;
    private int gender;
    private String nickName;
    private int majorAge;
    private float avgRating;
    private ArrayList<DesignCase> portfolio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Designer() {

    }

    public Designer(String name, int gender, String nickName, int majorAge, float avgRating, ArrayList<DesignCase> portfolio) {
        this.name = name;
        this.gender = gender;
        this.nickName = nickName;
        this.majorAge = majorAge;
        this.avgRating = avgRating;
        this.portfolio = portfolio;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getMajorAge() {
        return majorAge;
    }

    public void setMajorAge(int majorAge) {
        this.majorAge = majorAge;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public ArrayList<DesignCase> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<DesignCase> portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        String str = getNickName() + " / 평점 : " + getAvgRating() + "점";
        return str;
    }
}
