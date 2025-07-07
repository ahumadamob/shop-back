package com.ahumada.shop.service;

import com.ahumada.shop.entity.Categoria;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> getAllCategories();

    Categoria getCategoryById(Long id);

    Categoria createCategory(Categoria categoria);

    Categoria updateCategory(Long id, Categoria categoria);

    void deleteCategory(Long id);

    List<Categoria> getCategoryTree();
}
