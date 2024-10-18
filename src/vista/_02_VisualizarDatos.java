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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;

public class _02_VisualizarDatos extends JFrame implements Vista {

	private Controlador miControlador;
	private Modelo miModelo;
	private JTable tableUsuarios;
	private JLabel lblTItuloVentana;
	private JScrollPane scrollPane;
	private JTable table;
	private Object[][] data;
	private JLabel lblVolver;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JLabel lblTitulo;
	private JLabel lblAutor;
	private JLabel lblEliminarResultado;
	private JLabel lblModificadoResultado;
	private JButton btnRecargar;
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

	public _02_VisualizarDatos() {

		setResizable(false);
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		lblTItuloVentana = new JLabel("Visualizar Datos");
		lblTItuloVentana.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTItuloVentana.setBounds(300, 36, 284, 69);
		getContentPane().add(lblTItuloVentana);
		setVisible(false);

		String[] columnNames = { "ID", "Título", "Autor", "Año Publicación" };

		DefaultTableModel model = new DefaultTableModel(new String[0][0], columnNames);
		table = new JTable(model);

		scrollPane = new JScrollPane(table);

		scrollPane.setBounds(50, 115, 510, 400);
		getContentPane().add(scrollPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String[][] data = miControlador.recogerRegistros();
				DefaultTableModel model = new DefaultTableModel(data, columnNames) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setModel(model);
			}
		});

		lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(".\\adjuntos\\flechaAtras.png"));
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetearCampos();
				miControlador.cambiarVentana(2, 0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		lblVolver.setBounds(817, 11, 61, 44);
		getContentPane().add(lblVolver);

		btnModificar = new JButton("Modificar");

		btnModificar.setBounds(760, 292, 96, 33);
		btnModificar.setBorder(null);
		btnModificar.setEnabled(false);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");

		btnEliminar.setBounds(760, 408, 96, 33);
		btnEliminar.setBorder(null);
		getContentPane().add(btnEliminar);

		txtTitulo = new JTextField();
		txtTitulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				comprobarModificar();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				comprobarModificar();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				comprobarModificar();
			}
		});
		txtTitulo.setBounds(710, 171, 146, 19);
		txtTitulo.setText("");
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);

		txtAutor = new JTextField();
		txtAutor.setBounds(710, 212, 146, 19);
		getContentPane().add(txtAutor);
		txtAutor.setColumns(10);
		txtAutor.setText("");
		txtAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				comprobarModificar();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				comprobarModificar();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				comprobarModificar();
			}
		});

		lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(648, 174, 52, 13);
		getContentPane().add(lblTitulo);

		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(648, 215, 52, 13);
		getContentPane().add(lblAutor);

		lblEliminarResultado = new JLabel("Eliminado con Éxito");
		lblEliminarResultado.setBounds(724, 451, 138, 13);
		lblEliminarResultado.setVisible(false);
		getContentPane().add(lblEliminarResultado);

		lblModificadoResultado = new JLabel("Modificado con Éxito");
		lblModificadoResultado.setBounds(724, 335, 140, 13);
		lblModificadoResultado.setVisible(false);
		getContentPane().add(lblModificadoResultado);

		btnRecargar = new JButton("Recargar");

		btnRecargar.setBounds(50, 72, 85, 21);
		btnRecargar.setBorder(null);
		getContentPane().add(btnRecargar);

		lblAnoPublicacion = new JLabel("Año Publicación:");
		lblAnoPublicacion.setBounds(615, 255, 85, 13);
		getContentPane().add(lblAnoPublicacion);

		txtAnoPublicacion = new JTextField();
		txtAnoPublicacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				comprobarModificar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				comprobarModificar();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				comprobarModificar();
			}
		});
		txtAnoPublicacion.setBounds(710, 252, 146, 19);
		getContentPane().add(txtAnoPublicacion);
		txtAnoPublicacion.setColumns(10);

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = table.getSelectedRow();
				String id = "";

				if (filaSeleccionada != -1) {
					id = table.getValueAt(filaSeleccionada, 0).toString();
				}

				String nuevoTitulo = txtTitulo.getText();
				String nuevoAutor = txtAutor.getText();
				String nuevoAnoPublicacion = txtAnoPublicacion.getText();

				boolean resultado = miControlador.editarRegistro(id, nuevoTitulo, nuevoAutor, nuevoAnoPublicacion);

				if (resultado) {
					resetearCampos();
					lblModificadoResultado.setText("Modificado con Éxito");
					lblModificadoResultado.setVisible(true);
				} else {
					lblModificadoResultado.setText("   Error papichulo");
					lblModificadoResultado.setVisible(true);
				}
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = table.getSelectedRow();
				String id = "";

				if (filaSeleccionada != -1) {
					id = table.getValueAt(filaSeleccionada, 0).toString();
				}

				int resultado = miControlador.eliminarRegistro(id);

				if (resultado != -1) {
					lblEliminarResultado.setText("Eliminado con Éxito");
					lblEliminarResultado.setVisible(true);
				} else {
					lblEliminarResultado.setText("      Error");
					lblEliminarResultado.setVisible(true);
				}
			}
		});

		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}
		});

		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				comprobarEliminar();
			}
		});

		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] data = miControlador.recogerRegistros();
				DefaultTableModel model = new DefaultTableModel(data, columnNames) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setModel(model);
			}
		});

	}

	public void resetearCampos() {
		txtTitulo.setText("");
		txtAutor.setText("");
		txtAnoPublicacion.setText("");
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		lblEliminarResultado.setVisible(false);
		lblModificadoResultado.setVisible(false);
	}

	public void comprobarEliminar() {
		if (table.getSelectedRow() == -1) {
			btnEliminar.setEnabled(false);
		} else {
			btnEliminar.setEnabled(true);
		}
	}

	public void comprobarModificar() {

		if ((txtTitulo.getText().equals("")) || (txtAutor.getText().equals("")) || (txtAnoPublicacion.getText().equals("")) || (table.getSelectedRow() == -1)) {
			btnModificar.setEnabled(false);
		} else {
			btnModificar.setEnabled(true);
		}

	}

}
