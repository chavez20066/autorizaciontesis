package com.biblioteca.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biblioteca.entity.AutorCybertesisDetalle;

@Repository("autorAutorizacionRepository")
public interface AutorAutorizacionRepository extends JpaRepository<AutorCybertesisDetalle, Serializable>{

	@Query("select a from AutorCybertesisDetalle a where a.Id = ?1 ")
	public abstract AutorCybertesisDetalle findById(int id);
	
	//@Query("select a from AutorCybertesisDetalle a where CodAlumno =?#{[0]} AND Correo =?#{[0].correo}")
	//public abstract List<AutorCybertesisDetalle> findByCodigoByEmail(String codigo,String correo);

	/**@Query("select a from AutorCybertesisDetalle a where a.CodAlumno =:codigo and a.Correo =:correo")
	public  List<AutorCybertesisDetalle> findByCodigoByEmail(@Param("codigo") String codigo,
			@Param("correo") String correo);*/
	//select u from User u where u.emailAddress = ?1 and u.lastname = ?2
	@Query("select a from AutorCybertesisDetalle a where a.CodAlumno = ?1 and a.Correo = ?2")
	public abstract List<AutorCybertesisDetalle> findByCodAlumnoAndCorreo(String CodAlumno, String Correo);
	
	
	/*@Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname") 
	User findByLastnameOrFirstname(@Param("lastname") String lastname, 
			@Param("firstname") String firstname);

    @Query(value = "SELECT * FROM Autor_CybertesisDetalle acd WHERE " +
            "acd.CodAlumno =:codigo AND acd.Correo =:correo",
            nativeQuery = true
    )
    List<AutorCybertesisDetalle> findByCodigoByEmail(@Param("codigo") String codigo,
			@Param("correo") String correo);*/
	
	

}
