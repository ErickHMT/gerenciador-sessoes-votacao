package com.gerenciadorsessoesvotacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorsessoesvotacao.entity.Sessao;
import com.gerenciadorsessoesvotacao.entity.VotoSessao;
import com.gerenciadorsessoesvotacao.repository.VotoSessaoRepository;

import javassist.NotFoundException;

@Service
public class VotoSessaoService {
	
	@Autowired
	SessaoService sessaoService;
	
	@Autowired
	VotoSessaoRepository votoSessaoRepository;
	
	/**
	 * Busca votos de acordo com o id de sessão informado
	 * 
	 * @param sessaoId
	 * @return
	 */
	public Optional<List<VotoSessao>> getVotosBySessaoId(Long sessaoId) {
		return Optional.ofNullable(votoSessaoRepository.getVotoSessaosBySessaoId(sessaoId));
	}

	/**
	 * Registra um novo voto
	 * 
	 * @param votoSessao
	 * @param sessaoId
	 * @return
	 * @throws NotFoundException 
	 */
	public VotoSessao registrarVoto(VotoSessao votoSessao, Long sessaoId) throws NotFoundException {		
		// TODO Validar responsável
//		Long idResponsavel = votoSessao.getIdResponsavel();
		Optional<Sessao> sessaoOpt = sessaoService.getById(sessaoId);
		
		if(sessaoOpt.isPresent()) {
			var localDateTimeAtual = LocalDateTime.now();
			var sessao = sessaoOpt.get();
			var inicioSessao = sessao.getInicioSessao();
			var fimSessao = sessao.getFimSessao();
			
			if(localDateTimeAtual.isBefore(fimSessao) && localDateTimeAtual.isAfter(inicioSessao)) {
				return votoSessaoRepository.save(votoSessao);				
			} else {
				// TODO sessão expirada
				throw new NotFoundException("Sessão não encontrada");
			}
		}
		
		return null;
	}
	
}
