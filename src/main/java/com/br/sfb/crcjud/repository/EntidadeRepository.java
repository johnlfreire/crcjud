package com.br.sfb.crcjud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.br.sfb.crcjud.entities.Entidade;

public interface EntidadeRepository extends PagingAndSortingRepository<Entidade, Long>{
	 Page<Entidade> findByNomeContaining(String nome, Pageable pageable);
	 
	
}
