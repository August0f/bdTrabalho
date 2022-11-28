package com.gabriel.petshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.petshop.model.Cliente;
import com.gabriel.petshop.repository.ClienteRepository;

@Service
public class ClienteSercive {
    
    @Autowired
    private ClienteRepository repository;
    
    public void salvar(Cliente curso) {
        repository.save(curso);
    }

    public List<Cliente> gecClientes() {
        return repository.findAll();
    }
}
