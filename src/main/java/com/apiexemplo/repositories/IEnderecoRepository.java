package com.apiexemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiexemplo.domains.Endereco;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long>{

}
