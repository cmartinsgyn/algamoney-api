package com.algaworks.algamoney.api.repository;

import com.algaworks.algamoney.api.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categorias, Long> {

}
