package saldosPorCli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Saldos {

	Connection conexion = null;
	ResultSet crs = null;
	Statement st = null;
	
	
	public static void main(String[] args) throws SQLException, IOException {
	
		
		long start = System.currentTimeMillis();
		
				
		Object [] linea = new Object[2];
		
		
		Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "lidia");
		
		Statement st = conexion.createStatement();
		
		//String sqlDebitos =  "select id_cliente, round(sum(case when  tipo_operacion ='C' then monto else (monto * (-1)) end)::numeric,2) as saldo into saldosporCli from movimi group by id_cliente;";
		String sqlDebitos =  "select id_cliente, round(sum(case when  tipo_operacion ='C' then monto else (monto * (-1)) end)::numeric,2) as saldo into saldosporCli10 from movimientos10tres group by id_cliente;";
		
		ResultSet crs = st.executeQuery(sqlDebitos);
 
		/*System.out.println("---------Listado de Saldos por Cliente-----------" );
		
		System.out.println("-----id_Cliente -------------------- Monto------" );
		while (crs.next()) {
         
		//	list.add(map(crs));
			
		//	System.out.println(map(crs));
			 for (int i=0;i<2;i++){
				 
				 linea[i] = crs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
			 	
				
			 }
			 System.out.println(crs.getObject(1) + " " + crs.getObject(2));
		}*/
        
                
        
		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;
		
	    System.out.println("Tiempo de ejecución: " + totalTime + " segundos");

		}
}
	
