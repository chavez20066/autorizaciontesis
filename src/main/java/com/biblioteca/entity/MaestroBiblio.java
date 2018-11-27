package com.biblioteca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="maestrobiblio")
@NamedNativeQueries({   
    @NamedNativeQuery(
            name    =   "getAlumno",
            query	=	"select codigo,dni,datos,cod_prog,programa,direccion,tel_fij,celular,email,p.idFacultad from maestroBiblio mb " + 
            		"inner join programas p " + 
            		"on mb.cod_prog=p.idPrograma "+
            		"where mb.codigo=? ",   
            		resultClass=MaestroBiblio.class
    )
})
public class MaestroBiblio {
	
	@Id
	@Column(name="codigo")
	private String Codigo;
	
	@Column(name="dni")
	private String Dni;
	
	@Column(name="datos")
	private String Datos;
	
	@Column(name="cod_prog")
	private String Cod_Prog;
	
	@Column(name="programa")
	private String Programa;
	
	@Column(name="direccion")
	private String Direccion;
	
	@Column(name="tel_fij")
	private String Tel_Fij;
	
	@Column(name="celular")
	private String Celular;
	
	@Column(name="email")
	private String Email;
	
	@Column(name="idfacultad")
	private String IdFacultad;

	public MaestroBiblio(String codigo, String dni, String datos, String cod_Prog, String programa, String direccion,
			String tel_Fij, String celular, String email, String idFacultad) {
		super();
		Codigo = codigo;
		Dni = dni;
		Datos = datos;
		Cod_Prog = cod_Prog;
		Programa = programa;
		Direccion = direccion;
		Tel_Fij = tel_Fij;
		Celular = celular;
		Email = email;
		IdFacultad = idFacultad;
	}

	public MaestroBiblio() {
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getDatos() {
		return Datos;
	}

	public void setDatos(String datos) {
		Datos = datos;
	}

	public String getCod_Prog() {
		return Cod_Prog;
	}

	public void setCod_Prog(String cod_Prog) {
		Cod_Prog = cod_Prog;
	}

	public String getPrograma() {
		return Programa;
	}

	public void setPrograma(String programa) {
		Programa = programa;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTel_Fij() {
		return Tel_Fij;
	}

	public void setTel_Fij(String tel_Fij) {
		Tel_Fij = tel_Fij;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getIdFacultad() {
		return IdFacultad;
	}

	public void setIdFacultad(String idFacultad) {
		IdFacultad = idFacultad;
	}

	@Override
	public String toString() {
		return "MaestroBiblio [Codigo=" + Codigo + ", Dni=" + Dni + ", Datos=" + Datos + ", Cod_Prog=" + Cod_Prog
				+ ", Programa=" + Programa + ", Direccion=" + Direccion + ", Tel_Fij=" + Tel_Fij + ", Celular="
				+ Celular + ", Email=" + Email + ", IdFacultad=" + IdFacultad + "]";
	}
	
	

	
}
