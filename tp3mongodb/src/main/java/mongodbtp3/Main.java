package mongodbtp3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand.OutputType;

public class Main {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		// archivo = new File ("C:\\pruebaPostgres.txt");
		archivo = new File("C:\\movimientos_1000000.txt");
		// archivo = new File ("C:\\movimientos_10000000.txt");

		// LeerTexto a = new LeerTexto();
		ArrayList<Linea> listarray = new ArrayList<Linea>();
		try {
			fr = new FileReader(archivo);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		br = new BufferedReader(fr);
		// Paso variable a mongodb y cargar la base
		// PASO 1: Conexi�n al Server de MongoDB Pasandole el host y el puerto

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		// PASO 2: Conexi�n a la base de datos
		DB db = mongoClient.getDB("tp3mongodb");

		// DB db = mongoClient.getDB("tpmongodb");
		// crear colleccion en mongodb
		DBCollection colleccion = db.getCollection("movi1");

		// Para que no recalcule con valores de corridas anteriores
		colleccion.drop();
		;

		String linea;
		Linea listaLinea = null;
		listaLinea = new Linea();

		while ((linea = br.readLine()) != null) {
			String listaarray[] = linea.split(",");

			listaLinea.id_Cliente = listaarray[0];
			listaLinea.monto = Double.parseDouble(listaarray[1].toString());
			listaLinea.tipo_operacion = listaarray[2];

			colleccion.insert(listaLinea.toDBObjectLinea());

		}

		long time_end = System.currentTimeMillis();
		long totalTime = (time_end - start) / 1000;

		System.out.println("Tiempo de ejecución: " + totalTime + " segundos");

	}
}
