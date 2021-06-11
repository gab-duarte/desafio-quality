package com.example.quality.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Propriedade {

    private String nome;
    private String bairro;
    private List<Comodo> comodos;

    public Propriedade(String nome, String bairro, List<Comodo> comodos) {
        this.nome = nome;
        this.bairro = bairro;
        this.comodos = comodos;
    }

    public Double totalMetrosQuadradosDaPropriedade(){
        return comodos.stream().mapToDouble(Comodo::metrosQuadrados).sum();
    }

    public Comodo maiorComodo(){
        return this.comodos.stream().max(Comodo::compareTo).orElse(null);
    }

    public Map<String, Double> metrosQuadradosDeCadaComodo(){
        Map<String, Double> metrosQuadrados = new HashMap<>();
        for (Comodo comodo: comodos){
            metrosQuadrados.put(comodo.getName(), comodo.metrosQuadrados());
        }
        return metrosQuadrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public List<Comodo> getComodos() {
        return comodos;
    }

    public void setComodos(List<Comodo> comodos) {
        this.comodos = comodos;
    }

    @Override
    public String toString() {
        return "Propriedade{" +
                "nome='" + nome + '\'' +
                ", bairro='" + bairro + '\'' +
                ", comodos=" + comodos +
                '}';
    }
}
