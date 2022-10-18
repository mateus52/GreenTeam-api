package com.santos.greenteam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santos.greenteam.entity.Metodo;


public interface MetodoRepository extends JpaRepository<Metodo, Long>{

	public Optional<Metodo> findByNomeMetodo(String nomeEquipe);

	public boolean existsByNomeMetodo(String nomeEquipe);
}
