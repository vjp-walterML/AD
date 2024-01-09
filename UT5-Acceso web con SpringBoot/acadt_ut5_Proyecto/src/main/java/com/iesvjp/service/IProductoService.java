package com.iesvjp.service;

import java.util.List;

import com.iesvjp.model.Producto;

public interface IProductoService {
	
	public Producto addProducto(Producto productoModel);

	public List<Producto> listAllProductos();

	public Producto findProductoById(int id);

	public void removeProducto(int id);
}
