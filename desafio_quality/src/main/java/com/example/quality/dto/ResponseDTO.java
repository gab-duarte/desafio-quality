package com.example.quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private Double valorM2;
    private Double valorPropriedade;
    private List<Double> m2PorComodo;

    public ResponseDTO(Double valorM2, Double valorPropriedade, List<Double> m2PorComodo) {
        this.valorM2 = valorM2;
        this.valorPropriedade = valorPropriedade;
        this.m2PorComodo = m2PorComodo;
    }

    public Double getValorM2() {
        return valorM2;
    }

    public void setValorM2(Double valorM2) {
        this.valorM2 = valorM2;
    }

    public Double getValorPropriedade() {
        return valorPropriedade;
    }

    public void setValorPropriedade(Double valorPropriedade) {
        this.valorPropriedade = valorPropriedade;
    }

    public List<Double> getM2PorComodo() {
        return m2PorComodo;
    }

    public void setM2PorComodo(List<Double> m2PorComodo) {
        this.m2PorComodo = m2PorComodo;
    }
}
