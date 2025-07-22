package com.ahumada.shop.service.jpa;

import com.ahumada.shop.entity.Categoria;
import com.ahumada.shop.exception.ResourceNotFoundException;
import com.ahumada.shop.exception.DuplicateResourceException;
import com.ahumada.shop.repository.CategoriaRepository;
import com.ahumada.shop.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoryById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
    }

    @Override
    public Categoria createCategory(Categoria categoria) {
        // Las categorías ya no tienen relaciones jerárquicas
        try {
            categoriaRepository.save(categoria);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Categoría duplicada");
        }
        return categoria;
    }

    @Override
    public Categoria updateCategory(Long id, Categoria categoria) {
        Categoria existing = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        existing.setNombre(categoria.getNombre());
        existing.setUrlAmigable(categoria.getUrlAmigable());

        try {
            categoriaRepository.save(existing);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Categoría duplicada");
        }
        return existing;
    }

    @Override
    public void deleteCategory(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<Categoria> getCategoryTree() {
        return categoriaRepository.findAll();
    }
}
