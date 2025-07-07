package com.ahumada.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para representar categorias en las respuestas de la API.
 * Incluye el identificador y la estructura completa de hijos.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaResponseDto {

    private Long id;
    private String nombre;
    private String urlAmigable;
    private Long padreId;
    private List<CategoriaResponseDto> hijos;
}

