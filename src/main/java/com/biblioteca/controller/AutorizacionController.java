package com.biblioteca.controller;

import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblioteca.email.Email;
import com.biblioteca.entity.AutorCybertesisDetalle;
import com.biblioteca.entity.AutorizCybertesis;
import com.biblioteca.entity.Departamento;
import com.biblioteca.entity.Distrito;
import com.biblioteca.entity.Facultad;
import com.biblioteca.entity.Grado;
import com.biblioteca.entity.ModalidadesSustentacion;
import com.biblioteca.entity.Programa;
import com.biblioteca.entity.Provincia;
import com.biblioteca.model.AlumnoModel;
import com.biblioteca.model.Autor;
import com.biblioteca.model.Autorizaciontesis;
import com.biblioteca.model.DepartamentoModel;
import com.biblioteca.service.AutorizacionService;
import com.biblioteca.service.DepartamentoService;
import com.biblioteca.service.DocenteService;
import com.biblioteca.service.FacultadService;
import com.google.gson.Gson;
/*
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRSaver;*/

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;

@Controller
public class AutorizacionController {

	private static final Log LOG = LogFactory.getLog(AutorizacionController.class);

	@Autowired
	@Qualifier("departamentoServiceImpl")
	private DepartamentoService departamentoService;

	@Autowired
	@Qualifier("docenteServiceImpl")
	private DocenteService docenteService;

	@Autowired
	@Qualifier("facultadServiceImpl")
	private FacultadService facultadService;

	@Autowired
	@Qualifier("autorizacionServiceImpl")
	private AutorizacionService autorizacionService;

	@Autowired
	DataSource datasource;
	
	@Autowired
	@Qualifier("email")
	private Email email;
	
	@GetMapping("/mantenimiento")
	public String showMantenimiento(Model model) {
		return "mantenimiento";
	}
	@GetMapping(value= {"/","","/autorizaciontesis/","/autorizaciontesis"})
	public String showAutorizacion(Model model) {
		   
		LOG.info("METHOD: showAutorizacion()");
		AutorizCybertesis autorizacion=new AutorizCybertesis();					
		
		//autorizacion.setTipoInvest("1");
		List<DepartamentoModel> departamentos = new ArrayList<DepartamentoModel>();
		List<Facultad> facultades = new ArrayList<Facultad>();
		List<Grado> grados = new ArrayList<Grado>();
		ModalidadesSustentacion modalidad=facultadService.findModalidadById(1);
		
		

		departamentos = departamentoService.listAllDepartamentos();
		facultades = facultadService.listallFacultades();
		grados = facultadService.listAllGrados();
		autorizacion.setModalidad(modalidad);

		LOG.info("Returning to showAutorizacion() view");
		model.addAttribute("autorizacion", autorizacion);
		model.addAttribute("departamentos", departamentos);
		model.addAttribute("facultades", facultades);
		model.addAttribute("grados", grados);
		//model.addAttribute("modalidades", modalidades);
		model.addAttribute("TipoInvest", 1);

		return "index";
	}
		
	@PostMapping(value= {"/autorizaciontesis/ver","/autorizaciontesis/autorizaciontesis/ver"})
	public String ver(@RequestParam(name = "codigoForm", required = true) String codigo,
			@RequestParam(name = "emailForm", required = true) String email,
			Model model) {
		
		LOG.info("METHOD: ver(): PARAMS=>"+ codigo+"=>"+email);
		
		AutorCybertesisDetalle autor=null;
		List<AutorCybertesisDetalle> autores=null;
		autores=autorizacionService.findAutorByEmail(codigo, email);
	
		
		if(autores!=null && autores.size()>0) {
			autor=autores.get(0);
			List<AutorizCybertesis> autorizaciones=null;
			autorizaciones=autorizacionService.findAutorizacionByCodAlumno(codigo);
			
			LOG.info("Returning to ver() view"+autorizaciones.toString());
			AutorizCybertesis autorizacion=autorizacionService.findAutorizacionById(autorizaciones.get(0).getAutorizac());
			
			LOG.info("Returning to ver() view"+autorizacion.toString());
			LOG.info("Returning to ver() view"+autorizacion.getAutores().get(0).toString());
			LOG.info("Returning to ver() view"+autorizacion.getAutores().size());
			LOG.info("Returning to ver() view "+autorizacion.getAutores().get(0).getPrograma().toString());
			
			List<DepartamentoModel> departamentos = new ArrayList<DepartamentoModel>();
			List<Facultad> facultades = new ArrayList<Facultad>();
			List<Grado> grados = new ArrayList<Grado>();

			departamentos = departamentoService.listAllDepartamentos();
			facultades = facultadService.listallFacultades();
			grados = facultadService.listAllGrados();
			
			LOG.info("Returning to showAutorizacion() view");
			model.addAttribute("autorizacion", autorizacion);
			model.addAttribute("departamentos", departamentos);
			model.addAttribute("facultades", facultades);
			model.addAttribute("grados", grados);

			
		}
		else {
			LOG.info("ver() Autor no exites");
			LOG.info("Returning to showAutorizacion() view");
			return "redirect:/autorizaciontesis/";
		}
				
		return "index";
	}

