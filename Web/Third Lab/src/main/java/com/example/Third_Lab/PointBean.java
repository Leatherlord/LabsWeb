package com.example.Third_Lab;


import java.sql.Timestamp;
import java.util.Date;

public class PointBean {
    private double X = 0;
    private double Y = 0;
    private double R = 4;
    private Timestamp date = new Timestamp(new Date().getTime());

    public boolean getResult() {
        if ((X <= 0 && Y >= 0 && X >= -R/2 && Y <= R ) ||
                (X >= 0 && Y >= 0 && Y <= -2*X + R) ||
                (X >= 0 && Y <= 0 && (Math.pow(X, 2) + Math.pow(Y, 2) <= Math.pow(R, 2)) )
        ) {
            return true;
        }
        return false;
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

    public double getX() {
        return X;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setX(double x) {
        X = x;
    }
}
