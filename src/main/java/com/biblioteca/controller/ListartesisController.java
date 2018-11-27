package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.entity.Documento;
import com.biblioteca.entity.Facultad;
import com.biblioteca.service.DocumentoService;
import com.biblioteca.service.FacultadService;


@Controller
public class ListartesisController {

	private static final Log LOG = LogFactory.getLog(ListartesisController.class);


	@Autowired
	@Qualifier("facultadServiceImpl")
	private FacultadService facultadService;
	

	@Autowired
	@Qualifier("documentoServiceImpl")
	private DocumentoService documentoService;
	
	
	@GetMapping("/listartesis")
	public String showListarTesis(Model model) {
		LOG.info("METHOD: showListarTesis()");
		List<Facultad> facultades = new ArrayList<Facultad>();
		facultades = facultadService.listallFacultades();
		
		model.addAttribute("facultades", facultades);
		return "listar";
		
	}
	//"data": {"idfacultad": idFacultad, "idescuela": idEscuela,"anio": anio,"autor": autor,"titulo":titulo}
	//@GetMapping(value="/listartesis", produces = "application/json")
	@PostMapping(value="/listartesis", produces = "application/json")
	public ResponseEntity<String> ListBuscandoTesis(
			@RequestParam(name = "idfacultad", required = true) String idfacultad,
			@RequestParam(name = "idescuela", required = true) String idescuela,
			@RequestParam(name = "anio", required = true) String anio,
			@RequestParam(name = "autor", required = true) String autor,
			@RequestParam(name = "titulo", required = true) String titulo) {
		
		LOG.info("METHOD: ListBuscandoTesis()" +idfacultad +"-"+ idescuela+"-"+anio+"-"+autor+"-"+titulo);
				
		List<Documento> ListaTesis = documentoService
				.findDocumentoByAutorAndByTituloAndByFacultadAndByprogramaAndByAnio(autor, titulo, idfacultad,idescuela, anio);
	
		LOG.info("METHOD: ListBuscandoTesis()"+ ListaTesis.size());
		
        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectP;
        
        try {
	        for (Documento tesis : ListaTesis) {  

        		varJsonObjectP=new JSONObject();
	        	varJsonObjectP.put("codigo", tesis.getCodigotesis());
	        	varJsonObjectP.put("titulo", tesis.getTitulo());
	        	varJsonObjectP.put("autor", tesis.getAutor());
	        	varJsonObjectP.put("anio", tesis.getFechaaceptacion());        	
				varJsonArrayP.put(varJsonObjectP);
			}
        	
        LOG.info("array json" + varJsonArrayP.length());
		
        varJsonObjectResultado.put("data",varJsonArrayP);
        varJsonObjectResultado.put("result","ok");
        }catch (Exception e) {
			// TODO: handle exception
        	  LOG.info("error catch" + e.getMessage());
		}  
        return new ResponseEntity<>(varJsonObjectResultado.toString(), HttpStatus.OK);
    }
	
	
}
