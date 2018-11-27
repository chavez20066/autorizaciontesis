package com.biblioteca.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.converter.AlumnoConverter;
import com.biblioteca.entity.Facultad;
import com.biblioteca.entity.Grado;
import com.biblioteca.entity.MaestroBiblio;
import com.biblioteca.entity.ModalidadesSustentacion;
import com.biblioteca.entity.Programa;
import com.biblioteca.model.AlumnoModel;
import com.biblioteca.repository.FacultadRepository;
import com.biblioteca.repository.GradoRepository;
import com.biblioteca.repository.ModalidadesSustRepository;
import com.biblioteca.repository.ProgramaRepository;
import com.biblioteca.service.FacultadService;

@Service("facultadServiceImpl")
public class FacultadServiceImpl implements FacultadService {
	
	private static final Log LOG = LogFactory.getLog(DepartamentoServiceImpl.class);

	@Autowired
	@Qualifier("facultadRepository")
	private FacultadRepository facultadRepository;
	
	@Autowired
	@Qualifier("programaRepository")
	private ProgramaRepository programaRepository;
	
	
	@Autowired
	@Qualifier("gradoRepository")
	private GradoRepository gradoRepository;
	
	@Autowired
	@Qualifier("modalidadesSustRepository")
	private ModalidadesSustRepository modalidadesSustRepository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	@Qualifier("alumnoConverter")
	private AlumnoConverter alumnoConverter;

		
	@Override
	@Transactional
	public List<Facultad> listallFacultades() {
					
		LOG.info("METHOD: listallFacultades()");
		return facultadRepository.findAllOrderByFacultad();
	}

	@Override
	@Transactional
	public Facultad findFacultadById(String idFacultad) {
		LOG.info("METHOD: findFacultadById(): "+ idFacultad);
		return facultadRepository.findByIdFacultad(idFacultad);
	}

	@Override
	@Transactional
	public Programa findProgramaById(String idPrograma) {
		LOG.info("METHOD: findProgramaById()");
		return programaRepository.findByidPrograma(idPrograma);
	}

	@Override
	@Transactional
	public List<Grado> listAllGrados() {		
		List<Grado> grados = manager.createNamedQuery("getGrados", Grado.class)
									.getResultList();
		return grados;
	}
	

	@Override
	@Transactional
	public AlumnoModel findAlumnoById(String Codigo) {
		// TODO Auto-generated method stub

		/*MaestroBiblio mb =new MaestroBiblio();
		mb= manager.createNamedQuery("getAlumno", MaestroBiblio.class)
									.setParameter(0, Codigo).getSingleResult();*/
		AlumnoModel alumno=null;
		MaestroBiblio mb =null;
		try{
			mb= manager.createNamedQuery("getAlumno", MaestroBiblio.class)
					.setParameter(0, Codigo).getSingleResult();
		}
		catch (NoResultException nre){
		//Ignore this because as per your logic this is ok!
		}

		if(mb == null){
		 //Do your logic..
			return alumno;
		}
		else {
			alumno=alumnoConverter.converterMaestroBiblio2Alumno(mb);
			return alumno;
		}
	
	}

	@Override
	public Grado findGradoById(String idPrograma) {
		// TODO Auto-generated method stub
		return gradoRepository.findByidPrograma(idPrograma);
	}

	@Override
	public ModalidadesSustentacion findModalidadById(int id) {
		// TODO Auto-generated method stub
		return modalidadesSustRepository.findBycModCod(id);
	}

	@Override
	public List<ModalidadesSustentacion> listModalidadesOrderByModalidad() {
		// TODO Auto-generated method stub
		return modalidadesSustRepository.findAllOrderBycModDe2();
	}
	
	
	
	
	
	

}
