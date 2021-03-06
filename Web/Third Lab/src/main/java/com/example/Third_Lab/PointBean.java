package com.example.Third_Lab;


import java.sql.Timestamp;
import java.util.Date;


public class PointBean {
    private double X;
    private double Y;
    private double R;
    private Timestamp date = new Timestamp(new Date().getTime());

    public boolean getResult() {
        return (X <= 0 && Y >= 0 && X >= -R / 2 && Y <= R) ||
                (X >= 0 && Y >= 0 && Y <= -2 * X + R) ||
                (X >= 0 && Y <= 0 && (Math.pow(X, 2) + Math.pow(Y, 2) <= Math.pow(R, 2)));
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
