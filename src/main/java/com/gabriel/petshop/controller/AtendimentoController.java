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

import com.gabriel.petshop.model.Atendimento;
import com.gabriel.petshop.repository.AtendimentoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/atendimento")
@AllArgsConstructor
public class AtendimentoController {
   
    @Autowired	
	private AtendimentoRepository atendimentoRepository;
		

		@GetMapping
		public List<Atendimento> list(){
	 		return atendimentoRepository.findAll();
		}

		@GetMapping("/{id}")
		public ResponseEntity<Atendimento> findById(@PathVariable Long id) {
			return atendimentoRepository.findById(id)
					.map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public Atendimento create(@RequestBody Atendimento atendimento) {
			return atendimentoRepository.save(atendimento);
		}

		@PutMapping
		public Atendimento editar(@RequestBody Atendimento atendimento){
			Atendimento editarAtendimento = atendimentoRepository.save(atendimento);
			return editarAtendimento;
		}

		
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			return atendimentoRepository.findById(id)
					.map(recordFound -> {
						atendimentoRepository.deleteById(id);
						return ResponseEntity.noContent().<Void>build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
}
