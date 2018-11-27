package com.biblioteca.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.entity.AutorCybertesisDetalle;
import com.biblioteca.entity.AutorizCybertesis;
import com.biblioteca.repository.AutorAutorizacionRepository;
import com.biblioteca.repository.AutorizacionRepository;
import com.biblioteca.service.AutorizacionService;


@Service("autorizacionServiceImpl")
public class AutorizacionServiceImpl implements AutorizacionService{

	private static final Log LOG = LogFactory.getLog(AutorizacionServiceImpl.class);

	@Autowired
	@Qualifier("autorizacionRepository")
	private AutorizacionRepository autorizacionRepository;
	
	@Autowired
	@Qualifier("autorAutorizacionRepository")
	private AutorAutorizacionRepository autorAutorizacionRepository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public void saveAutorizacion(AutorizCybertesis autorizacion) {
		// TODO Auto-generated method stub
		LOG.info("METHOD: saveAutorizacion()"+ autorizacion.toString());
		List<AutorCybertesisDetalle> autores=autorizacion.getAutores();
		if(autores.size()!=0)
		{
			for (AutorCybertesisDetalle autor : autores) {
				LOG.info("Autor data()"+ autor.toString());				
			}			
		}		
		autorizacionRepository.save(autorizacion);		
	}

	@Override
	@Transactional
	public void saveAutorAutorizacion(AutorCybertesisDetalle autorAutorizacion) {
		// TODO Auto-generated method stub
		LOG.info("METHOD: saveAutorAutorizacion()"+ autorAutorizacion.toString());		
		autorAutorizacionRepository.save(autorAutorizacion);		
		
	}

	@Override
	@Transactional
	public String getIdAutorizacion() {
		LOG.info("METHOD: getIdAutorizacion()");
		
		String result=(String) manager.createNamedStoredProcedureQuery("{call SPObtenerIdAutCyber()}").getSingleResult();
		
		return result;
	}

	@Override
	@Transactional
	public AutorCybertesisDetalle findAutorById(int Id) {
		// TODO Auto-generated method stub
		return autorAutorizacionRepository.findById(Id);
	}

	@Override
	@Transactional
	public List<AutorizCybertesis> findAutorizacionByCodAlumno(String codigo) {	
		
		return autorizacionRepository.findByCodAlumno(codigo);
	}

	@Override
	@Transactional
	public List<AutorCybertesisDetalle> findAutorByEmail(String codigo, String email) {
		// TODO Auto-generated method stub
		LOG.info("METHOD: findAutorByEmail() + PARAMETROS:"+codigo+" correo: "+email);
		return autorAutorizacionRepository.findByCodAlumnoAndCorreo(codigo, email);
	}

	@Override
	@Transactional
	public AutorizCybertesis findAutorizacionById(int Autorizac) {
		// TODO Auto-generated method stub
		return autorizacionRepository.findByAutorizac(Autorizac);
	}

}
