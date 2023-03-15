package com.santos.greenteam.entity.enums;

public enum StatusPartida {

	PARTIDA_NAO_INICIADA(1, "Partida não niciada"),
	PARTIDA_EM_ANDAMENTO(2, "Partida em andamento"),
	PARTIDA_ENCERRADA(3, "Partida encerrada");
	
	private int cod;
	private String descricao;
	
	private StatusPartida(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusPartida toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusPartida x : StatusPartida.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
