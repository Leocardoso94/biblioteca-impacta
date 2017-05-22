package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Assunto implements Crud {
	private int idassunto;
	private ArrayList<Obra> obras = new ArrayList<>();
	private String nome_assunto;
	private Map<String, String> params = new HashMap<>();

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

	
	public void insert() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Integer.toString(getIdassunto()));
		this.setData(new Sql().select("CALL sp_assunto_insert( :ID )", params));
		
	}

	
	public void delete() throws ClassNotFoundException, SQLException {
		this.params.clear();
		params.put("ID", Integer.toString(getIdassunto()));
		new Sql().query("DELETE FROM tb_assuntos WHERE idassunto = :ID", params);
		this.setIdassunto(0);
		this.setNome_assunto(null);
	}

	
	public void loadById() throws ClassNotFoundException, SQLException {
		this.params.clear();
		params.put("ID", Integer.toString(getIdassunto()));
		this.setData(new Sql().select("SELECT FROM tb_assuntos WHERE idassunto = :ID", params));

	}

	
	public void setData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this.setIdassunto(rs.getInt("idassunto"));
			this.setNome_assunto(rs.getString(nome_assunto));
		}
	}

	public static ResultSet getList() throws ClassNotFoundException, SQLException {
			return new Sql().select("SELECT * FROM tb_assuntos ORDER BY nome_assunto", null);
	}

	@Override
	public void update() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
