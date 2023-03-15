package com.santos.greenteam.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.santos.greenteam.entity.Campeonato;
import com.santos.greenteam.entity.Usuario;
import com.santos.greenteam.entity.enums.Perfil;
import com.santos.greenteam.repository.CampeonatosRepository;
import com.santos.greenteam.repository.UsuarioRepository;

@Service
public class DBservice {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CampeonatosRepository campeonatoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Campeonato camp = new Campeonato(null, 	"Italiano", "bundesliga", null);
		campeonatoRepository.save(camp);

		Usuario userAdmin = new Usuario(null, "Mateus", pe.encode("123"), "mateusdossantos@gmail.com");
		userAdmin.addPerfil(Perfil.ADMIN);
		
		Usuario userClient = new Usuario(null, "Eve", pe.encode("456"), "eve@gmail.com");
		userClient.addPerfil(Perfil.CLIENTE);
		
		usuarioRepository.saveAll(Arrays.asList(userAdmin, userClient));	
		
	}

}
