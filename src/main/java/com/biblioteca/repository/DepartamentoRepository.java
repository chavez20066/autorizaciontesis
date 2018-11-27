package com.biblioteca.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Departamento;



@Repository("departamentoRepository")
public interface DepartamentoRepository extends JpaRepository<Departamento,Serializable>{

	//public abstract Departamento findById(int id);
	
	//Departamento findById(int iddepartamento);
	@Query("select d from Departamento d join fetch d.provincias p where d.iddepartamento = ?1  order by p.provincia")
	public abstract Departamento findByiddepartamento(int id);
	
}
