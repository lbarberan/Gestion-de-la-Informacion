package formaDpostgres;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultaMovDos {
	
	Connection conexion = null;
	
	public static void main(String[] args) throws Exception {
		
		long start = System.currentTimeMillis();
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		//archivo = new File ("C:\\pruebaPostgres.txt");
		//archivo = new File ("C:\\movimientos_1000000.txt");
		archivo = new File ("C:\\movimientos_10000000.txt");
		
		try {
			fr = new FileReader (archivo);
				
		} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		br = new BufferedReader(fr);
		String linea;
		Cliente cliente = null;
		int ejecucionOk = 0;
		
		Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "lidia");
		
			 String query = "INSERT INTO movimi10 "
					 + "(id_cliente, monto, tipo_operacion) VALUES" 
					 + "(?, ?, ?)";
			
		PreparedStatement st = conexion.prepareStatement(query);	
			
		while ((linea = br.readLine())!= null) {

			String listaarray[] = linea.split(",");
	
			
			st.setString(1, listaarray[0]);
			st.setDouble(2, Double.parseDouble(listaarray[1].toString()));
			st.setString(3, listaarray[2]);
			
			ejecucionOk = st.executeUpdate();
						
				if(ejecucionOk != 1){
					throw new Exception("Exception ejecucionOk Alta OK");
				}
			
			}	
		
			
		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;
		
	    System.out.println("Tiempo de ejecución: " + totalTime + " segundos");
			 }
	}

//System.out.println("Tiempo de ejecución: " + totalTime + " segundos");
//System.out.println("pasa por aca: " + query);
