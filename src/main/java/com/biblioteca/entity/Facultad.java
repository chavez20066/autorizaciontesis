package com.biblioteca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="facultades")
public class Facultad {
	
	
	@Column(name="id")
	private int id;
	
	@Id
	@Column(name="idfacultad")
	private String idFacultad;
	
	@Column(name="facultad")
	private String Facultad;
	
	@Column(name="estado")
	private String Estado;
	
	@OneToMany(/*mappedBy = "departamento",*/ cascade = CascadeType.ALL)
	@JoinColumn(name="idfacultad")
	//@OneToMany(mappedBy = "idFacultad", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name="idFacultad")
	private List<Programa> programas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idfacultad")
	private List<AutorCybertesisDetalle> autores;
	
	
	public Facultad(int id, String idFacultad, String facultad, String estado) {
		super();
		this.id = id;
		this.idFacultad = idFacultad;
		Facultad = facultad;
		Estado = estado;
	}
	
	
	public Facultad() {
		programas = new ArrayList<Programa>();
	}
	
	

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(String idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getFacultad() {
		return Facultad;
	}

	public void setFacultad(String facultad) {
		Facultad = facultad;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Facultad [id=" + id + ", idFacultad=" + idFacultad + ", Facultad=" + Facultad + ", Estado=" + Estado
				+ "]";
	}

	
}
