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
                .urlAmigable(entity.getUrlAmigable());
        return builder.build();
    }

    public List<CategoriaResponseDto> toResponseDtoList(List<Categoria> entities) {
        return entities.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public Categoria toEntity(CategoriaRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Categoria.builder()
                .nombre(dto.getNombre())
                .urlAmigable(dto.getUrlAmigable())
                .build();
    }
}
