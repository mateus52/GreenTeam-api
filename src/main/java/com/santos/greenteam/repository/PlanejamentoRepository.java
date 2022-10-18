package com.santos.greenteam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.santos.greenteam.entity.Planejamento;


public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long>{

	@Query(name = "buscar_quantidade_planejamento",
			value = "select count(*) from planejamento as p "
					+ "where p.current_timestamp > dateadd(hour, 3, p.data_hora_partida) "
					+ "and status_partida = 'PARTIDA_NAO_INICIADA' ",
			nativeQuery = true)
	public Integer buscarQuantidadesPlanejemento();
	
	@Query(name = "buscar_quantidade_planejamento",
			value = "select * from planejamento as p "
					+ "where p.current_timestamp > dateadd(hour, 3, p.data_hora_partida) "
					+ "and status_partida = 'PARTIDA_NAO_INICIADA' ",
			nativeQuery = true)
	public List<Planejamento> buscarQuantidadespartidas();

}
