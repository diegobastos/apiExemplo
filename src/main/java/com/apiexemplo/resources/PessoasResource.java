package com.apiexemplo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiexemplo.domains.Pessoa;
import com.apiexemplo.repositories.IPessoaRepository;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoasResource {
	
	@Autowired
	private IPessoaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listPessoas() {	
       	return ResponseEntity.
			   status(HttpStatus.OK).
			   body( repository.findAll() );
	}
	
	@PostMapping()
	public void salvarPessoa(@RequestBody Pessoa p) {
		repository.save(p);
	}

}
