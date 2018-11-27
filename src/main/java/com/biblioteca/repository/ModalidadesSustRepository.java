package com.biblioteca.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.biblioteca.entity.ModalidadesSustentacion;


@Repository("modalidadesSustRepository")
public interface ModalidadesSustRepository extends JpaRepository<ModalidadesSustentacion, Serializable> {

	public abstract ModalidadesSustentacion findBycModCod(int id);
	
	@Query("select ms from ModalidadesSustentacion ms order by ms.cModDe2")
	public abstract List<ModalidadesSustentacion> findAllOrderBycModDe2();
}
