package com.gabriel.petshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(length = 11, nullable = false, unique = true)
	private long cpf;
	
	@Column
	private String nome;
	
	@Column
	private String telefone;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pet> pets;
	
	//one to many para a tabela atendimento
	@OneToMany
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private List<Atendimento> atendimentos;
}
