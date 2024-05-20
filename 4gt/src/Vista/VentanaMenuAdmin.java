package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.conexionMYSQL;
import modelo.Cancion;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaMenuAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuAdmin frame = new VentanaMenuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Ventana con diferentes elecciones para poder ver o gestionar.
	 */
	public VentanaMenuAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Menu administrador");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 670, 565);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaLogin obj = new VentanaLogin();
				obj.setVisible(true);
				dispose();
			}
		});
		atras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		atras.setBounds(48, 89, 138, 47);
		panel.add(atras);
		
		/**
		 * Boton que al presionar se dirige a la pantalla de gestionarMusica con los
		 * datos de las canciones en el
		 */
		JButton gestionarMusica = new JButton("Gestionar musica");
		gestionarMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT IDAudio, NombreA, Duracion, Tipo FROM audios WHERE Tipo='Cancion'";
				
				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();

					List<Cancion> canciones = crearListaCanciones(rs);

					gestion obj = new gestion();
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
		});
		gestionarMusica.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gestionarMusica.setBounds(158, 186, 359, 47);
		panel.add(gestionarMusica);

		/**
		 * Boton que al presionar se dirige a la pantalla de gestionarPodcast con los
		 * datos de las canciones en el
		 * 
		 */

		JButton gestionarPodcast = new JButton("Gestionar Podcast");
		gestionarPodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		gestionarPodcast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gestionarPodcast.setBounds(158, 277, 359, 47);
		panel.add(gestionarPodcast);

		/**
		 * Boton que al presionar se dirige a la pantalla de menu estadisticas
		 */

		JButton estadisticas = new JButton("Estadisticas");
		estadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuEstadisticas obj = new MenuEstadisticas();
				obj.setVisible(true);
				dispose();

			}
		});
		estadisticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		estadisticas.setBounds(158, 373, 359, 47);
		panel.add(estadisticas);

		JLabel lblMenuAdministrador = new JLabel("Menu administrador");
		lblMenuAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMenuAdministrador.setBounds(196, 89, 354, 47);
		panel.add(lblMenuAdministrador);
	}

	/**
	 * Metodo para no repertir la recopilacion de datos en el ArrayLis, en mas de una ocasion
	 * 
	 */
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
}
