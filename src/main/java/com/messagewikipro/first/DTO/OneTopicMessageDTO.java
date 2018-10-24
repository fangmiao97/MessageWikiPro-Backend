package com.messagewikipro.first.DTO;

/**
 * 一个主题
 */

public class OneTopicMessageDTO {


    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String title;
    private String username;
    private String time;
    private String content;
    private String collectionsNum;
    private String commentsNum;
    private String likesNum;

    public String getCollectionsNum() {
        return collectionsNum;
    }

    public void setCollectionsNum(String collectionsNum) {
        this.collectionsNum = collectionsNum;
    }

    public String getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(String commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(String likesNum) {
        this.likesNum = likesNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
