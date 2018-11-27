package com.biblioteca.service;

import java.util.List;


import com.biblioteca.entity.Documento;

public interface DocumentoService {

	public abstract List<Documento> findDocumentoByAutorAndByTituloAndByFacultadAndByprogramaAndByAnio(String autor,
			String titulo,String facultad,String programa,String anio);
				
	
}
