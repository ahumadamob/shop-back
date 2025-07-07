package com.ahumada.shop.service;

import com.ahumada.shop.dto.CategoriaDto;

import java.util.List;

public interface ICategoriaService {

    List<CategoriaDto> getAllCategories();

    CategoriaDto getCategoryById(Long id);

    CategoriaDto createCategory(CategoriaDto dto);

    CategoriaDto updateCategory(Long id, CategoriaDto dto);

    void deleteCategory(Long id);

    List<CategoriaDto> getCategoryTree();
}
