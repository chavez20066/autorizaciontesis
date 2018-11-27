package com.biblioteca.entity;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "departamentos")
public class Departamento {

	
	@Id
	@Column(name = "iddepartamento")
	private int iddepartamento;

	@Column(name = "Departamento")
	private String departamento;

	/*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "iddepartamento")
	private List<Provincia> provincias;*/
	
	@OneToMany(/*mappedBy = "departamento",*/ cascade = CascadeType.ALL)
	@JoinColumn(name="iddepartamento")
	private List<Provincia> provincias = new ArrayList<>();
		

	public Departamento(int iddepartamento, String departamento) {
		super();
		this.iddepartamento = iddepartamento;
		this.departamento = departamento;
	}

	public Departamento() {
	}

	public int getIdDepartamento() {
		return iddepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.iddepartamento = idDepartamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

		
	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	@Override
	public String toString() {
		return "Departamentos [id_departamento=" + iddepartamento + ", departamento=" + departamento + "]";
	}
}
