package com.assignment.eshopapp;

import java.util.List;

class ProductsDetails {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }


    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public String id;
    public String name;
    public String date_added;

    public List<Variants> getVariantsList() {
        return variants;
    }

    public void setVariantsList(List<Variants> variants) {
        this.variants = variants;
    }

    public List<Variants> variants;
    public Tax tax;
}
