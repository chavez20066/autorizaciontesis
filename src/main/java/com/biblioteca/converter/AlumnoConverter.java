package com.biblioteca.converter;

import org.springframework.stereotype.Component;

import com.biblioteca.entity.MaestroBiblio;
import com.biblioteca.model.AlumnoModel;

@Component("alumnoConverter")
public class AlumnoConverter {

	public AlumnoModel converterMaestroBiblio2Alumno(MaestroBiblio mb){
		AlumnoModel alumnoModel=new AlumnoModel();
		alumnoModel.setCodigo(mb.getCodigo());
		alumnoModel.setDni(mb.getDni());
		alumnoModel.setIdEscuela(mb.getCod_Prog());
		alumnoModel.setEscuela(mb.getPrograma());
		alumnoModel.setDireccion(mb.getDireccion());
		alumnoModel.setTel_fij(mb.getTel_Fij());
		alumnoModel.setCelular(mb.getCelular());
		alumnoModel.setEmail(mb.getEmail());
		alumnoModel.setIdFacultad(mb.getIdFacultad());
		
		 String[] datosCompletos = mb.getDatos().split("/");
         String ApellidoPaterno, ApellidoMaterno, nombres, Completos;
         if (datosCompletos.length > 0) {
             if (datosCompletos.length == 1) {
                 Completos = datosCompletos[0];
                 alumnoModel.setApellidos(Completos);
                 alumnoModel.setNombres("");
             }
             if (datosCompletos.length == 2) {
                 ApellidoPaterno = datosCompletos[0];
                 nombres = datosCompletos[1];
                 alumnoModel.setApellidos(ApellidoPaterno);
                 alumnoModel.setNombres(nombres);
                 
             }
             if (datosCompletos.length == 3) {
                 ApellidoPaterno = datosCompletos[0];
                 ApellidoMaterno = datosCompletos[1];
                 nombres = datosCompletos[2];               
                 alumnoModel.setApellidos(ApellidoPaterno + " " + ApellidoMaterno);
                 alumnoModel.setNombres(nombres);
             }
         }
		
		return alumnoModel;
		
	}
	
}
