package tpmongodb.tp;
//import java.util.ArrayList;

//import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class Palabra {

	private String palabra;
	private Integer cantidad;
	public Palabra() {
	}

	public Palabra(String palabra, Integer cantidad) {
		this.palabra = palabra;
		this.cantidad = cantidad;
	}

	// Transformo un objecto que me da MongoDB a un Objecto Java
	public Palabra(BasicDBObject dBObjectPalabra) {
		this.palabra = dBObjectPalabra.getString("palabra");
		this.cantidad = dBObjectPalabra.getInt("cantidad");
	}

	public BasicDBObject toDBObjectPalabra() {

		// Creamos una instancia BasicDBObject
		BasicDBObject dBObjectPalabra = new BasicDBObject();

		dBObjectPalabra.append("palabra", this.getpalabra());
		dBObjectPalabra.append("cantidad", this.getcantidad());

		return dBObjectPalabra;
	}

	public String getpalabra() {
		return palabra;
	}

	public void setpalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getcantidad() {
		return cantidad;
	}

	public void setcantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	@Override
	public String toString() {
		return "palabra: " + this.getpalabra() + " " + this.getcantidad() + " / cantidad de letras: ";
	}
}
