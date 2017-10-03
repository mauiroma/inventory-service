package com.redhat.coolstore.inventory.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_INVENTORY", uniqueConstraints = @UniqueConstraint(columnNames = "itemId"))
public class Inventory implements Serializable {
    @Id
    private String itemId;

    private int quantity;

    @Column(length = 255)
    private String location;

    @Column(length = 255)
    private String link;

    public Inventory() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @Override
    public String toString() {
        return "Inventory [itemId='" + itemId + '\'' + ", quantity=" + quantity + '\'' + ", location=" + location + '\'' + ", link=" + link +']';
    }

}