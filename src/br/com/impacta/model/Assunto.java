package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.helpers.Validador;
import br.com.impacta.sql.Sql;

public class Assunto implements Crud {
	private int idassunto;
	private ArrayList<Obra> obras = new ArrayList<>();
	private String nome_assunto;
	private Map<String, String> params = new HashMap<>();
	private Sql SQL = new Sql();

	public void addObra(Obra obra) {
		this.obras.add(obra);
	}

	public ArrayList<Obra> getObras() {
		return this.obras;
	}

	public int getIdassunto() {
		return idassunto;
	}

	public void setIdassunto(int idassunto) {
		this.idassunto = idassunto;
	}

	public String getNome_assunto() {
		return nome_assunto;
	}

	public void setNome_assunto(String nome_assunto) {
		this.nome_assunto = nome_assunto;
	}

	public void insert() throws SQLException {
		params.clear();
		params.put("NOME", this.getNome_assunto());
		SQL.query("INSERT INTO tb_assuntos (nome_assunto) VALUES ( :NOME )", params);
	}

	public void delete() throws SQLException {
		this.params.clear();
		params.put("ID", Integer.toString(getIdassunto()));
		if (Validador.deletar("SELECT * FROM `tb_obras` WHERE `idassunto` = :ID", params)) {
			SQL.query("DELETE FROM tb_assuntos WHERE idassunto = :ID", params);
		}
		this.setIdassunto(0);
		this.setNome_assunto(null);
	}

	public void loadById() throws SQLException {
		this.params.clear();
		params.put("ID", Integer.toString(getIdassunto()));
		ResultSet rs = SQL.select("SELECT * FROM tb_assuntos WHERE idassunto = :ID", params);
		rs.next();
		this.setData(this, rs);

	}

	public void setData(Assunto assunto, ResultSet rs) throws SQLException {
		assunto.setIdassunto(rs.getInt("idassunto"));
		assunto.setNome_assunto(rs.getString("nome_assunto"));
	}

	public ArrayList<Assunto> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Assunto> assuntos = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_assuntos ORDER BY nome_assunto", null);
		while (rs.next()) {
			Assunto assunto = new Assunto();
			assunto.setData(assunto, rs);
			assuntos.add(assunto);
		}
		return assuntos;
	}

	@Override
	public void update() throws SQLException {
		params.clear();
		params.put("ID", Integer.toString(this.getIdassunto()));
		params.put("NOME", this.getNome_assunto());
		SQL.query("UPDATE tb_assuntos SET nome_assunto = :NOME WHERE idassunto = :ID", params);

	}
}
