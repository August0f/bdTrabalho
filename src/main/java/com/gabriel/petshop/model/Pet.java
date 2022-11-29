package com.gabriel.petshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column
	private String tipo;
	
	@Column
	private String raca;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id",  nullable=false)
	private Cliente cliente;
	
	//one to many para a tabela atendimento
	@OneToMany
	@JoinColumn(name = "pet_id", referencedColumnName = "id")
	private List<Atendimento> atendimentos;
}
