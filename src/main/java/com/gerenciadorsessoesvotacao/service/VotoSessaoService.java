package com.gerenciadorsessoesvotacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciadorsessoesvotacao.core.VotoSessaoException;
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
	public VotoSessao registrarVoto(VotoSessao votoSessao, Long sessaoId) throws VotoSessaoException, NotFoundException {		
		Long idAssociado = votoSessao.getIdResponsavel();
		Optional<Sessao> sessaoOpt = sessaoService.getById(sessaoId);
		
		if(sessaoOpt.isPresent()) {
			var sessao = sessaoOpt.get();
			votoSessao.setSessao(sessao);

			var localDateTimeAtual = LocalDateTime.now();
			var inicioSessao = sessao.getInicioSessao();
			var fimSessao = sessao.getFimSessao();
			
			// Cadastro de voto deve ser feito dentro do período de duração da sessão
			if(localDateTimeAtual.isAfter(inicioSessao) && localDateTimeAtual.isBefore(fimSessao)) {
				if(AssociadoPodeVotar(idAssociado, sessaoId)) {
					return votoSessaoRepository.save(votoSessao);				
				}

				throw new VotoSessaoException("Associado já votou na sessão!!");					
			} else {
				throw new VotoSessaoException("Sessão de votação expirada!!");
			}
		}
		
		throw new NotFoundException("Sessão não encontrada!");
	}

	/**
	 * Retorna boolean que informa se o associado pode votar na sessão em questão 
	 * 
	 * @param associadoId
	 * @param sessaoId
	 * @return
	 */
	private boolean AssociadoPodeVotar(Long associadoId, Long sessaoId) {
		// TODO Validar associado
		List<VotoSessao> votosAssociado = getVotosByAssociado(associadoId, sessaoId);
		
		if(votosAssociado.size() >= 1) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Busca lista de votos do associado informado
	 * 
	 * @param associadoId
	 * @param sessaoId
	 * @return
	 */
	public List<VotoSessao> getVotosByAssociado(Long associadoId, Long sessaoId) {
		return votoSessaoRepository.getVotosByAssociado(associadoId, sessaoId);
	}
	
}
