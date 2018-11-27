package com.biblioteca.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Distritos")
@NamedNativeQueries({   
    @NamedNativeQuery(
            name    =   "getDistritosbyIdDepartamento",
            query	=	"select * from distritos " + 
            		"where idDepartamento=? and idProvincia=?",   
            		resultClass=Distrito.class
    )
})
public class Distrito{
	
	
	@Id
	@Column(name="iddistrito")
	private int idDistrito;
	
	@Column(name="distrito")
	private String Distrito;

	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idprovincia")
	@JsonIgnore
	private Provincia provincia;
	
	@Column(name="iddepartamento")
	private int idDepartamento;
	
	

	public Distrito(int idDistrito, String distrito, Provincia provincia, int idDepartamento) {
		super();
		this.idDistrito = idDistrito;
		Distrito = distrito;
		this.provincia = provincia;
		this.idDepartamento = idDepartamento;
	}
	

	public Distrito() {		
	}


	public int getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getDistrito() {
		return Distrito;
	}

	public void setDistrito(String distrito) {
		Distrito = distrito;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}


	@Override
	public String toString() {
		return "Distrito [idDistrito=" + idDistrito + ", Distrito=" + Distrito + ", idDepartamento=" + idDepartamento + "]";
	}
	
	
}
