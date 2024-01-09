package com.iesvjp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iesvjp.model.Categoria;
import com.iesvjp.repository.ICategoriaRepository;

@Service("categoriaService")
public class CategoriaService implements ICategoriaService {

	@Autowired
	@Qualifier("categoriaRepository")
	private ICategoriaRepository categoriaRepository;

	@Override
	public Categoria addCategoria(Categoria categoriaModel) {
		Categoria categoria = categoriaRepository.save(categoriaModel);
		return categoria;
	}

	@Override
	public List<Categoria> listAllCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}

	@Override
	public Categoria findCategoriaById(int id) {
		return categoriaRepository.findById(id);
	}

	@Override
	public void removeCategoria(int id) {
		Categoria categoria = findCategoriaById(id);
		if (categoria != null) {
			categoriaRepository.delete(categoria);
		}
	}

}
