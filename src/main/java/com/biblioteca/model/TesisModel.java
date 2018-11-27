package com.biblioteca.model;

public class TesisModel {

	private String codigo;
	private String autor;
	private String titulo;
	private String fecha;
	
	public TesisModel() {
		
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "TesisModel [codigo=" + codigo + ", autor=" + autor + ", titulo=" + titulo + ", fecha=" + fecha + "]";
	}
	
	
	
	
}
