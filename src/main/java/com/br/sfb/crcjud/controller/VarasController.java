package com.br.sfb.crcjud.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.sfb.crcjud.entities.Vara;
import com.br.sfb.crcjud.services.EntidadeService;
import com.br.sfb.crcjud.services.UfService;
import com.br.sfb.crcjud.services.VaraService;

@Controller
@RequestMapping("/varas")
public class VarasController {

	@Autowired
	private UfService ufService;
	@Autowired
	private EntidadeService entidadeService;
	@Autowired
	private VaraService varaService;

	@GetMapping
	public ModelAndView varaIndex(Vara vara) {
		ModelAndView mv = new ModelAndView("vara/vara");
		mv.addObject("vara", vara);
		mv.addObject("ufs", ufService.findAll());
		mv.addObject("entidades", entidadeService.findAll());
		return mv;
	}

	@PostMapping()
	public ModelAndView varaSave(@Valid Vara vara,BindingResult result, RedirectAttributes attributes) {
		System.out.println(result.hasErrors());
		if(result.hasErrors())
        {
			System.out.println("Erro");
			return varaIndex(vara);
        }
		
		attributes.addFlashAttribute("mensagem", "Vara salva com sucesso!");
		varaService.save(vara);
		return new ModelAndView("redirect:/varas/list");
	}

	@GetMapping("/list")
	public String varaList(Pageable pageable,Model model) {	
		model.addAttribute("pageable", varaService.findAllPageable(pageable));
		return "vara/vara-list";
	}
	@GetMapping("/{id}")
	public ModelAndView varaUpdate(@PathVariable("id") long id,Model model) {
		ModelAndView mv = new ModelAndView("vara/vara");
		model.addAttribute("vara", varaService.findId(id));
		model.addAttribute("ufs", ufService.findAll());
		model.addAttribute("entidades", entidadeService.findAll());
		return mv;	
	}
	@PostMapping("/search")
	public String varaSearch(@RequestParam String nome,Model model,Pageable pageable) {	
		model.addAttribute("pageable", varaService.findNamePageable(nome,pageable));
		return "vara/vara-list";
	}	
	
	
	@GetMapping("bycidade")
	@ResponseBody()
	public List<Vara> varasAll(@RequestParam(name = "id", defaultValue = "-1") Long id) {		
		return varaService.findByCidade(id);
	}
}
