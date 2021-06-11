package com.example.quality.mapper;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.entity.Comodo;

import java.util.ArrayList;
import java.util.List;

public abstract class ComodoMapper {

    public static Comodo comodoDTOtoComodo(ComodoDTO comodoDTO){
        return new Comodo(comodoDTO.getName(), comodoDTO.getWidth(), comodoDTO.getLength());
    }

    public static ComodoDTO comodoToComodoDTO(Comodo comodo){
        return new ComodoDTO(comodo.getName(), comodo.getWidth(), comodo.getLength());
    }

    public static List<Comodo> postsDTOtoComodosList(List<ComodoDTO> comodoDTOS){
        List<Comodo> comodos = new ArrayList<>();
        comodoDTOS.forEach(comodo -> comodos.add(comodoDTOtoComodo(comodo)));
        return comodos;
    }
}
