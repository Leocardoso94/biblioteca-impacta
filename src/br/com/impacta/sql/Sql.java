package br.com.impacta.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Sql {
	private final String HOSTNAME = "127.0.0.1";
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DBNAME = "impacta";
	private Connection conn;

	public Sql() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME + "/" + DBNAME, USERNAME, PASSWORD);

	}

	/**Monta a query, insere os valores de cada parâmetro desejado
	 * @param statement	 * 	
	 * @param parameters
	 * @throws SQLException
	 */
	private void setParams(NamedParameterStatement statement, Map<String, String> parameters) throws SQLException {
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			this.bindParam(statement, entry.getKey(), entry.getValue());
		}
	}

	/**Coloca cada parametro na posição correta 
	 * @param statement
	 * @param key
	 * @param value
	 * @throws SQLException
	 */
	private void bindParam(NamedParameterStatement statement, String key, String value) throws SQLException {
		statement.setString(key, value);
	}


	/**Executa um comando no banco de dados, ideal para inserts, deletes e updates
	 * @param rawQuery
	 * 				Consulta desejada
	 * @param params
	 * 				Os parametros da consulta
	 * @throws SQLException
	 */
	public void query(String rawQuery, Map<String, String> params) throws SQLException {
		NamedParameterStatement stmt = new NamedParameterStatement(conn, rawQuery);
		this.setParams(stmt, params);
		stmt.execute();
		stmt.close();
	}

	
	/**Executa uma Consulta na base de dados
	 * @param rawQuery
	 * 				Consulta desejada
	 * @param params
	 * 				Os parametros da consulta
	 * @return
	 * 				Retorna um ResultSet com a consulta utilizada
	 * @throws SQLException
	 */
	public ResultSet select(String rawQuery, Map<String, String> params) throws SQLException {
		NamedParameterStatement stmt = new NamedParameterStatement(conn, rawQuery);
		if(params!=null){
		this.setParams(stmt, params);
		}
		return stmt.executeQuery();
	}

}
