package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Editora implements Crud {
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
	public void insert() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("NOME", this.getNome_editora());
		ResultSet rs = new Sql().select("CALL `sp_editora_insert`( :NOME )", this.params);

		this.setData(rs);

	}

	@Override
	public void delete() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdeditora()));
		new Sql().query("DELETE FROM tb_editoras where ideditora = :ID", params);
		this.setIdeditora(0);
	}

	public void update() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdeditora()));
		params.put("NOME", this.getNome_editora());
		new Sql().query("UPDATE   `impacta`.`tb_editoras` SET  `nome_editora` = :NOME WHERE ideditora = :ID", params);
	}

	@Override
	public void loadById() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdeditora()));
		this.setData(new Sql().select("SELECT * FROM tb_editoras where ideditora = :ID", params));

	}

	@Override
	public void setData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this.setNome_editora(rs.getString("nome_editora"));
			this.setIdeditora(rs.getLong("ideditora"));
		}

	}

	public ArrayList<Editora> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Editora> editoras = new ArrayList<>();
		ResultSet rs = new Sql().select("SELECT * FROM tb_editoras ORDER BY ideditora DESC", null);
		while (rs.next()) {
			Editora editora = new Editora();
			editora.setIdeditora(rs.getLong("ideditora"));
			editora.setNome_editora(rs.getString("nome_editora"));
			editoras.add(editora);
		}
		return editoras;
	}

}
