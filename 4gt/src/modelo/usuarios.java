package modelo;

public class usuarios {
int IDCliente; 
String nombre , apellido , usuario,  fechNacimiento , fechInscripcion, tipo;







public usuarios(int IDCliente, String nombre, String apellido, String usuario, String fechNacimiento,
		String fechInscripcion, String tipo) {
	super();
	this.IDCliente = IDCliente;
	this.nombre = nombre;
	this.apellido = apellido;
	this.usuario = usuario;
	this.fechNacimiento = fechNacimiento;
	this.fechInscripcion = fechInscripcion;
	this.tipo = tipo;
}

public usuarios() {
	// TODO Auto-generated constructor stub
}

public int getIDCliente() {
	return IDCliente;
}

public void setIDCliente(int iDCliente) {
	IDCliente = iDCliente;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellido() {
	return apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public String getFechNacimiento() {
	return fechNacimiento;
}

public void setFechNacimiento(String fechNacimiento) {
	this.fechNacimiento = fechNacimiento;
}

public String getFechInscripcion() {
	return fechInscripcion;
}

public void setFechInscripcion(String fechInscripcion) {
	this.fechInscripcion = fechInscripcion;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}



}

