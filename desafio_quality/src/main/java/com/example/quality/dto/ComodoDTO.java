package com.example.quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComodoDTO {

    @NotBlank(message = "O nome do Comodo não pode estar vazio")
    @Pattern(regexp = "[A-Z]+[a-zA-Zà-úÀ-Ú\\s]*", message = "O nome do Comodo deve começar com uma letra maiúscula")
    @Size(max = 30, message = "O comprimento do nome do Comodo não pode exceder 30 caracteres")
    private String name;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @DecimalMax(value = "25.0", message = "A largura máxima permitida por cômodo é de 25 metros")
    private Double width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33.0", message = "O comprimento máximo permitido por cômodo é de 25 metros")
    private Double length;

    public ComodoDTO(String name, Double width, Double length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "ComodoDTO{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
