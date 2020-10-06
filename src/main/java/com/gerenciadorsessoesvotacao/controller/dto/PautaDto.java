package com.gerenciadorsessoesvotacao.controller.dto;

import com.gerenciadorsessoesvotacao.entity.Pauta;

public class PautaDto {

    private Long id;
    private String conteudo;

    public PautaDto(Pauta pauta) {
    	if(pauta == null) {
    		return;
    	}
    	
        this.id = pauta.getId();
        this.conteudo = pauta.getConteudo();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
    
}
