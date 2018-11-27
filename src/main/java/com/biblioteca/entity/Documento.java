package com.biblioteca.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="documento")
public class Documento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private int codigo;
	
	private String codigotesis;
	
	private String autor;
	
	private String titulo;
	
	private String disciplina;
	
	private String universidad;
	
	private String facultad;
	
	private String programa;
	
	private String fechaaceptacion;
	
	private String editor;
	
	private String grado;
	
	private String copyright;
	
	private String resumen;
	
	private String palabrasclave;
	
	private String ubicacion;
	
	private String estado;
	
	private String fecha;
	
	private String cod1;
	
	private String cod2;
	
	private String cod3;
	
	private String calificacion;
	
	private String contenido;
	
	private String paginacion;
	
	private String lugar;
	
	private String editorial;
	
	private String añoinvest;
	
	private String añopresentesis;
	
	private String url;
	
	private String idioma;
	
	private String digital;
	
	private String tipomaterial;
	
	private String longitud;
	
	private String tipo;
	
	private String estado_eliminacion;
	
	private String urlpdf;
	
	private String formato;

	public Documento() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCodigotesis() {
		return codigotesis;
	}

	public void setCodigotesis(String codigotesis) {
		this.codigotesis = codigotesis;
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

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getFechaaceptacion() {
		return fechaaceptacion;
	}

	public void setFechaaceptacion(String fechaaceptacion) {
		this.fechaaceptacion = fechaaceptacion;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getPalabrasclave() {
		return palabrasclave;
	}

	public void setPalabrasclave(String palabrasclave) {
		this.palabrasclave = palabrasclave;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCod1() {
		return cod1;
	}

	public void setCod1(String cod1) {
		this.cod1 = cod1;
	}

	public String getCod2() {
		return cod2;
	}

	public void setCod2(String cod2) {
		this.cod2 = cod2;
	}

	public String getCod3() {
		return cod3;
	}

	public void setCod3(String cod3) {
		this.cod3 = cod3;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(String paginacion) {
		this.paginacion = paginacion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getAñoinvest() {
		return añoinvest;
	}

	public void setAñoinvest(String añoinvest) {
		this.añoinvest = añoinvest;
	}

	public String getAñopresentesis() {
		return añopresentesis;
	}

	public void setAñopresentesis(String añopresentesis) {
		this.añopresentesis = añopresentesis;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDigital() {
		return digital;
	}

	public void setDigital(String digital) {
		this.digital = digital;
	}

	public String getTipomaterial() {
		return tipomaterial;
	}

	public void setTipomaterial(String tipomaterial) {
		this.tipomaterial = tipomaterial;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado_eliminacion() {
		return estado_eliminacion;
	}

	public void setEstado_eliminacion(String estado_eliminacion) {
		this.estado_eliminacion = estado_eliminacion;
	}

	public String getUrlpdf() {
		return urlpdf;
	}

	public void setUrlpdf(String urlpdf) {
		this.urlpdf = urlpdf;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Documento [codigo=" + codigo + ", codigotesis=" + codigotesis + ", autor=" + autor + ", titulo="
				+ titulo + ", disciplina=" + disciplina + ", universidad=" + universidad + ", facultad=" + facultad
				+ ", programa=" + programa + ", fechaaceptacion=" + fechaaceptacion + ", editor=" + editor + ", grado="
				+ grado + ", copyright=" + copyright + ", resumen=" + resumen + ", palabrasclave=" + palabrasclave
				+ ", ubicacion=" + ubicacion + ", estado=" + estado + ", fecha=" + fecha + ", cod1=" + cod1 + ", cod2="
				+ cod2 + ", cod3=" + cod3 + ", calificacion=" + calificacion + ", contenido=" + contenido
				+ ", paginacion=" + paginacion + ", lugar=" + lugar + ", editorial=" + editorial + ", añoinvest="
				+ añoinvest + ", añopresentesis=" + añopresentesis + ", url=" + url + ", idioma=" + idioma
				+ ", digital=" + digital + ", tipomaterial=" + tipomaterial + ", longitud=" + longitud + ", tipo="
				+ tipo + ", estado_eliminacion=" + estado_eliminacion + ", urlpdf=" + urlpdf + ", formato=" + formato
				+ "]";
	}

	
	
}
