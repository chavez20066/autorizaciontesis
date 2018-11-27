package com.biblioteca.model;


public class DepartamentoModel {
	
	private int idDepartamento;
	private String departamento;
		 	
		
	public DepartamentoModel(int idDepartamento, String departamento) {
		super();
		this.idDepartamento = idDepartamento;
		this.departamento = departamento;
	}
	
	public DepartamentoModel() {		
	}
		
	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int i) {
		this.idDepartamento = i;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "DepartamentoModel [idDepartamento=" + idDepartamento + ", departamento=" + departamento + "]";
	}

	
	
	
	

}
