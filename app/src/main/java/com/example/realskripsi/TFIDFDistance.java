package com.example.realskripsi;

/**
 * Created by ASUS A46CM on 2018/05/27.
 */

public class TFIDFDistance {
    private String name;
    private double value;
    private int parent;

    public TFIDFDistance() {

    }

    public TFIDFDistance(String name, double value, int parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
