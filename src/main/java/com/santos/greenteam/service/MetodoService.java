package com.santos.greenteam.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.greenteam.DTO.MetodoDTO;
import com.santos.greenteam.DTO.MetodoResponseDTO;
import com.santos.greenteam.entity.Metodo;
import com.santos.greenteam.exception.BadRequestException;
import com.santos.greenteam.exception.NotFoundException;
import com.santos.greenteam.repository.MetodoRepository;

@Service
public class MetodoService {

	@Autowired
	private MetodoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Metodo buscarMetodoId(Long id) {
		return repository.findById(id).
				orElseThrow(() -> new NotFoundException("Nenhuma Metodo encontrado com o id informado: " + id));
	}
	
	public Metodo buscarMetodoPorNome(String nome) {
		return repository.findByNomeMetodo(nome).
				orElseThrow(() -> new NotFoundException("Nenhuma Metodo encontrado com o nome informado: " + nome));
	}

	public MetodoResponseDTO buscarMetodos() {
		MetodoResponseDTO dto = new MetodoResponseDTO();
		dto.setMetodos(repository.findAll());
		
		return dto;
	}

	public Metodo inserirMetodo(@Valid MetodoDTO dto) {
		boolean exists = repository.existsByNomeMetodo(dto.getNomeMetodo());
		if(exists) {
			throw new BadRequestException("Já existe um Metodo cadastrado com esse nome!");
		}
		
		Metodo Metodo = modelMapper.map(dto, Metodo.class);
		
		return repository.save(Metodo);
	}

	public void alterarMetodo(Long id, @Valid MetodoDTO dto) {
		boolean exists = repository.existsById(id);
		if(!exists) {
			throw new BadRequestException("Não foi possível alterar o Metodo: ID inexistente");
		}
		
		Metodo Metodo = modelMapper.map(dto, Metodo.class);
		Metodo.setId(id);
		repository.save(Metodo);
	}
}
