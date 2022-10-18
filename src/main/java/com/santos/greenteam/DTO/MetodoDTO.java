package com.santos.greenteam.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nomeMetodo;
	

}
