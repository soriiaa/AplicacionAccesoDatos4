package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Controlador;
import vista.Vista;

public class Modelo {

	private Vista[] misVistas;
	private Controlador miControlador;
	private Connection miConexion;

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	public Modelo() {

		String url = "jdbc:mysql://localhost:3306/libreria";
		String username = "root";
		String password = "";

		try {
			miConexion = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión exitosa a MySQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean anadirLibro(String nombre, String apellido, String anoPublicacion) {

	    int anoPubli = -1;
	    
	    if (anoPublicacion.matches("\\d+")) {
	        anoPubli = Integer.parseInt(anoPublicacion);
	    }

	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        rs = stmt.executeQuery("SELECT * FROM libros");

	        rs.moveToInsertRow();
	        
	        rs.updateString("titulo", nombre);
	        rs.updateString("autor", apellido);
	        
	        if (anoPubli != -1) {
	            rs.updateInt("anio_publicacion", anoPubli);
	        } else {
	            rs.updateInt("anio_publicacion", 0);
	        }

	        rs.insertRow();

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;

	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public String[][] recogerRegistros() {

	    String consulta = "SELECT * FROM libros";

	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        rs = stmt.executeQuery(consulta);

	        ArrayList<String[]> listaResultados = new ArrayList<>();
	        
	        while (rs.next()) {
	            String id = rs.getString("id");
	            String titulo = rs.getString("titulo");
	            String autor = rs.getString("autor");
	            String anoPublicacion = rs.getString("anio_publicacion");

	            String[] arrayFila = { id, titulo, autor, anoPublicacion };
	            listaResultados.add(arrayFila);
	        }

	        String[][] arrayRegistros = new String[listaResultados.size()][4];

	        for (int i = 0; i < listaResultados.size(); i++) {
	            for (int j = 0; j < 4; j++) {
	                arrayRegistros[i][j] = listaResultados.get(i)[j];
	            }
	        }

	        return arrayRegistros;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;

	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public int eliminarRegistro(String id) {

	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        rs = stmt.executeQuery("SELECT * FROM libros WHERE id = " + id);

	        if (rs.next()) {
	            rs.deleteRow();
	            return 1;
	        } else {
	            return 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;

	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public boolean editarRegistro(String idLibro, String nuevoTitulo, String nuevoAutor, String nuevoAnoPublicacion) {

	    Statement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        rs = stmt.executeQuery("SELECT * FROM libros WHERE id = " + idLibro);

	        if (rs.next()) {
	            rs.updateString("titulo", nuevoTitulo);
	            rs.updateString("autor", nuevoAutor);

	            int anoPubli = -1;
	            if (nuevoAnoPublicacion.matches("\\d+")) {
	                anoPubli = Integer.parseInt(nuevoAnoPublicacion);
	            }

	            rs.updateInt("anio_publicacion", anoPubli);
	            
	            rs.updateRow();

	            return true;
	        } else {
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;

	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
