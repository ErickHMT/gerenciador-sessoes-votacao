package com.gerenciadorsessoesvotacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorsessoesvotacao.entity.VotoSessao;

@Repository
public interface VotoSessaoRepository extends JpaRepository<VotoSessao, Long> {

	List<VotoSessao> getVotoSessaosBySessaoId(Long id);
	
    @Query(value="SELECT * FROM VOTO_SESSAO v WHERE v.id_associado = :associadoId AND v.sessao_id = :sessaoId", nativeQuery = true)
    List<VotoSessao> getVotosByAssociado(@Param("associadoId") Long idAssociado, @Param("sessaoId") Long sessaoId);
	
}
