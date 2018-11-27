package com.biblioteca.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Docente;

@Repository("docenteRepository")
public interface DocenteRepository extends JpaRepository<Docente, Serializable> {
	
	//@Query("Select d from docente d where d.apellidopaterno like:apellidopaterno")
	//public List<Docente> findByPlaceContaining(String apellidopaterno);
}
