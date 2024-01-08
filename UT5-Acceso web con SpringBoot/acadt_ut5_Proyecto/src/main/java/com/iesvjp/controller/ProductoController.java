package com.iesvjp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/producto")
public class ProductoController {

	private static final String LISTADO_PRODUCTOS = "admin/list-producto";

	@GetMapping("/")
	public String listadoCategorias() {
		return LISTADO_PRODUCTOS;
	}
}
