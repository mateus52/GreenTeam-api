package com.santos.greenteam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "partidas")
public class Partidas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partida_id")
	private Integer id;
	
	@Column(name = "status_partdida")
	private String statusPartdida;
	
	private String timeCasa;
	
	@Column(name = "logo_time_casa")
	private String urlLogoTimeCasa;
	
	private String timeVisitante;
	
	@Column(name = "logo_time_visitante")
	private String urLogoTimeVisitante;
	
	@Column(name = "data_partida")
	private String dataPartida;
	
	@Column(name = "hora_partida")
	private String horaPartida;
	
	@Column(name = "gols_time_casa")
	private Integer golsTimeCasa;
	
	@Column(name = "gols_time_visitante")
	private Integer golsTimeVisitante;
	
	@ManyToOne
	@JoinColumn(name = "campeonato_id")
	private Campeonato campeonato;
	
}
