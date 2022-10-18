package com.santos.greenteam.controller;

import java.net.URI;

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

import com.santos.greenteam.DTO.PartidaFuturaDTO;
import com.santos.greenteam.DTO.PlanejamentoDTO;
import com.santos.greenteam.DTO.PlanejamentoResponseDTO;
import com.santos.greenteam.entity.Planejamento;
import com.santos.greenteam.exception.StandardError;
import com.santos.greenteam.service.PlanejamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("API - Planejamentos")
@RestController
@RequestMapping("/api/v1/Planejamentos")
public class PlanejamentoController {
	
	@Autowired
	private PlanejamentoService PlanejamentoService;
	
	@ApiOperation(value = "Buscar Planejamento por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Planejamento.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Planejamento> buscarPlanejamentoId(@PathVariable("id") Long id){
		
		return ResponseEntity.ok().body(PlanejamentoService.buscarPlanejamentoId(id));
		
	}
	
	@ApiOperation(value = "Listar Planejamentos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PlanejamentoResponseDTO.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@GetMapping
	public ResponseEntity<PlanejamentoResponseDTO> listarPlanejamentos(){
		
		return ResponseEntity.ok().body(PlanejamentoService.buscarPlanejamentos());
	}
	
	@ApiOperation(value = "Inserir Planejamentos")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED", response = Planejamento.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@PostMapping
	public ResponseEntity<Planejamento> inserirEquipe(@Valid @RequestBody PartidaFuturaDTO dto) {
		Planejamento Planejamento = PlanejamentoService.inserirPlanejamento(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(Planejamento.getId()).toUri();
		
		return ResponseEntity.created(location).body(Planejamento);
		
	}
	
	@ApiOperation(value = "Alterar Planejamentos")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO CONTENT", response = Void.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> alterarEquipe(@PathVariable("id") Long id, @Valid @RequestBody PlanejamentoDTO dto){
		PlanejamentoService.atualizaPlanejamento(id, dto);
		return ResponseEntity.noContent().build();
	}

}
