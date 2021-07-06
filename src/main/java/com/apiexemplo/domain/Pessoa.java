package com.apiexemplo.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Pessoa {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotNull
	private String nome;
	
	@NotNull
	private String sobrenome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date nascimento;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("uuid")
	@Column(name = "ext_uuid", updatable = false, 
			columnDefinition = "varchar(36) not null")
    private String uuid;
	
	public Pessoa() {
		UUID uuid_ = UUID.randomUUID();
		this.uuid = uuid_.toString(); 
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getUuid() {
		return uuid;
	}
	
}
