package com.br.sfb.crcjud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.entities.Permissao;
import com.br.sfb.crcjud.repository.PermissaoRepository;

@Service
public class PermissaoService{
@Autowired
private PermissaoRepository permissaoRepository;


	public List<Permissao> findAll() {
		return permissaoRepository.findAll();
	}	
}
