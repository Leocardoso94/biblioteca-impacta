package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.helpers.Validador;
import br.com.impacta.sql.Sql;

public class Editora implements Crud {
	private static final Sql SQL = new Sql();
	private long ideditora;
	private String nome_editora;
	private Map<String, String> params = new HashMap<>();

	public long getIdeditora() {
		return ideditora;
	}

	public void setIdeditora(long ideditora) {
		this.ideditora = ideditora;
	}

	public String getNome_editora() {
		return nome_editora;
	}

	public void setNome_editora(String nome_editora) {
		this.nome_editora = nome_editora.trim();
	}

	@Override
	public void insert() throws SQLException {
		params.clear();
		params.put("NOME", this.getNome_editora());
		SQL.query("INSERT INTO tb_editoras (`nome_editora`) VALUES ( :NOME )", this.params);
	}

	@Override
	public void delete() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdeditora()));
		if (Validador.deletar("SELECT * FROM tb_obras where ideditora = :ID", params)) {
			SQL.query("DELETE FROM tb_editoras where ideditora = :ID", params);
		}
		this.setIdeditora(0);
	}

	public void update() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdeditora()));
		params.put("NOME", this.getNome_editora());
		SQL.query("UPDATE   `impacta`.`tb_editoras` SET  `nome_editora` = :NOME WHERE ideditora = :ID", params);
	}

	@Override
	public void loadById() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdeditora()));
		ResultSet rs = SQL.select("SELECT * FROM tb_editoras where ideditora = :ID", params);
		rs.next();
		this.setData(this, rs);
		SQL.closeConnection();
	}

	public void setData(Editora editora, ResultSet rs) throws SQLException {
		editora.setNome_editora(rs.getString("nome_editora"));
		editora.setIdeditora(rs.getLong("ideditora"));
	}

	public ArrayList<Editora> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Editora> editoras = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_editoras ORDER BY nome_editora", null);
		while (rs.next()) {
			Editora editora = new Editora();
			editora.setData(editora, rs);
			editoras.add(editora);
		}
		SQL.closeConnection();
		return editoras;
	}

	public int contagemDeObrasPorEditora() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdeditora()));
		ResultSet rs = SQL.select("SELECT COUNT(*) FROM tb_obras WHERE `ideditora` = :ID", params);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}
		SQL.closeConnection();
		return rows;
	}

	public ArrayList<Editora> busca(String busca) throws SQLException {
		params.put("BUSCA", busca);
		ResultSet rs = new Sql()
				.select("SELECT * From tb_editoras WHERE nome_editora LIKE :BUSCA or ideditora LIKE :BUSCA", params);
		ArrayList<Editora> editoras = new ArrayList<>();
		while (rs.next()) {
			Editora editora = new Editora();
			editora.setData(editora, rs);
			editoras.add(editora);
		}
		SQL.closeConnection();
		return editoras;
	}

}
