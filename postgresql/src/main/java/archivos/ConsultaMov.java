package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

public class ConsultaMov {

	Connection conexion = null;
	ResultSet resultado;
	Statement st;

	public static void main(String[] args) throws SQLException {

		long start = System.currentTimeMillis();

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		// archivo = new File ("C:\\movimientos_1000000.txt");
		archivo = new File("C:\\movimientos_10000000.txt");
		try {
			fr = new FileReader(archivo);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		br = new BufferedReader(fr);

		String linea;

		Cliente listaLinea = null;

		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres",
					"lidia");
			Statement st = conexion.createStatement();

			while ((linea = br.readLine()) != null) {

				String listaarray[] = linea.split(",");

				String sql = "insert into movicli10 (id_cliente, monto, tipo_operacion) values ('"
						+ listaarray[0]
						+ "' , "
						+ Double.parseDouble(listaarray[1].toString())
						+ ",'"
						+ (listaarray[2].charAt(0)) + "');";

				st.execute(sql);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;

		System.out.println("Tiempo de ejecución: " + totalTime + " segundos");

	}

}
