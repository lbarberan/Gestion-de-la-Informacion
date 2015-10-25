package mongodbtp3;

import com.mongodb.BasicDBObject;

public class Linea {

	String id_Cliente;
	Double monto;
	String tipo_operacion;

	public Linea() {
	}

	public Linea(String id_Cliente, Double monto, String tipo_operacion) {
		this.id_Cliente = id_Cliente;
		this.monto = monto;
		this.tipo_operacion = tipo_operacion;

	}

	// Transformo un objecto que me da MongoDB a un Objecto Java
	public Linea(BasicDBObject dBObjectLinea) {
		this.id_Cliente = dBObjectLinea.getString("id_cliente");
		this.monto = dBObjectLinea.getDouble("monto");
		this.tipo_operacion = dBObjectLinea.getString(tipo_operacion);
	}

	public BasicDBObject toDBObjectLinea() {

		// Creo una instancia BasicDBObject
		BasicDBObject dBObjectLinea = new BasicDBObject();

		dBObjectLinea.append("id_Cliente", this.getId_Cliente());
		dBObjectLinea.append("monto", this.getMonto());
		dBObjectLinea.append("tipo_operacion", this.getTipo_operacion());

		return dBObjectLinea;
	}

	public String getTipo_operacion() {
		return tipo_operacion;
	}

	public String getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(String id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}

	/*
	 * 
	 * @Override public String toString() { return "palabra: " +
	 * this.getpalabra() + " " + this.getcantidad() + " / cantidad de letras: ";
	 * } }
	 */

}
