package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import modelo.albums;
import modelo.artistaClase;
import modelo.canciones;
import Controlador.Metodos;

public class ArtistaKC extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textAreaDsk;
    private JTextArea textAreaInfm;
    private JLabel lblFoto;
    Metodos metodo = new Metodos();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaArtistas frame = new VentanaArtistas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ArtistaKC(String iDartista2) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("Ventana Artista KC");
        
        
         JPanel panel = new JPanel();
        panel.setBounds(0, 0, 691, 587);
        contentPane.add(panel);

        JButton btnNewButton = new JButton("Atras");
        btnNewButton.setBounds(0, 0, 90, 47);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaArtistas obj = new VentanaArtistas();
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

        textAreaInfm = new JTextArea();
        textAreaInfm.setBounds(338, 77, 326, 162);
        textAreaInfm.setFont(new Font("Mongolian Baiti", Font.PLAIN, 13));
        textAreaInfm.setLineWrap(true);
        textAreaInfm.setRows(8);
        textAreaInfm.setWrapStyleWord(true);
        panel.add(textAreaInfm);

        textAreaDsk = new JTextArea();
        textAreaDsk.setBounds(129, 74, 199, 244);
        panel.add(textAreaDsk);

        lblFoto = new JLabel("");
        lblFoto.setBounds(371, 250, 270, 198);
        panel.add(lblFoto);
        
        // Llamar al método albumsBD de la clase Metodos para crear los botones con las imágenes de los álbumes
        // Reemplaza IDArtista por el ID del artista actual
  
        ArrayList<albums> album = metodo.albumsBD(iDartista2);
        int posX = 50; // Posición X inicial para los botones
        int posY = 80; // Posición Y de los botones
        int separacion = 30; // Separación entre botones

        /**/ System.out.println(album);
        
        for (int i = 0; i < album.size(); i++) {
            albums albumActual = album.get(i);
            String idAlbum = albumActual.getIDAlbum();
            String fotoAlbum = albumActual.getImagen();
           
            fotoAlbum = fotoAlbum.trim();
            JButton botonAlbum = new JButton();
            botonAlbum.setFont(new Font("Tahoma", Font.PLAIN, 14));
            
            botonAlbum.setIcon(new ImageIcon(fotoAlbum));
            botonAlbum.setBounds(posX, posY, 70, 60);
            panel.add(botonAlbum);

            posY += 60 + separacion;
            
            botonAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<canciones> cancion = metodo.obtenerCanciones(idAlbum);
                    AlbumArtista obj = new AlbumArtista(albumActual, cancion);
                    obj.mostrarInfm(cancion ,albumActual);
                    obj.setVisible(true);
                    dispose();
                }
            
            });
        
        }

    }

    public void mostrarInfm(ArrayList<albums> albums, artistaClase artista) {
        String formato1 = "";
        String formato2 = "";
        String img = artista.getImg();
        for (int i = 0; i < albums.size(); i++) {
            albums album = albums.get(i);
            formato1 += " " + album.getTitulo() + "\n" +
                        "Lanzamiento: " + album.getAño() + "\n" +
                        "Género: " + album.getGenero() + "\n\n";
        }

        formato2 += artista.getNombre() + "\n" +
                    artista.getCaracteristicas() + "\n" +
                    "Descripción: \n" + artista.getDescripcion() + "\n\n";

        settextAreaDsk(formato1);
        settextAreaInfm(formato2);
        setlblFoto(img);
    }

    public void settextAreaDsk(String formato1) {
        textAreaDsk.setText(formato1);
    }

    public void settextAreaInfm(String formato2) {
        textAreaInfm.setText(formato2);
    }

   
    	public void setlblFoto(String img) {
    		  try {
    			    ImageIcon imagenIcon = new ImageIcon(img);
    		        Image imagen = imagenIcon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
    		        ImageIcon imagenIcono = new ImageIcon(imagen);
    		        lblFoto.setIcon(imagenIcono);
    		    } catch (Exception e) {
    		        e.printStackTrace();
    		        System.out.println("Error al cargar la imagen: " + e.getMessage());
    		    }

    	}}

