package tpmongodb.tp;
import java.io.*;
import com.google.gson.Gson; 

public class leertexto {
    
	    public String leerTxt(String direccion){ //direccion del archivo
	        
	        String texto = "";
	        
	        try{
	            BufferedReader bf = new BufferedReader(new FileReader(direccion));
	            String temp = "";
	            String bfRead;
	            while((bfRead = bf.readLine()) != null){ 
	                
	            	//ciclo, mientras bfRead tiene datos
	                temp = temp + bfRead; //guardado el texto del archivo
	            }
	            
	            texto = temp;
	            
	        }catch(Exception e){ 
	            System.err.println("No se encontro archivo");
	        }
	        
	        return texto;
	        
	    }

	    
	        public static void convertirjson(String cadena) throws java.io.IOException {
	            Gson gson = new Gson();
	            String datos = cadena;
	            String jsonString = gson.toJson(datos);
	            System.out.println("JSON: " + jsonString);        
	     
	    }
	    
	    
	}


	
