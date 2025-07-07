package com.ahumada.shop.controller;

import com.ahumada.shop.dto.CategoriaDto;
import com.ahumada.shop.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<List<CategoriaDto>> getAllCategories() {
        return ResponseEntity.ok(categoriaService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getCategoryById(id));
    }

    @PostMapping("/")
    public ResponseEntity<CategoriaDto> createCategory(@RequestBody @Validated CategoriaDto dto) {
        CategoriaDto created = categoriaService.createCategory(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategory(@PathVariable Long id, @RequestBody @Validated CategoriaDto dto) {
        CategoriaDto updated = categoriaService.updateCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoriaService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/arbol")
    public ResponseEntity<List<CategoriaDto>> getCategoryTree() {
        return ResponseEntity.ok(categoriaService.getCategoryTree());
    }
}
