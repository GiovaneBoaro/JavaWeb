package com.manga.appmanga.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.manga.appmanga.model.Manga;
import com.manga.appmanga.repository.MangaRepository;

@Service
@Transactional
@Controller
public class MangaController {

	@Autowired
	private MangaRepository rep;
	
	public List<Manga> listarTodos() {
		return rep.findAll();
	}
	
	public void salvar(Manga man) {
		rep.save(man);
	}
	
	public Manga getManga(long id) {
		return rep.findById(id).get();
	}
	
	public void excluir(long id) {
		rep.deleteById(id);
	}
	
	@RequestMapping("/")
	public String chamarLista(Model modelo) {
		List<Manga> listaMangas = listarTodos();
		modelo.addAttribute("listaMangas", listaMangas);
		return "index";
	}
	
	@RequestMapping("/novo")
	public ModelAndView chamarNovo() {
		ModelAndView modelo = new ModelAndView("novo");
		Manga manga = new Manga();
		modelo.addObject("manga", manga);
		return modelo;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView chamarEditar(@PathVariable(name = "id") long id) {
		ModelAndView modelo = new ModelAndView("editar");
		Manga manga = getManga(id);
		modelo.addObject("manga", manga);
		return modelo;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String chamarSalvar(@ModelAttribute("manga") Manga manga) {
		salvar(manga);
		return "redirect:/";
	}
	
	@RequestMapping("/excluir/{id}")
	public String chamarExcluir(@PathVariable(name = "id") long id) {
		excluir(id);
		return "redirect:/";
	}
	
	
}
