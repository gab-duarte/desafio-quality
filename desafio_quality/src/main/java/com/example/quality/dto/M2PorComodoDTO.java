package com.example.quality.dto;

public class M2PorComodoDTO {

    private String comodo;
    private Double m2;

    public M2PorComodoDTO(String comodo, Double m2) {
        this.comodo = comodo;
        this.m2 = m2;
    }

    public String getComodo() {
        return comodo;
    }

    public void setComodo(String comodo) {
        this.comodo = comodo;
    }

    public Double getM2() {
        return m2;
    }

    public void setM2(Double m2) {
        this.m2 = m2;
    }
}
