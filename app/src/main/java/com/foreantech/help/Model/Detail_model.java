package com.foreantech.help.Model;

public class Detail_model {
    String name,image,url_for_intent;

    public Detail_model(String name, String image, String url_for_intent) {
        this.name = name;
        this.image = image;
        this.url_for_intent = url_for_intent;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getUrl_for_intent() {
        return url_for_intent;
    }
}