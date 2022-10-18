package com.santos.greenteam.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santos.greenteam.entity.Metodo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nomeEquipeCasa;
	
	@NotBlank
	private String nomeEquipeVisitante;
	
	@NotNull
	@ApiModelProperty(example = "dd/MM/yyyy HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_paulo")
	private Date dataHoraPartida;
	
	@NotBlank
	private String statusPartida;
	
	private Integer golsEquipeCasa;
	
	private Integer golsEquipeVisitante;
	
	
	private Double odd;
	
	
	private Double valor;
	
	
	private Metodo metodo;
}
