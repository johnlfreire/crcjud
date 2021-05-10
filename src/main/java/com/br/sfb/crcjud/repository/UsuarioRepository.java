package com.br.sfb.crcjud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.entities.Usuario;
import com.br.sfb.crcjud.entities.Vara;


@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> ,JpaSpecificationExecutor<Usuario>{
	
	public Optional<Usuario> findByEmail(String email);

	public List<Usuario> findByIdIn(Long[] codigos);
	
	@Query("select distinct p.nome from Usuario u inner join u.perfis g inner join g.permissoes p where u = :usuario")
	public List<String> permissoes(Usuario usuario);

	public Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
	
	Page<Usuario> findByVara(Vara vara, Pageable pageable);
		
	Optional<Usuario> findByCpf(String cpf);
	
	@Query("select distinct u from Usuario u inner join u.vara v inner join v.entidade e where e = :entidade ")
	public Page<Usuario> usuariosByEntidade(Entidade entidade, Pageable pageable);

	
}