	@GetMapping("/autorizaciontesis/autocomplete")
	public String showAutocomplete() {
		LOG.info("METHOD: showAutocomplete()");
		LOG.info("Returning to showAutocomplete() view");
		return "autocomplete";
	}

	@RequestMapping(value = "/suggestion", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Map<String, String>> autocompleteSuggestions(
			@RequestParam(name = "request", required = false) String searchstr) {
		LOG.info("METHOD: autocompleteSuggestions()");
		return new ResponseEntity<Map<String, String>>(docenteService.listDocentes(searchstr), HttpStatus.OK);

	}

	@PostMapping("/autorizaciontesis/listDocentes")
	public ResponseEntity<Map<String, String>> listDocentes() {
		LOG.info("METHOD: listDocentes()");
		return new ResponseEntity<Map<String, String>>(docenteService.lisAllDocentes(), HttpStatus.OK);
	}

	// ANTES @RequestMapping(value = "/provincias", method = RequestMethod.GET,
	// headers = "Accept=application/json")
	@GetMapping("/autorizaciontesis/provincias")
	public ResponseEntity<List<Provincia>> ProvinciasList(
			@RequestParam(name = "iddepartamento", required = true) int idDepartamento) {
		LOG.info("METHOD: ProvinciasList() PARAMETROS:" + idDepartamento);

		Departamento departamento = departamentoService.findDepartamentoById(idDepartamento);
		// List<Provincia>
		// provincias=departamentoService.findAllByIdDepartamento(idDepartamento);

		LOG.info("Returning  ProvinciasList() PARAMETROS:" + departamento.getProvincias().size());
		return new ResponseEntity<List<Provincia>>(departamento.getProvincias(), HttpStatus.OK);
	}

	@GetMapping("/autorizaciontesis/distritos")
	public ResponseEntity<List<Distrito>> DistritosList(
			@RequestParam(name = "iddepartamento", required = true) int idDepartamento,
			@RequestParam(name = "idprovincia", required = true) int idProvincia) {
		LOG.info("METHOD: DistritosList() PARAMETROS:" + idDepartamento + "," + idProvincia);

		List<Distrito> distritos = departamentoService.findByiddepartamentoWhithidprovincia(idDepartamento,
				idProvincia);
		LOG.info("Returning  DistritosList() " + distritos.size());
		return new ResponseEntity<List<Distrito>>(distritos, HttpStatus.OK);
	}

	@GetMapping("/autorizaciontesis/grados")
	public ResponseEntity<List<Grado>> gradosList() {
		LOG.info("METHOD: gradosList() ");
		List<Grado> grados = facultadService.listAllGrados();
		LOG.info("Returning  gradosList() " + grados.size());
		return new ResponseEntity<List<Grado>>(grados, HttpStatus.OK);
	}

	@PostMapping("/autorizaciontesis/alumno")
	public ResponseEntity<AlumnoModel> getAlumno(
			@RequestParam(name = "codigoAlumno", required = true) String codigoAlumno) {
		LOG.info("METHOD: getAlumno() ");

		List<AutorizCybertesis> autorizaciones=null;
		autorizaciones=autorizacionService.findAutorizacionByCodAlumno(codigoAlumno);
		
		if(autorizaciones.size()!=0) {
			//return "redirect:/ver/" + factura.getCliente().getId();
			LOG.info("METHOD: getAlumno() "+ autorizaciones.get(0));
			//return "redirect:/ver/" + autorizaciones.get(0);
			return new ResponseEntity(HttpStatus.FOUND);
		}	
		
		AlumnoModel alumno = facultadService.findAlumnoById(codigoAlumno);
		if (alumno == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		LOG.info("Returning  getAlumno() " + alumno.toString());
		return new ResponseEntity<AlumnoModel>(alumno, HttpStatus.OK);
	}

	@GetMapping("/autorizaciontesis/escuelas")
	public ResponseEntity<List<Programa>> EscuelasList(
			@RequestParam(name = "idfacultad", required = true) String idFacultad) {
		LOG.info("METHOD: EscuelasList() PARAMETROS:" + idFacultad);
		Facultad facultad = facultadService.findFacultadById(idFacultad);
		if (facultad == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		LOG.info("Returning  EscuelasList() PARAMETROS:" + facultad.getProgramas().size());
		return new ResponseEntity<List<Programa>>(facultad.getProgramas(), HttpStatus.OK);
	}

	@PostMapping("/autorizaciontesis/GuardarAutorizacion")
	public ResponseEntity<String> GuardarAutorización(
			@RequestParam(name = "datosAutorizacion", required = true) String datosAutorizacion,
			@RequestParam(name = "Autores", required = true) String Autores,HttpServletRequest request) throws JSONException {

		
		final Gson gson = new Gson();// ISO-8859-1

		final Autorizaciontesis myAutorizacion = gson.fromJson(datosAutorizacion, Autorizaciontesis.class);
		final Autor[] misAutores = gson.fromJson(Autores, Autor[].class);

		LOG.info("METHOD: GuardarAutorización() Autorizacion:" + myAutorizacion.toString());
		LOG.info("METHOD: GuardarAutorización() Autor:" + misAutores[0].toString());

		AutorizCybertesis autorizacion = new AutorizCybertesis();
		List<AutorCybertesisDetalle> ListAutores = null;

		Calendar c1 = Calendar.getInstance();

		/* obtener asesor */
		String[] datosAsesor = myAutorizacion.getAsesor().split("\\|");
		String asesor = "", codigoAsesor = "";
		if (datosAsesor.length > 0) {
			if (datosAsesor.length == 1) {
				asesor = datosAsesor[0];
				codigoAsesor = "0001";
			}
			if (datosAsesor.length == 2) {
				asesor = datosAsesor[0];
				codigoAsesor = datosAsesor[1];
			}
		}

		autorizacion.setAutorizac(myAutorizacion.getAutorizac());
		autorizacion.setAnyo("" + c1.get(Calendar.YEAR));
		autorizacion.setCod1("00");
		autorizacion.setCod2("0000");
		autorizacion.setCod3("NN");
		autorizacion.setTitulo(myAutorizacion.getTitulo().toUpperCase());
		autorizacion.setIdGrado(misAutores[0].getIdTitulo());
		//autorizacion.setGrado("null");
		autorizacion.setPublicar("S");
		autorizacion.setFecha(c1.getTime());
		autorizacion.setCusuario("EXTERNO");
		autorizacion.setModalidad((facultadService.findModalidadById(Integer.parseInt(myAutorizacion.getTipoTesis()))));
		autorizacion.setFechaRegistro(c1.getTime());
		autorizacion.setAsesor(asesor);
		autorizacion.setCodAsesor(codigoAsesor);

		autorizacionService.saveAutorizacion(autorizacion);
		autorizacion.setAutorizCompl(String.format("%05d", autorizacion.getAutorizac()) + "-" + c1.get(Calendar.YEAR));
		// autorizacionService.saveAutorizacion(autorizacion);

		LOG.info("METHOD: GuardarAutorización() ID:" + autorizacion.getAutorizac());

		AutorCybertesisDetalle autorAutorizacion;
		for (Autor autor : misAutores) {
			
			
			autorAutorizacion = new AutorCybertesisDetalle();
			autorAutorizacion.setAutorizCompl(autorizacion.getAutorizCompl());
			autorAutorizacion.setCodAlumno(autor.getCodigo());
			autorAutorizacion.setApellidos(autor.getApellidos());
			autorAutorizacion.setNombres(autor.getNombres());
			autorAutorizacion.setDni(autor.getDni());
			autorAutorizacion.setFacultad(facultadService.findFacultadById(autor.getIdFacultad()));
			autorAutorizacion.setPrograma(facultadService.findProgramaById(autor.getIdEscuela()));
			autorAutorizacion.setTelefono(autor.getTelefono());
			autorAutorizacion.setCelular(autor.getCelular());
			autorAutorizacion.setCorreo(autor.getEmail());
			autorAutorizacion.setIdDepartamento(Integer.parseInt(autor.getIdDepartamento()));
			autorAutorizacion.setIdProvincia(Integer.parseInt(autor.getIdProvincia()));
			autorAutorizacion.setIdDistrito(Integer.parseInt(autor.getIdDistrito()));
			autorAutorizacion.setDomicilio(autor.getDireccion().toUpperCase());
			autorAutorizacion.setGrado(facultadService.findGradoById(autor.getIdTitulo()));  
			autorAutorizacion.setAutorizacion(autorizacion);
			autorizacion.addAutor(autorAutorizacion);
			// autorizacionService.saveAutorAutorizacion(autorAutorizacion);

			// ListAutores.add(autorAutorizacion);
		}
		autorizacionService.saveAutorizacion(autorizacion);
		//mav.addObject("idAutorizacion", autorizacion.getAutorizac());
		//request.getSession().setAttribute("idAutorizacion",autorizacion.getAutorizac());
		request.getSession().setAttribute("autorizacion",autorizacion);	
		
		
		
		JSONObject varJsonObjectResultado = new JSONObject();			  
		varJsonObjectResultado.put("RESULT","OK");
		varJsonObjectResultado.put("idAutorizacion", autorizacion.getAutorizac());
		
		return new ResponseEntity<>(varJsonObjectResultado.toString(), HttpStatus.OK);
	}
		
	@GetMapping("/verPDF")
	public String verPDF(@RequestParam(name = "idAutorizacion", required = true) int idAutorizacion,
			HttpServletRequest request, HttpServletResponse response,
			Authentication authentication,Map<String,Object> model){

		LOG.info("METHOD: VERPDF()==>INICIO<==");
		
		//AutorizCybertesis autorizacion=autorizacionService.findAutorizacionById(8298);		
		
		AutorizCybertesis autorizacion = autorizacionService.findAutorizacionById(idAutorizacion);	
		
		if(autorizacion==null) return null;		
		LOG.info("METHOD: VERPDF()=>"+autorizacion.toString());		
		model.put("Autorizacion", autorizacion);		
				
		return "reportePDF";
		
	}
	
}
