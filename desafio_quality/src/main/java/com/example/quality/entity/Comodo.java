package com.example.quality.entity;

public class Comodo implements Comparable<Comodo> {

    private String name;
    private Double width;
    private Double length;

    public Comodo(String name, Double width, Double length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public Double metrosQuadrados(){
        return this.width * this.length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Comodo{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                '}';
    }

    @Override
    public int compareTo(Comodo o) {
        return this.metrosQuadrados().compareTo(o.metrosQuadrados());
    }
}
