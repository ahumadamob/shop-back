package com.miempresa.catalogo.service;

import com.miempresa.catalogo.dto.CategoriaDto;

import java.util.List;

public interface CategoryService {

    List<CategoriaDto> getAllCategories();

    CategoriaDto getCategoryById(Long id);

    CategoriaDto createCategory(CategoriaDto dto);

    CategoriaDto updateCategory(Long id, CategoriaDto dto);

    void deleteCategory(Long id);

    List<CategoriaDto> getCategoryTree();
}
