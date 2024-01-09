package com.iesvjp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesvjp.model.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Long> {
	public Producto findById(int id);
}
