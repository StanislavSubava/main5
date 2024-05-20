package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import modelo.canciones;

public class VentanaReprdcPD extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaInfm;
	private JPanel contentPane;
	private JButton btnSiguiente;
	private JButton btnReprPausa;
	private JButton btnAnterior;
	private long clipTimePosition = 0;
	private int newpscionPdcastnSelec;
	private int newpsciondspPdcasSelec;
	private Clip clip;
	private boolean paused = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReprdcPD frame = new VentanaReprdcPD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param podcasts 
	 * @param podcast 
	 */
	
	
	
	public VentanaReprdcPD(canciones podcast, ArrayList<canciones> podcasts) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Ventana ");

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 690, 461);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaPodCasts obj = new VentanaPodCasts();
				obj.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(0, 0, 90, 47);
		panel.add(btnNewButton);

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setIcon(new ImageIcon("img/usuu.jpg"));
		
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

		JLabel lblfotAlbm = new JLabel("New label");
		lblfotAlbm.setBounds(119, 43, 420, 232);
		panel.add(lblfotAlbm);
		
		
		

		textAreaInfm = new JTextArea();
		textAreaInfm.setLineWrap(true);
		textAreaInfm.setBounds(119, 366, 420, 95);
		panel.add(textAreaInfm);

	lblfotAlbm = new JLabel("New label");
	lblfotAlbm.setBounds(119, 43, 420, 232);
	panel.add(lblfotAlbm);

	File audEleg = new File(podcast.getEnlazeAudio());
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
        	    	
        	        if( paused==false) {
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
	        if (newpscionPdcastnSelec > 0 &&  newpscionPdcastnSelec < podcasts.size()) {
	            int cancionAnt = newpscionPdcastnSelec--;
	            JOptionPane.showMessageDialog(null, "Se ha llegado al final de la lista de canciones.");
	            System.out.println(cancionAnt);
	        } else {
	            // Detener la reproducción de la canción actual
	            if (clip != null && clip.isRunning()) {
	                clip.stop();
	                paused = true;
	            }
	            try {
	                // Decrementar el índice de la canción actual
	            	newpscionPdcastnSelec = podcasts.size() - 1;
	                // Obtener la anterior canción
	                canciones pdcastAnterior = podcasts.get(newpscionPdcastnSelec);
	                // Cargar la anterior canción
	                System.out.println(pdcastAnterior);
	                String audioFilePath = pdcastAnterior.getEnlazeAudio().replace(File.separator, "/");
	                System.out.println(audioFilePath);
	                String audio = pdcastAnterior.getEnlazeAudio();
	                playAudio(audio);
	                
	                /**
	                 * File audEleg = new File(cancionSeleccionada.getEnlazeAudio());
                       String audioFilePath = audEleg.getPath(); // Cambiar getAbsolutePath() a getPath()
                       System.out.println(audioFilePath);
                       String audio = audEleg.getPath();
	                 * */
	                // Actualizar la etiqueta de la canción
	                String nmbrAnt = pdcastAnterior.getNombreA();
	                String drcnAnt = pdcastAnterior.getDuracion();
	                String formato = "Nombre de la canción: "  + nmbrAnt +  "Duración del audio: " + drcnAnt; 
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
	        	newpsciondspPdcasSelec++;
	        	if (newpsciondspPdcasSelec > podcasts.size() - 1) {
	                // Mostrar un mensaje antes de volver al inicio
	                JOptionPane.showMessageDialog(null, "Se ha llegado al final de la lista de canciones.");
	                newpsciondspPdcasSelec = 0; // Volver al inicio si llegamos al final
	            }
	            // Obtener la siguiente canción
	            canciones nextCancion = podcasts.get(newpsciondspPdcasSelec);
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
	btnmeGusta.setBounds(474, 301, 65, 40);
	panel.add(btnmeGusta);

	
	
	JButton btnMenu = new JButton("=");
	btnMenu.setBounds(119, 301, 65, 40);
	panel.add(btnMenu);
	
	}
	 
	public VentanaReprdcPD() {
		// TODO Auto-generated constructor stub
	}

	public void mostrarInformacionPd(canciones podcast, ArrayList<canciones> podcasts) {
		// TODO Auto-generated method stub
		System.out.println(podcast);
		String formato1 = "";
		String nmbrPd = podcast.getNombreA();
		String drcnPd = podcast.getDuracion();

		formato1 = "Nombre del Podcast: " + nmbrPd + "\n" + "Duración del PodCast: " + drcnPd;
		textAreaInfm.setText(formato1);


	}

	

	private void playAudio(String audio) throws LineUnavailableException {
		// TODO Auto-generated method stub
	
		try {
			File audioFile = new File(audio);
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
					playAudio(audio);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
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
}
