package com.gerenciadorsessoesvotacao.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gerenciadorsessoesvotacao.controller.dto.PautaDto;
import com.gerenciadorsessoesvotacao.entity.Pauta;
import com.gerenciadorsessoesvotacao.service.PautaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

	@Autowired
	PautaService pautaService;

	@ApiOperation(value = "Retorna uma pauta de acordo com o id informado")
	@GetMapping("/{pautaId}")
	public ResponseEntity<?> getById(@PathVariable Long pautaId) {
		Optional<Pauta> pauta = pautaService.getById(pautaId);
		
		if(pauta.isPresent()) {
			return ResponseEntity.ok(new PautaDto(pauta.get()));						
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@ApiOperation(value = "Cria uma pauta")
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Pauta pauta, UriComponentsBuilder uriBuilder){
    	Pauta novaPauta = pautaService.save(pauta);
        URI uri = uriBuilder.path("/pauta/{pautaId}").buildAndExpand(novaPauta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PautaDto(novaPauta));
    }

}
