package com.br.sfb.crcjud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.sfb.crcjud.entities.Cidade;
import com.br.sfb.crcjud.entities.Uf;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	List<Cidade> findByEstadosId(Long id);
	List<Cidade> findByEstados(Optional<Uf> optional);
}
