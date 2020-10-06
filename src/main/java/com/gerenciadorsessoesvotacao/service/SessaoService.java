package com.gerenciadorsessoesvotacao.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorsessoesvotacao.entity.Sessao;
import com.gerenciadorsessoesvotacao.repository.SessaoRepository;

@Service
public class SessaoService {
	
	public static final Long DURACAO_PADRAO = 1L;
	
	@Autowired
	PautaService pautaService;
	
	@Autowired
	SessaoRepository sessaoRepository;
	
	/**
	 * Busca uma sessão de acordo com o id informado
	 * 
	 * @param sessaoId
	 * @return
	 */
	public Optional<Sessao> getById(Long sessaoId) {
		return sessaoRepository.findById(sessaoId);
	}
	
	/**
	 * Abre uma nova Sessão com o tempo de duração informado
	 * 
	 * @param pautaId
	 * @param duracao
	 */
	public Sessao abrirSessaoComDuracao(Long pautaId, Long duracao) {
		return novaSessao(pautaId, duracao);
	}

	/**
	 * Abre uma nova sessão com o tempo de duração padrão
	 * 
	 * @param pautaId
	 */
	public Sessao abrirSessao(Long pautaId) {
		return novaSessao(pautaId, DURACAO_PADRAO);
	}
	
	/**
	 * Cria uma nova Sessão de acordo com a pauta e duração informadas
	 * 
	 * @param pautaId
	 * @param duracao
	 */
	private Sessao novaSessao(Long pautaId, Long duracao) {
		var pauta = pautaService.getById(pautaId);
		var inicioSessao = LocalDateTime.now();
		var fimSessao = inicioSessao.plusMinutes(duracao);
		
		var sessao = new Sessao();
		sessao.setInicioSessao(inicioSessao);
		sessao.setFimSessao(fimSessao);

		if(pauta.isPresent()) {
			sessao.setPauta(pauta.get());			
		}
		
		return sessaoRepository.save(sessao);
	}
	
}
