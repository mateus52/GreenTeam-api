package com.santos.greenteam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santos.greenteam.entity.Campeonato;

@Repository
public interface CampeonatosRepository extends JpaRepository<Campeonato, Integer>{

	Optional<Campeonato> findByNomeIdent(String nome);

	boolean existsByNomeCampeonato(String nomeCampeonato);

	@Query(name = "buscar_identificadores",
			value = "select c.nome_ident from campeonato as c "
					+ " group by c.nome_ident ",
			nativeQuery = true)
	public List<String> buscarIdentificadores();

}
