package com.example.data.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.data.model.Cita;
import com.example.data.model.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {

	List<Cita> findByUsuarioId(Long usuarioId);
	    // Método para buscar y eliminar una cita por su hora
	    @Transactional
	    void deleteByHoraAndUsuario(String hora, Usuario usuario);
	}



