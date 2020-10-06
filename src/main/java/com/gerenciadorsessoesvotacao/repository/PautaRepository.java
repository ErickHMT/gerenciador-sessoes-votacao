package com.gerenciadorsessoesvotacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciadorsessoesvotacao.entity.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
