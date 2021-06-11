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
    public void setup() {
        this.comodos.add(new ComodoDTO("Sala", 12.5, 15.5));
        this.comodos.add(new ComodoDTO("Quarto", 10.5, 14.5));
        //this.comodos.add(new ComodoDTO("Banheiro", 12.5, 14.5));

        this.propriedadeDTO = new PropriedadeDTO("Mansão", "Fonseca", comodos);
    }

    @Test
    void getTotalM2PropriedadeTeste() throws PropriedadeNotFoundException {
        Assertions.assertEquals(346.0, this.propriedadeService.getTotalM2Propriedade(propriedadeDTO.getNome()).getBody());
    }

    @Test
    void valorPropriedade() throws PropriedadeNotFoundException {
        Assertions.assertEquals(346000.0, this.propriedadeService.getValorPropriedade(propriedadeDTO.getNome()).getBody());
    }

    @Test
    void propriedadeExiste() throws PropriedadeNotFoundException {
        Assertions.assertEquals("Mansão", this.propriedadeService.verifyIfPropriedadeExists(propriedadeDTO.getNome()).getNome());
        Assertions.assertThrows(PropriedadeNotFoundException.class, ()-> this.propriedadeService.verifyIfPropriedadeExists("Mansão do Gabriel"));
    }

    @Test
    void bairroExiste() throws BairroNotFoundException {
        Assertions.assertThrows(BairroNotFoundException.class, ()-> this.propriedadeService.verifyIfBairroExists("Brasilia"));
        Assertions.assertThrows(BairroNotFoundException.class, ()-> this.propriedadeService.verifyIfBairroExists("Riooooooo"));
        Assertions.assertThrows(BairroNotFoundException.class, ()-> this.propriedadeService.verifyIfBairroExists("São Paulo"));
        Assertions.assertEquals("Fonseca", this.propriedadeService.verifyIfBairroExists("Fonseca"));
    }

    @Test
    void getMaiorComodo() throws PropriedadeNotFoundException {
        Assertions.assertEquals("Sala", this.propriedadeService.getMaiorComodo(propriedadeDTO.getNome()).getName());
    }

    @Test
    void totalM2PorComodo() throws PropriedadeNotFoundException {
        final Double[] m2PorComodoArray = new Double[] {193.75, 152.25};
        Assertions.assertArrayEquals(m2PorComodoArray, this.propriedadeService.getM2PorComodo(propriedadeDTO.getNome()).toArray());
    }
}
