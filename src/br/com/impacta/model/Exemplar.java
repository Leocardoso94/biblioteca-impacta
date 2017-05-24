package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.helpers.Validador;
import br.com.impacta.sql.Sql;

public class Exemplar implements Crud {
	private long num_exemplar;
	private long idobra;
	private boolean emprestado;
	private String nomeobra;
	private Date data_aquisicao;
	private Map<String, String> params = new HashMap<>();
	private Sql SQL = new Sql();

	public long getNum_exemplar() {
		return num_exemplar;
	}

	public void setNum_exemplar(long num_exemplar) {
		this.num_exemplar = num_exemplar;
	}

	public long getIdobra() {
		return idobra;
	}

	public void setIdobra(long idobra) {
		this.idobra = idobra;
	}

	public boolean isEmprestado() {
		return emprestado;
	}

	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}

	public Date getData_aquisicao() {
		return data_aquisicao;
	}

	public void setData_aquisicao(Date data_aquisicao) {
		this.data_aquisicao = data_aquisicao;
	}

	public void insert() throws SQLException {
		params.clear();
		params.put("NUM", Long.toString(getNum_exemplar()));
		params.put("ID", Long.toString(getIdobra()));
		params.put("DATA_AQUISICAO", new SimpleDateFormat("yyyy/MM/dd").format(this.getData_aquisicao()));
		SQL.query("INSERT INTO `impacta`.`tb_exemplares` (  `num_exemplar`,  `idobra`,  `data_aquisicao`) "
				+ "VALUES  ( :NUM , :ID ,  :DATA_AQUISICAO  )", params);

	}

	public void delete() throws SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getNum_exemplar()));
		if (Validador.deletar("SELECT * FROM `tb_emprestimos` WHERE num_exemplar = :ID ", params)) {
			SQL.query("DELETE FROM tb_exemplares WHERE num_exemplar = :ID", params);
		}
		this.setNum_exemplar(0);
		this.setData_aquisicao(null);
		this.setEmprestado(false);
		this.setIdobra(0);
	}

	public void loadById() throws SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getNum_exemplar()));
		ResultSet rs = SQL.select("SELECT * FROM tb_exemplares WHERE num_exemplar = :ID ", params);
		rs.next();
		this.setData(this, rs);

	}

	public void setData(Exemplar exemplar, ResultSet rs) throws SQLException {
		exemplar.setData_aquisicao(rs.getDate("data_aquisicao"));
		exemplar.setNum_exemplar(rs.getLong("num_exemplar"));
		exemplar.setIdobra(rs.getLong("idobra"));
		exemplar.setEmprestado(rs.getBoolean("emprestado"));
		Obra obra = new Obra();
		obra.setIdobra(exemplar.getIdobra());
		obra.loadById();
		exemplar.setNomeobra(obra.getTitulo());
	}

	public ArrayList<Exemplar> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Exemplar> exemplares = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_exemplares ORDER BY num_exemplar", null);
		while (rs.next()) {
			Exemplar exemplar = new Exemplar();
			exemplar.setData(exemplar, rs);
			exemplares.add(exemplar);
		}
		return exemplares;
	}

	public static int count() throws ClassNotFoundException, SQLException {
		ResultSet rs = new Sql().select("SELECT COUNT(`num_exemplar`) FROM `tb_exemplares`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}

		return rows;
	}

	@Override
	public void update() throws SQLException {
		params.clear();
		params.put("NUM", Long.toString(getNum_exemplar()));
		params.put("ID", Long.toString(getIdobra()));
		params.put("DATA_AQUISICAO", new SimpleDateFormat("yyyy/MM/dd").format(this.getData_aquisicao()));
		SQL.query(
				"UPDATE `impacta`.`tb_exemplares` SET  `idobra` = :ID ,  `data_aquisicao` = :DATA_AQUISICAO WHERE num_exemplar = :NUM",
				params);
	}

	public String getNomeobra() {
		return nomeobra;
	}

	public void setNomeobra(String nomeobra) {
		this.nomeobra = nomeobra;
	}

}
