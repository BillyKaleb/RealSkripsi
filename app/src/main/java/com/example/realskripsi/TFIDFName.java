package com.example.realskripsi;

/**
 * Created by ASUS A46CM on 2018/05/27.
 */

public class TFIDFName {
    private String name;
    private double value;

    public TFIDFName() {

    }

    public TFIDFName(String name, double value) {
        this.name = name;
        this.value = value;
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
}
