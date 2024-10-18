package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Modelo;

public class _01_AgregarRegistros extends JFrame implements Vista {

	private Controlador miControlador;
	private Modelo miModelo;
	private JTextField txtTitulo;
	private JLabel lblTituloVentana;
	private JTextField txtAutor;
	private JLabel lblAutor;
	private JLabel lblInstrucciones;
	private JLabel lblTitulo;
	private JButton btnAnadir;
	private JLabel lblResultadoInsert;
	private JLabel lblVolver;
	private JLabel lblAnoPublicacion;
	private JTextField txtAnoPublicacion;

	@Override
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _01_AgregarRegistros() {

		setResizable(false);
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		lblTituloVentana = new JLabel("Añadir Registro");
		lblTituloVentana.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTituloVentana.setBounds(308, 57, 270, 50);
		getContentPane().add(lblTituloVentana);

		txtTitulo = new JTextField();
		txtTitulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				activarBoton();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				activarBoton();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				activarBoton();
			}
		});
		txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitulo.setBounds(273, 223, 339, 30);
		txtTitulo.setBorder(BorderFactory.createCompoundBorder(txtTitulo.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);

		txtAutor = new JTextField();
		txtAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				activarBoton();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				activarBoton();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				activarBoton();
			}
		});
		txtAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAutor.setBounds(273, 282, 339, 30);
		txtAutor.setBorder(BorderFactory.createCompoundBorder(txtAutor.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		
		txtAnoPublicacion = new JTextField();
		txtAnoPublicacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAnoPublicacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				activarBoton();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				activarBoton();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				activarBoton();
			}
		});
		txtAnoPublicacion.setBounds(273, 340, 339, 30);
		txtAnoPublicacion.setBorder(BorderFactory.createCompoundBorder(txtAnoPublicacion.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		getContentPane().add(txtAnoPublicacion);
		txtAnoPublicacion.setColumns(10);
		
		lblAnoPublicacion = new JLabel("Año Publicación:");
		lblAnoPublicacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnoPublicacion.setBounds(144, 346, 119, 13);
		getContentPane().add(lblAnoPublicacion);

		lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(204, 231, 59, 13);
		getContentPane().add(lblTitulo);

		lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutor.setBounds(204, 290, 58, 13);
		getContentPane().add(lblAutor);

		lblInstrucciones = new JLabel("Añadir un Libro a la Base de Datos");
		lblInstrucciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstrucciones.setBounds(273, 142, 339, 25);
		getContentPane().add(lblInstrucciones);

		lblResultadoInsert = new JLabel("Libro añadido con éxito");
		lblResultadoInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResultadoInsert.setBounds(365, 417, 156, 13);
		lblResultadoInsert.setVisible(false);
		getContentPane().add(lblResultadoInsert);
		setVisible(false);

		btnAnadir = new JButton("Añadir Libro");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean respuesta = miControlador.anadirLibro(txtTitulo.getText(),
						txtAutor.getText(), txtAnoPublicacion.getText());
				if (respuesta) {
					limpiarCampos();
					lblResultadoInsert.setText("Libro añadido con éxito");
					lblResultadoInsert.setVisible(true);
					btnAnadir.setEnabled(false);
				} else {
					lblResultadoInsert.setText("       Error");
					lblResultadoInsert.setVisible(true);
				}
			}
		});
		btnAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnAnadir.isEnabled()) {
					btnAnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btnAnadir.setBackground(new Color(70, 70, 70));
				}
			}
		});
		btnAnadir.setBackground(new Color(0, 0, 0));
		btnAnadir.setForeground(new Color(255, 255, 255));
		btnAnadir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadir.setBounds(368, 456, 149, 37);
		btnAnadir.setEnabled(false);
		btnAnadir.setBorder(null);
		getContentPane().add(btnAnadir);

		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnadir.setBackground(new Color(0, 0, 0));
			}
		});

		lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(".\\adjuntos\\flechaAtras.png"));
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
				btnAnadir.setEnabled(false);
				miControlador.cambiarVentana(1, 0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		lblVolver.setBounds(817, 11, 61, 44);
		getContentPane().add(lblVolver);

	}

	public void activarBoton() {

		if (txtTitulo.getText().equals("") || txtAutor.getText().equals("") || txtAnoPublicacion.getText().equals("")) {
			btnAnadir.setEnabled(false);
		} else {
			btnAnadir.setEnabled(true);
		}

	}

	public void limpiarCampos() {
		txtTitulo.setText("");
		txtAutor.setText("");
		txtAnoPublicacion.setText("");
	}
}
