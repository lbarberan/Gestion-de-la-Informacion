package archivos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*public class ConexionBD {

	Connection conexion = null;
	
	
	public void conectarse(){
		
		try {
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "lidia");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ingreso(String script){
		PreparedStatement sentencia = null;
		conectarse();
		try{
		
			sentencia = conexion.prepareStatement(script);
			sentencia.executeUpdate();
			
		}catch(Exception ex){
			
			
		}
		
		
	}
}*/
