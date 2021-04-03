package com.br.sfb.crcjud.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.br.sfb.crcjud.entities.Perfil;

public interface PerfilRepository extends PagingAndSortingRepository<Perfil, Long>{
	 Page<Perfil> findByNome(String nome, Pageable pageable);
	 List<Perfil> findAll();
}
