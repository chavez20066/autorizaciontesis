package com.biblioteca.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Provincias")
@NamedNativeQueries({   
    @NamedNativeQuery(
            name    =   "getProvinciabyIdDepartamento",
            query	=	"select * from Provincias p inner join Departamentos d on d.IdDepartamento=p.IdDepartamento inner join Distritos di on p.IdProvincia=di.IdDistrito " + 
            		"where p.idDepartamento=? and p.idProvincia=?",   
            		resultClass=Provincia.class
    )
})
public class Provincia {
	
	/**
	 * 
	 */
	
	@Id
	@Column(name="idprovincia")
	private int idprovincia;
	
	@Column(name="provincia")
	private String provincia;
	
	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="iddepartamento")
	@JsonIgnore
	private Departamento departamento;
	
	@OneToMany(/*mappedBy = "departamento",*/ cascade = CascadeType.ALL)
	@JoinColumn(name="idprovincia")
	private List<Distrito> distritos = new ArrayList<>();
	
	
	
	public int getIdprovincia() {
		return idprovincia;
	}

	public void setIdprovincia(int idprovincia) {
		this.idprovincia = idprovincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Provincia(int idprovincia, String provincia) {
		super();
		this.idprovincia = idprovincia;
		this.provincia = provincia;
	}

	public Provincia() {
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	@Override
	public String toString() {
		return "Provincia [idprovincia=" + idprovincia + ", provincia=" + provincia + ", departamento=" + departamento
				+ ", distritos=" + distritos + "]";
	}

	
	
	
	
	
	

}
