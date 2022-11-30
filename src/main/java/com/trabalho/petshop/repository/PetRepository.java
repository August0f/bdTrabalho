package com.trabalho.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.petshop.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

}
