package atmira.dto;

import java.time.LocalDate;

public class AsteroidDto {

	private String nombre; // Obtenido de "name",
	private double diametro; // Tamaño medio calculado
	private double  velocidad; // "close_approach_data:relative_velocity:kilometers_per_hour"
	private LocalDate fecha; // "close_approach_data:close_approach_date"
	private String planeta; // "close_approach_date:orbiting_body"
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getDiametro() {
		return diametro;
	}
	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getPlaneta() {
		return planeta;
	}
	public void setPlaneta(String planeta) {
		this.planeta = planeta;
	}

}
