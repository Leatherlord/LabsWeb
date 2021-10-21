package com.example.Second_Lab;

import java.util.Date;

public class Lane {
    final private double x;
    final private double y;
    final private double r;
    final private boolean isInArea;
    final private Date creationDate;
    final private boolean isError;
    final private String message;

    public Lane(double x, double y, double r, boolean isInArea, Date creationDate, boolean isError, String message) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = isInArea;
        this.creationDate = creationDate;
        this.isError = isError;
        this.message = message;
    }


    public boolean isInArea() {
        return isInArea;
    }

    public double getR() {
        return r;
    }

    public double getX() {
        return x;
    }

    public double getY() {

        return y;

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isError() {
        return isError;
    }

    public String getMessage() {
        return message;
    }
}