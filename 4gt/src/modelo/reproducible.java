
package modelo;

public interface reproducible {
    void load(String filePath);
    
	void play(String ruta);
    
    void pause();
    
    void cambiarVelocidad();
}
