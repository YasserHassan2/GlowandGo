package com.example.glowgo.Model;

public class TourPackage {

    String id,imgeUrl,packageName,packageCountry,packageDesc,packagePrice;

    public TourPackage(String id, String imgeUrl, String packageName, String packageCountry, String packageDesc, String packagePrice) {
        this.id = id;
        this.imgeUrl = imgeUrl;
        this.packageName = packageName;
        this.packageCountry = packageCountry;
        this.packageDesc = packageDesc;
        this.packagePrice = packagePrice;
    }

    public TourPackage(String id, String imgeUrl, String packageName, String packageCountry, String packagePrice) {
        this.id = id;
        this.imgeUrl = imgeUrl;
        this.packageName = packageName;
        this.packageCountry = packageCountry;
        this.packagePrice = packagePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageCountry() {
        return packageCountry;
    }

    public void setPackageCountry(String packageCountry) {
        this.packageCountry = packageCountry;
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }
}
