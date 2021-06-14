package com.example.quality.units;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.PropriedadeDTO;
import com.example.quality.exception.BairroNotFoundException;
import com.example.quality.exception.PropriedadeNotFoundException;
import com.example.quality.service.PropriedadeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class PropriedadeServiceTest {

    PropriedadeService propriedadeService = new PropriedadeService();
    List<ComodoDTO> comodos = new ArrayList<>();
    PropriedadeDTO propriedadeDTO;

    @BeforeEach
    public void setup() throws BairroNotFoundException {
        this.comodos.add(new ComodoDTO("Sala", 3.87, 2.53));
        this.comodos.add(new ComodoDTO("Quarto", 2.53, 2.53));
        this.propriedadeDTO = new PropriedadeDTO("Casa do Gabriel", "Icaraí", comodos);
        this.propriedadeService.salvaPropriedade(propriedadeDTO);
    }

    @Test
    void salvaPropriedade() throws BairroNotFoundException, PropriedadeNotFoundException {
        propriedadeDTO.setNome("Outra casa");
        propriedadeDTO.setBairro("Mutondo");
        propriedadeService.salvaPropriedade(propriedadeDTO);
        Assertions.assertEquals("Outra casa" , propriedadeService.verifyIfPropriedadeExists(propriedadeDTO.getNome()).getNome());
        Assertions.assertEquals("Mutondo" , propriedadeService.verifyIfPropriedadeExists(propriedadeDTO.getNome()).getBairro());
    }

    @Test
    void getTotalM2PropriedadeTeste() throws PropriedadeNotFoundException {
        Assertions.assertEquals(16.19, this.propriedadeService.getTotalM2Propriedade(propriedadeDTO.getNome()));
    }

    @Test
    void valorPropriedade() throws PropriedadeNotFoundException {
        Assertions.assertEquals(64760.0, this.propriedadeService.getValorPropriedade(propriedadeDTO.getNome()));
    }

    @Test
    void verifyIfPropriedadeExistsTest() throws PropriedadeNotFoundException {
        Assertions.assertEquals("Casa do Gabriel", this.propriedadeService.verifyIfPropriedadeExists(propriedadeDTO.getNome()).getNome());
        Assertions.assertThrows(PropriedadeNotFoundException.class, ()-> this.propriedadeService.verifyIfPropriedadeExists("Mansão do Gabriel"));
        Assertions.assertThrows(PropriedadeNotFoundException.class, ()-> this.propriedadeService.verifyIfPropriedadeExists("Casa do Prefeito"));
    }

    @Test
    void verifyIfBairroExistsTest() throws BairroNotFoundException {
        Assertions.assertTrue(this.propriedadeService.verifyIfBairroExists("Fonseca"));
        Assertions.assertThrows(BairroNotFoundException.class, ()-> this.propriedadeService.verifyIfBairroExists("Brasilia"));
        Assertions.assertThrows(BairroNotFoundException.class, ()-> this.propriedadeService.verifyIfBairroExists("Rio de Janeiro"));
        Assertions.assertThrows(BairroNotFoundException.class, ()-> this.propriedadeService.verifyIfBairroExists("São Paulo"));
    }

    @Test
    void getMaiorComodo() throws PropriedadeNotFoundException {
        Assertions.assertEquals("Sala", this.propriedadeService.getMaiorComodo(propriedadeDTO.getNome()).getName());
    }

    @Test
    void totalM2PorComodo() throws PropriedadeNotFoundException {
        final Double[] m2PorComodoArray = new Double[] {9.79, 6.40};
        Assertions.assertArrayEquals(m2PorComodoArray, this.propriedadeService.getM2PorComodo(propriedadeDTO.getNome()).toArray());
    }
}
