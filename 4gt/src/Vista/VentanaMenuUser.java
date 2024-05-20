package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMenuUser frame = new VentanaMenuUser();
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
	public VentanaMenuUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Menu User");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 691, 587);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaLogin obj = new VentanaLogin();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(0, 0, 90, 47);
		panel.add(btnNewButton);

		JButton btnPerfil = new JButton("Perfil");
	/*
	 ESTAS 3 Lineas de codigo hacen que el boton se parezca a la foto 
	 */	
		btnPerfil.setIcon(new ImageIcon("img/usuu.png"));
		btnPerfil.setContentAreaFilled(false);
		btnPerfil.setBorder(null);
		
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaPerfil obj = new VentanaPerfil();
				obj.setVisible(true);
				dispose();
			}
		});
		btnPerfil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPerfil.setBounds(618, 0, 90, 58);
		panel.add(btnPerfil);

		JLabel lblNewLabel = new JLabel("Selecciona");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(158, 97, 354, 47);
		panel.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Musica");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaArtistas obj = new VentanaArtistas();
				obj.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(58, 341, 120, 47);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("PodCast");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaPodCasts obj = new VentanaPodCasts();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(255, 341, 162, 47);
		panel.add(btnNewButton_1_1);

		JButton btnPlayList = new JButton("Mi PlayList");
		btnPlayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionPlayList obj = new GestionPlayList();
				obj.setVisible(true);
				dispose();
			}
		});
		btnPlayList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlayList.setBounds(477, 341, 152, 47);
		panel.add(btnPlayList);

		JLabel lblMenuUser = new JLabel("Menu user");
		lblMenuUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMenuUser.setBounds(158, 11, 354, 47);
		panel.add(lblMenuUser);

		JLabel lblfondomenu = new JLabel("");
		lblfondomenu.setIcon(new ImageIcon("fondomenu.jpg"));
		
		lblfondomenu.setBounds(0, 0, 691, 587);
		panel.add(lblfondomenu);
	}

}
