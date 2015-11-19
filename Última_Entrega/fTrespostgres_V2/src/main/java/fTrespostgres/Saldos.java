package fTrespostgres;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Saldos {

	public boolean calcularsaldos(Connection conexion) {

		Statement st = null;
		try {
			st = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*String sql = "select id_cliente, round(sum(case when  tipo_operacion ='C' then monto else (monto * (-1)) end)::numeric,2)"
		+ " as saldo into saldosCli1mill from movimientos group by id_cliente;";*/

		/*String sql = "select id_cliente, round(sum(case when  tipo_operacion ='C' then monto else (monto * (-1)) end)::numeric,2)"
				+ " as saldo into saldos10millones from diezmillones group by id_cliente;";*/

		String sql = "select id_cliente, round(sum(case when  tipo_operacion ='C' then monto else (monto * (-1)) end)::numeric,2)"
				+ " as saldo into saldosCli100mill from mov100notefab group by id_cliente;";


		
		
		
		try {
			st.execute(sql);
			return true;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}

}
