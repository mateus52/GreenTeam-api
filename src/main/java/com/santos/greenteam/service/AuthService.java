package com.santos.greenteam.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.santos.greenteam.entity.Usuario;
import com.santos.greenteam.exception.NotFoundException;
import com.santos.greenteam.repository.UsuarioRepository;

public class AuthService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			throw new NotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));
		
		repository.save(usuario);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}

