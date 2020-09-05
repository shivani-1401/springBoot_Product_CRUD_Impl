package com.stackroute.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity annotation specifies that the class is an entity and is mapped to a database table.
@Entity
public class Product {

    @Id
    private int prodId;
    private String prodName;
    private String prodCategory;

    public Product() {

    }

    public Product(int prodId, String prodName, String prodCategory) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodCategory = prodCategory;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", prodCategory='" + prodCategory + '\'' +
                '}';
    }
}
