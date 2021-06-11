package com.example.quality.controller;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.PropriedadeDTO;
import com.example.quality.exception.BairroNotFoundException;
import com.example.quality.exception.PropriedadeNotFoundException;
import com.example.quality.service.PropriedadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/propriedade")
public class PropriedadeController {


    private final PropriedadeService propriedadeService;

    public PropriedadeController(PropriedadeService propriedadeService){
        this.propriedadeService = propriedadeService;
    }

    @PostMapping
    public ResponseEntity<?> salvaPropriedade(@Valid @RequestBody PropriedadeDTO propriedade) throws BairroNotFoundException {
        return propriedadeService.salvaPropriedade(propriedade);
    }

    @GetMapping(path = "/{propriedadeNome}/totalm2")
    public ResponseEntity<?> m2Propriedade(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        return propriedadeService.getTotalM2Propriedade(propriedadeNome);
    }

    @GetMapping(path = "/{propriedadeNome}/valor")
    public ResponseEntity<?> valorPropriedade(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        return propriedadeService.getValorPropriedade(propriedadeNome);
    }

    @GetMapping(path = "/{propriedadeNome}/maiorComodo")
    public ComodoDTO maiorComodo(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        return propriedadeService.getMaiorComodo(propriedadeNome);
    }

    @GetMapping(path = "/{propriedadeNome}/m2PorComodo")
    public List<Double> m2PorComodo(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        return propriedadeService.getM2PorComodo(propriedadeNome);
    }

}
