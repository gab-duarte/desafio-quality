package com.example.quality.units.service;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.M2PorComodoDTO;
import com.example.quality.dto.PropriedadeDTO;
import com.example.quality.exception.BairroNotFoundException;
import com.example.quality.exception.PropriedadeNotFoundException;
import com.example.quality.service.PropriedadeService;
import org.assertj.core.groups.Tuple;
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
    void shouldReturnTotalM2Propriedade() throws PropriedadeNotFoundException {
        Assertions.assertEquals(16.19, this.propriedadeService.getTotalM2Propriedade(propriedadeDTO.getNome()));
    }

    @Test
    void shouldReturnValorPropriedade() throws PropriedadeNotFoundException {
        Assertions.assertEquals(64760.0, this.propriedadeService.getValorPropriedade(propriedadeDTO.getNome()));
    }

    @Test
    void verifyIfPropriedadeExists() throws PropriedadeNotFoundException {
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
    void shouldReturnMaiorComodo() throws PropriedadeNotFoundException {
        Assertions.assertEquals("Sala", this.propriedadeService.getMaiorComodo(propriedadeDTO.getNome()).getName());
    }

    @Test
    void shouldReturnTotalM2PorComodo() throws PropriedadeNotFoundException {

        List<M2PorComodoDTO> comodos = propriedadeService.getM2PorComodo(propriedadeDTO.getNome());

        org.assertj.core.api.Assertions.assertThat(comodos)
                .extracting(
                        M2PorComodoDTO::getComodo,
                        M2PorComodoDTO::getM2
                )
                .contains(Tuple.tuple("Sala", 9.79), Tuple.tuple("Quarto", 6.40));
    }
}
