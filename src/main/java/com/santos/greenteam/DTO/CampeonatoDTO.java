package com.santos.greenteam.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.santos.greenteam.entity.Campeonato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampeonatoDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nomeCampeonato;

	public CampeonatoDTO(Campeonato obj) {
		nomeCampeonato = obj.getNomeCampeonato();
	}
	
	

}
