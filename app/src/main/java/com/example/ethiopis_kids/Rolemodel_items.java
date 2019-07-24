package com.example.ethiopis_kids;

public class Rolemodel_items {
    String  model_title ;
    String model_cover ;
    String model_desc ;


    public Rolemodel_items() {
    }

    public Rolemodel_items(String model_title, String model_cover, String model_desc) {
        this.model_title = model_title;
        this.model_cover = model_cover;
        this.model_desc = model_desc;
    }

    public String getModel_title() {
        return model_title;
    }

    public void setModel_title(String model_title) {
        this.model_title = model_title;
    }

    public String getModel_cover() {
        return model_cover;
    }

    public void setModel_cover(String model_cover) {
        this.model_cover = model_cover;
    }

    public String getModel_desc() {
        return model_desc;
    }

    public void setModel_desc(String model_desc) {
        this.model_desc = model_desc;
    }
}
