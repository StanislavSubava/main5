package modelo;

import java.sql.Date;

/**
 * Clase para poder ordenar las fechas segun lo que se pida
 */

public class valorTiempo {
	
	 private Date fechaReproduccion;

	    public Date getFechaReproduccion() {
	        return fechaReproduccion;
	    }

	    public void setFechaReproduccion(Date fechaReproduccion) {
	        this.fechaReproduccion = fechaReproduccion;
	    }

	    @Override
	    public String toString() {
	        return "valorTiempo [fechaReproduccion=" + fechaReproduccion + "]";
	    }


}
