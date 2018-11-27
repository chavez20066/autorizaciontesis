package com.biblioteca.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Grado;

@Repository("gradoRepository")
public interface GradoRepository extends JpaRepository<Grado,Serializable> {
	
	public abstract Grado findByidPrograma(String idPrograma);

}
