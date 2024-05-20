package JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.valorTiempo;

public class TestTiempo {

    @Test
    public void testGetFechaReproduccionNull() {
        valorTiempo valorTiempo = new valorTiempo();
        assertNull(valorTiempo.getFechaReproduccion());
    }

    @Test
    public void testSetFechaReproduccionNull() {
        valorTiempo valorTiempo = new valorTiempo();
        valorTiempo.setFechaReproduccion(null);
        assertNull(valorTiempo.getFechaReproduccion());
    }

}