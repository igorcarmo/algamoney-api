package com.igorac.algamoney.api.repository;

import com.igorac.algamoney.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
