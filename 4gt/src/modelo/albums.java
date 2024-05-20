package modelo;

public class albums {
	String IDAlbum, IDMusico;
	String Titulo;
	String Año;
	String Genero;
	String Imagen;

	public albums(String iDAlbum, String iDMusico, String titulo, String año, String genero, String imagen) {
		super();
		IDAlbum = iDAlbum;
		IDMusico = iDMusico;
		Titulo = titulo;
		Año = año;
		Genero = genero;
		Imagen = imagen;
	}

	public albums() {
		// TODO Auto-generated constructor stub
	}

	public String getIDAlbum() {
		return IDAlbum;
	}

	public void setIDAlbum(String iDAlbum) {
		IDAlbum = iDAlbum;
	}

	public String getIDMusico() {
		return IDMusico;
	}

	public void setIDMusico(String iDMusico) {
		IDMusico = iDMusico;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getAño() {
		return Año;
	}

	public void setAño(String año) {
		Año = año;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

}