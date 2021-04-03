package com.br.sfb.crcjud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


import com.br.sfb.crcjud.entities.Usuario;
import com.br.sfb.crcjud.services.PerfilService;
import com.br.sfb.crcjud.services.UfService;
import com.br.sfb.crcjud.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
@Autowired
private UsuarioService usuarioService;
@Autowired
private UfService ufService;
@Autowired
private PerfilService perfilService;

@GetMapping()
public ModelAndView usuarioIndex(Usuario usuario) {
	ModelAndView mv = new ModelAndView("usuario/usuario");	
	mv.addObject("usuario",usuario);
	mv.addObject("ufs",ufService.findAll());
	mv.addObject("perfis",perfilService.findAll());
	return mv;	
}	

@PostMapping
@ResponseBody
public ModelAndView UsuarioSave(@Valid Usuario usuario, BindingResult result,RedirectAttributes attributes) {	
	System.out.println(result.hasErrors());
	if(result.hasErrors())
	{
		return usuarioIndex(usuario);
    }
	
	BCryptPasswordEncoder encore = new BCryptPasswordEncoder();
	usuario.setSenha("1234");
	usuario.setSenha(encore.encode(usuario.getSenha()));
	usuario.setAtivo(true);
	usuarioService.save(usuario);
	attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso!");
	return new ModelAndView("redirect:/usuarios/list");
	
}

@GetMapping("/list")
public String entidadeList(Pageable pageable,Model model) {	
	model.addAttribute("pageable", usuarioService.findAllPageable(pageable));
	return "usuario/usuario-list";
}
@GetMapping("/{id}")
public ModelAndView entidadeUpdate(@PathVariable("id") long id,Model model) {
	System.out.println(id);
	ModelAndView mv = new ModelAndView("usuario/usuario");
	model.addAttribute("usuario",usuarioService.findById(id));
	model.addAttribute("ufs",ufService.findAll());
	model.addAttribute("perfis",perfilService.findAll());
	return mv;	
}
@PostMapping("/search")
public String entidadeSearch(@RequestParam String nome,Model model,Pageable pageable) {	
	model.addAttribute("pageable", usuarioService.findNamePageable(nome,pageable));
	return "entidade/entidade-list";
}	

}
