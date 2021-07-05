package com.apiexemplo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiexemplo.domain.Pessoa;
import com.apiexemplo.repository.IPessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoasResource {
    //repository tem os métodos do CRUD
	//importar refs. CTRL + SHIFT + O
	
	@Autowired //injeção de dependência do nosso repository
	private IPessoaRepository repository;
	
	@GetMapping
	//     responseEntity : resposta ao método GET
	public ResponseEntity<List<Pessoa>> listPessoas() {	
       		
	   //construindo o corpo da Response(resposta)	
	   return ResponseEntity.
			   status(HttpStatus.OK).
			   body( repository.findAll() );
	}
	
	@PostMapping()          //requestBody mapeado para o parâmetro 
	                        //'p' do tipo Pessoa 
	public void salvarPessoa(@RequestBody Pessoa p) {

		repository.save(p); //salva uma nova pessoa no banco de dados

	}
	
	@PutMapping()
	public void atualizarNome() {
		
	}
	
	@DeleteMapping()
	public void excluirNome() {
		
	}
}
