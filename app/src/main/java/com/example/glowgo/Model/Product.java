package com.example.glowgo.Model;

public class Product {

    String id , product_imagURL, product_name,product_desc,product_price,snippet;

    public Product(String id, String product_imagURL, String product_name, String product_price, String snippet) {
        this.id = id;
        this.product_imagURL = product_imagURL;
        this.product_name = product_name;
        this.product_price = product_price;
        this.snippet = snippet;
    }

    public Product(String id, String product_imagURL, String product_name, String product_desc, String product_price, String snippet) {
        this.id = id;
        this.product_imagURL = product_imagURL;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_price = product_price;
        this.snippet = snippet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_imagURL() {
        return product_imagURL;
    }

    public void setProduct_imagURL(String product_imagURL) {
        this.product_imagURL = product_imagURL;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}
