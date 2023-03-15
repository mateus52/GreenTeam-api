package com.santos.greenteam.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santos.greenteam.entity.Usuario;
import com.santos.greenteam.service.UserService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired
	private UserService usuarioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuarioId(@PathVariable("id") Integer id){
		
		return ResponseEntity.ok().body(usuarioService.buscarUsuarioId(id));
		
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> buscarUsuarioEmail(@RequestParam("value") String email){
		
		return ResponseEntity.ok().body(usuarioService.buscarUsuarioEmail(email));
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public List<Usuario> listarUsuarios(){
		
		return usuarioService.buscarUsuarios();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserirUsuario(@Valid @RequestBody Usuario user) {
		Usuario usuario = usuarioService.inserirUsuario(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(location).body(usuario);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> alterarUsuario(@PathVariable("id") Integer id, @Valid @RequestBody Usuario user) {
		usuarioService.alterarUsuario(id, user);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value="/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = usuarioService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
