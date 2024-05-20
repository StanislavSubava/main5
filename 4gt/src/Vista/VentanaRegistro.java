package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.conexionMYSQL;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textNom;
	public JTextField textApe;
	public JTextField textUser;
	public JTextField textFechNa;
	public JTextField textFechIns;
	public JPasswordField textContra;
	public JPasswordField textRContra;
	private JLabel lblPremium;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		super("Ventana Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana Registro");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 670, 565);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				VentanaLogin obj = new VentanaLogin();
				obj.setVisible(true);
				dispose();
			}

		});
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(10, 11, 137, 37);
		panel.add(btnAtras);
		setVisible(true);

//Labels en metodo
		JLabel lblNewLabel = crearLabel("Nombre", 50, 73, 111, 28);
		panel.add(lblNewLabel);
		JLabel lblNewLabel1 = crearLabel("Apellido", 336, 73, 129, 28);
		panel.add(lblNewLabel1);
		JLabel lblNewLabel_1 = crearLabel("Usuario", 50, 127, 111, 28);
		panel.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = crearLabel("Contraseña", 50, 173, 111, 28);
		panel.add(lblNewLabel_2);
		JLabel lblNewLabel_3 = crearLabel("Repetir Contraseña", 336, 173, 162, 43);
		panel.add(lblNewLabel_3);
		// Las fechas tienen un parametro enconcreto - o /
		JLabel lblNewLabel_4 = crearLabel("Fecha de nacimiento", 50, 242, 180, 28);
		panel.add(lblNewLabel_4);
		JLabel lblNewLabel_5 = crearLabel("Fecha de inscripcion", 50, 291, 180, 28);
		panel.add(lblNewLabel_5);

		// Jtextfield en metodo

		textNom = crearTextField(157, 73, 169, 28);
		panel.add(textNom);
		textApe = crearTextField(450, 72, 188, 28);
		panel.add(textApe);
		textUser = crearTextField(157, 126, 169, 28);
		panel.add(textUser);
		textFechNa = crearTextField(250, 242, 169, 28);
		panel.add(textFechNa);
		textFechIns = crearTextField(250, 290, 169, 28);
		panel.add(textFechIns);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String contraseña = new String(textContra.getPassword());
				String repContraseña = new String(textRContra.getPassword());
				String sentencia = "INSERT INTO clientes(NombreC, Apellido, UsuarioCliente, ContraseñaCliente, repContraseñaCliente, FechaNacimiento, FechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?)";
				String textMal = "Hay errores a la hora de meter los datos en la base de datos";
				String nombre = textNom.getText();
				String apellido = textApe.getText();
				String usuario = textUser.getText();
				String fechNacimiento = textFechNa.getText();
				String fechInscripcion = textFechIns.getText();

				if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()
				        || repContraseña.isEmpty() || fechNacimiento.isEmpty() || fechInscripcion.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Campos Vacios,Porfavor Rellene todos los Campos");
				    return;
				}

				// Verificar si ya existe un usuario con el mismo nombre de usuario
				String verificarUsuarioQuery = "SELECT * FROM clientes WHERE UsuarioCliente = ?";
				try (Connection connection = conexionMYSQL.metodoConexion();
				     PreparedStatement verificarUsuarioStmt = connection.prepareStatement(verificarUsuarioQuery)) {
				    verificarUsuarioStmt.setString(1, usuario);
				    ResultSet resultSet = verificarUsuarioStmt.executeQuery();

				    if (resultSet.next()) {
				        JOptionPane.showMessageDialog(null, "Ya existe un usuario con el mismo nombre de usuario");
				        textUser.setText(""); // Vaciar el campo de usuario
				        return; // Salir del método si ya existe un usuario con el mismo nombre de usuario
				    }

				    // Inserta los datos
				    try (PreparedStatement st = connection.prepareStatement(sentencia)) {
				        st.setString(1, nombre);
				        st.setString(2, apellido);
				        st.setString(3, usuario);
				        st.setString(4, contraseña);
				        st.setString(5, repContraseña);
				        st.setString(6, fechNacimiento);
				        st.setString(7, fechInscripcion);

				        int rowsAffected = st.executeUpdate();

				        if (rowsAffected > 0) {
				            JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente. Volviendo al login...");
				            VentanaLogin obj = new VentanaLogin();
				            obj.setVisible(true);
				            System.out.println(obj);
				            dispose();

				            if (lblPremium.isVisible()) {
				                String sentencia2 = "UPDATE clientes SET Tipo='Premium' WHERE Usuario = ?";
				                try (PreparedStatement st2 = connection.prepareStatement(sentencia2)) {
				                    st2.setString(1, usuario);
				                }
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, textMal);
				        }
				    } catch (SQLException ex) {
				        JOptionPane.showMessageDialog(null, "Error al insertar datos en la base de datos: " + ex.getMessage());
				    }

				    // Valida si la contraseña son iguales
				   if(!contraseña.equals(repContraseña)) {
					   JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden: ");
					   textRContra.setText("");
					   return; //Para qaue no se guarde la contraseña
					    
				   }

				    // Parsea el estilo de como poner la fecha
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    try {
				        Date fechanacimiento = sdf.parse(fechNacimiento);
				        Date fechaInscripcion = sdf.parse(fechInscripcion);
				        System.out.println("Fechas; "+ fechanacimiento +" - "+fechaInscripcion);
				    } catch (ParseException ex) {
				        JOptionPane.showMessageDialog(null, "Error al parsear la fecha: " + ex.getMessage());
				    }

				} catch (SQLException sqlException) {
				    sqlException.printStackTrace();
				}
				
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGuardar.setBounds(136, 487, 162, 53);
		panel.add(btnGuardar);

		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblPremium.setVisible(!lblPremium.isVisible());

			}
		});
		btnPremium.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPremium.setBounds(402, 487, 162, 53);
		panel.add(btnPremium);

		textContra = (JPasswordField) createPasswordField(157, 176, 169, 28);
		panel.add(textContra);
		textRContra = (JPasswordField) createPasswordField(501, 176, 169, 28);
		panel.add(textRContra);

		lblPremium = new JLabel("Cuenta Premium");
	//	lblPremium.setIcon(new ImageIcon("C:\\Users\\in1dm3\\Desktop\\xxx\\Reto4_Grupo7\\src\\Img\\corona.jpg"));
		lblPremium.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPremium.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremium.setBounds(388, 400, 176, 81);
		panel.add(lblPremium);
		lblPremium.setVisible(false);
	}

	/**
	 * Este metodo genera Labels, y despues se le aplica unos valores dependiendo
	 * posicion, horizontal, vertical, anchura y largura
	 */
	private JLabel crearLabel(String text, int x, int y, int width, int i) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(x, y, width, 28);
		return label;
	}

	/**
	 * Este metodo genera TextField, y despues se le aplica unos valores dependiendo
	 * posicion, horizontal, vertical, anchura y largura
	 */
	private JTextField crearTextField(int x, int y, int width, int height) {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(x, y, width, height);
		return textField;
	}

	/**
	 * Este metodo genera para los TextField, pero de contraseñas, y despues se le
	 * aplica unos valores dependiendo posicion, horizontal, vertical, anchura y
	 * largura
	 */
	private JPasswordField createPasswordField(int x, int y, int width, int height) {
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(x, y, width, height);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		return passwordField;
	}


	
}