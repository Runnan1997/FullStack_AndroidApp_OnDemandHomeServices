package com.uottawa.runnan.seg_deliberable1.Model;

public class Product {
    private String _id;
    private String _productname;
    private double _price;
    private String _name;

    public Product(String _productname, String _name) {
        this._productname = _productname;
        this._name = _name;
    }


    public String get_name() {
        return _name;
    }
    public void set_name(String _name) {
        this._name = _name;
    }

    public Product() {
    }
    public Product(String id, String productname, double price) {
        _id = id;
        _productname = productname;
        _price = price;
    }
    public Product(String productname) {
        _productname = productname;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setProductName(String productname) {
        _productname = productname;
    }
    public String getProductName() {
        return _productname;
    }
    public void setPrice(double price) {
        _price = price;
    }
    public double getPrice() {
        return _price;
    }
}

