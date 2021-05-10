package com.br.sfb.crcjud.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.Exeception.UsuarioExistenteException;
import com.br.sfb.crcjud.entities.Usuario;
import com.br.sfb.crcjud.entities.Vara;
import com.br.sfb.crcjud.repository.UsuarioRepository;
import com.br.sfb.crcjud.specification.UsuarioSpecification;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;


	public List<Usuario> findAll() {		
		return (List<Usuario>) usuarioRepository.findAll();
	}
	@Transactional
	public Usuario save(Usuario usuario) {
		BCryptPasswordEncoder encore = new BCryptPasswordEncoder();
		usuario.setAtivo(true);
		usuario.setSenha(encore.encode(usuario.getSenha()));	
		List<Usuario> usuarioExistente = usuarioRepository.findAll(UsuarioSpecification.hasCpfOrEmail(usuario.getCpf(), usuario.getEmail()));		
		// Verifica se está sendo incluido um usuário novo para validar a existência do cpf e o email.
		if(usuarioExistente.size() > 1 && (!usuarioExistente.get(0).getEmail().equals(usuario.getEmail()) || !usuarioExistente.get(0).getCpf().equals(usuario.getCpf()))) {			
			throw new UsuarioExistenteException("Já existe um usuario com cpf ou email indicado");
		}
		
		return usuarioRepository.save(usuario);
	}

	public Usuario findById(long id) {	
		return usuarioRepository.findById(id).get();
	}

	public Page<Usuario> findAllPageable(Pageable pageable) {

		if(this.possuiPermissao("ROLE_ADMINISTRAR"))	
		return usuarioRepository.findAll(pageable);
		else if (this.possuiPermissao("ROLE_ENTIDADE_ADMINISTRADOR")) {
			return usuarioRepository.usuariosByEntidade(usuarioLogado().getEntidade(),pageable);
		} 
		else {			
			Vara usuarioVara = this.usuarioLogado().getVara();
			return usuarioRepository.findByVara(usuarioVara, pageable);
		}
	
	}
	public Page<Usuario> findNamePageable(String nome,Pageable pageable) {		
		if(this.possuiPermissao("ROLE_ADMINISTRAR"))	
			return usuarioRepository.findAll(UsuarioSpecification.hasFirstName(nome),pageable);
			else if (this.possuiPermissao("ROLE_ENTIDADE_ADMINISTRADOR")) {				
				return usuarioRepository.findAll(UsuarioSpecification.hasFirstName(nome).and(UsuarioSpecification.hasEntidade(usuarioLogado().getEntidade())),pageable);

			} 
			else {			
				Vara usuarioVara = this.usuarioLogado().getVara();
				return usuarioRepository.findByVara(usuarioVara, pageable);
			}
		
	}
 public Usuario usuarioLogado() {
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 return usuarioRepository.findByEmail(auth.getName()).get();
 }
 public boolean possuiPermissao(String permissao) {
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 return auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals(permissao));
 }

}
