package com.epam.web.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Lot {
    private long id;
    private String name;
    private String description;
    private Timestamp startTime;
    private Timestamp finishTime;
    private BigDecimal currentCost;
    private long sellerId;
    private long buyerId;
    private List<String> images;


    public Lot(long id, String name, String description, Timestamp startTime, Timestamp finishTime, BigDecimal currentCost, long sellerId, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.currentCost = currentCost;
        this.sellerId = sellerId;
        this.images = images;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lot lot = (Lot) o;

        if (id != lot.id) return false;
        if (sellerId != lot.sellerId) return false;
        if (buyerId != lot.buyerId) return false;
        if (name != null ? !name.equals(lot.name) : lot.name != null) return false;
        if (description != null ? !description.equals(lot.description) : lot.description != null) return false;
        if (startTime != null ? !startTime.equals(lot.startTime) : lot.startTime != null) return false;
        if (finishTime != null ? !finishTime.equals(lot.finishTime) : lot.finishTime != null) return false;
        if (currentCost != null ? !currentCost.equals(lot.currentCost) : lot.currentCost != null) return false;
        return images != null ? images.equals(lot.images) : lot.images == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (currentCost != null ? currentCost.hashCode() : 0);
        result = 31 * result + (int) (sellerId ^ (sellerId >>> 32));
        result = 31 * result + (int) (buyerId ^ (buyerId >>> 32));
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lot{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startTime=").append(startTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", currentCost=").append(currentCost);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", images=").append(images);
        sb.append('}');
        return sb.toString();
    }
}
