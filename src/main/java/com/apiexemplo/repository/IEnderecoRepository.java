package com.apiexemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiexemplo.domain.Endereco;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long>{

}
