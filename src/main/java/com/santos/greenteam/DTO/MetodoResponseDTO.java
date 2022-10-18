package com.santos.greenteam.DTO;

import java.io.Serializable;
import java.util.List;

import com.santos.greenteam.entity.Metodo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Metodo> metodos;

}
