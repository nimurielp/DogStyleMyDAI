package com.example.data;

//import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.data.model.Cita;
import com.example.data.model.Usuario;
import com.example.data.repository.CitaRepository;
import com.example.data.repository.UsuarioRepository;

@SpringBootTest
class SpringDbDogstyleApplicationTests {
	
	@Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

	@Test
	void contextLoads() {
		System.out.println("");
		System.out.println("-----------------------------------------------");
		System.out.println("|    PRUEBA MAPEADO BASE DE DATOS MyDAI       |");
		System.out.println("-----------------------------------------------");
		System.out.println("");
		System.out.println("CREAMOS LOS USUARIOS");
		createUsuarios();
		System.out.println("MOSTRAMOS LOS USUARIOS:");
		System.out.println("-----------------------------------------------");
		obtenerUsuarios();
		//eliminarCitaPorHora();
		//obtenerUsuarios();
		System.out.println("");
		System.out.println("MOSTRAMOS TODAS LAS CITAS:");
		System.out.println("-----------------------------------------------");
		mostrarTodasLasCitas();
		System.out.println("");
		System.out.println("VAMOS A ELIMINAR UN USUARIO Y CON ELLO LAS CITAS DE ESTE:");
		System.out.println("------------------------------------------------------------");
		eliminarUsuarioYSusCitas();
		System.out.println("");
		System.out.println("MOSTRAMOS DE NUEVO LOS USUARIOS:");
		System.out.println("------------------------------------------------------------");
		obtenerUsuarios();
		System.out.println("");
		System.out.println("MOSTRAMOS DE NUEVO LAS CITAS:");
		System.out.println("------------------------------------------------------------");
		mostrarTodasLasCitas();
		
		
	}
	

	public void createUsuarios() {
	    // Crear dos usuarios
	    Usuario usuario1 = new Usuario();
	    usuario1.setNombre("Nico");
	    usuario1.setEmail("nico@example.com");

	    Usuario usuario2 = new Usuario();
	    usuario2.setNombre("Marta");
	    usuario2.setEmail("marta@example.com");

	    // Guardar los usuarios en la base de datos
	    usuario1 = usuarioRepository.save(usuario1);
	    usuario2 = usuarioRepository.save(usuario2);

	    // Crear citas para el usuario 1
	    Cita cita1 = new Cita();
	    cita1.setFecha(new Date());
	    cita1.setHora("10:00");
	    cita1.setLibre(false);
	    cita1.setUsuario(usuario1);

	    Cita cita2 = new Cita();
	    cita2.setFecha(new Date());
	    cita2.setHora("11:00");
	    cita2.setLibre(false);
	    cita2.setUsuario(usuario1);

	    // Crear una cita para el usuario 2
	    Cita cita3 = new Cita();
	    cita3.setFecha(new Date());
	    cita3.setHora("14:00");
	    cita3.setLibre(false);
	    cita3.setUsuario(usuario2);

	    // Guardar las citas en la base de datos
	    cita1 = citaRepository.save(cita1);
	    cita2 = citaRepository.save(cita2);
	    cita3 = citaRepository.save(cita3);
	}
	
	public Iterable<Usuario> obtenerUsuarios() {
	    Iterable<Usuario> usuarios = usuarioRepository.findAllWithCitas();

	    for (Usuario usuario : usuarios) {
	        System.out.println("ID: " + usuario.getId());
	        System.out.println("Nombre: " + usuario.getNombre());
	        System.out.println("Email: " + usuario.getEmail());

	        // Mostrar las citas del usuario
	        List<Cita> citas = usuario.getCitas();
	        if (citas != null && !citas.isEmpty()) {
	            System.out.println("Citas del usuario:");
	            for (Cita cita : citas) {
	                System.out.println("  ID de la cita: " + cita.getId());
	                System.out.println("  Fecha de la cita: " + cita.getFecha());
	                System.out.println("  Hora de la cita: " + cita.getHora());
	                System.out.println("----------");
	            }
	        } else {
	            System.out.println("El usuario no tiene citas.");
	        }

	        System.out.println("----------");
	    }

	    return usuarios;
	}
	
	
	void eliminarUsuarioYSusCitas() {
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Introduce el ID del usuario que deseas eliminar: ");
	    Long usuarioId = scanner.nextLong();

	    Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

	    if (usuario != null) {
	        // Las citas asociadas se eliminarán automáticamente debido a la configuración de cascada
	        usuarioRepository.delete(usuario);

	        System.out.println("Usuario y sus citas eliminados: Usuario ID = " + usuarioId);
	    } else {
	        System.out.println("Usuario no encontrado con ID " + usuarioId);
	    }

	    scanner.close();
	}
	
	void mostrarTodasLasCitas() {
		
	    Iterable<Cita> citas = citaRepository.findAll();

	    for (Cita cita : citas) {
	        System.out.println("ID de la cita: " + cita.getId());
	        System.out.println("Fecha de la cita: " + cita.getFecha());
	        System.out.println("Hora de la cita: " + cita.getHora());

	        // Muestra el usuario asociado a la cita
	        System.out.println("Usuario asociado - ID: " + cita.getUsuario().getId());
	        System.out.println("Usuario asociado - Nombre: " + cita.getUsuario().getNombre());
	        System.out.println("----------");
	    }
	}

}

