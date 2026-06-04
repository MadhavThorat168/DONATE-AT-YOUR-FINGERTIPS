package com.example.donation_app;

public class Cloth {


    String item, qty, name, address, number, imageurl, lati, longi;

    public Cloth() {
    }

    public Cloth(String item, String qty, String name, String address, String number, String imageurl, String lati, String longi) {
        this.item = item;
        this.qty = qty;
        this.name = name;
        this.address = address;
        this.number = number;
        this.imageurl = imageurl;
        this.lati = lati;
        this.longi = longi;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}



