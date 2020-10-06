package com.gerenciadorsessoesvotacao.controller.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.gerenciadorsessoesvotacao.entity.Sessao;

public class SessaoDto {

    private Long id;
    private LocalDateTime inicioSessao;
    private LocalDateTime fimSessao;
    private Long duracaoEmMinutos;
    private List<VotoSessaoDto> votos;
    private PautaDto pauta;

    public SessaoDto(Sessao sessao) {
        this.id = sessao.getId();
        this.inicioSessao = sessao.getInicioSessao();
        this.fimSessao = sessao.getFimSessao();
        this.duracaoEmMinutos = ChronoUnit.MINUTES.between(this.inicioSessao, this.fimSessao);
        this.votos = VotoSessaoDto.from(sessao.getVotos());
        this.pauta = new PautaDto(sessao.getPauta());
    }

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

	public Long getDuracaoEmMinutos() {
		return duracaoEmMinutos;
	}

	public void setDuracaoEmMinutos(Long duracaoEmMinutos) {
		this.duracaoEmMinutos = duracaoEmMinutos;
	}

	public List<VotoSessaoDto> getVotos() {
		return votos;
	}

	public void setVotos(List<VotoSessaoDto> votos) {
		this.votos = votos;
	}

	public PautaDto getPauta() {
		return pauta;
	}

	public void setPauta(PautaDto pauta) {
		this.pauta = pauta;
	}
	
}
