package com.santos.greenteam.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "planejamento")
public class Planejamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planejamento_id")
	private Long Id;
	
	@Column(name = "equipe_casa")
	private String equipeCasa;
	
	@Column(name = "equipe_visitante")
	private String equipeVisitante;
	
	@ApiModelProperty(example = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_paulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_partida")
	private Date dataHoraPartida;
	
	@Column(name = "hora")
	private String hora;
	
	@Column(name = "status_partida")
	private String statusPartida;
	
	@Column(name = "gols_equipe_casa")
	private Integer golsEquipeCasa;
	
	@Column(name = "gols_equipe_visitante")
	private Integer golsEquipeVisitante;
	
	@Column(name = "odd")
	private Double odd;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "resultado")
	private String resultado;
	
	@ManyToOne
	@JoinColumn(name = "metodo_id")
	private Metodo metodo;
	
}
