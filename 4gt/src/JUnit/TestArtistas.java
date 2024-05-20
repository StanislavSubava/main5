package JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import modelo.artistaClase;

class TestArtistas {

	@Test
	void testartistaConstructor() {
		artistaClase artista = new artistaClase();
		assertNull(artista.getIDArtista());
		assertNull(artista.getNombre());
		assertNull(artista.getDescripcion());
		assertNull(artista.getImg());
		assertNull(artista.getCaracteristicas());
	}
	
	@Test
	void testGetterAndSetter() {
		artistaClase artista = new artistaClase();
		
		artista.setIDArtista("");
	    assertEquals("", artista.getIDArtista());

	    artista.setNombre("");
	    assertEquals("", artista.getNombre());

	    artista.setDescripcion("");
	    assertEquals("", artista.getDescripcion());

	    artista.setImg("");
	    assertEquals("", artista.getImg());

	    artista.setCaracteristicas("");
	    assertEquals("", artista.getDescripcion());

	}
	
}
