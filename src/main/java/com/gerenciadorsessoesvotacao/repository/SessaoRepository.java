package com.gerenciadorsessoesvotacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorsessoesvotacao.entity.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query(value="SELECT * FROM SESSAO s WHERE s.id_pauta = :pautaId", nativeQuery = true)
    List<Sessao> getSessoesByPautaId(@Param("pautaId") Long pautaId);
	
}
