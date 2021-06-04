package com.illuminer.illuminer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illuminer.illuminer.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	Page<Postagem> findByTextoContainingIgnoreCase(String texto, Pageable pageable);
}