package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import modelo.usuarios;
import Controlador.conexionMYSQL;

public class VentanaPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPerfil frame = new VentanaPerfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPerfil(String nombre, String apellido, String usuario, String contraseña, String repContraseña,
			String fechNacimiento, String fechInscripcion) {
		inicializar();
	}

	public VentanaPerfil() {
		inicializar();
	}

	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 670, 565);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaMenuUser obj = new VentanaMenuUser();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 62, 138, 47);
		panel.add(btnNewButton);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerfil.setBounds(158, 62, 354, 47);
		panel.add(lblPerfil);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 176, 532, 327);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);

		// Mostrar los datos del cliente en el JTextArea

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// Mostrar los datos del cliente en el JTextArea
				try (Connection conn = conexionMYSQL.metodoConexion()) {
				    PreparedStatement st = conn.prepareStatement("SELECT IDCliente, NombreC, Apellido, UsuarioCliente, FechaNacimiento, FechaRegistro, Tipo FROM clientes WHERE IDCliente = ?");
				    st.setInt(1, 1); // set the IDCliente value to 1
				    ResultSet rs = st.executeQuery();

				    if (rs.next()) {
				        usuarios cliente = new usuarios();
				        cliente.setIDCliente(rs.getInt("IDCliente"));
				        cliente.setNombre(rs.getString("NombreC"));
				        cliente.setApellido(rs.getString("Apellido"));
				        cliente.setUsuario(rs.getString("UsuarioCliente"));
				        cliente.setFechNacimiento(rs.getString("FechaNacimiento"));
				        cliente.setFechInscripcion(rs.getString("FechaRegistro"));
				        cliente.setTipo(rs.getString("Tipo"));

				        String text = "";
				        text += "ID: " + cliente.getIDCliente() + "\n";
				        text += "Nombre: " + cliente.getNombre() + "\n";
				        text += "Apellido: " + cliente.getApellido() + "\n";
				        text += "Usuario: " + cliente.getUsuario() + "\n";
				        text += "Fecha de nacimiento: " + cliente.getFechNacimiento() + "\n";
				        text += "Fecha de inscripción: " + cliente.getFechInscripcion() + "\n";
				        text += "Tipo: " + cliente.getTipo() + "\n";

				        textArea.setText(text);
				    } else {
				        JOptionPane.showMessageDialog(null, "No se encontraron datos del cliente con ID = 1");
				    }

				} catch (SQLException ex) {
				    JOptionPane.showMessageDialog(null, "Error al mostrar los datos del usuario: " + ex.getMessage());
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGuardar.setBounds(250, 120, 138, 47);
		panel.add(btnGuardar);

	}
}
