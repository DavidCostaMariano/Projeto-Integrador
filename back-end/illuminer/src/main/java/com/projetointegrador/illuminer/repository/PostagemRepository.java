package com.projetointegrador.illuminer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrador.illuminer.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	Page<Postagem> findByTextoContainingIgnoreCase(String texto, Pageable pageable);
	
	@Query(value = "SELECT * FROM tb_postagem p WHERE p.usuario_id = ?1", nativeQuery = true) 
	Page<Postagem> listarPostagensPorUsuario(Long idUsuario, Pageable pageable);
}
