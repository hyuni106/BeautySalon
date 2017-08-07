package com.thejoeunit.www.beautysalon.datas;

import com.thejoeunit.www.beautysalon.utils.DateTimeUtil;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by the on 2017-07-26.
 */

public class DesignCase implements Serializable {
    private int caseimgId;
    private Calendar createOn;
    private int userRating;
    private Designer designer;
    private User user;
    private int cost;
    private String userReview;

    public DesignCase(int caseimgId, Calendar createOn, int userRating, Designer designer, User user, int cost, String userReview) {
        this.caseimgId = caseimgId;
        this.createOn = createOn;
        this.userRating = userRating;
        this.designer = designer;
        this.user = user;
        this.cost = cost;
        this.userReview = userReview;
    }

    public int getCaseimgId() {
        return caseimgId;
    }

    public void setCaseimgId(int caseimgId) {
        this.caseimgId = caseimgId;
    }

    public Calendar getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Calendar createOn) {
        this.createOn = createOn;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    @Override
    public String toString() {
        String dateStr = DateTimeUtil.getDateTimeString(this.createOn);
        String str = this.user.getName() + " / " + dateStr;
        return str;
    }
}
