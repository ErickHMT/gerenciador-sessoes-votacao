package com.gerenciadorsessoesvotacao.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorsessoesvotacao.controller.dto.ResultadoSessaoDto;
import com.gerenciadorsessoesvotacao.core.GenericException;
import com.gerenciadorsessoesvotacao.entity.Sessao;
import com.gerenciadorsessoesvotacao.entity.enums.ResultadoSessaoEnum;
import com.gerenciadorsessoesvotacao.entity.enums.VotoEnum;
import com.gerenciadorsessoesvotacao.repository.SessaoRepository;

import javassist.NotFoundException;

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
		
		List<Sessao> sessoesDaPauta = sessaoRepository.getSessoesByPautaId(pautaId);
		if(sessoesDaPauta.size() >= 1) {
			throw new GenericException("Já existe uma sessão para a pauta em questão!");
		}
		
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
	
	/**
	 * Contabiliza os votos e retorna o resultado da sessão de votação
	 * 
	 * @param sessaoId
	 * @return
	 * @throws NotFoundException 
	 */
	public ResultadoSessaoDto getResultadoSessao(Long sessaoId) throws NotFoundException {
		Optional<Sessao> sessao = getById(sessaoId);
		
		if(sessao.isPresent()) {
			ResultadoSessaoDto resultado = new ResultadoSessaoDto(sessao.get());	

			var fimSessao = resultado.getFimSessao();
			if(LocalDateTime.now().isBefore(fimSessao)) {
				var fimSessaoFormatado = fimSessao.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
				throw new GenericException("Aguarde o fim da sessão para a consulta do resultado! Horário de encerramento: " + fimSessaoFormatado);
			}

			var votos = resultado.getVotos();			
			int qtdSim = votos.stream().filter(voto -> voto.getVoto() == VotoEnum.SIM).collect(Collectors.toList()).size();
			int qtdNao = votos.stream().filter(voto -> voto.getVoto() == VotoEnum.NAO).collect(Collectors.toList()).size();
			
			resultado.setQuantidadeSim(qtdSim);
			resultado.setQuantidadeNao(qtdNao);;
			
			if(qtdSim > qtdNao) {
				resultado.setResultado(ResultadoSessaoEnum.APROVADA);
			} else if(qtdSim < qtdNao) {
				resultado.setResultado(ResultadoSessaoEnum.REPROVADA);
			} else {
				resultado.setResultado(ResultadoSessaoEnum.EMPATE);
			}
			
			
			return resultado;
		}
		
		throw new NotFoundException("Não foi encontrado um resultado para a sessão informada!!");
	}
	
}
