package com.gerenciadorsessoesvotacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadorsessoesvotacao.entity.VotoSessao;

@Repository
public interface VotoSessaoRepository extends JpaRepository<VotoSessao, Long> {

	List<VotoSessao> getVotoSessaosBySessaoId(Long id);
	
}
