package com.ahumada.shop.controller;

import com.ahumada.shop.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<CategoriaDto>>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategories();
        List<CategoriaDto> dtos = categoriaMapper.toDtoList(categorias);
        ApiResponse<List<CategoriaDto>> response = ApiResponse.<List<CategoriaDto>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(dtos)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDto>> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoryById(id);
        ApiResponse<CategoriaDto> response = ApiResponse.<CategoriaDto>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(categoriaMapper.toDto(categoria))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<CategoriaDto>> crearCategoria(@RequestBody @Validated CategoriaDto dto) {
        Categoria entidad = categoriaMapper.toEntity(dto);
        Categoria creado = categoriaService.createCategory(entidad);
        ApiResponse<CategoriaDto> response = ApiResponse.<CategoriaDto>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .data(categoriaMapper.toDto(creado))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDto>> actualizarCategoria(@PathVariable Long id, @RequestBody @Validated CategoriaDto dto) {
        Categoria entidad = categoriaMapper.toEntity(dto);
        Categoria actualizado = categoriaService.updateCategory(id, entidad);
        ApiResponse<CategoriaDto> response = ApiResponse.<CategoriaDto>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(categoriaMapper.toDto(actualizado))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCategoria(@PathVariable Long id) {
        categoriaService.deleteCategory(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.NO_CONTENT.value())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/arbol")
    public ResponseEntity<ApiResponse<List<CategoriaDto>>> obtenerArbolCategorias() {
        List<Categoria> arbol = categoriaService.getCategoryTree();
        List<CategoriaDto> dtos = categoriaMapper.toDtoList(arbol);
        ApiResponse<List<CategoriaDto>> response = ApiResponse.<List<CategoriaDto>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(dtos)
                .build();
        return ResponseEntity.ok(response);
    }
}
