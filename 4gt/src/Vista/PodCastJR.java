package Vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import modelo.canciones;
import modelo.podcasters;

public class PodCastJR extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtrDscrpcn;
	private JLabel lblNombre;
	private JPanel buttonPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PodCastJR frame = new PodCastJR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param idPodcasters
	 */
	public PodCastJR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana Podcast JR");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 691, 453);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.setBounds(0, 0, 90, 47);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPodCasts obj = new VentanaPodCasts();
				obj.setVisible(true);
				dispose();
			}
		});
		panel.setLayout(null);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnNewButton);

		JButton btnPerfil = new JButton("Perfil");

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

		txtrDscrpcn = new JTextArea();
		txtrDscrpcn.setLineWrap(true);
		txtrDscrpcn.setWrapStyleWord(true);
		txtrDscrpcn.setBounds(353, 94, 259, 262);
		panel.add(txtrDscrpcn);

		lblNombre = new JLabel("label");
		lblNombre.setBounds(141, 17, 431, 112);
		panel.add(lblNombre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 94, 204, 356);
		panel.add(scrollPane);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1)); // Organiza los botones en una columna
		scrollPane.setViewportView(buttonPanel);

	}

	public void mostrarInformacion(ArrayList<canciones> podcasts, podcasters pdcast) {
		// TODO Auto-generated method stub
		String nmbr = pdcast.getNombreArtistico();
		String dscrpcn = pdcast.getDescripcionPodcasters();
		
		String formato1 = "";
		String duracionTotalMinutos = "0";
		System.out.println(duracionTotalMinutos);
		System.out.println(podcasts);
		System.out.println(pdcast);

		for (int i = 0; i < podcasts.size(); i++) {
			canciones podcast = podcasts.get(i);
			String nmbrAud = podcast.getNombreA();
			String drcnAud = podcast.getDuracion();
		

			formato1 += nmbrAud + " - " + drcnAud + "\n\n";

			String[] partesDuracion = drcnAud.split(":");
			int minutos = Integer.parseInt(partesDuracion[0]);
			duracionTotalMinutos += minutos;
			crearBotones(podcast, buttonPanel, formato1, podcasts);
		}

		String formato = "";
		formato += "" + nmbr + "\n\n" + "" + dscrpcn + "\n\n";

		settxtrDscrpcn(formato);
		setlblNombre(nmbr);

	}

	private void crearBotones(canciones podcast, JPanel buttonPanel2, String formato1, ArrayList<canciones> podcasts) {
		// TODO Auto-generated method stub
		String[] lineas = formato1.split("\n\n");
		for (String linea : lineas) {
			JButton button = new JButton(linea);
			button.setPreferredSize(new Dimension(180, 30)); // Establece el tamaño preferido del botón
			button.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Ajusta el tamaño de la fuente del texto en el botón
			buttonPanel.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaReprdcPD obj = new VentanaReprdcPD(podcast, podcasts);
					obj.mostrarInformacionPd(podcast, podcasts);
					obj.setVisible(true);
					dispose();
				}

			});
		}

	}

	private void setlblNombre(String nmbr) {
		// TODO Auto-generated method stub
		lblNombre.setText(nmbr);
	}

	private void settxtrDscrpcn(String formato) {
		// TODO Auto-generated method stub
		txtrDscrpcn.setText(formato);
	}

}