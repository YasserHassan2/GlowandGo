package com.example.glowgo.Model;

public class Category {
    String id,cat_title,cat_imageURL,categoriType;

    public Category(String id, String cat_title, String cat_imageURL,String categoriType) {
        this.id = id;
        this.cat_title = cat_title;
        this.cat_imageURL = cat_imageURL;
        this.categoriType = categoriType;
    }

    public String getCategoriType() {
        return categoriType;
    }

    public void setCategoriType(String categoriType) {
        this.categoriType = categoriType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public String getCat_imageURL() {
        return cat_imageURL;
    }

    public void setCat_imageURL(String cat_imageURL) {
        this.cat_imageURL = cat_imageURL;
    }
}
