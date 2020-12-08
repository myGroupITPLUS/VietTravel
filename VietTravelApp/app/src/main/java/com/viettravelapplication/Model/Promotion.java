package com.viettravelapplication.Model;
public class Promotion {
    private int id;
    private String sale;
    private String description;
    private String images;

    public Promotion(String sale, String description, String images) {
        this.sale = sale;
        this.description = description;
        this.images = images;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", sale='" + sale + '\'' +
                ", discription='" + description + '\'' +
                ", images='" + images + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}