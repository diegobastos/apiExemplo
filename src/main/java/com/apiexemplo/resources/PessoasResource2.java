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

import com.apiexemplo.domains.Endereco;
import com.apiexemplo.domains.Pessoa;
import com.apiexemplo.repositories.IEnderecoRepository;
import com.apiexemplo.repositories.IPessoaRepository;

@RestController
@RequestMapping("/v2/pessoas")
public class PessoasResource2 {

	@Autowired
	private IPessoaRepository repPessoa;

	@Autowired
	private IEnderecoRepository repEndereco;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listAllPessoas() {			 
		return ResponseEntity.
				status(HttpStatus.OK).
				body( repPessoa.findAll() );
	}

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Pessoa> buscarPorUuid(@PathVariable String uuid) {	
		return ResponseEntity.
				status(HttpStatus.OK).
				body( repPessoa.getByUuid(uuid) );
	}

	@PostMapping()
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa p) {

		return ResponseEntity.
				status(HttpStatus.OK).
				body( this.repPessoa.save(p) );
	}

	@DeleteMapping(value = "/{uuid}")
	public ResponseEntity<Void> excluirPessoa(@PathVariable String uuid) {
		Pessoa p = repPessoa.findByUuid(uuid);

		if ( p != null ) {
			repPessoa.deleteById(p.getId());
		} 

		return ResponseEntity.noContent().build();
	}

	@PutMapping()
	public void atualizarNome() {}

	@DeleteMapping(value = "/{uuid}/enderecos/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable("uuid") String uuid, 
											 @PathVariable("id") Long id){
		Pessoa p = repPessoa.findByUuid(uuid);
        final Endereco e;
		
		if ( p != null ) {
			e = repEndereco.getById(id);
			
			if (e != null && e.getPessoa().getId() == p.getId()) {
				repEndereco.deleteById(e.getId());
			}			
		} 

		return ResponseEntity.noContent().build();
	}
}