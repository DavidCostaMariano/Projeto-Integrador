package com.projetointegrador.illuminer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetointegrador.illuminer.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	List<Tema> findAllByNomeContainingIgnoreCase (String nome);

}
