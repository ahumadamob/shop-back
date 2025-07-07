package com.ahumadamob.shop.service.jpa;

import com.ahumada.shop.entity.Categoria;
import com.ahumada.shop.exception.ResourceNotFoundException;
import com.ahumada.shop.repository.CategoriaRepository;
import com.ahumada.shop.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Categoria parent = null;
        if (categoria.getPadre() != null && categoria.getPadre().getId() != null) {
            parent = categoriaRepository.findById(categoria.getPadre().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Padre no encontrado"));
            categoria.setPadre(parent);
        } else {
            categoria.setPadre(null);
        }
        categoriaRepository.save(categoria);
        return categoria;
    }

    @Override
    public Categoria updateCategory(Long id, Categoria categoria) {
        Categoria existing = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        existing.setNombre(categoria.getNombre());
        existing.setUrlAmigable(categoria.getUrlAmigable());

        Categoria nuevoPadre = null;
        if (categoria.getPadre() != null && categoria.getPadre().getId() != null) {
            nuevoPadre = categoriaRepository.findById(categoria.getPadre().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Padre no encontrado"));
            if (isDescendant(existing, nuevoPadre)) {
                throw new IllegalArgumentException("Ciclo en jerarquía de categorías");
            }
        }
        existing.setPadre(nuevoPadre);
        categoriaRepository.save(existing);
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
        return categoriaRepository.findByPadreIsNull();
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
