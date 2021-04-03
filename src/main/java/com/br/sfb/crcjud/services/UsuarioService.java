package com.br.sfb.crcjud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.entities.Usuario;
import com.br.sfb.crcjud.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public List<Usuario> findAll() {		
		return usuarioRepository.findAll();
	}

	public Usuario save(Usuario usuario) {		
		return usuarioRepository.save(usuario);
	}

	public Usuario findById(long id) {	
		return usuarioRepository.findById(id).get();
	}

	public Page<Usuario> findAllPageable(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}
	public Page<Entidade> findNamePageable(String nome,Pageable pageable) {		
		return usuarioRepository.findByNomeContaining(nome, pageable);
	}

}
