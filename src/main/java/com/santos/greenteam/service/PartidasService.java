package com.santos.greenteam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.greenteam.entity.Partidas;
import com.santos.greenteam.exception.NotFoundException;
import com.santos.greenteam.repository.PartidasRepository;
import com.santos.greenteam.util.PartidasUtil;

@Service
public class PartidasService {

	@Autowired
	private PartidasRepository repository;
	
	@Autowired
	private CampeonatosService service;
	
	@Autowired
	private PartidasUtil partidasUtil;

	public Partidas buscarPartidasId(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma Partida encontrada com o id informado: " + id));
	}

	public List<Partidas> buscarPartidass() {
		return repository.findAll();
	}
	
	

	public void inserirPartidas() {

		List<String> campeonatos = service.buscarIdentificadoresCampeonatos();

		if (!campeonatos.isEmpty()) {
			for (String s : campeonatos) {
				List<Partidas> partidas = partidasUtil.obtemInformacoesPartidaFuturas(s);
				if(!partidas.isEmpty()) {
				for (Partidas p : partidas) {
					p.setId(null);
					p.setCampeonato(service.buscarCampeonatoPorNome(s));
					repository.save(p);
				}
			}

		}
		}
	}
		
	public void delete(Integer id) {
		buscarPartidasId(id);
		repository.deleteById(id);

	}

	public List<Partidas> findPagePartidas(String data) {
		List<Partidas> listPartidas = repository.findAll();	
		listPartidas = listPartidas.stream().filter(c -> c.getDataPartida().equals(data)).collect(Collectors.toList());
		
		return listPartidas;
	}
	

}
