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


import com.gabriel.petshop.model.Cliente;
import com.gabriel.petshop.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController{

	@Autowired	
	private ClienteRepository clienteRepository;
		
		//Listando Cliente
		@GetMapping
		public List<Cliente> list(){
	 		return clienteRepository.findAll();
		}

		// @GetMapping("/api/cliente")
		// public ModelAndView getCliente(){
		// 	ModelAndView mv = new ModelAndView("cliente");

		// 	mv.addObject("cliente", new Cliente());
			
		// 	return mv;

		// }

		
		//Buscando cliente por Id
		// @GetMapping("/{id}")
		// public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		// 	return clienteRepository.findById(id)
		// 			.map(record -> ResponseEntity.ok().body(record))
		// 			.orElse(ResponseEntity.notFound().build());
		// }
		
		//Adicioanando Cliente
		@PostMapping
		@ResponseStatus(code = HttpStatus.CREATED)
		public Cliente create(@RequestBody Cliente cliente) {
			return clienteRepository.save(cliente);
		}

		//Atualizando cliente (maneira um pouco errada)
		@PutMapping
		public Cliente editar(@RequestBody Cliente cliente){
			Cliente editarCliente = clienteRepository.save(cliente);
			return editarCliente;
		}

		
		//Deletendo Cliente
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			return clienteRepository.findById(id)
					.map(recordFound -> {
						clienteRepository.deleteById(id);
						return ResponseEntity.noContent().<Void>build();
					})
					.orElse(ResponseEntity.notFound().build());
		}

		/*  Methodo de atulizar cliente mapeando o objeto (nao ta funcionando)
		@PutMapping("/{id}")
		public ResponseEntity<Cliente> update(@PathVariable Long id,
				@RequestBody Cliente cliente) {
			return clienteRepository.findById(id)
			.map(recordFound -> {
				recordFound.setCpf(cliente.getCpf());
				recordFound.setNome(cliente.getNome());
				recordFound.setTelefone(cliente.getTelefone());
				Cliente update = clienteRepository.save(recordFound);
				return ResponseEntity.ok().body(update)
			}).orElse(ResponseEntity.notFound().build());
					
		}*/

		@GetMapping("/{id}")
		public ResponseEntity<Cliente> findById(@PathVariable Long id, 
			@RequestBody Cliente cliente) {
			return clienteRepository.findById(id)
					.map(recordFound -> {
					recordFound.setId(cliente.getId());
					recordFound.setNome(cliente.getNome());
					recordFound.setCpf(cliente.getCpf());
					recordFound.setTelefone(cliente.getTelefone());
					return ResponseEntity.ok().body(recordFound);
					}).orElse(ResponseEntity.notFound().build());
		}
	
}