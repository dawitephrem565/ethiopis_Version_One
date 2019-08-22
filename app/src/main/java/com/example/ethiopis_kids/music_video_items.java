package com.example.ethiopis_kids;

public class music_video_items {
    String video_title;
    String Video_link;
    public music_video_items() {
    }
    public music_video_items(String video_title, String video_link) {
        this.video_title = video_title;
        Video_link = video_link;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_link() {
        return Video_link;
    }

    public void setVideo_link(String video_link) {
        Video_link = video_link;
    }
}
