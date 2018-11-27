package com.biblioteca.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="programas")
public class Programa {
	
	
	@Column(name="id")
	private int id;
	
	@Id
	@Column(name="idprograma")
	private String idPrograma;
	
	@Column(name="progprof")
	private String progProf;
	
	@Column(name="letras")
	private String Letras;
	
	@Column(name="estado")
	private String Estado;
	
	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idfacultad")
	private Facultad idFacultad;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idprograma")
	private List<AutorCybertesisDetalle> autores;
	

	public Programa(int id, String idPrograma, String progProf, String letras, String estado) {
		super();
		this.id = id;
		this.idPrograma = idPrograma;
		this.progProf = progProf;
		Letras = letras;
		Estado = estado;
	}
	

	public List<AutorCybertesisDetalle> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorCybertesisDetalle> autores) {
		this.autores = autores;
	}

	public Programa() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(String idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getProgProf() {
		return progProf;
	}

	public void setProgProf(String progProf) {
		this.progProf = progProf;
	}

	public String getLetras() {
		return Letras;
	}

	public void setLetras(String letras) {
		Letras = letras;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "programa [id=" + id + ", idPrograma=" + idPrograma + ", progProf=" + progProf + ", idFacultad="
				+ idFacultad + ", Letras=" + Letras + ", Estado=" + Estado + "]";
	}
	
	
	
}
