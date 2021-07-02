package com.apiexemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiexemplo.domain.Pessoa;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{

	//save
	//delete
	//findAll()
}