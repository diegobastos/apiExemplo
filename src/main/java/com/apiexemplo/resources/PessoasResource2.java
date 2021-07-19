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
import com.apiexemplo.services.EnderecosService;
import com.apiexemplo.services.PessoasService;

@RestController
@RequestMapping("/v2/pessoas")
public class PessoasResource2 {

	@Autowired
	private PessoasService svc;
	
	@Autowired
	private EnderecosService eSvc;

	@GetMapping
	public ResponseEntity<List<Pessoa>> listAllPessoas() {			 
		return ResponseEntity.
				status(HttpStatus.OK).
				body( svc.listarPessoas() );
	}

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Pessoa> buscarPorUuid(@PathVariable String uuid) {	
		return ResponseEntity.
				status(HttpStatus.OK).
				body( svc.listarPessoaPorUuid(uuid) );
	}

	@PostMapping()
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa p) {

		return ResponseEntity.
				status(HttpStatus.OK).
				body( this.svc.salvarPessoa(p) );
		
	}

	@DeleteMapping(value = "/{uuid}")
	public ResponseEntity<Void> excluirPessoa(@PathVariable String uuid) {
		if (svc.excluirPessoa(uuid)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PutMapping()
	public void atualizarNome() {}

	@DeleteMapping(value = "/{uuid}/enderecos/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable("uuid") String uuid, 
			                                 @PathVariable("id") Long id) {
		if (svc.excluirEndereco(uuid, id)) {
			return ResponseEntity.status(HttpStatus.OK).build();			
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}				
	}
	
	@PostMapping(value = "/{uuid}/enderecos")
	public ResponseEntity<?> salvarNovoEndereco(@PathVariable String uuid, 
	                                            @RequestBody Endereco endereco){
	   Pessoa p = svc.listarPessoaPorUuid(uuid);
	 
	   if (p != null){ 
	      endereco.setPessoa( p );
	      eSvc.salvarEndereco(endereco);     
	   }
	   
	   return ResponseEntity.noContent().build();

	}
}