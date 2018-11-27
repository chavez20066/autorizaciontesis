package com.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.model.DepartamentoModel;
import com.biblioteca.service.DepartamentoService;


@Controller
@RequestMapping("/autorizacionestesis")
public class DepartamentoController {

	private static final Log LOG = LogFactory.getLog(DepartamentoController.class);
	
	@Autowired
	@Qualifier("departamentoServiceImpl")
	private DepartamentoService departamentoService;
	
	@GetMapping("/departamentos")
	public ResponseEntity<List<DepartamentoModel>> DepartamentosList(){		
		List<DepartamentoModel> departamentos = new ArrayList<DepartamentoModel>();
		departamentos=departamentoService.listAllDepartamentos();	
		
		return new ResponseEntity<List<DepartamentoModel>> (departamentos,HttpStatus.OK);
	}
	
	@GetMapping("/provincias2")
	public  ResponseEntity<List<DepartamentoModel>> ProvinciasList(@RequestParam(name="idDepartamento", required=true) int id){		
		List<DepartamentoModel> departamentos = new ArrayList<DepartamentoModel>();
		departamentos=departamentoService.listAllDepartamentos();	 
		
		return new ResponseEntity<List<DepartamentoModel>> (departamentos,HttpStatus.OK);
	}
	
	
	
}
