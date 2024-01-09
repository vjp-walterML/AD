package com.iesvjp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesvjp.model.Categoria;
import com.iesvjp.service.ICategoriaService;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

	private static final Log LOG = LogFactory.getLog(CategoriaController.class);
	private static final String LISTADO_CATEGORIAS = "admin/list-categoria";
	private static final String FORMULARIO_CATEGORIA = "admin/form-categoria";

	@GetMapping("/")
	public ModelAndView listadoCategorias() {
		ModelAndView mav = new ModelAndView(LISTADO_CATEGORIAS);
		mav.addObject("categoriaModel", categoriaService.listAllCategorias());
		return mav;
	}

	@GetMapping("/nueva")
	public String showCategoriaForm(@RequestParam(name = "id", required = false) Integer id, Model model) {
		Categoria categoria = new Categoria();
		// Si el id Categoria es distinto de 0 ya existe
		if (id == null)
			id = 0;
		if (id != 0) {
			categoria = categoriaService.findCategoriaById(id);
		}
		model.addAttribute("categoriaModel", categoria);
		return FORMULARIO_CATEGORIA;
	}

	@PostMapping("/addCategoria")
	public String addCategoria(@ModelAttribute("categoriaModel") Categoria categoriaModel, RedirectAttributes ra) {
		LOG.info("METHOD: addContact -- PARAMS: " + categoriaModel);
		if (categoriaService.addCategoria(categoriaModel) != null) {
			ra.addFlashAttribute("result", 1);
		} else {
			ra.addFlashAttribute("result", 0);
		}
		return "redirect:/admin/categoria/";
	}

}
