package com.example.data.model;

import java.util.Date;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Cita{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
    private Date fecha;

    private String hora;

    private boolean libre;
	
    @ManyToOne
	private Usuario usuario;
	
	public Cita() {
		 this.libre = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, hora, id, libre, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora) && Objects.equals(id, other.id)
				&& libre == other.libre && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", libre=" + libre + ", usuario=" + usuario
				+ "]";
	}


	
	

}
