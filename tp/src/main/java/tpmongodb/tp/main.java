package tpmongodb.tp;

import java.io.IOException;
import java.util.ArrayList;

import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.mongodb.MapReduceCommand;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
import com.mongodb.MapReduceCommand.OutputType;


	public class main {


		public static void main(String[] args) throws IOException {
	    	
	    //creo una lista para almacenar cada palabra formateada entre "" para que mongo la lea 
	    ArrayList<Palabra> palabras = new ArrayList<Palabra>();
	    	
	    //Leer Archivo en Java
	    leertexto a = new leertexto();
	    
	    String s1 = a.leerTxt("d:\\mismongos\\tp\\datainput\\donQuijote.txt");
	        
	    //obtengo cada palabra por posición 
	    String[] colec = s1.split(" ");
	        
	    // recorro el array y formateo cada palabra entre comillas  
	    for(int x=0;x<colec.length;x++) {
	    	int cant =  colec[x].length()  ;
			colec[x] = "\"" + colec[x] + "\""  ;
	    	palabras.add(new Palabra(colec[x],cant));
	    	          
	        }
	        
	    //Paso variable a mongodb y cargar la base
	    // PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
	    MongoClient mongoClient = new MongoClient("localhost", 27017);
	  	
	    // PASO 2: Conexión a la base de datos
	  	DB db = mongoClient.getDB("tpmongodb");
	  	       	
	  	//crear colleccion en mongodb
	  	DBCollection colleccion = db.getCollection("collTextos");

		// PASO 3: CRUD (Create-Read-Update-Delete)
		// PASO 3.1: "CREATE" -> inserto los objetos palabras (o documentos en Mongo) en la coleccion Palabras
		
	  	for (Palabra pal : palabras)
	  		colleccion.insert(pal.toDBObjectPalabra());
	 
	  	// mapReduce para saber la cantidad de palabras con igual longitud
				
	  	String	map = "function() { emit(this.palabra.length, 1);}";

	  	
	  	String  reduce = "function(keyPalabra, values)"
	  			+ " { return Array.sum(values)};";
    
	  	
	  	MapReduceCommand MapReduceCommand = new MapReduceCommand (colleccion, map, reduce, null, OutputType.INLINE, null);

    //com.mongodb.MapReduceCommand mapcmd = null;
	//llamo a mapReduce 
	MapReduceOutput outPalabras = colleccion.mapReduce(MapReduceCommand);

	for (DBObject o : outPalabras.results()) {
  	System.out.println(o.toString());
	
	}
	  	
	}
	}	
  	
	
		   	        
	  		  
	    	