package com.biblioteca.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.Documento;

@Repository("documentoRepository")
public interface DocumentoRepository  extends JpaRepository<Documento, Serializable>{

	//Alguna Escuela, algun año
	@Query("SELECT d FROM Documento d WHERE d.autor like %?1% AND d.titulo like %?2% AND d.facultad = ?3 AND d.programa = ?4 AND d.fechaaceptacion = ?5")   
	public abstract List<Documento> findDocumentoByAutorAndByTituloAndByFacultadAndByprogramaAndByAnio(String autor,
			String titulo,String facultad,String programa,String anio);
	// Todas las Escuelas Todos los Años
	@Query("SELECT d FROM Documento d WHERE d.autor like %?1% AND d.titulo like %?2% AND d.facultad = ?3")   
	public abstract List<Documento> findDocumentoByAutorAndByTituloAndByFacultadAndAllProgramaAndAllAnio(String autor,
			String titulo,String facultad);
	
	// Todas las Escuelas, algun año en especifico
	@Query("SELECT d FROM Documento d WHERE d.autor like %?1% AND d.titulo like %?2% AND d.facultad = ?3 AND  d.fechaaceptacion = ?4")   
	public abstract List<Documento> findDocumentoByAutorAndByTituloAndByFacultadAndAllProgramaAndByAnio(String autor,
			String titulo,String facultad,String anio);
	
	// Alguna Escuela, Todos los años
	@Query("SELECT d FROM Documento d WHERE d.autor like %?1% AND d.titulo like %?2% AND d.facultad = ?3 AND d.programa like ?4")   
	public abstract List<Documento> findDocumentoByAutorAndByTituloAndByFacultadAndByProgramaAndAllAnio(String autor,
			String titulo,String facultad,String programa);
	
	
	
}
