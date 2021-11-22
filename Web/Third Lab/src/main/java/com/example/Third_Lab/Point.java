package com.example.Third_Lab;


import java.sql.Timestamp;

public class Point {
    private double X;
    private double Y;
    private double R;
    private Timestamp date;
    private boolean result;


    public Point() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }
}
