package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by tao on 2015/12/18.
 */
public class BookTravelInfo implements Serializable {
    @JsonProperty("bookUrl")
    private String bookUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("headImage")
    private String headImage;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("userHeadImg")
    private String userHeadImg;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("routeDays")
    private String routeDays;
    @JsonProperty("bookImgNum")
    private String bookImgNum;
    @JsonProperty("viewCount")
    private String viewCount;
    @JsonProperty("likeCount")
    private String likeCount;
    @JsonProperty("commentCount")
    private String commentCount;
    @JsonProperty("text")
    private String text;
    @JsonProperty("elite")
    private String elite;

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRouteDays() {
        return routeDays;
    }

    public void setRouteDays(String routeDays) {
        this.routeDays = routeDays;
    }

    public String getBookImgNum() {
        return bookImgNum;
    }

    public void setBookImgNum(String bookImgNum) {
        this.bookImgNum = bookImgNum;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getElite() {
        return elite;
    }

    public void setElite(String elite) {
        this.elite = elite;
    }
}
