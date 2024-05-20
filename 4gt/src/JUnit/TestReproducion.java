package JUnit;

import org.junit.Test;

import modelo.Reproducion;

import static org.junit.Assert.*;

public class TestReproducion {

    @Test
    public void testEmptyConstructor() {
        Reproducion reproducion = new Reproducion();
        assertNull(reproducion.getIDCliente());
        assertNull(reproducion.getNombreC());
        assertNull(reproducion.getIDAudio());
        assertNull(reproducion.getNombreA());
        assertNull(reproducion.getFechaReproduccion());
        assertNull(reproducion.getVecesReproducida());
        assertNull(reproducion.getTipo());
    }

    @Test
    public void testConstructorWithParams() {
        Reproducion reproducion = new Reproducion("Cliente1", "Audio1", "nombreA", "2022-01-01", "5", "Tipo1");
        assertEquals("Cliente1", reproducion.getIDCliente());
        assertEquals("Audio1", reproducion.getIDAudio());
        assertEquals("nombreA", reproducion.getNombreA());
        assertEquals("2022-01-01", reproducion.getFechaReproduccion());
        assertEquals("5", reproducion.getVecesReproducida());
        assertEquals("Tipo1", reproducion.getTipo());
    }

    @Test
    public void testSetters() {
        Reproducion reproducion = new Reproducion();
        reproducion.setIDCliente("Cliente2");
        reproducion.setNombreC("NombreC2");
        reproducion.setIDAudio("Audio2");
        reproducion.setNombreA("nombreA2");
        reproducion.setFechaReproduccion("2022-01-02");
        reproducion.setVecesReproducida("10");
        reproducion.setTipo("Tipo2");
        assertEquals("Cliente2", reproducion.getIDCliente());
        assertEquals("NombreC2", reproducion.getNombreC());
        assertEquals("Audio2", reproducion.getIDAudio());
        assertEquals("nombreA2", reproducion.getNombreA());
        assertEquals("2022-01-02", reproducion.getFechaReproduccion());
        assertEquals("10", reproducion.getVecesReproducida());
        assertEquals("Tipo2", reproducion.getTipo());
    }
}