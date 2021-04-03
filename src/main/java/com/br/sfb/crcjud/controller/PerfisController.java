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

import com.br.sfb.crcjud.entities.Perfil;
import com.br.sfb.crcjud.services.PerfilService;
import com.br.sfb.crcjud.services.PermissaoService;

@Controller
@RequestMapping("/perfis")
public class PerfisController {
	@Autowired
	private PermissaoService permissaoService;
	@Autowired
	private PerfilService perfilService;

	
	@GetMapping()
	public ModelAndView perfilIndex(Perfil perfil) {
		ModelAndView mv = new ModelAndView("perfil/perfil");		
		mv.addObject("perfil",perfil);
		mv.addObject("permissoes",permissaoService.findAll());
		return mv;	
	}	
	@PostMapping()
	public ModelAndView perfilSave(@Valid Perfil perfil,BindingResult result,RedirectAttributes attributes) {
		System.out.println(result.hasErrors());
		if(result.hasErrors())
        {
			System.out.println("Erro");
			return perfilIndex(perfil);
        }
		attributes.addFlashAttribute("mensagem", "Perfil salvo com sucesso!");
	    perfilService.save(perfil);
	    return new ModelAndView("redirect:/perfis/list");
	}
	
	@GetMapping("/list")
	public String perfilList(Pageable pageable,Model model) {	
		model.addAttribute("pageable", perfilService.findAllPageable(pageable));
		return "perfil/perfil-list";
	}
	@GetMapping("/{id}")
	public ModelAndView perfilUpdate(@PathVariable("id") long id,Model model) {
		System.out.println(id);
		ModelAndView mv = new ModelAndView("perfil/perfil");
		model.addAttribute("perfil",perfilService.findById(id));
		model.addAttribute("permissoes",permissaoService.findAll());
		return mv;	
	}
	@PostMapping("/search")
	public String perfilSearch(@RequestParam String nome,Model model,Pageable pageable) {	
		model.addAttribute("pageable", perfilService.findNamePageable(nome,pageable));
		return "perfil/perfil-list";
	}
}
