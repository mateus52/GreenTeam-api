package com.santos.greenteam.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.greenteam.DTO.CampeonatoDTO;
import com.santos.greenteam.entity.Campeonato;
import com.santos.greenteam.exception.BadRequestException;
import com.santos.greenteam.exception.NotFoundException;
import com.santos.greenteam.repository.CampeonatosRepository;

@Service
public class CampeonatosService {

	@Autowired
	private CampeonatosRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Campeonato buscarCampeonatoId(Integer id) {
		return repository.findById(id).
				orElseThrow(() -> new NotFoundException("Nenhum Campeonato encontrado com o id informado: " + id));
	}
	
	public Campeonato buscarCampeonatoPorNome(String nome) {
		return repository.findByNomeIdent(nome).
				orElseThrow(() -> new NotFoundException("Nenhum Campeonato encontrado com o nome informado: " + nome));
	}

	public List<Campeonato> buscarCampeonatos() {
		return repository.findAll();
	}
	
	public List<String> buscarIdentificadoresCampeonatos() {
		return repository.buscarIdentificadores();
	}

	public Campeonato inserirCampeonato(@Valid CampeonatoDTO campeonato) {
		boolean exists = repository.existsByNomeCampeonato(campeonato.getNomeCampeonato());
		if(exists) {
			throw new BadRequestException("Já existe um Campeonato cadastrado com esse nome!");
		}
		Campeonato camp = modelMapper.map(campeonato, Campeonato.class);
		return repository.save(camp);
	}

	public void alterarCampeonato(Integer id, @Valid CampeonatoDTO campeonato) {
		boolean exists = repository.existsById(id);
		if(!exists) {
			throw new BadRequestException("Não foi possível alterar o Campeonato: ID inexistente");
		}
		Campeonato camp = modelMapper.map(campeonato, Campeonato.class);
		camp.setId(id);
		repository.save(camp);
	}
	
}
