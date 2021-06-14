package com.example.quality.integration;

import com.example.quality.controller.PropriedadeController;
import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.M2PorComodoDTO;
import com.example.quality.service.PropriedadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(PropriedadeController.class)
public class PropriedadeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PropriedadeService propriedadeServiceMock;

    @BeforeEach
    public void setup(){
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnM2Propriedade() throws Exception {
        Double m2 = 346.0;
        when(propriedadeServiceMock.getTotalM2Propriedade("Mansao")).thenReturn(m2);

        this.mockMvc.perform(get("/propriedade/{propriedadeNome}/totalm2", "Mansao"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valorM2").value(346.0));
    }

    @Test
    public void shouldReturnValorPropriedade() throws Exception {
        Double valor = 34600.0;
        when(propriedadeServiceMock.getValorPropriedade("Mansao")).thenReturn(valor);

        this.mockMvc.perform(get("/propriedade/{propriedadeNome}/valor", "Mansao"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.valorPropriedade").value(34600.0));
    }

    @Test
    public void shouldReturnMaiorComodo() throws Exception {
        ComodoDTO comodoDTO = new ComodoDTO("Sala", 12.5, 15.5);
        when(propriedadeServiceMock.getMaiorComodo("Mansao")).thenReturn(comodoDTO);

        this.mockMvc.perform(get("/propriedade/{propriedadeNome}/maiorComodo", "Mansao"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Sala"))
                .andExpect(jsonPath("$.width").value(12.5))
                .andExpect(jsonPath("$.length").value(15.5));

    }

    @Test
    public void shoudReturnM2PorComodo() throws Exception {
        List<M2PorComodoDTO> comodos = new ArrayList<>();

        comodos.add(new M2PorComodoDTO("Sala", 9.79));
        comodos.add(new M2PorComodoDTO("Quarto", 6.40));

        when(propriedadeServiceMock.getM2PorComodo("Mansao")).thenReturn(comodos);

        this.mockMvc.perform(get("/propriedade/{propriedadeNome}/m2PorComodo", "Mansao"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.comodos[0].comodo").value("Sala"))
                .andExpect(jsonPath("$.comodos[0].m2").value(9.79))
                .andExpect(jsonPath("$.comodos[1].comodo").value("Quarto"))
                .andExpect(jsonPath("$.comodos[1].m2").value(6.40));
    }
}
