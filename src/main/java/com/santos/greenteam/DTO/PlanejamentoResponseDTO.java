package com.santos.greenteam.DTO;

import java.io.Serializable;
import java.util.List;

import com.santos.greenteam.entity.Planejamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Planejamento> planejamento;
}
