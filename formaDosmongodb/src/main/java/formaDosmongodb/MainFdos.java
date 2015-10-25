package formaDosmongodb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.mongodb.DBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;

public class MainFdos {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		// Se solicita que se ingrese la opción a procesar
		System.out.println("Por favor ingresar la opcion que desea procesar");
		System.out.println(" 1: Carga la BD , 2:  Consulta de Saldos");

		Scanner scanner = new Scanner(System.in);
		String opcion = scanner.nextLine();

		// Paso variable a mongodb y cargar la base
		// PASO 1: Conexi�n al Server de MongoDB Pasandole el host y el puerto

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		// PASO 2: Conexi�n a la base de datos
		DB db = mongoClient.getDB("tp3mongodb");

		// DB db = mongoClient.getDB("tpmongodb");
		// crear colleccion en mongodb
		DBCollection colleccion = db.getCollection("movi1");

		if (opcion == "1") {

			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;

			// archivo = new File("C:\\movimientos_1000000.txt");
			archivo = new File("C:\\movimientos_10000000.txt");

			try {

				fr = new FileReader(archivo);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			br = new BufferedReader(fr);

			// Para que no recalcule con valores de corridas anteriores
			// colleccion.drop();

			String linea;

			Linea listaLinea = null;

			listaLinea = new Linea();

			BulkWriteOperation builder = colleccion
					.initializeUnorderedBulkOperation();

			while ((linea = br.readLine()) != null) {

				String listaarray[] = linea.split(",");

				listaLinea.id_Cliente = listaarray[0];
				listaLinea.monto = Double.parseDouble(listaarray[1].toString());
				listaLinea.tipo_operacion = listaarray[2];

				builder.insert(listaLinea.toDBObjectLinea());

			}

			BulkWriteResult result = builder.execute();
		}
		Saldos saldos = new Saldos();
		saldos.mostrarsaldos(colleccion);

		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;

		System.out.println("Tiempo de ejecución: " + totalTime + " segundos");

	}

}
