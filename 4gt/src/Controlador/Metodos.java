package Controlador;

import java.sql.*;
import java.util.ArrayList;




import javax.swing.JOptionPane;


import modelo.albums;
import modelo.artistaClase;

import modelo.canciones;
import modelo.podcasters;


public class Metodos {

    public  ArrayList<artistaClase> obtenerArtistas() {
        ArrayList<artistaClase> artistas = new ArrayList<artistaClase>();
        try (Connection conn = conexionMYSQL.metodoConexion()) {
            String sql = "SELECT * FROM musicos";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                
            	artistaClase artista = new artistaClase();
               
            	String descripcion = rs.getString("DescripcionMusico");
                String caracteristica = rs.getString("Caracteristica");
                String idmusico = rs.getString("IDMusico");
                String nombre = rs.getString("NombreArtistico");
                String img =rs.getString("Imagen");
               
                artista.setNombre(nombre);
                artista.setCaracteristicas(caracteristica);
                artista.setDescripcion(descripcion);
                artista.setImg(img);
                artista.setIDArtista(idmusico);
                artistas.add(artista);
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
        return artistas;
    }

    public ArrayList<albums> albumsBD(String IDArtista) {
        ArrayList<albums> albums = new ArrayList<albums>();
        String sql = "SELECT a.IDAlbum, a.IDMusico , a.Imagen, a.Titulo, a.Año, a.Genero FROM album a JOIN musicos m ON a.IDMusico = m.IDMusico WHERE m.IDMusico = '"+ IDArtista+"' ";
        try (Connection conn = conexionMYSQL.metodoConexion()) {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                albums album = new albums();
                String idAlbum = rs.getString("IDAlbum");
                String idmusico = rs.getString("IDMusico");
                String imgAlbm = rs.getString("Imagen");
                String nmbrAlbm = rs.getString("Titulo");
                String lanzamiento = rs.getString("Año");
                String genero = rs.getString("Genero");
                album.setImagen(imgAlbm);
                album.setTitulo(nmbrAlbm);
                album.setGenero(genero);
                album.setAño(lanzamiento);
                album.setIDAlbum(idAlbum);
                album.setIDMusico(idmusico);
                albums.add(album);
               
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
        return albums;
    }
    
    

	public ArrayList<canciones> obtenerCanciones(String idAlbum) {
		// TODO Auto-generated method stub
	    ArrayList<canciones> canciones = new ArrayList<canciones>();
	    String sql = "SELECT a.NombreA AS NombreCancion, a.Duracion, c.IDAlbum,  a.EnlazeAudio ,  a.Tipo FROM audios a INNER JOIN canciones c ON a.IDAudio = c.IDAudio WHERE a.Tipo = 'Cancion' AND c.IDAlbum = '"+ idAlbum+"' ";
        try (Connection conn = conexionMYSQL.metodoConexion()) {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
            	canciones cancion = new canciones();
            	String nmbrcncn = rs.getString("NombreCancion");
            	String drcn = rs.getString("Duracion");
            	String idAlbum1 = rs.getString("IDAlbum");
            	String tipo = rs.getString("Tipo");
                String enlazeAud = rs.getString("EnlazeAudio");
            	cancion.setNombreA(nmbrcncn);
            	cancion.setDuracion(drcn);
            	cancion.setIDAlbum(idAlbum1);
            	cancion.setTipo(tipo);
            	cancion.setEnlazeAudio(enlazeAud);
                canciones.add(cancion);
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
		
		return canciones;
	}
	public ArrayList<podcasters> obtenerPodCaster() {
		// TODO Auto-generated method stub
		ArrayList<podcasters> podcasters = new ArrayList<podcasters>();
		String sql ="SELECT * FROM podcaster";
		 try (Connection conn = conexionMYSQL.metodoConexion()) {
	            PreparedStatement st = conn.prepareStatement(sql);
	            ResultSet rs = st.executeQuery();
	            while (rs.next()) {
	            	podcasters podcast = new podcasters();
	            	String idPodcsat = rs.getString("IDPodcaster");
	            	String nmbr = rs.getString("NombreArtistico");
	            	String img = rs.getString("Imagen");
	            	String dscrpcn = rs.getString("DescripcionPodcaster");
	            	podcast.setIDPodcasters(idPodcsat);
	            	podcast.setNombreArtistico(nmbr);
	            	podcast.setImagen(img);
	            	podcast.setDescripcionPodcasters(dscrpcn);
	            	podcasters.add(podcast);
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
			
			return podcasters;
		}

	public ArrayList<canciones> obtenerPodcasts(String idPodcasters) {
	    ArrayList<canciones> podcasts = new ArrayList<canciones>();
	    System.out.println(idPodcasters);
	    String sql = "SELECT a.IDAudio, a.NombreA, a.Duracion, a.Tipo, a.EnlazeAudio " +
	                 "FROM audios a " +
	                 "JOIN podcast p ON a.IDAudio = p.IDAudio " +
	                 "WHERE a.Tipo = 'podcast' AND p.IDPodcaster = ?";
	    try (Connection conn = conexionMYSQL.metodoConexion()) {
	        PreparedStatement st = conn.prepareStatement(sql);
	        st.setString(1, idPodcasters); // Establecer el valor del marcador de posición
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            canciones pdcast = new canciones();
	            pdcast.setIDAudio(rs.getString("IDAudio"));
	            pdcast.setNombreA(rs.getString("NombreA"));
	            pdcast.setDuracion(rs.getString("Duracion"));
	            pdcast.setTipo(rs.getString("Tipo"));
	            pdcast.setEnlazeAudio(rs.getString("EnlazeAudio"));
	            podcasts.add(pdcast);
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
	    return podcasts;
	}

}
	
	
