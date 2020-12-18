package com.viettravelapplication.Model;

public class Banner {
    private int id;
    private String name;
    private String descriptions;
    private String url;
    private String images;

    public Banner(int id, String name, String descriptions, String url, String images) {
        this.id = id;
        this.name = name;
        this.descriptions = descriptions;
        this.url = url;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", url='" + url + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
