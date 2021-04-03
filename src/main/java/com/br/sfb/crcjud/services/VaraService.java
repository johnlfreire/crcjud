package com.br.sfb.crcjud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.entities.Vara;
import com.br.sfb.crcjud.repository.VaraRepository;

@Service
public class VaraService {
	@Autowired
	private VaraRepository varaRepository;
	
	public Vara save(Vara vara) {
		varaRepository.save(vara);
		return null;
	}
	public List<Vara> findByCidade(long cidadeId) {
		return varaRepository.findByCidadeId(cidadeId);
		
	}
	public Iterable<Vara> findAllPageable(Pageable pageable) {		
		return varaRepository.findAll(pageable);
	}
	public Iterable<Vara>  findNamePageable(String nome, Pageable pageable) {
		
		return varaRepository.findByNome(nome, pageable);
	}
	public Vara findId(long id) {		
		return varaRepository.findById(id).get();
	}
}
