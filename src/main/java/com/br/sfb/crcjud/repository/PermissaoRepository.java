package com.br.sfb.crcjud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.sfb.crcjud.entities.Permissao;
@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Long> {
Optional<Permissao> findById(Long id);

}
