package com.example.quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropriedadeDTO {

    @NotBlank(message = "O nome da Propriedade não pode estar vazio")
    @Pattern(regexp = "[A-Z]+[a-zA-Zà-úÀ-Ú\\s]*", message = "O nome da Propriedade deve começar com uma letra maiúscula")
    @Size(max = 30, message = "O comprimento do nome da Propriedade não pode exceder 30 caracteres")
    private String nome;


    @NotBlank(message = "O bairro não pode estar vazio")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres")
    private String bairro;

    @Valid
    @NotNull
    private List<ComodoDTO> comodos;

    public PropriedadeDTO(String nome, String bairro, List<ComodoDTO> comodos) {
        this.nome = nome;
        this.bairro = bairro;
        this.comodos = comodos;
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

    public List<ComodoDTO> getComodos() {
        return comodos;
    }

    public void setComodos(List<ComodoDTO> comodos) {
        this.comodos = comodos;
    }

}
