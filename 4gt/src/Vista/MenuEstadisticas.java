package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.conexionMYSQL;
import modelo.Reproducion;

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

public class MenuEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEstadisticas frame = new MenuEstadisticas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Este Menu tiene diferentes opciones par poder ver las estadistcas de los
	 * difetentes tipos de canciones, podcast y plyalist Cada boton de esta pagina
	 * ira dirigida a la misma ventan, pero con diferetes resultados.
	 */
	
	public MenuEstadisticas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Menu Estadisticas");

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
		btnNewButton.setBounds(84, 102, 138, 47);
		panel.add(btnNewButton);

		JLabel lblEstadisticas = new JLabel("Estadisticas");
		lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEstadisticas.setBounds(232, 102, 354, 47);
		panel.add(lblEstadisticas);

		/*
		 * Solo se mostrara las Estadisticas de las musica En MSQL
		 * 
		 */

		JButton musica = new JButton("Top Musica más escuchada");
		musica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT\n"
		                + "    c.NombreC AS NombreC,\n"
		                + "    a.NombreA AS NombreA,\n"
		                + "    a.Duracion,\n"
		                + "    r.FechaReproduccion,\n"
		                + "    r.VecesReproducida,\n"
		                + "    a.Tipo\n"
		                + "FROM\n"
		                + "    clientes c\n"
		                + "JOIN\n"
		                + "    reproducciones r ON c.IDCliente = r.IDCliente\n"
		                + "JOIN\n"
		                + "    audios a ON r.IDAudio = a.IDAudio\n"
		                + "WHERE\n"
		                + "		a.Tipo='Cancion';";

				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();

					List<Reproducion> reproduciones = crearListaReproducion(rs);

					estadisticasCancion obj = new estadisticasCancion();
					obj.valoresTabla(reproduciones);
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
		musica.setFont(new Font("Tahoma", Font.PLAIN, 20));
		musica.setBounds(84, 178, 502, 53);
		panel.add(musica);

//Solo se mostrsara las estadisticas de los Podcast
		JButton podcast = new JButton("Top Podcast más escuchado");
		podcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	String sql = "SELECT reproducciones.IDCliente, reproducciones.IDAudio, audios.Nombre, FechaReproduccion, VecesReproducida, Tipo FROM reproducciones JOIN audios ON reproducciones.IDAudio = audios.IDAudio WHERE Tipo = 'Podcast'";
			*/
				String sql = "SELECT\n"
		                + "    c.NombreC AS NombreC,\n"
		                + "    a.NombreA AS NombreA,\n"
		                + "    a.Duracion,\n"
		                + "    r.FechaReproduccion,\n"
		                + "    r.VecesReproducida,\n"
		                + "    a.Tipo\n"
		                + "FROM\n"
		                + "    clientes c\n"
		                + "JOIN\n"
		                + "    reproducciones r ON c.IDCliente = r.IDCliente\n"
		                + "JOIN\n"
		                + "    audios a ON r.IDAudio = a.IDAudio\n"
		                + "WHERE\n"
		                + "		a.Tipo='Podcast';";
				
				
				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();

					List<Reproducion> reproduciones = crearListaReproducion(rs);

					estadisticasPodcast obj = new estadisticasPodcast();
					obj.valoresTabla(reproduciones);
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
		podcast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		podcast.setBounds(84, 257, 502, 53);
		panel.add(podcast);

//Estadisticas generales, musica y podcast junto		
		JButton musicaYpodcast = new JButton("Top más escuchado");
		musicaYpodcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	String sql = "SELECT reproducciones.IDCliente, reproducciones.IDAudio, audios.Nombre, FechaReproduccion, VecesReproducida, Tipo FROM reproducciones JOIN audios ON reproducciones.IDAudio = audios.IDAudio";
			*/
	
				String sql = "SELECT\n"
			            + "    c.NombreC AS NombreC,\n"
			            + "    a.NombreA AS NombreA,\n"
			            + "    a.Duracion,\n"
			            + "    r.FechaReproduccion,\n"
			            + "    r.VecesReproducida,\n"
			            + "    a.Tipo\n"
			            + "FROM\n"
			            + "    clientes c\n"
			            + "JOIN\n"
			            + "    reproducciones r ON c.IDCliente = r.IDCliente\n"
			            + "JOIN\n"
			            + "    audios a ON r.IDAudio = a.IDAudio\n"
			            + "WHERE\n"
			            + "    a.Tipo IN ('Cancion', 'Podcast');";
				
				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					ResultSet rs = st.executeQuery();

					List<Reproducion> reproduciones = crearListaReproducion(rs);

					estadisticasTotal obj = new estadisticasTotal();
					obj.valoresTabla(reproduciones);
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
		musicaYpodcast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		musicaYpodcast.setBounds(84, 344, 502, 53);
		panel.add(musicaYpodcast);

		
		/*
		 * Canciones que esten en mi PlayList NO ESTA HECHO, no se que poner
		 */
		JButton playList = new JButton("Top PlayList");
		playList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estadisticasCancion obj = new estadisticasCancion();
				obj.setVisible(true);
				dispose();
			}
		});
		playList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playList.setBounds(84, 424, 502, 53);
		panel.add(playList);

	}

	/**
	 * Metodo para no repertir la recopilacion de datos en el ArrayLis, en mas de
	 * una ocasion
	 * 
	 */
	protected List<Reproducion> crearListaReproducion(ResultSet rs) {
		List<Reproducion> reproduciones = new ArrayList<>();

		try {
			while (rs.next()) {
				Reproducion Reproducion = new Reproducion();	
				Reproducion.setNombreC(rs.getString("NombreC"));
				Reproducion.setNombreA(rs.getString("NombreA"));
				Reproducion.setFechaReproduccion(rs.getString("FechaReproduccion"));
				Reproducion.setVecesReproducida(rs.getString("VecesReproducida"));
				Reproducion.setTipo(rs.getString("Tipo"));

				reproduciones.add(Reproducion);
			}
		} catch (SQLException ex) {

			ex.printStackTrace();
		}
		return reproduciones;
	}
	
}
