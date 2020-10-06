package com.gerenciadorsessoesvotacao.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.sun.istack.NotNull;

@Entity
public class Sessao {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SESSAO_SEQ")
    @SequenceGenerator(name = "PK_SESSAO_SEQ", sequenceName = "PK_SESSAO_SEQ", allocationSize = 1)
    private Long id;
    
    @NotNull
    private LocalDateTime inicioSessao;

    @NotNull
    private LocalDateTime fimSessao;
    
    @OneToMany(mappedBy = "sessao")
    private List<VotoSessao> votos;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private Pauta pauta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInicioSessao() {
		return inicioSessao;
	}

	public void setInicioSessao(LocalDateTime inicioSessao) {
		this.inicioSessao = inicioSessao;
	}

	public LocalDateTime getFimSessao() {
		return fimSessao;
	}

	public void setFimSessao(LocalDateTime fimSessao) {
		this.fimSessao = fimSessao;
	}

	public List<VotoSessao> getVotos() {
		return votos;
	}

	public void setVotos(List<VotoSessao> votos) {
		this.votos = votos;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

}
