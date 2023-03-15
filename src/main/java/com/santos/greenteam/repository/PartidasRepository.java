package com.santos.greenteam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santos.greenteam.entity.Partidas;

@Repository
public interface PartidasRepository extends JpaRepository<Partidas, Integer>{

	@Query(name = "buscar_partidas_data",
			value = "select * from partidas as p "
					+ "where p.data_hora_partida =:dataPartida",
			nativeQuery = true)
	public List<Partidas> buscarPartidasDia(@Param("dataPartida") Date dataPartida);

}
