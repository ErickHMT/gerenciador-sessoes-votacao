package com.gerenciadorsessoesvotacao.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gerenciadorsessoesvotacao.controller.dto.VotoSessaoDto;
import com.gerenciadorsessoesvotacao.entity.VotoSessao;
import com.gerenciadorsessoesvotacao.service.VotoSessaoService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/voto") 
public class VotoSessaoController {

	@Autowired
	VotoSessaoService votoSessaoService;
	
    @PostMapping(value = "/sessao/{sessaoId}")
    public ResponseEntity<?> criarSessao(@PathVariable Long sessaoId, @RequestBody VotoSessao votoSessao, UriComponentsBuilder uriBuilder) throws NotFoundException {
        VotoSessao voto = votoSessaoService.registrarVoto(votoSessao, sessaoId);
        URI uri = uriBuilder.path("/sessao/{sessaoId}").buildAndExpand(sessaoId).toUri();

        if(voto != null) {
        	return ResponseEntity.created(uri).body(new VotoSessaoDto(voto));        	
        } else {
        	return ResponseEntity.badRequest().build();
        }
    }
    
}
