package com.santos.greenteam.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.santos.greenteam.DTO.PartidaFuturaDTO;

@Service
public class PartidasFuturasUtil {
	
	private static final  Logger LOGGER = LoggerFactory.getLogger(PartidasFuturasUtil.class);

	private static final String BASE_URL = "https://www.espn.com.br/futebol/fixtures/_/data/";	
	private static final String URL_LIGA = "/liga/";	
	
	public List<PartidaFuturaDTO> obtemInformacoesPartida(String url) {

		Document document = null;

		try {
			document = Jsoup.connect(url).get();

			document = Jsoup.connect(url).get();
			String title = document.title();
			LOGGER.info("Titulo da pagina: {}", title);

			Element table = document.selectFirst("table[class=schedule has-team-logos soccer align-left]");

			Element tbody = table.getElementsByTag("tbody").first();

			List<Element> partidas = tbody.getElementsByTag("tr");

			List<PartidaFuturaDTO> partidasDto = new ArrayList<>();

			for (Element partida : partidas) {

				List<Element> atributos = partida.getElementsByTag("td");
				// busca o nome da equipe de casa
				Element tdCasa = atributos.get(0);
				Element ElementNomeCasa = tdCasa.getElementsByTag("a").first();
				String nomeEquipeCasa = ElementNomeCasa.select("span").text();

				// busca a logo da equipe de casa
				Element spanLogo = tdCasa.select("span[class=team-logo]").first();
				String urlLogoCasa = spanLogo.getElementsByTag("img").attr("src");

				Element tdVisitante = atributos.get(1);
				Element ElementNomeVisitante = tdVisitante.getElementsByTag("a").first();
				String nomeEquipeVisitante = ElementNomeVisitante.select("span").text();

				// busca a logo da equipe de casa
				Element spanLogoVisitante = tdVisitante.select("span[class=team-logo]").first();
				String urlLogoVisitante = spanLogoVisitante.getElementsByTag("img").attr("src");

				Element tdData = atributos.get(2);
				String dataPartida = tdData.attr("data-date");

				PartidaFuturaDTO dto = new PartidaFuturaDTO();
				dto.setStatusPartdida(StatusPartida.PARTIDA_NAO_INICIADA.toString());
				dto.setEquipeCasa(nomeEquipeCasa);
				dto.setUrLogoEquipeCasa(urlLogoCasa);
				dto.setEquipeVisitante(nomeEquipeVisitante);
				dto.setUrLogoEquipeVisitantea(urlLogoVisitante);
				dto.setDataHoraPartida(DataUtil.formataStringEmDate(dataPartida.substring(0, 10).replace("-", "/")));
				dto.setHoraPartida((dataPartida.substring(11, 16)));
				
				partidasDto.add(dto);

			}
			
			return partidasDto;

		} catch (IOException e) {
			LOGGER.error("ERRO AO CONECTAR COM A ESPN -> {}", e.getMessage());
		}
		return null;
	}
	
	public String montaUrl(String data, String liga) {
		try {
			
			return BASE_URL + data + URL_LIGA + liga;
			
		}catch (Exception e ) {
			LOGGER.error("ERRO: {}", e.getMessage());
		}
		
		return null;
	}
	
}
