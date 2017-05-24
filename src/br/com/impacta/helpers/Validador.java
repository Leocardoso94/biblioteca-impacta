package br.com.impacta.helpers;

import java.sql.SQLException;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Validador {
	public static boolean deletar(String query, Map<String, String> params) throws SQLException {
		return !new Sql().select(query, params).next();
	}
}
