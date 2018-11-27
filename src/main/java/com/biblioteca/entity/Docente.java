package com.biblioteca.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Docentes")

@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getAllDocentes",
            query   =   "SELECT * " +
                        "FROM docentes",
                        resultClass=Docente.class
    ),
    @NamedNativeQuery(
            name    =   "getAllDocentesByName",
            query	=	"select distinct top 20 * from  docentes\r\n" + 
            		"where len(codigoDocente)=4 and codigoDocente is not null and ISNUMERIC(codigoDocente)=1 and "
            		+ "CONCAT(ApellidoPaterno,' ',ApellidoMaterno,' ',Nombres) LIKE ? COLLATE Traditional_Spanish_ci_ai order by ApellidoPaterno",   
                        resultClass=Docente.class
    )
})
public class Docente {

	@Id
	@Column(name = "codigodocente")
	private String codigoDocente;

	@Column(name = "apellidopaterno")
	private String apellidoPaterno;

	@Column(name = "apellidomaterno")
	private String apellidoMaterno;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "fechanacimiento")
	private Date fechaNacimiento;

	@Column(name = "fechaingreso")
	private Date fechaIngreso;

	@Column(name = "codigodepartamento")
	private String codigoDepartamento;

	@Column(name = "categoria")
	private String Categoria;
	
	@Column(name = "regimen")
	private String Regimen;

	@Column(name = "activo")
	private Boolean activo;

	public Docente() {
	}	

	public Docente(String codigoDocente, String apellidoPaterno, String apellidoMaterno, String nombres,
			Date fechaNacimiento, Date fechaIngreso, String codigoDepartamento, String categoria, String regimen,
			Boolean activo) {
		super();
		this.codigoDocente = codigoDocente;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombres = nombres;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.codigoDepartamento = codigoDepartamento;
		Categoria = categoria;
		Regimen = regimen;
		this.activo = activo;
	}

	


	public String getCategoria() {
		return Categoria;
	}



	public void setCategoria(String categoria) {
		Categoria = categoria;
	}



	public String getCodigoDocente() {
		return codigoDocente;
	}

	public void setCodigoDocente(String codigoDocente) {
		this.codigoDocente = codigoDocente;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getRegimen() {
		return Regimen;
	}

	public void setRegimen(String regimen) {
		Regimen = regimen;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
