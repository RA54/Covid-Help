package com.foreantech.help.Model;

public class Model_Main {
    String id;
    String name;
    String url;

    public Model_Main(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}