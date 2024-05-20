package JUnit;



import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import modelo.Cancion;

public class TestCancion {

	@Test
	void testarcancionConstructor() {
		Cancion cancion = new Cancion();
		assertNull(cancion.getId());

	}
	
	@Test
	void testGetterAndSetter() {
		Cancion cancion = new Cancion();
		
		cancion.setId(0);
		assertNull(cancion.getId());
		

	}

}
