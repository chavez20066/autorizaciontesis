package com.biblioteca.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="programascybertesis")
@NamedNativeQueries({   
    @NamedNativeQuery(
            name    =   "getGrados",
            query	=	"select distinct id,idPrograma,ProgProf,IdFacultad,UPPER(grado) as grado,EstadoGrado from dbo.ProgramasCybertesis \r\n" + 
            		"where EstadoGrado='A' and idPrograma!='00' and idPrograma!='xx' and grado is not null and grado!='Sin Grado'  \r\n" + 
            		"order by grado",   
            		resultClass=Grado.class
    )
})
public class Grado implements Serializable{
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="id")
	private int id;
	
	@Id
	@Column(name="idprograma")
	private String idPrograma;
	
	@Column(name="progprof")
	private String progProf;
	
	@Column(name="idfacultad")
	private String idFacultad;
			
	@Column(name="grado")
	private String grado;
	
	@Column(name="estadogrado")
	private String EstadoGrado;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idprograma")
	private List<AutorCybertesisDetalle> autores;

	public Grado(String idPrograma, String grado) {
		super();
		this.idPrograma = idPrograma;
		this.grado = grado;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getProgProf() {
		return progProf;
	}



	public void setProgProf(String progProf) {
		this.progProf = progProf;
	}



	public String getIdFacultad() {
		return idFacultad;
	}



	public void setIdFacultad(String idFacultad) {
		this.idFacultad = idFacultad;
	}



	public String getEstadoGrado() {
		return EstadoGrado;
	}



	public void setEstadoGrado(String estadoGrado) {
		EstadoGrado = estadoGrado;
	}



	public List<AutorCybertesisDetalle> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorCybertesisDetalle> autores) {
		this.autores = autores;
	}

	public Grado() {		
	}

	public String getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(String idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	@Override
	public String toString() {
		return "Grado [idPrograma=" + idPrograma + ", Grado=" + grado + "]";
	}	
	
}
