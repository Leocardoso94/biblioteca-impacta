package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.helpers.Validador;
import br.com.impacta.sql.Sql;

public class Autor implements Crud {
	private static final Sql SQL = new Sql();
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

	public void insert() throws SQLException {
		params.clear();
		params.put("NOME", this.getNome_autor());
		SQL.query("INSERT INTO tb_autores (nome_autor) VALUES ( :NOME )", this.params);
	}

	public void delete() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdautor()));
		if(Validador.deletar("SELECT * FROM `tb_obras` where idautor = :ID", params)){
		SQL.query("DELETE FROM tb_autores where idautor = :ID", params);
		}
		this.setIdautor(0);
	}

	@Override
	public void loadById() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdautor()));
		ResultSet rs = SQL.select("SELECT * FROM tb_autores where idautor = :ID", params);
		rs.next();
		this.setData(this, rs);
	}

	/**
	 * Método utilizado para setar os atributos após alguma consulta
	 * 
	 * @param autor
	 * 
	 */
	public void setData(Autor autor, ResultSet rs) throws SQLException {
		autor.setNome_autor(rs.getString("nome_autor"));
		autor.setIdautor(rs.getLong("idautor"));
	}

	/**
	 * @return Retorna um ResultSet com os autores
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Autor> getList() throws ClassNotFoundException, SQLException {

		ArrayList<Autor> autores = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_autores ORDER BY nome_autor", null);
		while (rs.next()) {
			Autor autor = new Autor();
			autor.setData(autor, rs);
			autores.add(autor);
		}
		return autores;
	}

	public void update() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdautor()));
		params.put("NOME", this.getNome_autor());
		SQL.query("UPDATE   `impacta`.`tb_autores` SET  `nome_autor` = :NOME WHERE idautor = :ID", params);
	}

}
