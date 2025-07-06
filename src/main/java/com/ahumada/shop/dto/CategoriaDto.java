package com.ahumada.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaDto {

    private Long id;
    private String nombre;
    private String urlAmigable;
    private Long padreId;
    private List<CategoriaDto> hijos;
}
