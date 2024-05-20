package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionMYSQL {

	public static Connection metodoConexion() throws SQLException {

		String SERVIDOR_DB = "jdbc:mysql://localhost:33061/";
		String NOMBRE_DB = "grupo7_reto4";
		String USUARIO = "root";
		String CONTRASEÑA = "elorrieta";

		return DriverManager.getConnection(SERVIDOR_DB + NOMBRE_DB, USUARIO, CONTRASEÑA);
		/*
		  String SERVIDOR_DB = "jdbc:mysql://domainreto4.duckdns.org:3306/"; 		  
		  String NOMBRE_DB = "grupo7_reto4"; 
		  String USUARIO = "mikel"; 
		  String CONTRASEÑA ="123";
		 
		 return DriverManager.getConnection(SERVIDOR_DB+NOMBRE_DB,USUARIO,CONTRASEÑA);
	 */
	}

	
}
