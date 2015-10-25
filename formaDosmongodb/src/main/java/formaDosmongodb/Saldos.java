package formaDosmongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
import com.mongodb.MapReduceCommand.OutputType;

public class Saldos {

	public void mostrarsaldos(DBCollection colleccion) {
		String map = "function() {" + "	if(this.tipo_operacion == 'D') " + "{"
				+ " saldo = (-1) * this.monto;}" + " else{"
				+ " saldo = this.monto;}" + " {"
				+ " emit(this.id_Cliente,saldo);} }";

		String reduce = "function(keyidCliente, saldos)"
				+ " { return Array.sum(saldos)};";

		MapReduceCommand saldosCli = new MapReduceCommand(colleccion, map,
				reduce, null, OutputType.INLINE, null);
		// llamo a mapReduce

		MapReduceOutput outSaldos = colleccion.mapReduce(saldosCli);

		for (DBObject o : outSaldos.results()) {
			System.out.println(o.toString());
		}
		// TODO Auto-generated method stub

	}

}
