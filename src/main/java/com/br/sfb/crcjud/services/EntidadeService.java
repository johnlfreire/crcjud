package com.br.sfb.crcjud.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.entities.Perfil;
import com.br.sfb.crcjud.repository.EntidadeRepository;

@Service
public class EntidadeService {
	@Autowired
	private EntidadeRepository entidadeRepository;

	public Entidade findById(long id) {
		return entidadeRepository.findById(id).get();
	}
	public Iterable<Entidade> findAll() {
		return entidadeRepository.findAll();
	}
	public Iterable<Entidade> findAllPageable(Pageable pageable) {
		return entidadeRepository.findAll(pageable);
	}
	public Page<Entidade> findNamePageable(String nome,Pageable pageable) {		
		return entidadeRepository.findByNomeContaining(nome, pageable);
	}
	@Transactional
	public Perfil save(Entidade entidade) {
		entidadeRepository.save(entidade);
		return null;
	}
}