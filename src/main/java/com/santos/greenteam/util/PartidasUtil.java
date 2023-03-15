package com.santos.greenteam.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.santos.greenteam.entity.Partidas;
import com.santos.greenteam.entity.enums.StatusPartida;

@Service
public class PartidasUtil {
	
	private static final  Logger LOGGER = LoggerFactory.getLogger(PartidasUtil.class);
	private static final String BASE_URL = "https://pt.besoccer.com/competicao/";	

	public List<Partidas> obtemInformacoesPartidaFuturas(String liga) {

		String url = BASE_URL + liga;
		
		Document document = null;

		try {
			document = Jsoup.connect(url).get();

			document = Jsoup.connect(url).get();
			String title = document.title();
			LOGGER.info("Titulo da pagina: {}", title);
			LOGGER.info("URL: {}", url);

			Element table = document.selectFirst("div[class=panel-body p0 match-list-new]");

			List<Element> partidas = table.getElementsByClass("match-link p0");

			List<Partidas> partidasFuturas = new ArrayList<>();
			
			for (Element partida : partidas) {

				Elements atributos = partida.getElementsByTag("a");
				
				// busca os informações das equipes
				Elements teamLeft = atributos.select("div[class=team-info ta-r]");
				String nomeEquipeCasa = teamLeft.select("div[class=name]").text();
				String urlEquipeCasa = teamLeft.select("img[class=pv3 va-m team-shield]").attr("src");
				
				Elements teamRight = atributos.select("div[class=team-info]");
				String nomeEquipeVisitante = teamRight.select("div[class=name]").text();
				String urlEquipeVisitante = teamRight.select("img[class=pv3 va-m team-shield]").attr("src");
				
				//Busca a hora das partidas
				Elements elementHora = atributos.select("div[class=marker]");
				String horaPartida = elementHora.select("p[class= match_hour time]").text();
				
				//Busca a data das partidas
				String dataPartida = atributos.select("div[class=date-transform date ta-c]").text();
				
				LOGGER.info("Partida: {} x {} - {}/{} ", nomeEquipeCasa, nomeEquipeVisitante, dataPartida, horaPartida);

				Partidas partidaUnica = new Partidas();
				
				partidaUnica.setStatusPartdida(StatusPartida.PARTIDA_NAO_INICIADA.toString());
				partidaUnica.setTimeCasa(nomeEquipeCasa);
				partidaUnica.setUrlLogoTimeCasa(urlEquipeCasa);
				partidaUnica.setTimeVisitante(nomeEquipeVisitante);
				partidaUnica.setUrLogoTimeVisitante(urlEquipeVisitante);
				partidaUnica.setDataPartida(dataPartida);
				partidaUnica.setHoraPartida(horaPartida);
				partidasFuturas.add(partidaUnica);
			}
			
			return partidasFuturas;

		} catch (IOException e) {
			LOGGER.error("ERRO AO CONECTAR -> {}", e.getMessage());
		}
		return null;
	}
	
}
