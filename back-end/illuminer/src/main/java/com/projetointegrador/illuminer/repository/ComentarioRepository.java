package com.projetointegrador.illuminer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetointegrador.illuminer.model.Comentario;
import com.projetointegrador.illuminer.model.Usuario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
	Page<Comentario> findByUsuario(Usuario usuario, Pageable pageable);
	 

}
