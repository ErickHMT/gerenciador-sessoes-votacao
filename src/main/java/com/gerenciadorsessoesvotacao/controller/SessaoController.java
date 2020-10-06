package com.gerenciadorsessoesvotacao.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gerenciadorsessoesvotacao.controller.dto.ResultadoSessaoDto;
import com.gerenciadorsessoesvotacao.controller.dto.SessaoDto;
import com.gerenciadorsessoesvotacao.entity.Sessao;
import com.gerenciadorsessoesvotacao.service.SessaoService;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/sessao") 
public class SessaoController {

	@Autowired
	SessaoService sessaoService;
	
	@ApiOperation(value = "Busca uma Sessão de acordo com o id informado")
	@GetMapping("/{sessaoId}")
	public ResponseEntity<?> getById(@PathVariable Long sessaoId) {
		Optional<Sessao> sessao = sessaoService.getById(sessaoId);

		if(sessao.isPresent()) {
			return ResponseEntity.ok(new SessaoDto(sessao.get()));						
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "Busca o resultado uma Sessão de votações")
	@GetMapping("/{sessaoId}/resultado")
	public ResponseEntity<?> getResultadoSessao(@PathVariable Long sessaoId) throws NotFoundException {
		ResultadoSessaoDto resultadoSessao = sessaoService.getResultadoSessao(sessaoId);
		return ResponseEntity.ok(resultadoSessao);						
	}
	
	@ApiOperation(value = "Cria uma Sessão de votações para a pauta informada com duração padrão")
    @PostMapping(value = "/pauta/{pautaId}")
    public ResponseEntity<?> criarSessao(@PathVariable Long pautaId, UriComponentsBuilder uriBuilder){
        Sessao sessao = sessaoService.abrirSessao(pautaId);
        URI uri = uriBuilder.path("/sessao/pauta/{pautaId}").buildAndExpand(pautaId).toUri();
        return ResponseEntity.created(uri).body(new SessaoDto(sessao));
    }
    
	@ApiOperation(value = "Cria uma Sessão de votações para a pauta informada com duração informada (em minutos)")
    @PostMapping(value = "/pauta/{pautaId}/duracao/{duracao}")
    public ResponseEntity<?> criarSessao(@PathVariable Long pautaId, @PathVariable Long duracao, UriComponentsBuilder uriBuilder){
        Sessao sessao = sessaoService.abrirSessaoComDuracao(pautaId, duracao);
        URI uri = uriBuilder.path("/sessao/pauta/{pautaId}").buildAndExpand(pautaId).toUri();
        return ResponseEntity.created(uri).body(new SessaoDto(sessao));
    }
    
}
