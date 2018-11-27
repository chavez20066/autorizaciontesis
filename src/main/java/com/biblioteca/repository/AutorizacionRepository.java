package com.biblioteca.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.AutorizCybertesis;

@Repository("autorizacionRepository")
public interface AutorizacionRepository extends JpaRepository<AutorizCybertesis, Serializable> {
	

	@Query("select  a from AutorizCybertesis a join fetch a.autores acd where acd.CodAlumno = ?1")	
	public abstract List<AutorizCybertesis> findByCodAlumno(String CodAlumno);
	
	@Query("select  a from AutorizCybertesis a right join fetch a.autores acd where a.Autorizac = ?1")	
	public abstract AutorizCybertesis findByAutorizac(int Autorizac);

}
