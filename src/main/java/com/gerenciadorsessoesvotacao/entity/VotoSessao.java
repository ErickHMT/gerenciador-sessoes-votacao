package com.gerenciadorsessoesvotacao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.gerenciadorsessoesvotacao.entity.enums.VotoEnum;
import com.sun.istack.NotNull;

@Entity
public class VotoSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_VOTO_SESSAO_SEQ")
    @SequenceGenerator(name = "PK_VOTO_SESSAO_SEQ", sequenceName = "PK_VOTO_SESSAO_SEQ", allocationSize = 1)
    private Long id;
    
    @NotNull
    private VotoEnum voto;
    
    @NotNull
    private Long idResponsavel;
    
    @NotNull
    @ManyToOne
    private Sessao sessao;

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

	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
    
}
