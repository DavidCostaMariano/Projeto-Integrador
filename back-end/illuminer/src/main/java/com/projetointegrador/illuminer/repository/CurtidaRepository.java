package com.projetointegrador.illuminer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrador.illuminer.model.Curtida;
import com.projetointegrador.illuminer.model.CurtidaPK;
import com.projetointegrador.illuminer.model.Postagem;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida, CurtidaPK>{
	
	@Query(value = "select * from tb_curtida c where c.postagem_id = ?1", nativeQuery = true)
	List<Curtida> obterCurtidasPostagem(Postagem postagem);

}
