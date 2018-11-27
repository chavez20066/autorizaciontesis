package com.biblioteca.converter;

import org.springframework.stereotype.Component;

import com.biblioteca.entity.Docente;
import com.biblioteca.model.DocenteAutoCompleteModel;

@Component("docenteConverter")
public class DocenteConverter {
	
	public DocenteAutoCompleteModel convertDocente2DocenteModel(Docente docente) {
		
		/*DocenteModel docenteModel=new DocenteModel();
		
		docenteModel.setValue(docente.getApellidoPaterno()+' '+
				docente.getApellidoMaterno()+' '+docente.getNombres()+'|'+docente.getCodigoDocente());
		docenteModel.setData(docente.getCodigoDocente());*/
		DocenteAutoCompleteModel docenteModel=new DocenteAutoCompleteModel();
		
		docenteModel.setDocente(docente.getApellidoPaterno()+' '+
				docente.getApellidoMaterno()+' '+docente.getNombres()+'|'+docente.getCodigoDocente());
		
		
		return docenteModel;
		
	}

}
