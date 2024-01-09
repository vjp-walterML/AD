package com.iesvjp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesvjp.model.Categoria;

@Repository("categoriaRepository")
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
	public Categoria findById(int id);
}
