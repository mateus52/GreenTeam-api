package com.santos.greenteam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santos.greenteam.DTO.PartidaFuturaDTO;
import com.santos.greenteam.entity.Partidas;
import com.santos.greenteam.service.PartidasService;

@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController {

	@Autowired
	private PartidasService partidaService;

	@GetMapping("/{id}")
	public ResponseEntity<Partidas> buscarPartidaId(@PathVariable("id") Integer id) {

		return ResponseEntity.ok().body(partidaService.buscarPartidasId(id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePartida(@PathVariable Integer id) {
		partidaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/partidasFuturas")
	public ResponseEntity<List<PartidaFuturaDTO>> findPage(
			@RequestParam(value = "data") String data)
	{
		List<Partidas> list = partidaService.findPagePartidas(data);
		List<PartidaFuturaDTO> listDto = list.stream().map(obj -> new PartidaFuturaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}	
}
