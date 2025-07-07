package com.ahumada.shop.mapper;

import com.ahumada.shop.dto.CategoriaDto;
import com.ahumada.shop.entity.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    public CategoriaDto toDto(Categoria entity) {
        if (entity == null) {
            return null;
        }
        CategoriaDto.CategoriaDtoBuilder builder = CategoriaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .urlAmigable(entity.getUrlAmigable())
                .padreId(entity.getPadre() != null ? entity.getPadre().getId() : null);
        if (entity.getHijos() != null && !entity.getHijos().isEmpty()) {
            builder.hijos(entity.getHijos().stream().map(this::toDto).collect(Collectors.toList()));
        }
        return builder.build();
    }

    public List<CategoriaDto> toDtoList(List<Categoria> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Categoria toEntity(CategoriaDto dto) {
        if (dto == null) {
            return null;
        }
        Categoria.CategoriaBuilder builder = Categoria.builder()
                .nombre(dto.getNombre())
                .urlAmigable(dto.getUrlAmigable());
        if (dto.getPadreId() != null) {
            Categoria padre = new Categoria();
            padre.setId(dto.getPadreId());
            builder.padre(padre);
        }
        if (dto.getHijos() != null && !dto.getHijos().isEmpty()) {
            Set<Categoria> hijos = dto.getHijos().stream().map(this::toEntity).collect(Collectors.toSet());
            builder.hijos(hijos);
        }
        Categoria categoria = builder.build();
        categoria.setId(dto.getId());
        return categoria;
    }
}
