package com.apiexemplo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiexemplo.domains.Endereco;
import com.apiexemplo.domains.Pessoa;
import com.apiexemplo.repositories.IEnderecoRepository;
import com.apiexemplo.repositories.IPessoaRepository;

@Service
public class PessoasService {
	
	@Autowired
	private IPessoaRepository repPessoa;
	
	@Autowired
	private IEnderecoRepository repEndeco;

	public List<Pessoa> listarPessoas(){
		return repPessoa.findAll();
	}
	
	public Pessoa listarPessoaPorUuid(String uuid) {
		return repPessoa.findByUuid(uuid);
	}
	
	public Pessoa salvarPessoa(Pessoa p) {	
		p.setUuid( UUIDServices.genUUID() );
		return repPessoa.save(p);
	}
	
	public boolean excluirPessoa(String uuid) {
		Pessoa p = repPessoa.findByUuid(uuid);

		if ( p != null ) {
			repPessoa.deleteById(p.getId());
			return true;
		}
		return false;
	}
	
	public boolean excluirEndereco(String uuid, Long idEnd) {
		Pessoa p = repPessoa.findByUuid(uuid);
        final Endereco e;
		
		if ( p != null ) {
			e = repEndeco.getById(idEnd);
			
			if (e != null && e.getPessoa().getId() == p.getId()) {
				repEndeco.deleteById(e.getId());
				return true;
			}			
		} 
		return false;
		
	}
}
