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

import com.gabriel.petshop.model.Servico;
import com.gabriel.petshop.repository.ServicoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/servico")
@AllArgsConstructor
public class ServicoController {

    @Autowired	
	private ServicoRepository servicoRepository;
		
		@GetMapping
		public List<Servico> list(){
			return servicoRepository.findAll();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Servico> findById(@PathVariable Long id) {
			return servicoRepository.findById(id)
					.map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public Servico create(@RequestBody Servico servico) {
			return servicoRepository.save(servico);
		}

		@PutMapping
		public Servico editar(@RequestBody Servico servico){
			Servico editarServico =servicoRepository.save(servico);
			return editarServico;
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id){
			return servicoRepository.findById(id)
			.map(recordFound -> {
				servicoRepository.deleteById(id);
				return ResponseEntity.noContent().<Void>build();
			})
			.orElse(ResponseEntity.notFound().build());
		}
    
}
