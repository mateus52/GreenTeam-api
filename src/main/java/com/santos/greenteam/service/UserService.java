package com.santos.greenteam.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.santos.greenteam.entity.Usuario;
import com.santos.greenteam.entity.enums.Perfil;
import com.santos.greenteam.exception.BadRequestException;
import com.santos.greenteam.exception.ForbiddenException;
import com.santos.greenteam.exception.NotFoundException;
import com.santos.greenteam.repository.UsuarioRepository;
import com.santos.greenteam.security.UserSS;

@Service
public class UserService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public Usuario buscarUsuarioId(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new ForbiddenException("Acesso negado");
		}
		
		return repository.findById(id).
				orElseThrow(() -> new NotFoundException("Nenhum Usuario encontrado com o id informado: " + id));
	}
	
	public Usuario buscarUsuarioEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new ForbiddenException("Acesso negado");
		}
		
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			throw new NotFoundException("Email não encontrado: " + email);
		}
		
		return usuario;
	}
	

	public List<Usuario> buscarUsuarios() {
		return repository.findAll();
	}

	public Usuario inserirUsuario(Usuario usuario) {
		boolean exists = repository.existsByEmail(usuario.getEmail());
		if(exists) {
			throw new BadRequestException("Já existe um Usuario cadastrado com esse email!");
		}
		
		Usuario Usuario = modelMapper.map(usuario, Usuario.class);
		
		return repository.save(Usuario);
	}

	public void alterarUsuario(Integer id, Usuario usuario) {
		boolean exists = repository.existsById(id);
		if(!exists) {
			throw new BadRequestException("Não foi possível alterar o Usuario: ID inexistente");
		}
		
		Usuario user = modelMapper.map(usuario, Usuario.class);
		user.setId(id);
		repository.save(user);
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new ForbiddenException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}