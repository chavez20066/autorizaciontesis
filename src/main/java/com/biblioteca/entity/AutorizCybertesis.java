package com.biblioteca.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="autoriz_cybertesis")
public class AutorizCybertesis {
	
	
	@Column(name="autorizcompl")
	private String AutorizCompl;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="autorizac")
	private int Autorizac;
	
	@Column(name="anyo")
	private String Anyo;
	
	@Column(name="cod1")
	private String Cod1;
	
	@Column(name="cod2")
	private String Cod2;
	
	@Column(name="cod3")
	private String Cod3;
	
	@Column(name="titulo")
	private String Titulo;
	
	@Column(name="idgrado")
	private String IdGrado;
	
	/*@Column(name="grado")
	private String Grado;*/
	
	@Column(name="publicar")
	private String Publicar;
	
	@Column(name="fecha")
	private Date Fecha;
	
	@Column(name="cusuario")
	private String cusuario;
	
	/*@Column(name="tipoinvest")
	private String TipoInvest;*/
	
	@Column(name="fecharegistro")
	private Date FechaRegistro;
	
	@Column(name="asesor")
	private String Asesor;
	
	@Column(name="codasesor")
	private String CodAsesor;

	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "autorizac")
	private List<AutorCybertesisDetalle> autores;
	
	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoinvest")	
	private ModalidadesSustentacion modalidad;
	
	

	public AutorizCybertesis(String autorizCompl, int autorizac, String anyo, String cod1, String cod2,
			String cod3, String titulo, String idGrado, String grado, String publicar, Date fecha, String cusuario,
			String tipoInvest, Date fechaRegistro, String asesor, String codAsesor,
			List<AutorCybertesisDetalle> autores) {
		super();		
		AutorizCompl = autorizCompl;
		Autorizac = autorizac;
		Anyo = anyo;
		Cod1 = cod1;
		Cod2 = cod2;
		Cod3 = cod3;
		Titulo = titulo;
		IdGrado = idGrado;
		Publicar = publicar;
		Fecha = fecha;
		this.cusuario = cusuario;		
		FechaRegistro = fechaRegistro;
		Asesor = asesor;
		CodAsesor = codAsesor;
		/*this.autores = autores;*/
	}

	public AutorizCybertesis() {
		autores=new ArrayList<>();
	}
	
	



	public ModalidadesSustentacion getModalidad() {
		return modalidad;
	}

	public void setModalidad(ModalidadesSustentacion modalidad) {
		this.modalidad = modalidad;
	}

	public String getAutorizCompl() {
		return AutorizCompl;
	}

	public void setAutorizCompl(String autorizCompl) {
		AutorizCompl = autorizCompl;
	}

	public int getAutorizac() {
		return Autorizac;
	}

	public void setAutorizac(int autorizac) {
		Autorizac = autorizac;
	}

	public String getAnyo() {
		return Anyo;
	}

	public void setAnyo(String anyo) {
		Anyo = anyo;
	}

	public String getCod1() {
		return Cod1;
	}

	public void setCod1(String cod1) {
		Cod1 = cod1;
	}

	public String getCod2() {
		return Cod2;
	}

	public void setCod2(String cod2) {
		Cod2 = cod2;
	}

	public String getCod3() {
		return Cod3;
	}

	public void setCod3(String cod3) {
		Cod3 = cod3;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getIdGrado() {
		return IdGrado;
	}

	public void setIdGrado(String idGrado) {
		IdGrado = idGrado;
	}

	
	public String getPublicar() {
		return Publicar;
	}

	public void setPublicar(String publicar) {
		Publicar = publicar;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getCusuario() {
		return cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	
	public Date getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}

	public String getAsesor() {
		return Asesor;
	}

	public void setAsesor(String asesor) {
		Asesor = asesor;
	}

	public String getCodAsesor() {
		return CodAsesor;
	}

	public void setCodAsesor(String codAsesor) {
		CodAsesor = codAsesor;
	}

	public List<AutorCybertesisDetalle> getAutores() {
		return autores;
	}
	
	public void addAutor(AutorCybertesisDetalle autor) {
		autores.add(autor);
	}

	public void setAutores(List<AutorCybertesisDetalle> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "AutorizCybertesis [AutorizCompl=" + AutorizCompl + ", Autorizac=" + Autorizac + ", Anyo=" + Anyo
				+ ", Cod1=" + Cod1 + ", Cod2=" + Cod2 + ", Cod3=" + Cod3 + ", Titulo=" + Titulo + ", IdGrado=" + IdGrado
				+ ", Publicar=" + Publicar + ", Fecha=" + Fecha + ", cusuario=" + cusuario
				+ ", FechaRegistro=" + FechaRegistro + ", Asesor=" + Asesor
				+ ", CodAsesor=" + CodAsesor + ", autores=" + autores.size() + "]";
	}

	
	
	
	
	
	
}
