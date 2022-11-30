package com.trabalho.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.petshop.model.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

}
