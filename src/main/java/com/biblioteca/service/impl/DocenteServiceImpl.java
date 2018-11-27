package com.biblioteca.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.entity.Docente;
import com.biblioteca.repository.DocenteRepository;
import com.biblioteca.service.DocenteService;

@Service("docenteServiceImpl")
public class DocenteServiceImpl implements DocenteService {

	private static final Log LOG = LogFactory.getLog(DocenteServiceImpl.class);

	@Autowired
	@Qualifier("docenteRepository")
	private DocenteRepository docenteRepository;
	
	@PersistenceContext
	private EntityManager manager;

	/*
	 * @Override public List<Docente> listDocentes(String searchDocente) { String
	 * likeExpression = "%" + searchDocente + "%";
	 * 
	 * 
	 * // List<Docente>
	 * docentes=docenteRepositoy.findByPlaceContaining(searchDocente); List<Docente>
	 * docentes = manager.createNamedQuery("getAllDocentesByName", Docente.class)
	 * .setParameter(0, likeExpression).getResultList();
	 * 
	 * List<String> docentesModel = new ArrayList<String>();
	 * 
	 * for (Docente docente : docentes) {
	 * docentesModel.add(docenteConverter.convertDocente2DocenteModel(docente).
	 * getDocente()); } LOG.info("METHOD: listDocentes()"); SuggestionWrapper sw =
	 * new SuggestionWrapper(); sw.setSuggestions(docentesModel); return docentes; }
	 */

	@Override
	@Transactional
	public Map<String, String> listDocentes(String searchDocente) {
		String likeExpression = "%" + searchDocente + "%";

		
		Map<String, String> map = new HashMap<String, String>();	    
		
		List<Docente> docentes = manager.createNamedQuery("getAllDocentesByName", Docente.class)
				.setParameter(0, likeExpression).getResultList();

		for (Docente docente : docentes) {		
			map.put(docente.getApellidoPaterno() + ' ' + docente.getApellidoMaterno() + ' '
					+ docente.getNombres() + '|' + docente.getCodigoDocente() , null);
			
		}
		LOG.info("METHOD: listDocentes():"+ map.toString());
		
		return map;
	}

	@Override
	@Transactional
	public Map<String, String> lisAllDocentes() {
		Map<String, String> map = new HashMap<String, String>();
		List<Docente> docentes=docenteRepository.findAll();
		for (Docente docente : docentes) {		
			map.put(docente.getApellidoPaterno() + ' ' + docente.getApellidoMaterno() + ' '
					+ docente.getNombres() + '|' + docente.getCodigoDocente() , null);
			
		}
		LOG.info("METHOD: lisAllDocentes():"+ map.size());
		
		return map;
	}

}
