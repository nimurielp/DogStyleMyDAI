package com.example.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.data.model.Usuario;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByEmail(String email);
   
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.citas")
    List<Usuario> findAllWithCitas();

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:nombre%")
    List<Usuario> buscarPorNombre(@Param("nombre") String nombre);
    
}


