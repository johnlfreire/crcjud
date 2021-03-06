package com.br.sfb.crcjud.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.sfb.crcjud.entities.Usuario;





public class UsuarioServicoDetails extends User implements UserDetails{

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioServicoDetails(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {		
		super(usuario.getEmail(), usuario.getSenha(), authorities);		
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
