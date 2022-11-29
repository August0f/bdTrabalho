package com.gabriel.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.petshop.model.Pet;
import com.gabriel.petshop.repository.PetRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pet")
@AllArgsConstructor
public class PetController {
	
	@Autowired	
	private PetRepository petRepository;
		
		@GetMapping
		public List<Pet> list(){
			return petRepository.findAll();
		}
		
		// @GetMapping("/{id}")
		// public ResponseEntity<Pet> findById(@PathVariable Long id) {
		// 	return petRepository.findById(id)
		// 			.map(record -> ResponseEntity.ok().body(record))
		// 			.orElse(ResponseEntity.notFound().build());
		// }
		
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public Pet create(@RequestBody Pet pets) {
			return petRepository.save(pets);
		}

		@PutMapping
		public Pet editar(@RequestBody Pet pet){
			Pet editarPet = petRepository.save(pet);
			return editarPet;
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id){
			return petRepository.findById(id)
			.map(recordFound -> {
				petRepository.deleteById(id);
				return ResponseEntity.noContent().<Void>build();
			})
			.orElse(ResponseEntity.notFound().build());
		}

		@GetMapping("/{id}")
		public ResponseEntity<Pet> findById(@PathVariable Long id, 
			@RequestBody Pet pet) {
			return petRepository.findById(id)
					.map(recordFound -> {
					recordFound.setId(pet.getId());
					recordFound.setNome(pet.getNome());
					recordFound.setRaca(pet.getRaca());
					recordFound.setTipo(pet.getTipo());
					Pet js = petRepository.save(recordFound);
					return ResponseEntity.ok().body(recordFound);
					}).orElse(ResponseEntity.notFound().build());
		}
}
