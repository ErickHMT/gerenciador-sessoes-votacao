package com.gerenciadorsessoesvotacao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_PAUTA_SEQ")
    @SequenceGenerator(name = "PK_PAUTA_SEQ", sequenceName = "PK_PAUTA_SEQ", allocationSize = 1)
    private Long id;
    
    @NotNull
    private String conteudo;
    
    @JsonIgnore
    @OneToOne(mappedBy = "pauta")
    private Sessao sessao;

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

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
    
}
  