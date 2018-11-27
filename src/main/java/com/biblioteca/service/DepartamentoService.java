package com.biblioteca.service;

import java.util.List;

import com.biblioteca.entity.Departamento;
import com.biblioteca.entity.Distrito;
import com.biblioteca.entity.Provincia;
import com.biblioteca.model.DepartamentoModel;

public interface DepartamentoService {
	
	public abstract List<DepartamentoModel> listAllDepartamentos();
	
	public abstract Departamento findDepartamentoById(int iddepartamento);
	
	public abstract Provincia findProvinciaById(int idprovincia);
	
	public abstract Distrito findDistritoById(int idDistrito);
	
	public abstract List<Distrito> findByiddepartamentoWhithidprovincia(int idDepartamento,int idprovincia);
	
	public abstract List<Provincia> findAllByIdDepartamento(int idDepartamento);

}
