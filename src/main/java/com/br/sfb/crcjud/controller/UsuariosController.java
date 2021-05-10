package com.br.sfb.crcjud.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.br.sfb.crcjud.Exeception.UsuarioExistenteException;
import com.br.sfb.crcjud.entities.Perfil;
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
	if(usuarioService.possuiPermissao("ROLE_ADMINISTRAR")) {	
		mv.addObject("ufs",ufService.findAll());
		mv.addObject("perfis",perfilService.findAll());
		}else if(usuarioService.possuiPermissao("ROLE_VARA") || usuarioService.possuiPermissao("ROLE_ENTIDADE_ADMINISTRADOR")){
			Usuario usuarioLogado = usuarioService.usuarioLogado();
			List<Perfil> permissoesUsuario = usuarioLogado.getPerfis();			
			mv.addObject("ufs",usuarioLogado.getCidade().getEstados());
			mv.addObject("perfis",permissoesUsuario.stream().filter(e -> !e.getNome().equals("Administrador de Entidade")).collect(Collectors.toList()));
		}
		else {
			Usuario usuarioLogado = usuarioService.usuarioLogado();			
			mv.addObject("ufs",usuarioLogado.getCidade().getEstados());
			mv.addObject("perfis",perfilService.findAll().stream().filter(e -> e.getNome().equals("Operador")).collect(Collectors.toList()));
		}

	return mv;	
}	

@PostMapping
@ResponseBody
public ModelAndView UsuarioSave(@Valid Usuario usuario, BindingResult result,RedirectAttributes attributes) {	

	
	if(result.hasErrors())
	{
		return usuarioIndex(usuario);
    }
	try {		
		usuario.setEntidade(usuario.getVara().getEntidade());
		usuarioService.save(usuario);
	} catch (UsuarioExistenteException e) {
		result.rejectValue("cpf", e.getMessage(), e.getMessage());
		return usuarioIndex(usuario);
	}

	attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso!");
	return new ModelAndView("redirect:/usuarios/list");
	
}

@GetMapping("/list")
public String usuarioList(Pageable pageable,Model model) {	
	model.addAttribute("pageable", usuarioService.findAllPageable(pageable));
	return "usuario/usuario-list";
}
@GetMapping("/{id}")
public ModelAndView usuarioUpdate(@PathVariable("id") long id,Model model) {
	ModelAndView mv = new ModelAndView("usuario/usuario");
	model.addAttribute("usuario",usuarioService.findById(id));
	if(usuarioService.possuiPermissao("ROLE_ADMINISTRAR")) {	
		mv.addObject("ufs",ufService.findAll());
		mv.addObject("perfis",perfilService.findAll());
		}else if(usuarioService.possuiPermissao("ROLE_VARA") || usuarioService.possuiPermissao("ROLE_ENTIDADE_ADMINISTRADOR")){
			Usuario usuarioLogado = usuarioService.usuarioLogado();
			List<Perfil> permissoesUsuario = usuarioLogado.getPerfis();			
			mv.addObject("ufs",usuarioLogado.getCidade().getEstados());
			mv.addObject("perfis",permissoesUsuario.stream().filter(e -> !e.getNome().equals("Administrador de Entidade")).collect(Collectors.toList()));
		}
		else {
			Usuario usuarioLogado = usuarioService.usuarioLogado();			
			mv.addObject("ufs",usuarioLogado.getCidade().getEstados());
			mv.addObject("perfis",perfilService.findAll().stream().filter(e -> e.getNome().equals("Operador")).collect(Collectors.toList()));
		}

	return mv;	
}

@PostMapping("/search")
public String usuarioSearch(@RequestParam String nome,Model model,Pageable pageable) {	
	model.addAttribute("pageable", usuarioService.findNamePageable(nome,pageable));
	return "usuario/usuario-list";
}	

}
