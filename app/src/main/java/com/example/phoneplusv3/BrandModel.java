package com.example.phoneplusv3;

public class BrandModel {

    private String brandName;
    private  int brandImg;

    public BrandModel(String brandName, int brandImg) {
        this.brandName = brandName;
        this.brandImg = brandImg;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(int brandImg) {
        this.brandImg = brandImg;
    }

    @Override
    public String toString() {
        return "BrandModel{" +
                "brandName='" + brandName + '\'' +
                ", brandImg=" + brandImg +
                '}';
    }
}
