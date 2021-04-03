package com.br.sfb.crcjud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.services.EntidadeService;
import com.br.sfb.crcjud.services.UfService;

@Controller
@RequestMapping("entidades")
public class EntidadesController {

@Autowired
private UfService ufService;
@Autowired
private EntidadeService entidadeService;

@GetMapping
public ModelAndView entidadeIndex(Entidade entidade) {
	ModelAndView mv = new ModelAndView("entidade/entidade");	
	mv.addObject("entidade",entidade);
	mv.addObject("ufs",ufService.findAll());
	return mv ;	
}
@PostMapping
public ModelAndView entidadeSave(@Valid Entidade entidade,BindingResult result, RedirectAttributes attributes) {
	System.out.println(result.hasErrors());
	if(result.hasErrors())
    {
		System.out.println("Erro");
		return entidadeIndex(entidade);
    }
	attributes.addFlashAttribute("mensagem", "Entidade salva com sucesso!");
	entidadeService.save(entidade);
    return new ModelAndView("redirect:/entidades/list");
}	
@GetMapping("/list")
public String entidadeList(Pageable pageable,Model model) {	
	model.addAttribute("pageable", entidadeService.findAllPageable(pageable));
	return "entidade/entidade-list";
}
@GetMapping("/{id}")
public ModelAndView entidadeUpdate(@PathVariable("id") long id,Model model) {
	System.out.println(id);
	ModelAndView mv = new ModelAndView("entidade/entidade");
	model.addAttribute("entidade",entidadeService.findById(id));
	model.addAttribute("ufs",ufService.findAll());
	return mv;	
}
@PostMapping("/search")
public String entidadeSearch(@RequestParam String nome,Model model,Pageable pageable) {	
	model.addAttribute("pageable", entidadeService.findNamePageable(nome,pageable));
	return "entidade/entidade-list";
}	
}
