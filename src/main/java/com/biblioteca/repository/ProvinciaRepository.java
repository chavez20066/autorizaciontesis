package com.biblioteca.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Provincia;

@Repository("provinciaRepository")
public interface ProvinciaRepository extends JpaRepository<Provincia, Serializable> {

	public abstract Provincia findByIdprovincia(int id);
	
	//@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
	//@Query("select p from Provincia p join fetch p.departamento d where d.idDepartamento=?1 and p.idprovincia=?2")
	//public abstract Provincia findByiddepartamentoWhithidprovincia(int idDepartamento, int idprovincia);
	//fetchByIdWithClienteWhithItemFacturaWithProducto
	
	@Query("select p from Provincia p join fetch p.departamento d where d.iddepartamento = ?1  order by p.provincia")
	public abstract List<Provincia> findAllByIdDepartamento(int idDepartamento);
	
	
}
