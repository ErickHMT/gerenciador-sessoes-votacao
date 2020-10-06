package com.gerenciadorsessoesvotacao.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gerenciadorsessoesvotacao.entity.VotoSessao;
import com.gerenciadorsessoesvotacao.entity.enums.VotoEnum;

public class VotoSessaoDto {

    private Long id;
    private VotoEnum voto;
    private Long idAssociado;
    
    public VotoSessaoDto(VotoSessao votoSessao) {
        this.id = votoSessao.getId();
        this.voto = votoSessao.getVoto();
        this.idAssociado = votoSessao.getIdAssociado();
    }
    
    public static List<VotoSessaoDto> from(List<VotoSessao> votos) {
    	List<VotoSessaoDto> votosDto = new ArrayList<VotoSessaoDto>();
    	
    	if(votos != null) {
    		votosDto = votos.stream().map(voto ->  new VotoSessaoDto(voto)).collect(Collectors.toList());    		
    	}
        
        return votosDto;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VotoEnum getVoto() {
		return voto;
	}

	public void setVoto(VotoEnum voto) {
		this.voto = voto;
	}

	public Long getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}
	
}
