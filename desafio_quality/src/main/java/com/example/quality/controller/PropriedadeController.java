package com.example.quality.controller;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.M2PorComodoDTO;
import com.example.quality.dto.PropriedadeDTO;
import com.example.quality.dto.ResponseDTO;
import com.example.quality.exception.BairroNotFoundException;
import com.example.quality.exception.PropriedadeNotFoundException;
import com.example.quality.service.PropriedadeService;
import org.springframework.http.HttpStatus;
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
        propriedadeService.salvaPropriedade(propriedade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{propriedadeNome}/totalm2")
    public ResponseEntity<ResponseDTO> m2Propriedade(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        Double m2Propriedade = propriedadeService.getTotalM2Propriedade(propriedadeNome);
        ResponseDTO responseDTO = new ResponseDTO(m2Propriedade, null, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{propriedadeNome}/valor")
    public ResponseEntity<ResponseDTO> valorPropriedade(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        Double valorPropriedade =  propriedadeService.getValorPropriedade(propriedadeNome);
        ResponseDTO responseDTO = new ResponseDTO(null, valorPropriedade, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{propriedadeNome}/maiorComodo")
    public ResponseEntity<ComodoDTO> maiorComodo(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        ComodoDTO comodoDTO = propriedadeService.getMaiorComodo(propriedadeNome);
        return new ResponseEntity<>(comodoDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{propriedadeNome}/m2PorComodo")
    public ResponseEntity<ResponseDTO> m2PorComodo(@PathVariable String propriedadeNome) throws PropriedadeNotFoundException {
        List<M2PorComodoDTO> comodos = propriedadeService.getM2PorComodo(propriedadeNome);
        ResponseDTO responseDTO = new ResponseDTO(null, null, comodos);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
