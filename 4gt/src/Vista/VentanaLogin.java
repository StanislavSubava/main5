package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.conexionMYSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VentanaLogin extends JFrame {

	
	private static final long serialVersionUID = -4288056913774964566L;
	private JPanel contentPane;
	public JTextField textUser;
	private JButton btnRegistrarse;
	private JPanel panel;
	public JPasswordField textContra;
	private JComboBox<String> listaUsers;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Descripción: Abre la ventana principal de la aplicación para solicitar al
	 * usuario que introduzca sus credenciales y asi poderlo tener localizado en la
	 * aplicación.
	 * 
	 * @author in1dm3
	 */
	public VentanaLogin() {
		super("Ventana Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana Login");

		panel = new JPanel();
		panel.setBounds(0, 0, 690, 587);
		contentPane.add(panel);

		panel.setLayout(null);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setBounds(168, 132, 123, 33);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 20));
		panel.add(lblUser);

		JLabel lblContra = new JLabel("Contraseña");
		lblContra.setForeground(new Color(255, 255, 255));
		lblContra.setBounds(168, 202, 150, 33);
		lblContra.setHorizontalAlignment(SwingConstants.CENTER);
		lblContra.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 20));
		panel.add(lblContra);

		textUser = new JTextField();
		textUser.setToolTipText("");
		textUser.setBounds(327, 131, 220, 33);
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(textUser);
		textUser.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText();
				String password = new String(textContra.getPassword());

				if (user.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos Vacios en Usuario o Contraseña");
					return; // Salir del método actionPerformed si hay campos vacíos
				}
				String sql = "SELECT * FROM clientes WHERE UsuarioCliente = ? AND ContraseñaCliente = ?";
				String sqlAdmin = "SELECT * FROM administrador WHERE UsuarioAdmin = ? AND ContraseñaAdmin = ?";

				try (Connection conn = conexionMYSQL.metodoConexion()) {
					PreparedStatement st = conn.prepareStatement(sql);
					st.setString(1, user);
					st.setString(2, password);

					ResultSet rs = st.executeQuery();

					if (rs.next()) {
						// Usuario regular
						VentanaMenuUser obj = new VentanaMenuUser();
						obj.setVisible(true);
						dispose();
					} else {
						PreparedStatement stAdmin = conn.prepareStatement(sqlAdmin);
						stAdmin.setString(1, user);
						stAdmin.setString(2, password);

						ResultSet rsAdmin = stAdmin.executeQuery();

						if (rsAdmin.next()) {
							// User is an admin user
							VentanaMenuAdmin obj1 = new VentanaMenuAdmin();
							obj1.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña invalida");
						}

					}

					rs.close();
					st.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage());
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
				}
			}
		});
		btnLogin.setBounds(384, 373, 163, 58);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnLogin);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				VentanaRegistro obj = new VentanaRegistro();
				System.out.println(obj);
				dispose();
			}
		});
		btnRegistrarse.setBounds(155, 373, 163, 58);
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnRegistrarse);

		listaUsers = new JComboBox<>();
		listaUsers.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaUsers.setModel(new DefaultComboBoxModel<String>(new String[] { "User ", "Admin" }));
		listaUsers.setBounds(327, 262, 220, 33);
		panel.add(listaUsers);

		// Listener para el JComboBox
		listaUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificar la selección actual del JComboBox
				String selectedItem = (String) listaUsers.getSelectedItem();
				if (selectedItem.equals("Admin")) {
					// Si se selecciona "Admin", ocultar el botón de registrarse
					btnRegistrarse.setVisible(false);
				} else {
					btnRegistrarse.setVisible(true);
				}
			}
		});

		textContra = new JPasswordField();
		textContra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textContra.setBounds(327, 201, 220, 33);
		panel.add(textContra);

		JLabel lblfondo = new JLabel("");
		lblfondo.setIcon(new ImageIcon("img/fondolog.jpg"));
		
		lblfondo.setBounds(0, 0, 691, 587);
		panel.add(lblfondo);

		lblNewLabel = new JLabel("BasSoniua");
		lblNewLabel.setLabelFor(panel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(348, 50, 94, 33);
		panel.add(lblNewLabel);
	}
}
