package com.example.goshop.entity;

import java.io.Serializable;

public class Product implements Serializable {

    public int id;

    public String name;

    public String description;

    public double price;

    public double quantityInStock;

    public double alertQuantity;
    public String uri;

    public int serverId;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", alertQuantity=" + alertQuantity +
                ", serverId=" + serverId +
                '}';
    }
}
