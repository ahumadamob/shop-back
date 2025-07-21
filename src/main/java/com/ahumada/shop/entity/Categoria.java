package com.ahumada.shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias", uniqueConstraints = @UniqueConstraint(columnNames = "url_amigable"))
public class Categoria extends BaseEntity {

    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @Column(name = "url_amigable", nullable = true, length = 50, unique = true)
    @Size(min = 3, max = 50, message = "La URL amigable debe tener entre 3 y 50 caracteres.")
    @Pattern(regexp = "^[a-z0-9\\-]+$", message = "La URL amigable s\u00f3lo puede contener min\u00fasculas, d\u00edgitos y guiones.")
    private String urlAmigable;

}
