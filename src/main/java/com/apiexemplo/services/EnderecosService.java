package com.apiexemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiexemplo.domains.Endereco;
import com.apiexemplo.repositories.IEnderecoRepository;

@Service
public class EnderecosService {

	@Autowired
	private IEnderecoRepository repository;
	
	public boolean salvarEndereco(Endereco e) {
		if( this.repository.save(e) != null) {
			return true;
		}
		return false;
	}
}
