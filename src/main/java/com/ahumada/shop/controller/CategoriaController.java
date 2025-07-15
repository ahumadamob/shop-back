package com.ahumada.shop.controller;

import com.ahumada.shop.dto.ApiResponse;
import com.ahumada.shop.dto.CategoriaRequestDto;
import com.ahumada.shop.dto.CategoriaResponseDto;
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
    public ResponseEntity<ApiResponse<List<CategoriaResponseDto>>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategories();
        List<CategoriaResponseDto> dtos = categoriaMapper.toResponseDtoList(categorias);
        ApiResponse<List<CategoriaResponseDto>> response = ApiResponse.<List<CategoriaResponseDto>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(dtos)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaResponseDto>> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.getCategoryById(id);
        ApiResponse<CategoriaResponseDto> response = ApiResponse.<CategoriaResponseDto>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(categoriaMapper.toResponseDto(categoria))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<CategoriaResponseDto>> crearCategoria(@RequestBody @Validated CategoriaRequestDto dto) {
        Categoria entidad = categoriaMapper.toEntity(dto);
        Categoria creado = categoriaService.createCategory(entidad);
        ApiResponse<CategoriaResponseDto> response = ApiResponse.<CategoriaResponseDto>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .data(categoriaMapper.toResponseDto(creado))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaResponseDto>> actualizarCategoria(@PathVariable Long id, @RequestBody @Validated CategoriaRequestDto dto) {
        Categoria entidad = categoriaMapper.toEntity(dto);
        Categoria actualizado = categoriaService.updateCategory(id, entidad);
        ApiResponse<CategoriaResponseDto> response = ApiResponse.<CategoriaResponseDto>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(categoriaMapper.toResponseDto(actualizado))
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
    public ResponseEntity<ApiResponse<List<CategoriaResponseDto>>> obtenerArbolCategorias() {
        List<Categoria> arbol = categoriaService.getCategoryTree();
        List<CategoriaResponseDto> dtos = categoriaMapper.toResponseDtoList(arbol);
        ApiResponse<List<CategoriaResponseDto>> response = ApiResponse.<List<CategoriaResponseDto>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .data(dtos)
                .build();
        return ResponseEntity.ok(response);
    }
}
