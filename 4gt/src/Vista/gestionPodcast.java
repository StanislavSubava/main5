package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.conexionMYSQL;
import modelo.Cancion;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class gestionPodcast extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton añadir;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestionPodcast frame = new gestionPodcast();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * En esta ventana se podra gestionar la tabla metida en el textArea, podiendo
	 * modificar el nombre, eliminar la fila o crear una nueva
	 */
	public gestionPodcast() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana de Gestion");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 670, 565);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMenuAdmin obj = new VentanaMenuAdmin();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 10, 138, 47);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 464, 488);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Duracion", "Tipo" }));
		scrollPane.setViewportView(table);
		/*
		 * Se genera las tablas con los valores en la tabla que esta encima
		 */
		valoresTabla(getCanciones());

		/*
		 * Boton de Crear una fila para poder escribir los valores de la nueva cancion,
		 * Se aparece un boton que permite la consulta de añadir la cancion a la base de
		 * datos
		 */
		JButton crear = new JButton("Crear");
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] row = { "", "", "" };
				model.addRow(row);
				añadir.setVisible(true);
				table.editCellAt(model.getRowCount() - 1, 0);
			}
		});
		crear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		crear.setBounds(484, 136, 176, 39);
		panel.add(crear);
		/*
		 * Boton de eliminar la cancion de la base de datos
		 */
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int idAudio = (int) table.getValueAt(selectedRow, 0);

					int confirmResult = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas eliminar esta canción?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmResult == JOptionPane.YES_OPTION) {
						borrarDeLaBD(idAudio);
						recargarTabla();
					}
				}
			}

			private void borrarDeLaBD(int idAudio) {
				String sql = "DELETE FROM audios WHERE IDAudio = ?";
				// Change the table name above to the correct one
				// For example: "DELETE FROM audio_table WHERE IDAudio = ?"

				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					// ResultSet rs = st.executeQuery();
					st.setInt(1, idAudio);
					st.executeUpdate();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al eliminar la canción de la base de datos " + ex.getMessage());
					ex.printStackTrace();
				}

			}
		});
		eliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		eliminar.setBounds(484, 218, 176, 39);
		panel.add(eliminar);
		/*
		 * Boton de Modificar datos de la tabla y de la base de datos
		 * 
		 * funciona, Pero ERROR, cambia todo el sistema de la duracion
		 */
		JButton modificar = new JButton("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lineaSelec = table.getSelectedRow();

				if (lineaSelec != -1) {
					int idAudio = (int) table.getValueAt(lineaSelec, 0);
					
					int selectedRow = lineaSelec; // consider using a more descriptive variable name
					int columnIndex = 1; // consider using a constant or an enum for column indices

					String nombre = (String) table.getValueAt(selectedRow, columnIndex);

					// consider adding null checks and handling potential exceptions
					if (nombre == null) {
					    nombre = ""; // or some default value
					}

			//		int duracion = (int) table.getValueAt(lineaSelec, 2);

					int confirmResultNom = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas modificar el nombre de esta canción?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);
					if (confirmResultNom == JOptionPane.YES_OPTION) {
						modificarBD(idAudio, nombre);
						recargarTabla();
					}
					
					/*
					  int confirmResultDura = JOptionPane.showConfirmDialog(null,
					  "¿Estás seguro de que deseas modificar el nombre de esta canción?",
					  "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
					  
					  if (confirmResultDura == JOptionPane.YES_OPTION) { // modificarBD(duracion);
					  }
					 */

				}
			}

			private void modificarBD(int idAudio, String nuevoNombre) {
			    String sql = "UPDATE audios SET NombreA = ? WHERE IDAudio = ?";

			    try (Connection conn = conexionMYSQL.metodoConexion();
			         PreparedStatement st = conn.prepareStatement(sql)) {

			        st.setString(1, nuevoNombre);
			        st.setInt(2, idAudio);

			        int rowsAffected = st.executeUpdate();

			        if (rowsAffected > 0) {
			            System.out.println("El nombre se ha cambiado perfectamente");
			        } else {
			            System.out.println("No se ha actualizado.");
			        }

			    } catch (SQLException e) {
			        System.out.println("Error al modificar los datos: " + e.getMessage());
			    }
			}
			

		});
		modificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		modificar.setBounds(484, 304, 176, 39);
		panel.add(modificar);

		JLabel lblNewLabel = new JLabel("Gestion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(158, 10, 318, 47);
		panel.add(lblNewLabel);
		/*
		 * Boton añadir, coge los valores de las celdas, y los mete en la tabla audios,
		 * como nuevas canciones
		 * 
		 * No funciona del todo Bien, Hay un problema, que se genera otra vez la tabla
		 * completa y no solo la fila
		 */
		añadir = new JButton("Añadir");
		añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String nombreC = (String) table.getValueAt(selectedRow, 1);
					String value = (String) table.getValueAt(selectedRow, 2);
					int duracion = value != null && !value.isEmpty() ? Integer.parseInt(value) : 0;

					int confirmResult = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que deseas añadir esta canción?", "Confirmar eliminación",
							JOptionPane.YES_NO_OPTION);

					if (confirmResult == JOptionPane.YES_OPTION) {
						subirBD(nombreC, duracion);
						recargarTabla();
					}
				}

			}

			private void subirBD(String nombreC, int duracion) {
				Cancion cancion = new Cancion(nombreC, duracion);
				List<Cancion> canciones = new ArrayList<>();
				canciones.add(cancion);

				String sql = "INSERT INTO audios (NombreA, Duracion, Tipo) VALUES (?,?, 'Podcast')";

				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					for (Cancion c : canciones) {
						st.setString(1, c.getNombre());
						st.setInt(2, c.getDuracion());
						st.addBatch();
					}
					st.executeBatch();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,
							"Error al insertar los datos de las canciones: " + ex.getMessage());
					ex.printStackTrace();
				}

			}
		});
		/**
		 * JOptionPane.showMessageDialog(null, "Error al insertar las canciones: " +
		 * ex.getMessage()); ex.printStackTrace();
		 */

		añadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		añadir.setBounds(484, 86, 176, 39);
		añadir.setVisible(false);
		panel.add(añadir);

	}

	protected List<Cancion> crearListaCanciones(ResultSet rs) {
		List<Cancion> canciones = new ArrayList<>();

		try {
			while (rs.next()) {

				Cancion cancion = new Cancion();
				cancion.setId(rs.getInt("IDAudio"));
				cancion.setNombre(rs.getString("NombreA"));
				cancion.setDuracion(rs.getInt("Duracion"));
				cancion.setTipo(rs.getString("Tipo"));

				canciones.add(cancion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return canciones;
	}

	/*
	 * Recarga los datos de la tabla con todos los datos nuevos y solo modificando
	 * los que se cambian
	 */
	private void recargarTabla() {
		String sql = "SELECT IDAudio, NombreA, Duracion, Tipo FROM audios WHERE Tipo='Podcast'";

		try (Connection conn = conexionMYSQL.metodoConexion()) {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			List<Cancion> canciones = crearListaCanciones(rs);

			gestionPodcast obj = new gestionPodcast();
			obj.valoresTabla(canciones);
			obj.setVisible(true);
			dispose();

			rs.close();
			st.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	/*
	 * Genera la tabla con sus columnas
	 */
	void valoresTabla(List<Cancion> canciones) {
		model = new DefaultTableModel(new String[] { "IDAudio", "Nombre", "Duracion" }, 0);
		table.setModel(model);

		for (Cancion cancion : canciones) {
			Object[] row = { cancion.getId(), cancion.getNombre(), cancion.getDuracion() };
			model.addRow(row);
		}
	}

	/*
	 * Clase para poder coger las canciones desde una clase
	 */
	private List<Cancion> getCanciones() {
		List<Cancion> canciones = new ArrayList<>();
		return canciones;
	}
}
