package com.ahumada.shop.controller;

import com.ahumada.shop.dto.CategoriaDto;
import com.ahumada.shop.entity.Categoria;
import com.ahumada.shop.mapper.CategoriaMapper;
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
public class CategoriaController {

    private final ICategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    @GetMapping("/")
    public ResponseEntity<List<CategoriaDto>> getAllCategories() {
        List<Categoria> categories = categoriaService.getAllCategories();
        List<CategoriaDto> dtos = categoriaMapper.toDtoList(categories);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getCategoryById(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoryById(id);
        return ResponseEntity.ok(categoriaMapper.toDto(categoria));
    }

    @PostMapping("/")
    public ResponseEntity<CategoriaDto> createCategory(@RequestBody @Validated CategoriaDto dto) {
        Categoria entity = categoriaMapper.toEntity(dto);
        Categoria created = categoriaService.createCategory(entity);
        return new ResponseEntity<>(categoriaMapper.toDto(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateCategory(@PathVariable Long id, @RequestBody @Validated CategoriaDto dto) {
        Categoria entity = categoriaMapper.toEntity(dto);
        Categoria updated = categoriaService.updateCategory(id, entity);
        return ResponseEntity.ok(categoriaMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoriaService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/arbol")
    public ResponseEntity<List<CategoriaDto>> getCategoryTree() {
        List<Categoria> tree = categoriaService.getCategoryTree();
        List<CategoriaDto> dtos = categoriaMapper.toDtoList(tree);
        return ResponseEntity.ok(dtos);
    }
}
