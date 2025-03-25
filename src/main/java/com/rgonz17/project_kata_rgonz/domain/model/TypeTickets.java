package com.rgonz17.project_kata_rgonz.domain.model;

public class TypeTickets {
    private int total;
    private int sold;

    private double price;

    public TypeTickets() {
    }

    public TypeTickets(int total, int sold, double price) {
        this.total = total;
        this.sold = sold;
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
