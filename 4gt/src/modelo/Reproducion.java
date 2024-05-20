package modelo;

/**
 * Clase para los objetos de Reproducion, con sus atributos
 */

public class Reproducion {
	private String IDCliente;
	private String NombreC;
	private String IDAudio;
	private String nombreA;
	private String FechaReproduccion;
	private String VecesReproducida;
	private String Tipo;

	public Reproducion() {

	}

	public Reproducion(String NombreC, String nombreA, String fechaReproduccion, String vecesReproducida, String tipo) {
		super();
		this.NombreC = NombreC;
		this.nombreA = nombreA;
		FechaReproduccion = fechaReproduccion;
		VecesReproducida = vecesReproducida;
		Tipo = tipo;

	}

	public Reproducion(String iDCliente, String iDAudio, String nombreA, String fechaReproduccion,
			String vecesReproducida, String tipo) {
		super();
		IDCliente = iDCliente;
		IDAudio = iDAudio;
		this.nombreA = nombreA;
		FechaReproduccion = fechaReproduccion;
		VecesReproducida = vecesReproducida;
		Tipo = tipo;
	}

	public String getIDCliente() {
		return IDCliente;
	}

	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}

	public String getNombreC() {
		return NombreC;
	}

	public void setNombreC(String NombreC) {
		this.NombreC = NombreC;
	}

	public String getIDAudio() {
		return IDAudio;
	}

	public void setIDAudio(String iDAudio) {
		IDAudio = iDAudio;
	}

	public String getNombreA() {
		return nombreA;
	}

	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}

	public String getFechaReproduccion() {
		return FechaReproduccion;
	}

	public void setFechaReproduccion(String fechaReproduccion) {
		FechaReproduccion = fechaReproduccion;
	}

	public String getVecesReproducida() {
		return VecesReproducida;
	}

	public void setVecesReproducida(String vecesReproducida) {
		VecesReproducida = vecesReproducida;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

}
