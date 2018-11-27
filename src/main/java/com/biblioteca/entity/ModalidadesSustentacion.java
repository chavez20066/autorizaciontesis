package com.biblioteca.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Modalidades_Sustentacion")
public class ModalidadesSustentacion {
	
	@Id
	@Column(name="cmodcod")
	private int cModCod;
	
	@Column(name="cmodde2")
	private String cModDe2;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cmodcod")
	private List<AutorizCybertesis> autorizacion;

	public ModalidadesSustentacion() {
		
	}

	
	public List<AutorizCybertesis> getAutorizacion() {
		return autorizacion;
	}


	public void setAutorizacion(List<AutorizCybertesis> autorizacion) {
		this.autorizacion = autorizacion;
	}


	public int getcModCod() {
		return cModCod;
	}

	public void setcModCod(int cModCod) {
		this.cModCod = cModCod;
	}

	public String getcModDe2() {
		return cModDe2;
	}

	public void setcModDe2(String cModDe2) {
		this.cModDe2 = cModDe2;
	}

	@Override
	public String toString() {
		return "ModalidadesSustentacion [cModCod=" + cModCod + ", cModDe2=" + cModDe2 + "]";
	}
	
	

	
}
