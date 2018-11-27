package com.biblioteca.model;

public class Autorizaciontesis {

	int Autorizac;
    String TipoTesis;
    String Titulo;
    /*  Autor[] Autores;*/
    String Asesor;

    
    public int getAutorizac() {
		return Autorizac;
	}

	public void setAutorizac(int autorizac) {
		Autorizac = autorizac;
	}

	public String getTipoTesis() {
        return TipoTesis;
    }

    public void setTipoTesis(String TipoTesis) {
        this.TipoTesis = TipoTesis;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /* public Autor[] getAutores() {
        return Autores;
    }

    public void setAutores(Autor[] Autores) {
        this.Autores = Autores;
    }*/
    public String getAsesor() {
        return Asesor;
    }

    public void setAsesor(String Asesor) {
        this.Asesor = Asesor;
    }

	@Override
	public String toString() {
		return "Autorizaciontesis [Autorizac=" + Autorizac + ", TipoTesis=" + TipoTesis + ", Titulo=" + Titulo
				+ ", Asesor=" + Asesor + "]";
	}

	
    
    

}
