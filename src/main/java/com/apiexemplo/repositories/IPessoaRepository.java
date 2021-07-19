package com.apiexemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiexemplo.domains.Pessoa;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{
    //Retorno atributoPesquisado(parametro de pesquisa)
	Pessoa getByNome(String nome);
	 
	Pessoa getByUuid(String uuid);

	Pessoa findByUuid(String uuid);
		
}