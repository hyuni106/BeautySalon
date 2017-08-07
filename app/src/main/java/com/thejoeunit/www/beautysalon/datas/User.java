package com.thejoeunit.www.beautysalon.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by the on 2017-07-26.
 */

public class User implements Serializable{
    // User 클래스 객체들이
    // intent.putExtra => intent에 첨부되어 전송될 수 있도록 하는 구문
    private String name;
    private int gender;
    private Calendar birthDay;
    private ArrayList<Designer> likeDesigners;
    private String profilePicPath;

    public User() {

    }

    public User(String name, int gender, Calendar birthDay, ArrayList<Designer> likeDesigners, String path) {
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.likeDesigners = likeDesigners;
        this.profilePicPath = path;
    }

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

    public String getProfilePicPath() {
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath) {
        this.profilePicPath = profilePicPath;
    }

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public ArrayList<Designer> getLikeDesigners() {
        return likeDesigners;
    }

    public void setLikeDesigners(ArrayList<Designer> likeDesigners) {
        this.likeDesigners = likeDesigners;
    }
}
