package com.biblioteca.model;

public class AlumnoModel {
	
	private String codigo;
	private String apellidos;
	private String nombres;
	private String dni;
	private String idEscuela;
	private String escuela;
	private String direccion;
	private String tel_fij;
	private String celular;
	private String email;
	private String idFacultad;
	public AlumnoModel(String codigo, String apellidos, String nombres, String dni, String idEscuela, String escuela,
			String direccion, String tel_fij, String celular, String email, String idFacultad) {
		super();
		this.codigo = codigo;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.dni = dni;
		this.idEscuela = idEscuela;
		this.escuela = escuela;
		this.direccion = direccion;
		this.tel_fij = tel_fij;
		this.celular = celular;
		this.email = email;
		this.idFacultad = idFacultad;
	}
	public AlumnoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getIdEscuela() {
		return idEscuela;
	}
	public void setIdEscuela(String idEscuela) {
		this.idEscuela = idEscuela;
	}
	public String getEscuela() {
		return escuela;
	}
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTel_fij() {
		return tel_fij;
	}
	public void setTel_fij(String tel_fij) {
		this.tel_fij = tel_fij;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdFacultad() {
		return idFacultad;
	}
	public void setIdFacultad(String idFacultad) {
		this.idFacultad = idFacultad;
	}
	@Override
	public String toString() {
		return "AlumnoModel [codigo=" + codigo + ", apellidos=" + apellidos + ", nombres=" + nombres + ", dni=" + dni
				+ ", idEscuela=" + idEscuela + ", escuela=" + escuela + ", direccion=" + direccion + ", tel_fij="
				+ tel_fij + ", celular=" + celular + ", email=" + email + ", idFacultad=" + idFacultad + "]";
	}
	
}
