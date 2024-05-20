package Vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.exportacion;
import modelo.albums;
import modelo.canciones;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

public class VentanaReprdc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAnterior;
	private JTextArea textAreaInfm;
	private Clip clip;
	private boolean paused = false;
	private JButton btnSiguiente;
	private JButton btnReprPausa;
	private long clipTimePosition = 0;
	private int newpscionCancionSelec;
	private int newpsciondspCancionSelec;
	private JLabel lblfotAlbm;
	private JButton exportar, añadirPlayList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReprdc frame = new VentanaReprdc();
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
	 * @param cancionSeleccionada
	 */
	public VentanaReprdc(canciones cancionSeleccionada, int pscionCancionSelec, ArrayList<canciones> listaCanciones) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 690, 461);
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

		lblfotAlbm = new JLabel("new label");
		lblfotAlbm.setBounds(119, 43, 420, 232);
		panel.add(lblfotAlbm);

		File audEleg = new File(cancionSeleccionada.getEnlazeAudio());
		String audioFilePath = audEleg.getAbsolutePath().replace(File.separator, "/");
		System.out.println(audioFilePath);
		String audio = audEleg.getAbsolutePath();

		btnReprPausa = new JButton("[]|>");
		btnReprPausa.setBounds(293, 301, 65, 40);
		btnReprPausa.addActionListener(e -> {
			if (clip == null) {
				System.out.println("Clip inicio");
				try {
					playAudio(audio);
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println(paused);

				if (paused == false) {
					clipTimePosition = clip.getMicrosecondPosition();
					System.out.println("Pausa");
					clip.stop();
					paused = true;
					btnReprPausa.setText("[]|>");
				} else {
					clip.setMicrosecondPosition(clipTimePosition);
					clip.start();
					paused = false;
					btnReprPausa.setText("▌▌");
				}
			}
		});
		panel.add(btnReprPausa);

		btnAnterior = new JButton("<");
		btnAnterior.setBounds(200, 301, 65, 40);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newpscionCancionSelec > 0 && newpscionCancionSelec < listaCanciones.size()) {
					int cancionAnt = newpscionCancionSelec--;
					JOptionPane.showMessageDialog(null, "Se ha llegado al final de la lista de canciones.");
					System.out.println(cancionAnt);
				} else {
					// Detener la reproducción de la canción actual
					if (clip != null && clip.isRunning()) {
						clip.stop();
						paused = false;
					}
					try {
						// Decrementar el índice de la canción actual
						newpscionCancionSelec = listaCanciones.size() - 1;
						// Obtener la anterior canción
						canciones cancionAnterior = listaCanciones.get(newpscionCancionSelec);
						// Cargar la anterior canción
						System.out.println(cancionAnterior);
						String audioFilePath = cancionAnterior.getEnlazeAudio().replace(File.separator, "/");
						System.out.println(audioFilePath);
						String audio = cancionAnterior.getEnlazeAudio();
						playAudio(audio);

						/**
						 * File audEleg = new File(cancionSeleccionada.getEnlazeAudio()); String
						 * audioFilePath = audEleg.getPath(); // Cambiar getAbsolutePath() a getPath()
						 * System.out.println(audioFilePath); String audio = audEleg.getPath();
						 */
						// Actualizar la etiqueta de la canción
						String nmbrAnt = cancionAnterior.getNombreA();
						String drcnAnt = cancionAnterior.getDuracion();
						String formato = "Nombre de la canción: " + nmbrAnt + "Duración del audio: " + drcnAnt;
						textAreaInfm.setText(formato);
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnAnterior);
		panel.add(btnAnterior);

		btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(379, 301, 65, 40);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Detener la reproducción de la canción actual
				if (clip != null && clip.isRunning()) {
					clip.stop();
					paused = true;
				}
				try {
					// Incrementar el índice de la canción actual
					newpsciondspCancionSelec++;
					if (newpsciondspCancionSelec > listaCanciones.size() - 1) {
						// Mostrar un mensaje antes de volver al inicio
						JOptionPane.showMessageDialog(null, "Se ha llegado al final de la lista de canciones.");
						newpsciondspCancionSelec = 0; // Volver al inicio si llegamos al final
					}
					// Obtener la siguiente canción
					canciones nextCancion = listaCanciones.get(newpsciondspCancionSelec);
					// Cargar la siguiente canción
					String audioFilePath = nextCancion.getEnlazeAudio().replace(File.separator, "/");
					System.out.println(audioFilePath);
					String audio = nextCancion.getEnlazeAudio();
					playAudio(audio);
					// Actualizar la etiqueta de la canción
					String nmbrAnt = nextCancion.getNombreA();

					textAreaInfm.setText(nmbrAnt);
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnSiguiente);

		
		
		JButton btnmeGusta = new JButton("♥");
		btnmeGusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	ArrayList<listPlayList> playlist = new ArrayList<listPlayList>();
				playList canciones = new playList();
				String nombre = canciones.getNombre();

				playlist.add(nombre);
				
				System.out.println(nombre);
				*/
				
			}
		});
		btnmeGusta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnmeGusta.setBounds(474, 301, 65, 40);
		panel.add(btnmeGusta);

		JButton btnMenu = new JButton("=");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportar.setVisible(true);
				añadirPlayList.setVisible(true);
			}
		});
		btnMenu.setBounds(119, 301, 65, 40);
		panel.add(btnMenu);

		exportar = new JButton("Exportar");
		exportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				canciones cancion = new canciones(audio);
				String nombre = cancion.getNombreA();

				String localizacion = exportacion.exportarRuta();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String tiempo = sdf.format(new Date());

				String nombreArchivo = "CancionFavorita_" + tiempo + ".txt";
				File file = new File(localizacion, nombreArchivo);

				try (FileWriter lector = new FileWriter(file)) {
					lector.write(nombre);
					JOptionPane.showMessageDialog(null, "Se ha cargado bien el archivo!");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
				}
			}

		});
		exportar.setBounds(20, 301, 100, 40);
		exportar.setVisible(false);
		panel.add(exportar);

		añadirPlayList = new JButton("Añadir a la Playlist");
		añadirPlayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		añadirPlayList.setBounds(20, 250, 170, 40);
		añadirPlayList.setVisible(false);
		panel.add(añadirPlayList);

		textAreaInfm = new JTextArea();
		textAreaInfm.setLineWrap(true);
		textAreaInfm.setBounds(119, 366, 420, 95);
		panel.add(textAreaInfm);

	}

	private void playAudio(String audEleg) throws LineUnavailableException {
		try {
			File audioFile = new File(audEleg);
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
			System.out.println(audioFile);

			if (clip != null) {
				clip.close();
			}

			// Create a Clip to play the audio
			clip = AudioSystem.getClip();
			clip.open(ais);

			// Start playing the audio
			clip.start();
			paused = false;
			btnReprPausa.setText("▌▌");
			System.out.println(paused);
		} catch (IOException | UnsupportedAudioFileException e) {
			System.err.println("Error playing audio: " + e.getMessage());
		}

		btnReprPausa.addActionListener(e -> {
			if (clip == null) {
				try {
					playAudio(audEleg);
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
			} else {
				if (clip.isRunning()) {
					clipTimePosition = clip.getMicrosecondPosition();
					clip.stop();
					paused = false;
					btnReprPausa.setText("[]|>"); // Cambia el texto del botón a reproducir
				} else {
					clip.setMicrosecondPosition(clipTimePosition);
					clip.start();
					paused = true;
					btnReprPausa.setText("▌▌"); // Cambia el texto del botón a pausa
				}
			}
		});

	}

	public VentanaReprdc(canciones podcast, ArrayList<canciones> podcasts) {
		// TODO Auto-generated constructor stub
	}

	public VentanaReprdc() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Mostrar información de CANCIONES
	 * 
	 * @param albumActual
	 * @param cancionSeleccionada
	 * @param img
	 */
	public void mostrarInformacion(albums albumActual, canciones cancionSeleccionada) {
		// TODO Auto-generated method stub
		System.out.println(cancionSeleccionada);
		String formato = "";

		String nmbrAud = cancionSeleccionada.getNombreA();
		String nmbrAlbm = albumActual.getTitulo();
		String drcnAud = cancionSeleccionada.getDuracion();

		formato += "Nombre de la canción: " + nmbrAud + "\n" + "Nombre del álbum: " + nmbrAlbm + "\n"
				+ "Duración del audio: " + drcnAud;

		textAreaInfm.setText(formato);

	}

}
