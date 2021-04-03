package com.br.sfb.crcjud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.entities.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByEmail(String email);

	public List<Usuario> findByIdIn(Long[] codigos);
	
	@Query("select distinct p.nome from Usuario u inner join u.perfis g inner join g.permissoes p where u = :usuario")
	public List<String> permissoes(Usuario usuario);

	public Page<Entidade> findByNomeContaining(String nome, Pageable pageable);
}
