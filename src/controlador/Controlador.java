package controlador;

import javax.swing.JFrame;

import modelo.Modelo;
import vista.Vista;

public class Controlador {

	private Modelo miModelo;
	private Vista[] misVistas;

	public Controlador() {
		super();
	}

	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public void cambiarVentana(int desde, int hasta) {
		((JFrame) misVistas[desde]).setVisible(false);
		((JFrame) misVistas[hasta]).setVisible(true);
	}

	public boolean anadirLibro(String titulo, String autor, String anoPublicacion) {
		return miModelo.anadirLibro(titulo, autor, anoPublicacion);
	}

	public String[][] recogerRegistros() {
		return miModelo.recogerRegistros();
	}

	public int eliminarRegistro(String id) {
		return miModelo.eliminarRegistro(id);
	}

	public boolean editarRegistro(String id, String nuevoTitulo, String nuevoAutor, String nuevoAnoPublicacion) {

		return miModelo.editarRegistro(id, nuevoTitulo, nuevoAutor, nuevoAnoPublicacion);

	}

}
