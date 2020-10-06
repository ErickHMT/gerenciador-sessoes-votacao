package com.gerenciadorsessoesvotacao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorsessoesvotacao.entity.Pauta;
import com.gerenciadorsessoesvotacao.repository.PautaRepository;

@Service
public class PautaService {
	
	@Autowired
	PautaRepository pautaRepository;
	
	public Optional<Pauta> getById(Long pautaId) {
		return pautaRepository.findById(pautaId);
	}

    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
    
}
