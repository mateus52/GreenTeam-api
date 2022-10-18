package com.santos.greenteam.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.santos.greenteam.DTO.PartidaFinalizadaDTO;

@Service
public class PartidaFinalizadaUtil {

private static final  Logger LOGGER = LoggerFactory.getLogger(PartidaFinalizadaUtil.class);
	
	private static final String BASE_URL_GOOGLE = "https://www.google.com.br/search?q=";	
	private static final String COMPLEMENTO_URL_GOOGLE = "&hl=pt-BR";
	private static final String DIV_PLACAR_EQUIPE_CASA = "div[class=imso_mh__l-tm-sc imso_mh__scr-it imso-light-font]";
	private static final String DIV_PLACAR_EQUIPE_VISITANTE = "div[class=imso_mh__r-tm-sc imso_mh__scr-it imso-light-font]";
	private static final String DIV_NOME_EQUIPE_CASA = "div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String DIV_NOME_EQUIPE_VISITANTE = "div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String SPAN = "span";

	
	
	public PartidaFinalizadaDTO obtemInformacoesPartida(String url) {
		PartidaFinalizadaDTO PartidaFinalizadaDTO = new PartidaFinalizadaDTO();

		Document document = null;
		
		try {
			document = Jsoup.connect(url).get();
			String title = document.title();
			
			LOGGER.info("Titulo da pagina: {}", title);
			
			PartidaFinalizadaDTO.setStatusPartdida(StatusPartida.PARTIDA_ENCERRADA.toString());
			
			Integer placarEquipeCasa = recuperaPlacarEquipe(document, DIV_PLACAR_EQUIPE_CASA);
			PartidaFinalizadaDTO.setGolsEquipeCasa(placarEquipeCasa);
			LOGGER.info("Placar casa: {}",placarEquipeCasa);
			
			Integer placarEquipeVisitante = recuperaPlacarEquipe(document, DIV_PLACAR_EQUIPE_VISITANTE);
			PartidaFinalizadaDTO.setGolsEquipeVisitante(placarEquipeVisitante);
			LOGGER.info("Placar visitante: {}",placarEquipeVisitante);
			
			String nomeEquipeCasa = recuperaNomeEquipe(document, DIV_NOME_EQUIPE_CASA);
			PartidaFinalizadaDTO.setEquipeCasa(nomeEquipeCasa);
			LOGGER.info("Nome equipe casa: {}",nomeEquipeCasa);
			
			String nomeEquipeVisitante = recuperaNomeEquipe(document, DIV_NOME_EQUIPE_VISITANTE);
			PartidaFinalizadaDTO.setEquipeVisitante(nomeEquipeVisitante);
			LOGGER.info("Nome equipe visitante: {}",nomeEquipeVisitante);
			
			return PartidaFinalizadaDTO;
			
		} catch (IOException e) {
			LOGGER.error("ERRO AO CONECTAR COM O GOOGLE -> {}", e.getMessage());
		}
		
		return null;
	}
	
	public String recuperaNomeEquipe(Document document, String equipe) {
		Element element = document.selectFirst(equipe);
		String nomeEquipe = element.select(SPAN).text();
		
		return nomeEquipe;
	}
	
	public Integer recuperaPlacarEquipe(Document document, String equipe) {
		String placarEquipe = document.selectFirst(equipe).text();

		return formataPlacarStringInteger(placarEquipe);
	}
	

	public Integer formataPlacarStringInteger(String placar) {
		Integer valor;
		try {
			valor = Integer.parseInt(placar);
		} catch (Exception e) {
			valor = 0;
		}

		return valor;
	}
	
	public String montaUrlGoogle(String nomeEquipeCasa, String nomeEquipeVisitante, String data) {
		try {
			String equipeCasa = nomeEquipeCasa.replace(" ", "+").replace("-", "+");
			String equipeVisitante = nomeEquipeVisitante.replace(" ", "+").replace("-", "+");
			
			return BASE_URL_GOOGLE + equipeCasa + "+x+" + equipeVisitante + "+" + data + COMPLEMENTO_URL_GOOGLE;
			
		}catch (Exception e ) {
			LOGGER.error("ERRO: {}", e.getMessage());
		}
		
		return null;
	}
}
