package com.ahumada.shop.repository;

import com.ahumada.shop.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByPadreIsNull();
}
