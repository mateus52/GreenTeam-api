package com.santos.greenteam.DTO;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaFinalizadaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String statusPartdida;
	//equipe casa
	private String equipeCasa;
	private Integer golsEquipeCasa;

	//equipe visitante
	private String equipeVisitante;
	private Integer golsEquipeVisitante;
	
	private Date dataHoraPartida;
	private String horaPartida;

}
