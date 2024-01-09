package com.iesvjp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iesvjp.model.Producto;
import com.iesvjp.repository.IProductoRepository;

@Service("ProductoService")
public class ProductoService implements IProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;

	@Override
	public Producto addProducto(Producto productoModel) {
		Producto producto = productoRepository.save(productoModel);
		return producto;
	}

	@Override
	public List<Producto> listAllProductos() {
		List<Producto> productos = productoRepository.findAll();
		return productos;
	}

	@Override
	public Producto findProductoById(int id) {
		return productoRepository.findById(id);
	}

	@Override
	public void removeProducto(int id) {
		Producto producto = findProductoById(id);
		if (producto != null) {
			productoRepository.delete(producto);
		}
	}

}
