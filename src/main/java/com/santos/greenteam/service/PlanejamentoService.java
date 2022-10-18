package com.santos.greenteam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.greenteam.DTO.PartidaFinalizadaDTO;
import com.santos.greenteam.DTO.PartidaFuturaDTO;
import com.santos.greenteam.DTO.PlanejamentoDTO;
import com.santos.greenteam.DTO.PlanejamentoResponseDTO;
import com.santos.greenteam.entity.Planejamento;
import com.santos.greenteam.exception.NotFoundException;
import com.santos.greenteam.repository.PlanejamentoRepository;



@Service
public class PlanejamentoService {
	
	@Autowired
	private PlanejamentoRepository repository;
	
	@Autowired
	private MetodoService metodoService;
	
	public Planejamento buscarPlanejamentoId(Long id) {
		return repository.findById(id).
				orElseThrow(() -> new NotFoundException("Nenhum planejamento encontrado com o id informado: " + id));
	}
	
	public PlanejamentoResponseDTO buscarPlanejamentos() {
		PlanejamentoResponseDTO dto = new PlanejamentoResponseDTO();
		dto.setPlanejamento(repository.findAll());
		
		return dto;
	}
	
	public Planejamento inserirPlanejamento( PartidaFuturaDTO dto) {
		
		Planejamento planejamento = new Planejamento();
		planejamento.setEquipeCasa(dto.getEquipeCasa());
		planejamento.setEquipeVisitante(dto.getEquipeVisitante());
		planejamento.setDataHoraPartida(dto.getDataHoraPartida());
		planejamento.setHora(dto.getHoraPartida());
		
		
		return repository.save(planejamento);
	}
	
	public void atualizaPlanejamento(Long id, PlanejamentoDTO dto) {
	
		Planejamento planejamento = buscarPlanejamentoId(id);
		planejamento.setMetodo(metodoService.buscarMetodoPorNome(dto.getMetodo().getNomeMetodo()));
		planejamento.setOdd(dto.getOdd());
		planejamento.setValor(dto.getValor());
		
		repository.save(planejamento);
	}
	
	public void atualizaPlanejamentoFinalizado(Planejamento planejamento, PartidaFinalizadaDTO dto) {
		planejamento.setGolsEquipeCasa(dto.getGolsEquipeCasa());
		planejamento.setGolsEquipeVisitante(dto.getGolsEquipeVisitante());
		planejamento.setStatusPartida(dto.getStatusPartdida().toString());
		planejamento.setResultado(null);
		
		repository.save(planejamento);
	}
	
	public Integer buscarQuantidadesPlanejemento() {
	
	return repository.buscarQuantidadesPlanejemento();
	
	}

	public List<Planejamento> buscarPartidasPeriodos() {
		
		return repository.buscarQuantidadespartidas();
	}
}
