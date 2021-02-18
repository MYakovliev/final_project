package com.epam.web.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Lot {
    private int id;
    private String name;
    private String description;
    private Date finishTime;
    private BigDecimal currentCost;
    private User seller;
    private User buyer;

    public Lot(int id, String name, String description, Date finishTime, BigDecimal currentCost, User seller) {
        this.name = name;
        this.description = description;
        this.finishTime = finishTime;
        this.currentCost = currentCost;
        this.seller = seller;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

}
