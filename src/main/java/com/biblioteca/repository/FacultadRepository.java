package com.biblioteca.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Facultad;

@Repository("facultadRepository")
public interface FacultadRepository extends JpaRepository<Facultad,Serializable>{
	//OrderByFirstnameAsc
	
	@Query("select f from Facultad f order by f.Facultad")
	public abstract List<Facultad> findAllOrderByFacultad();
	
	//@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
//	@Query("select f from Facultad f join fetch f.idfacultad p order by f.Facultad")
	@Query("select f from Facultad f join fetch f.programas p where f.idFacultad = ?1 and p.Estado='A' order by p.progProf")
	public  abstract Facultad findByIdFacultad(String idfacultad);
	

}
