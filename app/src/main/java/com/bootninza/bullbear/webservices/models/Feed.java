package com.bootninza.bullbear.webservices.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class Feed implements Serializable {
    private String postContent;
    private List<ImageData> imageUrls;
    private Equity equity;
    private User user;
    private Date createDateTime;
    private Date updateDateTime;

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "postContent='" + postContent + '\'' +
                ", imgUrls=" + imageUrls +
                ", equity=" + equity +
                ", user=" + user +
                '}';
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public List<ImageData> getImgUrls() {
        return imageUrls;
    }

    public void setImgUrls(List<ImageData> imgUrls) {
        this.imageUrls = imgUrls;
    }

    public Equity getEquity() {
        return equity;
    }

    public void setEquity(Equity equity) {
        this.equity = equity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
