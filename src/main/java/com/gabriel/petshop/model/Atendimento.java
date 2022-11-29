package com.gabriel.petshop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	//many to one
	//funcionario id
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	//many to one
	// cliente id
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	//many to one
	//pet id
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	// many to many
	//serviço id
	@ManyToMany
	private List<Servico> servicos;
	
	//many to one
	//orcamento id
	@ManyToOne
	@JoinColumn(nullable = false)
	private Orcamento orcamento;

}
