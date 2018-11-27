package com.biblioteca.converter;

import org.springframework.stereotype.Component;

import com.biblioteca.entity.Departamento;
import com.biblioteca.model.DepartamentoModel;

@Component("departamentoConverter")
public class DepartamentoConverter {

	public Departamento converterDepartamentoModel2Departamento(DepartamentoModel departamentoModel) {
		
		Departamento departamento=new Departamento();
		departamento.setIdDepartamento(departamentoModel.getIdDepartamento());
		departamento.setDepartamento(departamentoModel.getDepartamento());	
		
		return departamento;
		
	}
	public DepartamentoModel convertDepartamento2DepartamentoModel(Departamento departamento) {
		DepartamentoModel dptoModel=new DepartamentoModel();
		
		dptoModel.setIdDepartamento(departamento.getIdDepartamento());
		dptoModel.setDepartamento(departamento.getDepartamento());
		
		return dptoModel;		
	}
	
}
