package org.example.freightr.TableCreation;

import java.util.Date;

public class Package {
    private int packageId;
    private String packageDescription;
    private Date sentDate;
    private double weight;
    private double height;
    private double length;
    private double breadth;
    private double price;

    public Package(int packageId, String packageDescription, Date sentDate, double weight, double height, double length, double breadth, double price) {
        this.packageId = packageId;
        this.packageDescription = packageDescription;
        this.sentDate = sentDate != null ? sentDate : new Date();
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.breadth = breadth;
        this.price = price;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Package ID: " + packageId + ", Description: " + packageDescription;
    }
}
