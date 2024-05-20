package modelo;

import javax.sound.sampled.Clip;

public class canciones implements reproducible {

	String IDAudio, IDAlbum, NombreA, Duracion, Tipo, EnlazeAudio;
	Clip audio; // Variable para el clip de audio
	long clipTimePosition; // Variable para almacenar la posición de reproducción cuando se pausa
	boolean paused;

	public canciones(String iDAudio, String iDAlbum, String nombreA, String duracion, String tipo, String EnlazeAudio) {
		super();
		IDAudio = iDAudio;
		IDAlbum = iDAlbum;
		NombreA = nombreA;
		Duracion = duracion;
		Tipo = tipo;
		this.EnlazeAudio = EnlazeAudio;
	}

	public canciones(String iDAudio, String nombreA, String duracion, String tipo, String EnlazeAudio) {
		IDAudio = iDAudio;
		NombreA = nombreA;
		Duracion = duracion;
		Tipo = tipo;
		this.EnlazeAudio = EnlazeAudio;
	}

	public canciones(String nombreA) {
		NombreA = nombreA; 
	}

	public canciones() {
		
	}
	
	
	public String getIDAudio() {
		return IDAudio;
	}

	public void setIDAudio(String iDAudio) {
		IDAudio = iDAudio;
	}

	public String getIDAlbum() {
		return IDAlbum;
	}

	public void setIDAlbum(String iDAlbum) {
		IDAlbum = iDAlbum;
	}

	public String getNombreA() {
		return NombreA;
	}

	public void setNombreA(String nombre) {
		NombreA = nombre;
	}

	public String getDuracion() {
		return Duracion;
	}

	public void setDuracion(String duracion) {
		Duracion = duracion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getEnlazeAudio() {
		return EnlazeAudio;
	}

	public void setEnlazeAudio(String EnlazeAudio) {
		this.EnlazeAudio = EnlazeAudio;
	}

	public static canciones get(int i) {
		return null;
	}

	public void add(canciones pdcast) {
	}

	@Override
	public void play(String ruta) {
		/**
		 * try { InputStream audioInputStream = getClass().getResourceAsStream(ruta); if
		 * (audioInputStream == null) { System.err.println("No se encuentra el archivo
		 * de audio: " + ruta + " reproduciendo AUC121.wav"); audioInputStream =
		 * getClass().getResourceAsStream("/audio/AUC121.wav"); if (audioInputStream ==
		 * null) { System.err.println("No se pudo encontrar el archivo de respaldo:
		 * AUC121.wav"); return; } } AudioInputStream ais =
		 * AudioSystem.getAudioInputStream(new BufferedInputStream(audioInputStream));
		 * audio = AudioSystem.getClip(); audio.open(ais); audio.start(); } catch
		 * (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		 * e.printStackTrace(); }
		 */
	}

	@Override
	public void pause() {
		/**
		 * if (audio != null && audio.isRunning()) { paused = true; clipTimePosition =
		 * audio.getMicrosecondPosition(); audio.stop(); }
		 */
	}

	@Override
	public void cambiarVelocidad() {
		// Método no implementado aún
	}

	@Override
	public void load(String filePath) {
		// TODO Auto-generated method stub

	}
}
