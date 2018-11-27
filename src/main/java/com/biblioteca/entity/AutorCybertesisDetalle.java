package com.biblioteca.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="autor_cybertesisdetalle")
public class AutorCybertesisDetalle {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;

	@Column(name="autorizcompl")
	private String AutorizCompl;
	
	@Column(name="codalumno")
	private String CodAlumno;
	
	@Column(name="apellidos")
	private String Apellidos;
	
	@Column(name="nombres")
	private String Nombres;
	
	@Column(name="dni")
	private String Dni;
	
	/*@Column(name="idfacultad")
	private String IdFacultad;*/
	
	/*@Column(name="idprograma")
	private String IdPrograma;*/
	
	@Column(name="telefono")
	private String Telefono;
	
	@Column(name="celular")
	private String Celular;
	
	@Column(name="correo")
	private String Correo;
	
	@Column(name="iddepartamento")
	private int IdDepartamento;
	
	@Column(name="idprovincia")
	private int IdProvincia;
	
	@Column(name="iddistrito")
	private int IdDistrito;
	
	@Column(name="domicilio")
	private String Domicilio;
	
	/*@Column(name="autorizac")
	private String Autorizac;*/
	
	/*@Column(name="idgrado")
	private String IdGrado;*/
	
	@ManyToOne(fetch=FetchType.EAGER) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="autorizac")	
	private AutorizCybertesis autorizacion;
	
	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idfacultad")	
	private Facultad facultad;
	
	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idprograma")	
	private Programa programa;
	
	@ManyToOne(fetch=FetchType.LAZY) //	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idgrado")	
	private Grado grado;
		
	public AutorCybertesisDetalle(int id, String autorizCompl, String codAlumno, String apellidos, String nombres,
			String dni, String idFacultad, String idPrograma, String telefono, String celular, String correo,
			int idDepartamento, int idProvincia, int idDistrito, String domicilio, String idGrado,
			AutorizCybertesis autorizacion) {
		super();
		Id = id;
		AutorizCompl = autorizCompl;
		CodAlumno = codAlumno;
		Apellidos = apellidos;
		Nombres = nombres;
		Dni = dni;		
		Telefono = telefono;
		Celular = celular;
		Correo = correo;
		IdDepartamento = idDepartamento;
		IdProvincia = idProvincia;
		IdDistrito = idDistrito;
		Domicilio = domicilio;		
		this.autorizacion = autorizacion;
	}
	

	public AutorCybertesisDetalle() {}		
	public Grado getGrado() {
		return this.grado;
	}
	public void setGrado(Grado grado) {
		this.grado = grado;
	}
	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}


	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getAutorizCompl() {
		return AutorizCompl;
	}

	public void setAutorizCompl(String autorizCompl) {
		AutorizCompl = autorizCompl;
	}

	public String getCodAlumno() {
		return CodAlumno;
	}

	public void setCodAlumno(String codAlumno) {
		CodAlumno = codAlumno;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getNombres() {
		return Nombres;
	}

	public void setNombres(String nombres) {
		Nombres = nombres;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	
	

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public int getIdDepartamento() {
		return IdDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		IdDepartamento = idDepartamento;
	}

	public int getIdProvincia() {
		return IdProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		IdProvincia = idProvincia;
	}

	public int getIdDistrito() {
		return IdDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		IdDistrito = idDistrito;
	}

	public String getDomicilio() {
		return Domicilio;
	}

	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}

	/*public String getAutorizac() {
		return Autorizac;
	}

	public void setAutorizac(String autorizac) {
		Autorizac = autorizac;
	}*/

	
	public AutorizCybertesis getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(AutorizCybertesis autorizacion) {
		this.autorizacion = autorizacion;
	}



	@Override
	public String toString() {
		return "AutorCybertesisDetalle [Id=" + Id + ", AutorizCompl=" + AutorizCompl + ", CodAlumno=" + CodAlumno
				+ ", Apellidos=" + Apellidos + ", Nombres=" + Nombres + ", Dni=" + Dni 
				+ ", Telefono=" + Telefono + ", Celular=" + Celular + ", Correo="
				+ Correo + ", IdDepartamento=" + IdDepartamento + ", IdProvincia=" + IdProvincia + ", IdDistrito="
				+ IdDistrito + ", Domicilio=" + Domicilio +  ", autorizacion=" + autorizacion
				+ "]";
	}

	
	
}
