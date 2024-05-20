package JUnit;

import org.junit.Test;

import Vista.VentanaLogin;
import Vista.VentanaRegistro;

import static org.junit.Assert.*;

public class TestLogin {

	
	/**
	 * JTest sobre los campos vacios en la ventana Login
	*/
    @Test
    public void testVentanaLogin() {
        VentanaLogin ventanaLogin = new VentanaLogin();
        assertNotNull(ventanaLogin);

        // Verificar que el título de la ventana sea correcto
        assertEquals("Ventana Login", ventanaLogin.getTitle());

        // Verificar que los componentes no sean nulos
        assertNotNull(ventanaLogin.textUser);
        assertNotNull(ventanaLogin.textContra);


    }
    
    @Test
    public void testVentanRegistro() {
    	VentanaRegistro ventanaResgistro = new VentanaRegistro();
        assertNotNull(ventanaResgistro);
        
        // Verificar que el título de la ventana sea correcto
        assertEquals("Nombre", ventanaResgistro.getTitle());

        // Verificar que los componentes no sean nulos
        assertNotNull(ventanaResgistro.textNom);
        assertNotNull(ventanaResgistro.textApe);
        assertNotNull(ventanaResgistro.textUser);
        assertNotNull(ventanaResgistro.textFechNa);
        assertNotNull(ventanaResgistro.textFechIns);
        assertNotNull(ventanaResgistro.textContra);
        assertNotNull(ventanaResgistro.textRContra);
     
    }
    
    
}