package formaDpostgres;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

import java.util.Scanner;

public class ConsultaMovDos {

	Connection conexion = null;
	ResultSet crs = null;
	Statement st = null;

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

			// archivo = new File("C:\\pruebaPostgres.txt");
			// archivo = new File("C:\\movimientos_1000000.txt");
			archivo = new File("C:\\movimientos_10000000.txt");

			try {
				fr = new FileReader(archivo);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			br = new BufferedReader(fr);
			String linea;

			String query = "INSERT INTO movi1millon "
					+ "(id_cliente, monto, tipo_operacion) VALUES"
					+ "(?, ?, ?)";

			PreparedStatement st = conexion.prepareStatement(query);

			while ((linea = br.readLine()) != null) {

				String listaarray[] = linea.split(",");

				st.setString(1, listaarray[0]);
				st.setDouble(2, Double.parseDouble(listaarray[1].toString()));
				st.setString(3, listaarray[2]);

				st.execute();

			}
		}

		Saldos saldos = new Saldos();
		saldos.calcularsaldos(conexion);

		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;

		System.out.println("Tiempo de ejecución: " + totalTime + " segundos");
	}
}
