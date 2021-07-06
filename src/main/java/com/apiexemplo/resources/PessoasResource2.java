package com.apiexemplo.resources;

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
import org.springframework.web.bind.annotation.RestController;

import com.apiexemplo.domain.Pessoa;
import com.apiexemplo.repository.IPessoaRepository;

@RestController
@RequestMapping("/v2/pessoas")
public class PessoasResource2 {
	
	@Autowired
	private IPessoaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listPessoas() {	
       	return ResponseEntity.
			   status(HttpStatus.OK).
			   body( repository.findAll() );
	}
	

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Pessoa> getByUuid(@PathVariable String uuid) {	
       	return ResponseEntity.
			   status(HttpStatus.OK).
			   body( repository.getByUuid(uuid) );
	}
	
	@PostMapping()
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa p) {

		return ResponseEntity.
				status(HttpStatus.OK).
				body( this.repository.save(p) );
	}
	
	@PutMapping()
	public void atualizarNome() {}
	
	//[DELETE] -> http://localhost:1805/v2/pessoas/100
	@DeleteMapping(value = "/{id}")
	public void excluirPessoa(@PathVariable Long id) {
		if ( repository.findById(id).orElse(null) != null ) {
			repository.deleteById(id);
		} 
	}
}