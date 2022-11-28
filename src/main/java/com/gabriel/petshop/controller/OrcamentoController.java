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

import com.gabriel.petshop.model.Orcamento;
import com.gabriel.petshop.repository.OrcamentoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orcamento")
@AllArgsConstructor
public class OrcamentoController {
    
    @Autowired	
	private OrcamentoRepository orcamentoRepository;
		
		@GetMapping
		public List<Orcamento> list(){
	 		return orcamentoRepository.findAll();
		}
        
		//Buscando cliente por Id
		@GetMapping("/{id}")
		public ResponseEntity<Orcamento> findById(@PathVariable Long id) {
			return orcamentoRepository.findById(id)
					.map(record -> ResponseEntity.ok().body(record))
					.orElse(ResponseEntity.notFound().build());
		}
		
		//Adicioanando Cliente
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public Orcamento create(@RequestBody Orcamento orcamento) {
			return orcamentoRepository.save(orcamento);
		}

		//Atualizando cliente (maneira um pouco errada)
		@PutMapping
		public Orcamento editar(@RequestBody Orcamento orcamento){
			Orcamento editarorcamento = orcamentoRepository.save(orcamento);
			return editarorcamento;
		}

		
		//Deletendo Cliente
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			return orcamentoRepository.findById(id)
					.map(recordFound -> {
						orcamentoRepository.deleteById(id);
						return ResponseEntity.noContent().<Void>build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
    
}
