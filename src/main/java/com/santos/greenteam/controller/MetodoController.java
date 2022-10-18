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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santos.greenteam.DTO.MetodoDTO;
import com.santos.greenteam.DTO.MetodoResponseDTO;
import com.santos.greenteam.entity.Metodo;
import com.santos.greenteam.exception.StandardError;
import com.santos.greenteam.service.MetodoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public class MetodoController {
	
	@Autowired
	private MetodoService MetodoService;
	
	@ApiOperation(value = "Buscar Metodo por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Metodo.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@GetMapping("/{id}")
	public ResponseEntity<Metodo> buscarMetodoId(@PathVariable("id") Long id){
		
		return ResponseEntity.ok().body(MetodoService.buscarMetodoId(id));
		
	}
	
	@ApiOperation(value = "Listar Metodos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = MetodoResponseDTO.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@GetMapping
	public ResponseEntity<MetodoResponseDTO> listarMetodos(){
		
		return ResponseEntity.ok().body(MetodoService.buscarMetodos());
	}
	
	@ApiOperation(value = "Inserir Metodos")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED", response = Metodo.class),
			@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
			@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
			@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
			@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
			@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
	})
	@PostMapping
	public ResponseEntity<Metodo> inserirMetodo(@Valid @RequestBody MetodoDTO dto) {
		Metodo Metodo = MetodoService.inserirMetodo(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(Metodo.getId()).toUri();
		
		return ResponseEntity.created(location).body(Metodo);
		
	}
	
		@ApiOperation(value = "Alterar Metodos")
		@ApiResponses(value = {
				@ApiResponse(code = 204, message = "NO CONTENT", response = Void.class),
				@ApiResponse(code = 400, message = "BAD REQUEST", response = StandardError.class),
				@ApiResponse(code = 401, message = "UNAUTHORIZED", response = StandardError.class),
				@ApiResponse(code = 403, message = "FORBIDDEN", response = StandardError.class),
				@ApiResponse(code = 404, message = "NOT FOUND", response = StandardError.class),
				@ApiResponse(code = 500, message = "INTERNAL SERVER ERROR", response = StandardError.class),
		})
		@PutMapping("/{id}")
		public ResponseEntity<Void> alterarMetodo(@PathVariable("id") Long id, @Valid @RequestBody MetodoDTO dto){
			MetodoService.alterarMetodo(id, dto);
			return ResponseEntity.noContent().build();
		}

}
