package com.example.quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private Double valorM2;
    private Double valorPropriedade;
    private List<M2PorComodoDTO> comodos;

    public ResponseDTO(Double valorM2, Double valorPropriedade, List<M2PorComodoDTO> comodos) {
        this.valorM2 = valorM2;
        this.valorPropriedade = valorPropriedade;
        this.comodos = comodos;
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

    public List<M2PorComodoDTO> getComodos() {
        return comodos;
    }

    public void setComodos(List<M2PorComodoDTO> comodos) {
        this.comodos = comodos;
    }
}
