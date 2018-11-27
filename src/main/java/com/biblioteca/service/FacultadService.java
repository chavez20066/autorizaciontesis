package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Facultad;
import com.biblioteca.entity.Grado;
import com.biblioteca.entity.ModalidadesSustentacion;
import com.biblioteca.entity.Programa;
import com.biblioteca.model.AlumnoModel;

public interface FacultadService {
	
	public abstract List<Facultad> listallFacultades();
	
	public abstract Facultad findFacultadById(String idFacultad);
	
	public abstract Programa findProgramaById(String idPrograma);
	
	public abstract List<Grado> listAllGrados();
	
	public abstract AlumnoModel findAlumnoById(String Codigo);
	
	public abstract Grado findGradoById(String idPrograma);
	
	public abstract ModalidadesSustentacion findModalidadById(int id);
	
	public abstract List<ModalidadesSustentacion> listModalidadesOrderByModalidad();
	
}
