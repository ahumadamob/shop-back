package com.miempresa.catalogo.service;

import com.miempresa.catalogo.dto.CategoriaDto;
import com.miempresa.catalogo.entity.Categoria;
import com.miempresa.catalogo.exception.ResourceNotFoundException;
import com.miempresa.catalogo.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDto> getAllCategories() {
        return categoriaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDto getCategoryById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        return toDto(categoria);
    }

    @Override
    public CategoriaDto createCategory(CategoriaDto dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setUrlAmigable(dto.getUrlAmigable());
        if (dto.getPadreId() != null) {
            Categoria padre = categoriaRepository.findById(dto.getPadreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Padre no encontrado"));
            categoria.setPadre(padre);
        }
        categoriaRepository.save(categoria);
        return toDto(categoria);
    }

    @Override
    public CategoriaDto updateCategory(Long id, CategoriaDto dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        categoria.setNombre(dto.getNombre());
        categoria.setUrlAmigable(dto.getUrlAmigable());
        Categoria nuevoPadre = null;
        if (dto.getPadreId() != null) {
            nuevoPadre = categoriaRepository.findById(dto.getPadreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Padre no encontrado"));
            if (isDescendant(categoria, nuevoPadre)) {
                throw new IllegalArgumentException("Ciclo en jerarquía de categorías");
            }
        }
        categoria.setPadre(nuevoPadre);
        categoriaRepository.save(categoria);
        return toDto(categoria);
    }

    @Override
    public void deleteCategory(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<CategoriaDto> getCategoryTree() {
        return categoriaRepository.findByPadreIsNull().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CategoriaDto toDto(Categoria entity) {
        return CategoriaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .urlAmigable(entity.getUrlAmigable())
                .padreId(entity.getPadre() != null ? entity.getPadre().getId() : null)
                .hijos(entity.getHijos().stream().map(this::toDto).collect(Collectors.toList()))
                .build();
    }

    private boolean isDescendant(Categoria origin, Categoria target) {
        if (target == null) {
            return false;
        }
        Categoria parent = target.getPadre();
        while (parent != null) {
            if (parent.equals(origin)) {
                return true;
            }
            parent = parent.getPadre();
        }
        return false;
    }
}
