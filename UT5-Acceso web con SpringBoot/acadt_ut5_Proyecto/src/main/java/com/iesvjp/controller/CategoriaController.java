package com.iesvjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaController {
	
	private static final String LISTADO_CATEGORIAS = "admin/list-categoria";

	@GetMapping("/")
	public String listadoCategorias() {
		return LISTADO_CATEGORIAS;
	}
}
