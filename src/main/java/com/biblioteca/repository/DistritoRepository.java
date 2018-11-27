package com.biblioteca.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteca.entity.Distrito;

@Repository("distritoRepository")
public interface DistritoRepository extends JpaRepository<Distrito, Serializable>{	
	public abstract Distrito findByidDistrito(int id);
		
}
