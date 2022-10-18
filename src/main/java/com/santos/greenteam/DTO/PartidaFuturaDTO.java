package com.santos.greenteam.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaFuturaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String statusPartdida;
	
	@NotBlank
	private String EquipeCasa;
	
	private String urLogoEquipeCasa;

	@NotBlank
	private String EquipeVisitante;
	
	private String urLogoEquipeVisitantea;
	
	@NotNull
	private Date dataHoraPartida;
	
	@NotBlank
	private String horaPartida;

}
