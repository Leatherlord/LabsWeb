package com.example.Second_Lab;

public class Lane {
    final private double x;
    final private double y;
    final private double r;
    final private boolean isInArea;
    private int identifier = 0;

    public Lane(double x, double y, double r, boolean isInArea) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = isInArea;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
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

    @Override
    public String toString() {
        return "    {\n" +
                "       \"x\": \"" + getX() + "\",\n" +
                "       \"y\": \"" + getY() + "\",\n" +
                "       \"r\": \"" + getR() + "\",\n" +
                "       \"result\": \"" + isInArea() + "\"\n" +
                "   }";
    }
}
