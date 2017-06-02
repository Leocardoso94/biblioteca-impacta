package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.impacta.helpers.Validador;
import br.com.impacta.sql.Sql;

public class Obra implements Crud {
	private static final Sql SQL = new Sql();
	private long idobra;
	private long ideditora;
	private String editora;
	private int idassunto;
	private String assunto;
	private String titulo;
	private long idautor;
	private String autor;
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

	public String getEditora() throws SQLException {
		Editora editora = new Editora();
		editora.setIdeditora(this.getIdeditora());
		editora.loadById();
		this.setEditora(editora.getNome_editora());
		return this.editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAssunto() throws SQLException {
		Assunto assunto = new Assunto();
		assunto.setIdassunto(this.getIdassunto());
		assunto.loadById();
		this.setAssunto(assunto.getNome_assunto());
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void insert() throws SQLException {
		params.clear();
		params.put("EDITORA", Long.toString(this.getIdeditora()));
		params.put("TITULO", this.getTitulo());
		params.put("ANO", new SimpleDateFormat("yyyy").format(this.getAno_publicacao()));
		params.put("ASSUNTO", Long.toString(this.getIdassunto()));
		params.put("AUTOR", Long.toString(this.getIdautor()));
		SQL.query("INSERT INTO `impacta`.`tb_obras` (`ideditora`, `titulo`, `ano_publicacao`, `idassunto`, `idautor` ) "
				+ "VALUES (:EDITORA , :TITULO , :ANO , :ASSUNTO , :AUTOR )", params);
	}

	public void delete() throws SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getIdobra()));
		if (Validador.deletar(
				"SELECT COUNT(*) FROM `tb_exemplares` b INNER JOIN `tb_obras` c ON c.`idobra` = b.`idobra` WHERE c.`idobra` = :ID",
				params)) {
			SQL.query("DELETE FROM tb_obras WHERE idobra = :ID", params);
		}
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
		obra.setIdautor(rs.getLong("idautor"));
	}

	public ArrayList<Obra> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Obra> obras = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_obras ORDER BY titulo", null);
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
		params.put("EDITORA", Long.toString(this.getIdeditora()));
		params.put("TITULO", this.getTitulo());
		params.put("ANO", new SimpleDateFormat("yyyy").format(this.getAno_publicacao()));
		params.put("ASSUNTO", Long.toString(this.getIdassunto()));
		params.put("AUTOR", Long.toString(this.getIdautor()));
		params.put("ID", Long.toString(this.getIdobra()));
		SQL.query("UPDATE tb_obras SET " + "titulo = :TITULO ," + "ideditora = :EDITORA ," + "idassunto = :ASSUNTO ,"
				+ "idautor = :AUTOR ," + "ano_publicacao = :ANO " + "WHERE idobra = :ID ", params);

	}

	public String getAutor() throws SQLException {
		Autor autor = new Autor();
		autor.setIdautor(this.getIdautor());
		autor.loadById();
		this.setAutor(autor.getNome_autor());
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public long getIdautor() {
		return idautor;
	}

	public void setIdautor(long l) {
		this.idautor = l;
	}
	public int contagemDeExemplaresPorObras() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdobra()));
		ResultSet rs = SQL.select("SELECT COUNT(*) FROM tb_exemplares WHERE `idobra` = :ID", params);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}
		return rows;
	}
}
