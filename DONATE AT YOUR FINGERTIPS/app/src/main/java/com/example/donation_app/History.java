package com.example.donation_app;

public class History {

    String dname,number,address,qty,item,rname,raddress,rnumber;

    public History() {
    }

    public History(String dname, String number, String address, String qty, String item, String rname, String raddress, String rnumber) {
        this.dname = dname;
        this.number = number;
        this.address = address;
        this.qty = qty;
        this.item = item;
        this.rname = rname;
        this.raddress = raddress;
        this.rnumber = rnumber;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRaddress() {
        return raddress;
    }

    public void setRaddress(String raddress) {
        this.raddress = raddress;
    }

    public String getRnumber() {
        return rnumber;
    }

    public void setRnumber(String rnumber) {
        this.rnumber = rnumber;
    }
}
