package com.projetointegrador.illuminer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrador.illuminer.model.Comentario;
import com.projetointegrador.illuminer.model.Usuario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
	@Query(value = "SELECT * FROM tb_comentario p WHERE p.postagem_id = ?1", nativeQuery = true)
	Page<Comentario> paginarComentariosPorPostagem(Long idPostagem, Pageable pageable);
		
	@Query(value = "SELECT * FROM tb_comentario p WHERE p.postagem_id = ?1", nativeQuery = true)
	List<Comentario> listarComentariosPorPostagem(Long idPostagem);
	
	Page<Comentario> findByUsuario(Usuario usuario, Pageable pageable);

}
