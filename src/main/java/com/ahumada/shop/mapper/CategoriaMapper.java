package com.ahumada.shop.mapper;

import com.ahumada.shop.dto.CategoriaRequestDto;
import com.ahumada.shop.dto.CategoriaResponseDto;
import com.ahumada.shop.entity.Categoria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {

    public CategoriaResponseDto toResponseDto(Categoria entity) {
        if (entity == null) {
            return null;
        }
        CategoriaResponseDto.CategoriaResponseDtoBuilder builder = CategoriaResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .urlAmigable(entity.getUrlAmigable())
                .padreId(entity.getPadre() != null ? entity.getPadre().getId() : null);
        if (entity.getHijos() != null && !entity.getHijos().isEmpty()) {
            builder.hijos(entity.getHijos().stream().map(this::toResponseDto).collect(Collectors.toList()));
        }
        return builder.build();
    }

    public List<CategoriaResponseDto> toResponseDtoList(List<Categoria> entities) {
        return entities.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public Categoria toEntity(CategoriaRequestDto dto) {
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
        return builder.build();
    }
}
