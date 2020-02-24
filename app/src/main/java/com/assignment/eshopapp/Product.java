package com.assignment.eshopapp;

import java.util.ArrayList;

public class Product {

    ArrayList < Categories > categories = new ArrayList<>();

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public ArrayList<Rankings> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<Rankings> rankings) {
        this.rankings = rankings;
    }

    ArrayList < Rankings > rankings = new ArrayList <> ();

}
