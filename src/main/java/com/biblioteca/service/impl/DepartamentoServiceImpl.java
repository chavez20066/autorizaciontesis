package com.biblioteca.service.impl;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.converter.DepartamentoConverter;
import com.biblioteca.entity.Departamento;
import com.biblioteca.entity.Distrito;
import com.biblioteca.entity.Provincia;
import com.biblioteca.model.DepartamentoModel;
import com.biblioteca.repository.DepartamentoRepository;
import com.biblioteca.repository.DistritoRepository;
import com.biblioteca.repository.ProvinciaRepository;
import com.biblioteca.service.DepartamentoService;

@Service("departamentoServiceImpl")
public class DepartamentoServiceImpl implements DepartamentoService {

	private static final Log LOG = LogFactory.getLog(DepartamentoServiceImpl.class);

	@Autowired
	@Qualifier("departamentoRepository")
	private DepartamentoRepository departamentoRepositoy;

	@Autowired
	@Qualifier("provinciaRepository")
	private ProvinciaRepository provinciaRepositoy;

	@Autowired
	@Qualifier("distritoRepository")
	private DistritoRepository distritoRepositoy;

	@Autowired
	@Qualifier("departamentoConverter")
	private DepartamentoConverter departamentoConverter;

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<DepartamentoModel> listAllDepartamentos() {
		List<Departamento> departamentos = departamentoRepositoy.findAll();
		List<DepartamentoModel> departamentosModel = new ArrayList<DepartamentoModel>();

		for (Departamento departamento : departamentos) {
			departamentosModel.add(departamentoConverter.convertDepartamento2DepartamentoModel(departamento));
		}
		LOG.info("METHOD: listAllDepartamentos()"+ departamentosModel.toString());
		return departamentosModel;
	}

	@Override
	@Transactional
	public Departamento findDepartamentoById(int iddepartamento) {

		return departamentoRepositoy.findByiddepartamento(iddepartamento);		
	}

	@Override
	@Transactional
	public Provincia findProvinciaById(int idprovincia) {
		// TODO Auto-generated method stub
		return provinciaRepositoy.findByIdprovincia(idprovincia);
	}

	@Override
	@Transactional
	public Distrito findDistritoById(int idDistrito) {
		// TODO Auto-generated method stub
		return distritoRepositoy.findByidDistrito(idDistrito);
	}

	@Override
	@Transactional
	public List<Distrito> findByiddepartamentoWhithidprovincia(int idDepartamento, int idprovincia) {
		// TODO Auto-generated method stub
		// return
		// provinciaRepositoy.findByiddepartamentoWhithidprovincia(idDepartamento,
		// idprovincia);

		List<Distrito> distritos = manager.createNamedQuery("getDistritosbyIdDepartamento", Distrito.class)
				.setParameter(0, idDepartamento).setParameter(1, idprovincia).getResultList();
		
		

		LOG.info("METHOD: findByiddepartamentoWhithidprovincia():" + distritos.size());

		return distritos;
	}

	@Override
	@Transactional
	public List<Provincia> findAllByIdDepartamento(int idDepartamento) {
		// TODO Auto-generated method stub
		return provinciaRepositoy.findAllByIdDepartamento(idDepartamento);
	}

}
