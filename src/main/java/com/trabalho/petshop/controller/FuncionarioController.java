package com.trabalho.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.trabalho.petshop.model.Funcionario;
import com.trabalho.petshop.repository.FuncionarioRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/funcionario")
@AllArgsConstructor
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

        @GetMapping
        public List<Funcionario> list(){
            return funcionarioRepository.findAll();
        }

        @PostMapping
        @ResponseStatus(code = HttpStatus.CREATED)
        public Funcionario create(@RequestBody Funcionario funcionario){
            return funcionarioRepository.save(funcionario);
        }

        @PutMapping
        public Funcionario editar(@RequestBody Funcionario funcionario){
            Funcionario editarFuncionario = funcionarioRepository.save(funcionario);
            return editarFuncionario;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id){
            return funcionarioRepository.findById(id)
            .map(recordFound -> {
                funcionarioRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
        }
}
