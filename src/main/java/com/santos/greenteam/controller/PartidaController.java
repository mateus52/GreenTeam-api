package com.santos.greenteam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santos.greenteam.DTO.PartidaFinalizadaDTO;
import com.santos.greenteam.DTO.PartidaFuturaDTO;
import com.santos.greenteam.exception.StandardError;
import com.santos.greenteam.service.PartidaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("API - Partidas")
@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController {

	@Autowired
	private PartidaService partidaService;
	
	@ApiOperation(value = "Listar Partidas futuras")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PartidaFuturaDTO.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@GetMapping("/futuras")
	public List<PartidaFuturaDTO> listarPlanejamentos(@RequestParam String data, @RequestParam String liga){
		
		return partidaService.listarPartidasFuturas(data, liga);
	}
	
	@ApiOperation(value = "Buscar partida finalizada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = PartidaFinalizadaDTO.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@GetMapping("/finalizadas")
	public ResponseEntity<PartidaFinalizadaDTO> buscarPlanejamentoId(@RequestParam String nomeEquipeCasa, 
			@RequestParam String nomeEquipeVisitante, @RequestParam String data){
		
		return ResponseEntity.ok().body(partidaService.listarPartidaFinalizada(nomeEquipeCasa, nomeEquipeVisitante, data));
		
	}
}
