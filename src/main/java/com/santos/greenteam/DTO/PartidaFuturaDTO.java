package com.santos.greenteam.DTO;

import java.io.Serializable;

import com.santos.greenteam.entity.Partidas;

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
	
	private String timeCasa;
	
	private String urlLogoTimeCasa;

	private String timeVisitante;
	
	private String urLogoTimeVisitante;

	private String dataPartida;
	
	private String horaPartida;

	public PartidaFuturaDTO(Partidas obj) {
		super();
		statusPartdida = obj.getStatusPartdida();
		timeCasa = obj.getTimeCasa();
		urlLogoTimeCasa = obj.getUrlLogoTimeCasa();
		timeVisitante = obj.getTimeVisitante();
		urLogoTimeVisitante = obj.getUrLogoTimeVisitante();
		dataPartida = obj.getDataPartida();
		horaPartida = obj.getHoraPartida();
	}
	
	

}
