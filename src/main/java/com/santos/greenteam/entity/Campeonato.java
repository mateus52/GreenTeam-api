package com.santos.greenteam.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "campeonato")
public class Campeonato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campeonato_id")
	private Integer id;
	
	@Column(name = "nome_campeonato")
	private String nomeCampeonato;
	
	@Column(name = "nome_ident")
	private String nomeIdent;
	
	@JsonIgnore
	@OneToMany(mappedBy="campeonato")
	private List<Partidas> partidas = new ArrayList<>();

}
