package com.biblioteca.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Programa;

@Repository("programaRepository")
public interface ProgramaRepository extends JpaRepository<Programa,Serializable>{
	
	public abstract Programa findByidPrograma(String idPrograma);
	
}
