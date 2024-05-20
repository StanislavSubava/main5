// TestAlbums.java
package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.albums;

class TestAlbums {

	@Test
	void testAlbumConstructor() {
		albums album = new albums();
		assertNull(album.getIDAlbum());
		assertNull(album.getIDMusico());
		assertNull(album.getTitulo());
		assertNull(album.getAño());
		assertNull(album.getGenero());
		assertNull(album.getImagen());
	}
	
	@Test
	void testGetterAndSetter() {
	    albums album = new albums();

	    album.setIDAlbum("");
	    assertEquals("", album.getIDAlbum());

	    album.setIDMusico("");
	    assertEquals("", album.getIDMusico());

	    album.setTitulo("");
	    assertEquals("", album.getTitulo());

	    album.setAño("");
	    assertEquals("", album.getAño());

	    album.setGenero("");
	    assertEquals("", album.getGenero());

	    album.setImagen("");
	    assertEquals("", album.getImagen());
	}

	@Test
	void testAlbumConstructorLleno() {
		albums album = new albums("1", "2", "Title", "2020", "Rock", "image.jpg");
		assertEquals("1", album.getIDAlbum());
		assertEquals("2", album.getIDMusico());
		assertEquals("Title", album.getTitulo());
		assertEquals("2020", album.getAño());
		assertEquals("Rock", album.getGenero());
		assertEquals("image.jpg", album.getImagen());

		album.setIDAlbum("1");
		assertEquals("1", album.getIDAlbum());

		album.setIDMusico("2");
		assertEquals("2", album.getIDMusico());

		album.setTitulo("Title");
		assertEquals("Title", album.getTitulo());

		album.setAño("2020");
		assertEquals("2020", album.getAño());

		album.setGenero("Rock");
		assertEquals("Rock", album.getGenero());

		album.setImagen("image.jpg");
		assertEquals("image.jpg", album.getImagen());
		
		
	}

}