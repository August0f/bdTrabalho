package com.gabriel.petshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column
	private String nome;
	
	@Column
	private float preco;

	// many to many para atendimento
	@ManyToMany(mappedBy = "servicos")
	private List<Atendimento> atendimentos;
}
