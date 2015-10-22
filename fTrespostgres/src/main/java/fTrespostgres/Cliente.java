package fTrespostgres;

public class Cliente {
	String id_cliente;
	Double monto;
	String tipo_operacion;
	
	
	
	public void cliente (){
	}

	public void cliente (String id_cliente, Double monto, String tipo_operacion) {
		this.id_cliente = id_cliente;
		this.monto = monto;
		this.tipo_operacion = tipo_operacion;
		
	}

	
	
	public String getId_cliente() {
		return id_cliente;
	}
	
	public void setId_Cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Double getMonto() {
		return monto;
	}
	
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	public String getTipo_operacion() {
		return tipo_operacion;
	}
	
	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	

}
