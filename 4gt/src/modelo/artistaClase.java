package modelo;

public class artistaClase {
	@Override
	public String toString() {
		return "artistas [IDArtista=" + IDArtista + ", nombre=" + nombre + ", descripcion=" + descripcion + ", img="
				+ img + ", caracteristicas=" + caracteristicas + "]";
	}

	String IDArtista, nombre, descripcion, img, caracteristicas;

	
	public artistaClase(String iDArtista, String nombre, String descripcion, String img, String caracteristicas) {
		super();
		IDArtista = iDArtista;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.img = img;
		this.caracteristicas = caracteristicas;
	}

	
	public String getCaracteristicas() {
		return caracteristicas;
	}


	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}





	public artistaClase() {
		// TODO Auto-generated constructor stub
	}


	public String getIDArtista() {
		return IDArtista;
	}

	public void setIDArtista(String iDArtista) {
		IDArtista = iDArtista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}