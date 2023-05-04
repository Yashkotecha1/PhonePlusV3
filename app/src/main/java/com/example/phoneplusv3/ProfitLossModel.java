package com.example.phoneplusv3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfitLossModel
{
    @PrimaryKey(autoGenerate = true)
    int id;
    String brandName;
    int purchasePrice;
    int sellPrice;
    int profitLoss;
    int totalQty;

    String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(int profitLoss) {
        this.profitLoss = profitLoss;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }
}
