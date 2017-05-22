package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Autor implements Crud {
	private long idautor;
	private String nome_autor;
	private ArrayList<Obra> obras = new ArrayList<>();
	private Map<String, String> params = new HashMap<>();

	public void addObra(Obra obra) {
		this.obras.add(obra);
	}

	public ArrayList<Obra> getObras() {
		return this.obras;
	}

	public long getIdautor() {
		return idautor;
	}

	public void setIdautor(long idautor) {
		this.idautor = idautor;
	}

	public String getNome_autor() {
		return nome_autor;
	}

	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor.trim();
	}

	public void insert() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("NOME", this.getNome_autor());
		ResultSet rs = new Sql().select("CALL `sp_autor_insert`( :NOME )", this.params);
		this.setData(rs);
	}

	public void delete() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdautor()));
		new Sql().query("DELETE FROM tb_autores where idautor = :ID", params);
		this.setIdautor(0);
	}

	@Override
	public void loadById() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdautor()));
		ResultSet rs = new Sql().select("SELECT * FROM tb_autores where idautor = :ID", params);

		this.setData(rs);
	}

	/**
	 * Método utilizado para setar os atributos após alguma consulta
	 * 
	 */
	public void setData(ResultSet rs) throws SQLException {

		while (rs.next()) {
			this.setNome_autor(rs.getString("nome_autor"));
			this.setIdautor(rs.getLong("idautor"));
		}

	}

	/**
	 * @return Retorna um ResultSet com os autores
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Autor> getList() throws ClassNotFoundException, SQLException {

		ArrayList<Autor> autores = new ArrayList<>();
		ResultSet rs = new Sql().select("SELECT * FROM tb_autores ORDER BY idautor DESC", null);
		while (rs.next()) {
			Autor autor = new Autor();
			autor.setIdautor(rs.getLong("idautor"));
			autor.setNome_autor(rs.getString("nome_autor"));
			autores.add(autor);
		}
		return autores;
	}

	public void update() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdautor()));
		params.put("NOME", this.getNome_autor());
		new Sql().query("UPDATE   `impacta`.`tb_autores` SET  `nome_autor` = :NOME WHERE idautor = :ID", params);
	}

}
