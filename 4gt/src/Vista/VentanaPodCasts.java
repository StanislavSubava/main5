package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import modelo.canciones;
import modelo.podcasters;
import Controlador.Metodos;

public class VentanaPodCasts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPodCasts frame = new VentanaPodCasts();
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
	public VentanaPodCasts() {
		Metodos metodo = new Metodos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana Podcast");
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 690, 587);
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
		
		
		
		JLabel lblArtsitas = new JLabel("PodCasters");
		lblArtsitas.setBackground(Color.WHITE);
		lblArtsitas.setFont(new Font("Wide Latin", Font.BOLD, 20));
		lblArtsitas.setBounds(263, 11, 161, 95);
		panel.add(lblArtsitas);
		
		ArrayList<podcasters> podcasters = metodo.obtenerPodCaster();
		  int posX = 0; // Posición X inicial para los botones
	      int posY = 100; // Posición Y de los botones
	      int separacion = 20; // Separación entre botones   
	      for (int i = 0; i < 3; i++) {
	    	  podcasters pdcast = podcasters.get(i);
	    	  String nmbr = pdcast.getNombreArtistico();
	    	  String idPodcasters = pdcast.getIDPodcasters();	    	  
	    	  String img =pdcast.getImagen();
	    	  
	    	  img = img.trim();
	    	  JButton botonPodCaster = new JButton(img);
	    	  botonPodCaster.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    	  botonPodCaster.setBounds(posX,posY,210,200);
	    	  botonPodCaster.setIcon(new ImageIcon(img));
	            panel.add(botonPodCaster);
	            
	            JLabel etiquetaPodCaster = new JLabel(nmbr);
	            etiquetaPodCaster.setFont(new Font("Tahoma", Font.PLAIN, 12));
	            etiquetaPodCaster.setBounds(posX + 50, 320, 150, 20); // Ajusta la posición según tu diseño
	            panel.add(etiquetaPodCaster);
	         
	         posX += 220 + separacion;

	         botonPodCaster.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                ArrayList<canciones> podcasts = metodo.obtenerPodcasts(idPodcasters);
	                	PodCastJR obj = new PodCastJR();
	                    obj.mostrarInformacion(podcasts,pdcast);
	                    obj.setVisible(true);
	                    dispose();
	                }
	            
	            });
	        }    
	    	  
	    	  
	      }
	
	
	
	
		

	}
