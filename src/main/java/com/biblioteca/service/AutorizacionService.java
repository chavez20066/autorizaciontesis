package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.AutorCybertesisDetalle;
import com.biblioteca.entity.AutorizCybertesis;


public interface AutorizacionService {
	
	public abstract String getIdAutorizacion();
	
	public abstract void saveAutorizacion(AutorizCybertesis autorizacion);
	
	public abstract void saveAutorAutorizacion(AutorCybertesisDetalle autorAutorizacion);
	
	public abstract AutorCybertesisDetalle findAutorById(int Id);
	
	public abstract List<AutorizCybertesis> findAutorizacionByCodAlumno(String codigo);
	
	public abstract List<AutorCybertesisDetalle> findAutorByEmail(String codigo,String email);
	
	public abstract AutorizCybertesis findAutorizacionById(int id);
	
	

}
