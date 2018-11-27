package com.biblioteca.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.controller.ListartesisController;
import com.biblioteca.entity.Documento;
import com.biblioteca.repository.DocumentoRepository;
import com.biblioteca.service.DocumentoService;

@Service("documentoServiceImpl")
public class DocumentoServiceImpl implements DocumentoService{

	private static final Log LOG = LogFactory.getLog(DocumentoServiceImpl.class);

	@Autowired
	@Qualifier("documentoRepository")
	private DocumentoRepository documentoRepository;
	
	@Override
	@Transactional
	public List<Documento> findDocumentoByAutorAndByTituloAndByFacultadAndByprogramaAndByAnio(String autor,
			String titulo, String facultad, String programa, String anio) {
		LOG.info("METHOD: findDocumentoByAutorAndByTituloAndByFacultadAndByprogramaAndByAnio()");
		
		List<Documento> listaTesis;
		if(autor=="") {
			autor="%";
		}
		if(titulo=="") {
			titulo="%";
		}
				
		if(programa.equals("1") && anio.equals("1")) {
			LOG.info("TODOS LOS PROGRAMAS TODOS LOS AÑOS");
			
			return listaTesis=documentoRepository
					.findDocumentoByAutorAndByTituloAndByFacultadAndAllProgramaAndAllAnio(autor, titulo, facultad);
			
			
		}
		if(programa.equals("1")) {		
			LOG.info("TODOS LOS PROGRAMAS");
			return listaTesis=documentoRepository
					.findDocumentoByAutorAndByTituloAndByFacultadAndAllProgramaAndByAnio(autor, titulo, facultad,anio);
		}
		if(anio.equals("1")) {
			LOG.info("TODOS LOS AÑOS");
			return listaTesis=documentoRepository
					.findDocumentoByAutorAndByTituloAndByFacultadAndByProgramaAndAllAnio(autor, titulo, facultad, programa);			
		}
		return listaTesis=documentoRepository
				.findDocumentoByAutorAndByTituloAndByFacultadAndByprogramaAndByAnio(autor, titulo, facultad, programa, anio);		
	}
	

}
