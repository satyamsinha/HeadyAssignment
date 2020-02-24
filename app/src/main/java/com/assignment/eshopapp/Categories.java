package com.assignment.eshopapp;

import java.util.ArrayList;

class Categories {

    public String id;
    public String name;

    public ArrayList<ProductsDetails> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductsDetails> products) {
        this.products = products;
    }

    public ArrayList<ProductsDetails> products;


    public ArrayList<Integer> getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(ArrayList<Integer> child_categories) {
        this.child_categories = child_categories;
    }

    public ArrayList<Integer> child_categories;





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




}
