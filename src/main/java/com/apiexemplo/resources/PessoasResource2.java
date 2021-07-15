package com.apiexemplo.resources;

import java.util.List;

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

import com.apiexemplo.domains.Pessoa;
import com.apiexemplo.services.PessoasService;

@RestController
@RequestMapping("/v2/pessoas")
public class PessoasResource2 {

	private PessoasService svc;
	
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
				body( svc.salvarPessoa(p) );
	}

	@DeleteMapping(value = "/{uuid}")
	public ResponseEntity<Void> excluirPessoa(@PathVariable String uuid) {
		if (svc.excluirPessoa(uuid)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping()
	public void atualizarNome() {}

	@DeleteMapping(value = "/{uuid}/enderecos/{id}")
	public ResponseEntity<?> excluirEndereco(@PathVariable("uuid") String uuid, 
											 @PathVariable("id") Long id) {
        svc.excluirEndereco(uuid, id);

		return ResponseEntity.noContent().build();
	}
}