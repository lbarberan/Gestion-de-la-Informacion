package fTrespostgres;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class MoviTresCliente {

	Connection conexion = null;

	public static void main(String[] args) throws Exception {

		// Se solicita que se ingrese la opción a procesar
				System.out.println("Por favor ingresar la opcion que desea procesar");
				System.out.println(" 1: Carga la BD , 2:  Consulta de Saldos");
				Scanner scanner = new Scanner(System.in);
				String opcion = scanner.nextLine();

				Connection conexion = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/postgres", "postgres",
						"lidia");
		
		long start = System.currentTimeMillis();

		
		if (opcion == "1") {			
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		// archivo = new File ("C:\\movimientos_1000000.txt");
		//archivo = new File("C:\\movimientos_10000000.txt");
		archivo = new File("C:\\movimientos-100000000.txt");
		
		fr = new FileReader(archivo);

		br = new BufferedReader(fr);
		String linea;

		int ejecucionOk = 0;


		String query = "INSERT INTO diezmillones "
				+ "(id_cliente, monto, tipo_operacion) VALUES" + "(?, ?, ?)";

		PreparedStatement st = conexion.prepareStatement(query);

		int batchSize = 1000000;
		int count = 0;

		while ((linea = br.readLine()) != null) {

			String listaarray[] = linea.split(",");

			st.setString(1, listaarray[0]);
			st.setDouble(2, Double.parseDouble(listaarray[1].toString()));
			st.setString(3, listaarray[2]);
			st.addBatch();

			if (++count % batchSize == 0) {
				st.executeBatch();

			}

			st.executeBatch();
		}
		}
		Saldos saldos = new Saldos();
		saldos.calcularsaldos(conexion);

		
		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;

		System.out.println("Tiempo de ejecución: " + totalTime + " segundos");
	}
}
