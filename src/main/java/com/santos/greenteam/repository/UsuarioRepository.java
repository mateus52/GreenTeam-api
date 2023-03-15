package com.santos.greenteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santos.greenteam.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	boolean existsByEmail(String email);

	Usuario findByEmail(String email);


}
