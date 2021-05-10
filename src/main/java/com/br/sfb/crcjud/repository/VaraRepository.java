package com.br.sfb.crcjud.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.entities.Vara;

@Repository
public interface VaraRepository extends PagingAndSortingRepository<Vara, Long>{
     List<Vara> findByCidadeId(Long cidade);
     Page<Vara> findByNomeContaining(String nome, Pageable pageable);
     Page<Vara> findByEntidade(Entidade entidade, Pageable pageable);
}
