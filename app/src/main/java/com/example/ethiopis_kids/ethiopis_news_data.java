package com.example.ethiopis_kids;

public class ethiopis_news_data {
    String Headline;
    String Description;
    String URL;
    String Image;
    public ethiopis_news_data() {
    }
    public ethiopis_news_data(String headline, String description, String URL, String image) {
        Headline = headline;
        Description = description;
        this.URL = URL;
        Image = image;
    }

    public String getHeadline() {
        return Headline;
    }

    public void setHeadline(String headline) {
        Headline = headline;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
