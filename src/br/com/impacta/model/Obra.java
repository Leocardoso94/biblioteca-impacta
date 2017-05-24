package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.impacta.sql.Sql;

public class Obra implements Crud {
	private static final Sql SQL = new Sql();
	private long idobra;
	private long ideditora;
	private String editora;
	private int idassunto;
	private String assunto;
	private String titulo;
	private Date ano_publicacao;
	private HashMap<String, String> params = new HashMap<>();

	public long getIdobra() {
		return idobra;
	}

	public void setIdobra(long idobra) {
		this.idobra = idobra;
	}

	public long getIdeditora() {
		return ideditora;
	}

	public void setIdeditora(long ideditora) {
		this.ideditora = ideditora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getAno_publicacao() {
		return ano_publicacao;
	}

	public void setAno_publicacao(Date ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}

	public int getIdassunto() {
		return idassunto;
	}

	public void setIdassunto(int idassunto) {
		this.idassunto = idassunto;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void insert() throws SQLException {
		params.clear();
		// params.put("NOME", this.getNome_assunto());
		SQL.query("INSERT INTO tb_obras (nome_assunto) VALUES ( :NOME )", params);
	}

	public void delete() throws SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getIdobra()));
		SQL.query("DELETE FROM tb_obras WHERE idobra = :ID", params);
	}

	public void loadById() throws SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getIdobra()));
		ResultSet rs = SQL.select("SELECT * FROM tb_obras WHERE idobra = :ID", params);
		rs.next();
		this.setData(this, rs);

	}

	public void setData(Obra obra, ResultSet rs) throws SQLException {
		obra.setIdassunto(rs.getInt("idassunto"));
		obra.setTitulo(rs.getString("titulo"));
		obra.setAno_publicacao(rs.getDate("ano_publicacao"));
		obra.setIdeditora(rs.getLong("ideditora"));
		obra.setIdobra(rs.getLong("idobra"));
		Assunto assunto = new Assunto();
		assunto.setIdassunto(obra.getIdassunto());
		assunto.loadById();
		obra.setAssunto(assunto.getNome_assunto());
		Editora editora = new Editora();
		editora.setIdeditora(obra.getIdeditora());
		editora.loadById();
		obra.setEditora(editora.getNome_editora());
	}

	public ArrayList<Obra> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Obra> obras = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_obras ORDER BY idassunto DESC", null);
		while (rs.next()) {
			Obra obra = new Obra();
			obra.setData(obra, rs);
			obras.add(obra);
		}
		return obras;
	}

	@Override
	public void update() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdobra()));
		params.put("TITULO", this.getTitulo());
		SQL.query("UPDATE tb_obras SET nome_assunto = :NOME WHERE idassunto = :ID", params);

	}
}
