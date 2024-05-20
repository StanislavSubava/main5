package modelo;

/**
 * Clase para los objetos de canciones, con sus atributos
 */

public class Cancion {
	private int id;
	private String nombre;
	private int duracion;
	private String Tipo;

	public Cancion() {

	}

	public Cancion(String nombre, int duracion) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
	}

	public Cancion(String Nombre, int duracion, String Tipo) {
		super();

		this.nombre = Nombre;
		this.duracion = duracion;
		this.Tipo = Tipo;
	}

	public Cancion(int id, String nombre, int duracion, String Tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.Tipo = Tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String Tipo) {
		this.Tipo = Tipo;
	}


}
