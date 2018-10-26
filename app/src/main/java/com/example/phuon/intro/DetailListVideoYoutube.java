package com.example.phuon.intro;

import java.io.Serializable;

public class DetailListVideoYoutube implements Serializable{
    private String Title;
    private String Thumbnails;
    private String IDVideo;

    public DetailListVideoYoutube(String title, String thumbnails, String IDVideo) {
        Title = title;
        Thumbnails = thumbnails;
        this.IDVideo = IDVideo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnails() {
        return Thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        Thumbnails = thumbnails;
    }

    public String getIDVideo() {
        return IDVideo;
    }

    public void setIDVideo(String IDVideo) {
        this.IDVideo = IDVideo;
    }
}
