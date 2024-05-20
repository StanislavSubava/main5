package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import modelo.albums;

import modelo.canciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class AlbumArtista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblftoAlbm;
	private JTextArea textAreaInfmAlbm;
	private JTextArea textAreaNmbr;
	private JPanel panel;
	private JPanel buttonPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbumArtista frame = new AlbumArtista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlbumArtista(albums albumActual, ArrayList<canciones> cancion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Album Artista");

		panel = new JPanel();
		panel.setBounds(0, 0, 690, 461);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAlbum = new JLabel("");
		lblAlbum.setBackground(Color.WHITE);
		lblAlbum.setFont(new Font("Wide Latin", Font.BOLD, 20));
		lblAlbum.setBounds(84, 11, 309, 26);
		panel.add(lblAlbum);

		lblftoAlbm = new JLabel("");
		lblftoAlbm.setBounds(331, 204, 318, 246);
		panel.add(lblftoAlbm);

		textAreaInfmAlbm = new JTextArea();
		textAreaInfmAlbm.setLineWrap(true);
		textAreaInfmAlbm.setBounds(369, 77, 251, 108);
		panel.add(textAreaInfmAlbm);

		textAreaNmbr = new JTextArea();
		textAreaNmbr.setBounds(20, 63, 227, 22);
		panel.add(textAreaNmbr);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaArtistas obj = new VentanaArtistas();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(0, 0, 90, 47);
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 94, 204, 356);
		panel.add(scrollPane);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1)); // Organiza los botones en una columna
		scrollPane.setViewportView(buttonPanel);

	}

	public AlbumArtista() {
		// TODO Auto-generated constructor stub
	}

	public void setlblftoAlbm(String imgAlbm) {
		// TODO Auto-generated method stub

	}

	public void mostrarInfm(ArrayList<canciones> canciones, albums albumActual) {
		String nombre = "";
		String formato1 = "";
		String formato2 = "";
		int cantidadCanciones = canciones.size();
		String lanzamiento = albumActual.getAño();

		int duracionTotalMinutos = 0;
		nombre += albumActual.getTitulo();

		for (int i = 0; i < cantidadCanciones; i++) {
			canciones cancion = canciones.get(i);
			String nombreCancion = cancion.getNombreA();
			String duracionCancion = cancion.getDuracion();

			formato1 += nombreCancion + " - " + duracionCancion + "\n\n";

			// Actualizar la duración total
			String[] partesDuracion = duracionCancion.split(":");
			int minutos = Integer.parseInt(partesDuracion[0]);
			duracionTotalMinutos += minutos;
		}

		formato2 += "Lanzamiento: " + lanzamiento + "\n\n" + "Cantidad de Canciones: " + cantidadCanciones + "\n\n"
				+ "Duración del Álbum: " + duracionTotalMinutos + " minutos\n\n";

		// Mostrar la información en los componentes de la ventana
		settextAreaNmbr(nombre);
		settextAreaInfmAlbm(formato2);
		crearBotones(buttonPanel, formato1, albumActual, canciones);
		// Crear un JTextArea para mostrar el detalle de las canciones

	}

	private void settextAreaNmbr(String nombre) {
		// TODO Auto-generated method stub
		textAreaNmbr.setText(nombre);
	}

	private void settextAreaInfmAlbm(String formato2) {
		// TODO Auto-generated method stub
		textAreaInfmAlbm.setText(formato2);

	}

	/**
	 * 
	 * @param buttonPanel
	 * @param formato1
	 * @param albumActual
	 * @param listaCanciones
	 */
	private void crearBotones(JPanel buttonPanel, String formato1, albums albumActual,
			ArrayList<canciones> listaCanciones) {
		String[] lineas = formato1.split("\n\n");
		int i = 0;

		for (String linea : lineas) {
			JButton button = new JButton(linea);
			button.setPreferredSize(new Dimension(180, 30)); // Establece el tamaño preferido del botón
			button.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Ajusta el tamaño de la fuente del texto en el botón
			button.setToolTipText(String.valueOf(i));
			buttonPanel.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton botonPresionado = (JButton) e.getSource();
					// Tomo el nombre del boton que tiene la canción y la duración
					String nombreCancion = botonPresionado.getText();
					// Convertimos la posicion en un int
					int pscionCancionSelec = Integer.parseInt(button.getToolTipText());

					System.out.println("LA Posicion" + pscionCancionSelec);
					// Busco el Guión para quedarme solo con el nombre de la canción
					nombreCancion = nombreCancion.substring(0, nombreCancion.indexOf("-"));
					nombreCancion = nombreCancion.trim();

					// Ahora busco la canción en la LIsta de objetos, y se la asigno al objeto
					// cancionSeleccionada
					canciones cancionSeleccionada = null;
					for (canciones cancion : listaCanciones) {
						if (cancion.getNombreA().equals(nombreCancion)) {
							cancionSeleccionada = cancion;
							break;
						}
					}

					// EN caso de no encontrar la canción, controlo el error
					if (cancionSeleccionada != null) {
						
						
						VentanaReprdc obj = new VentanaReprdc(cancionSeleccionada, pscionCancionSelec, listaCanciones );
						obj.mostrarInformacion(albumActual, cancionSeleccionada);
						obj.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "La canción seleccionada no se encontró en la lista.");
					}
				}
				/* fin del action */ }); /* fin del listener */
			i++;
		}

	}
}
