package com.santos.greenteam.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santos.greenteam.DTO.CampeonatoDTO;
import com.santos.greenteam.entity.Campeonato;
import com.santos.greenteam.service.CampeonatosService;

@RestController
@RequestMapping("/api/v1/Campeonatos")
public class CampeonatoController {
	
	@Autowired
	private CampeonatosService campeonatoService;
	
	public ResponseEntity<Campeonato> buscarCampeonatoId(@PathVariable("id") Integer id){
		return ResponseEntity.ok().body(campeonatoService.buscarCampeonatoId(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CampeonatoDTO>> listarCampeonatos(){
		List<Campeonato> list = campeonatoService.buscarCampeonatos();
		List<CampeonatoDTO> listDto = list.stream().map(obj -> new CampeonatoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Campeonato> inserirCampeonato(@Valid @RequestBody CampeonatoDTO dto) {
		Campeonato Campeonato = campeonatoService.inserirCampeonato(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(Campeonato.getId()).toUri();
		
		return ResponseEntity.created(location).body(Campeonato);
		
	}

	@PutMapping
	public ResponseEntity<Void> alterarCampeonato(@Valid @RequestBody CampeonatoDTO dto, @PathVariable("id") Integer id){
		campeonatoService.alterarCampeonato(id, dto);
		return ResponseEntity.noContent().build();
		}

}
