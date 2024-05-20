package modelo;

public class podcasters {

	String IDPodcasters , NombreArtistico , Imagen , DescripcionPodcasters;

	
	public podcasters(String iDPodcasters, String nombreArtistico, String imagen, String descripcionPodcasters) {
		super();
		IDPodcasters = iDPodcasters;
		NombreArtistico = nombreArtistico;
		Imagen = imagen;
		DescripcionPodcasters = descripcionPodcasters;
	}

	public podcasters() {
		// TODO Auto-generated constructor stub
	}

	public String getIDPodcasters() {
		return IDPodcasters;
	}

	public void setIDPodcasters(String iDPodcasters) {
		IDPodcasters = iDPodcasters;
	}

	public String getNombreArtistico() {
		return NombreArtistico;
	}

	public void setNombreArtistico(String nombreArtistico) {
		NombreArtistico = nombreArtistico;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public String getDescripcionPodcasters() {
		return DescripcionPodcasters;
	}

	public void setDescripcionPodcasters(String descripcionPodcasters) {
		DescripcionPodcasters = descripcionPodcasters;
	}
}
