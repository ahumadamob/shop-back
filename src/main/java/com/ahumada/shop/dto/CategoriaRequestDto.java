package com.ahumada.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para crear y actualizar categorias.
 * No expone el identificador de la categoria ni objetos anidados.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDto {

    private String nombre;
    private String urlAmigable;
    private Long padreId;
}

