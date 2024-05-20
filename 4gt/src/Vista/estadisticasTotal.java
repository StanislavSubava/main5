package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.conexionMYSQL;
import modelo.Reproducion;
import modelo.valorTiempo;

public class estadisticasTotal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					estadisticasTotal frame = new estadisticasTotal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Se genera en esta ventana tanto las canciones como los podcast puediendo
	 * ordenarlas por semana, mes, año
	 */
	public estadisticasTotal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana estadisticas general");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 670, 565);
		contentPane.add(panel);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.setBounds(10, 10, 138, 47);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEstadisticas obj = new MenuEstadisticas();
				obj.setVisible(true);
				dispose();
			}
		});
		panel.setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnNewButton);

		JButton btnSemana = new JButton("Semana");
		btnSemana.setBounds(218, 10, 138, 47);
		btnSemana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql	="SELECT\n"
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
		                + "    a.Tipo IN ('Cancion', 'Podcast')\n"
		                + "ORDER BY\n"
		                + "    WEEK(r.FechaReproduccion);";
				
				
				try (Connection conn = conexionMYSQL.metodoConexion();
						PreparedStatement st = conn.prepareStatement(sql);
						ResultSet rs = st.executeQuery()) {
					/*
					 * Metodo para no repetir la creacion de las tablas con los valores
					 */ DefaultTableModel model = creacionRowTablas(rs);
					System.out.println(model);

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
					ex.printStackTrace();
				}

			}
		});
		btnSemana.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnSemana);

		JButton btnMes = new JButton("Mes");
		btnMes.setBounds(355, 10, 138, 47);
		btnMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql	="SELECT\n"
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
		                + "    a.Tipo IN ('Cancion', 'Podcast')\n"
		                + "ORDER BY\n"
		                + "    MONTH(r.FechaReproduccion);";

				try (Connection conn = conexionMYSQL.metodoConexion();
						PreparedStatement st = conn.prepareStatement(sql);
						ResultSet rs = st.executeQuery()) {
					/*
					 * Metodo para no repetir la creacion de las tablas con los valores
					 */ DefaultTableModel model = creacionRowTablas(rs);
					System.out.println(model);

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
					ex.printStackTrace();
				}

			}

		});

		btnMes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnMes);

		JButton btnAo = new JButton("Año");
		btnAo.setBounds(491, 10, 138, 47);
		btnAo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql	="SELECT\n"
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
		                + "    a.Tipo IN ('Cancion', 'Podcast')\n"
		                + "ORDER BY\n"
		                + "    YEAR(r.FechaReproduccion);";

				try (Connection conn = conexionMYSQL.metodoConexion();
						PreparedStatement st = conn.prepareStatement(sql);
						ResultSet rs = st.executeQuery()) {

					/*
					 * Metodo para no repetir la creacion de las tablas con los valores
					 */ DefaultTableModel model = creacionRowTablas(rs);
					System.out.println(model);

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
					ex.printStackTrace();
				}

			}
		});
		btnAo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnAo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 650, 486);
		panel.add(scrollPane);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "NombreC", "NombreA", "Fecha de Lanzamiento", "Veces Escuchadas" }));
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		scrollPane.setViewportView(table);

	}

	protected DefaultTableModel creacionRowTablas(ResultSet rs) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		try {
			while (rs.next()) {
				Object[] row = new Object[4];
				row[0] = rs.getString("NombreC");
				row[1] = rs.getString("NombreA");
				row[2] = rs.getString("FechaReproduccion");
				row[3] = rs.getString("VecesReproducida");

				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	protected List<valorTiempo> valoresOrdenadosMes() { /* , YEAR(FechaReproduccion) ASC */
		String sql = "SELECT FechaReproduccion FROM reproducciones ORDER BY MONTH(FechaReproduccion) ASC";
		List<valorTiempo> objList = new ArrayList<>();

		try (Connection conn = conexionMYSQL.metodoConexion();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			while (rs.next()) {
				valorTiempo vt = new valorTiempo();
				vt.setFechaReproduccion(rs.getDate("FechaReproduccion"));
				cargarDatos(vt);
				objList.add(vt);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
			ex.printStackTrace();
		}

		return objList;
	}

	private void cargarDatos(valorTiempo vt) {
		if (vt == null) {
			return;
		}

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

			st.setDate(1, vt.getFechaReproduccion());
			st.setDate(2, vt.getFechaReproduccion());

			ResultSet rs = st.executeQuery();

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);

			while (rs.next()) {
				Object[] row = new Object[4];
				row[0] = rs.getInt("NombreC");
				row[1] = rs.getString("NombreA");
				row[2] = rs.getString("FechaReproduccion");
				row[3] = rs.getString("VecesReproducida");

				model.addRow(row);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al cargar las canciones: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void valoresTabla(List<Reproducion> reproduciones) {
		String[] nomColumna = { "NombreC", "NombreA","Fecha de Lanzamiento", "Veces Escuchadas" };
		model = new DefaultTableModel(nomColumna, 0);
		table.setModel(model);

		for (Reproducion reproducion : reproduciones) {
			Object[]row = { reproducion.getNombreC(), reproducion.getNombreA(),
					reproducion.getFechaReproduccion(), reproducion.getVecesReproducida()};
			model.addRow(row);
		}
	}

}
