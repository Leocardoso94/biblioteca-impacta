package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Emprestimo implements Crud {
	private long idemprestimo;
	private long idpessoa;
	private long num_exemplar;
	private Date data_emprestimo;
	private Date data_prevista_retorno;
	private boolean finalizado;
	private final Sql SQL = new Sql();
	private final int MULTA = 2;
	private Map<String, String> params = new HashMap<>();

	public long getIdemprestimo() {
		return idemprestimo;
	}

	public void setIdemprestimo(long idemprestimo) {
		this.idemprestimo = idemprestimo;
	}

	public long getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(long idpessoa) {
		this.idpessoa = idpessoa;
	}

	public long getNum_exemplar() {
		return num_exemplar;
	}

	public void setNum_exemplar(long num_exemplar) {
		this.num_exemplar = num_exemplar;
	}

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public Date getData_prevista_retorno() {
		return data_prevista_retorno;
	}

	public void setData_prevista_retorno(Date data_prevista_retorno) {
		this.data_prevista_retorno = data_prevista_retorno;
	}

	public static int count() throws ClassNotFoundException, SQLException {
		Sql sql = new Sql();

		ResultSet rs = sql.select("SELECT COUNT(`idemprestimo`) FROM `tb_emprestimos`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}
		sql.closeConnection();
		return rows;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public void loadById() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdemprestimo()));
		ResultSet rs = SQL.select("SELECT * FROM tb_emprestimos where idemprestimo = :ID", params);
		rs.next();
		this.setData(this, rs);
		SQL.closeConnection();
	}

	@Override
	public void insert() throws SQLException {

		Pessoa pessoa = new Pessoa();
		pessoa.setIdpessoa(getIdpessoa());
		pessoa.loadById();

		Date dataPrevista = new Date();

		Calendar c1 = Calendar.getInstance();

		c1.setTime(new Date());
		c1.add(Calendar.DATE, +pessoa.diasEmprestimos());

		ResultSet rs = SQL.select(
				"SELECT * FROM tb_reservas where data_retirada > NOW() and num_exemplar = " + getNum_exemplar(),
				params);
		if (rs.next()) {
			Calendar c2 = Calendar.getInstance();
			c2.setTime(rs.getDate("data_retirada"));
			c2.add(Calendar.DATE, -1);
			if (c2.compareTo(c1) < 0) {
				c1 = c2;
			}
		}
		dataPrevista = c1.getTime();

		params.clear();
		params.put("NUM", Long.toString(this.getNum_exemplar()));
		params.put("ID", Long.toString(pessoa.getIdpessoa()));
		SQL.query("INSERT INTO tb_emprestimos (idpessoa, num_exemplar, data_prevista_retorno) VALUES ( '"
				+ pessoa.getIdpessoa() + "' , '" + getNum_exemplar() + "' ,'"
				+ new SimpleDateFormat("yyyy/MM/dd").format(dataPrevista) + "' )", null);
		SQL.query("UPDATE  `tb_exemplares` SET  `emprestado` = 1 WHERE num_exemplar = '" + getNum_exemplar() + "'",
				null);
		SQL.closeConnection();
	}

	@Override
	public void delete() throws SQLException {

	}

	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub

	}

	public void devolucao() throws SQLException {
		this.loadById();
		SQL.query("UPDATE  `tb_emprestimos` SET  `finalizado` = 1 WHERE idemprestimo = :ID", params);
		params.clear();
		params.put("NUM", Long.toString(getNum_exemplar()));
		SQL.query("UPDATE  `tb_exemplares` SET  `emprestado` = 0 WHERE num_exemplar = :NUM", params);
	}

	public String getNomeExemplar() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdemprestimo()));
		ResultSet rs = SQL
				.select("SELECT c.`titulo` FROM `tb_emprestimos` a INNER JOIN `tb_exemplares` b ON a.`num_exemplar` = b.`num_exemplar` INNER JOIN `tb_obras` c ON b.`idobra` = c.`idobra`"
						+ "WHERE a.idemprestimo = :ID", params);
		while (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public String getNomePessoa() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdemprestimo()));
		ResultSet rs = SQL.select(
				"SELECT b.`nome` FROM `tb_emprestimos` a INNER JOIN `tb_pessoas` b ON a.`idpessoa` = b.`idpessoa` WHERE a.`idemprestimo` = :ID",
				params);
		while (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public ArrayList<Emprestimo> getList() throws SQLException {
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_emprestimos ORDER BY  finalizado, idemprestimo DESC", null);
		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setData(emprestimo, rs);
			emprestimos.add(emprestimo);
		}
		SQL.closeConnection();
		return emprestimos;
	}

	public void setData(Emprestimo emprestimo, ResultSet rs) throws SQLException {
		emprestimo.setFinalizado(rs.getBoolean("finalizado"));
		emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
		emprestimo.setData_prevista_retorno(rs.getDate("data_prevista_retorno"));
		emprestimo.setIdemprestimo(rs.getLong("idemprestimo"));
		emprestimo.setIdpessoa(rs.getLong("idpessoa"));
		emprestimo.setNum_exemplar(rs.getLong("num_exemplar"));
	}

	public String situacao() {
		if (isFinalizado()) {
			return "Ok";
		}
		if (getData_prevista_retorno().compareTo(new Date()) == 1) {
			return "Pendente";
		}
		return "Atrasado";
	}

	public double valorDaMulta() {
		Date dataAte = new Date(); // pega data e hora atual

		long diferencaDias = 0;

		if (situacao() == "Atrasado") {
			diferencaDias = (dataAte.getTime() - getData_prevista_retorno().getTime()) / (1000 * 60 * 60 * 24);
		}
		return diferencaDias * MULTA;
	}

	public ArrayList<Emprestimo> busca(String busca) throws SQLException {
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT a.`data_emprestimo`,a.`data_prevista_retorno`,a.`finalizado`,a.`idemprestimo`,a.`idpessoa`,a.`num_exemplar` FROM `tb_emprestimos` a INNER JOIN `tb_pessoas` b ON b.`idpessoa` = a.`idpessoa` INNER JOIN `tb_exemplares` c ON c.`num_exemplar` = a.`num_exemplar` INNER JOIN `tb_obras` d ON d.`idobra` = c.`idobra` WHERE a.`idpessoa` LIKE :BUSCA OR b.`nome`  LIKE :BUSCA OR a.`idemprestimo` LIKE :BUSCA OR a.`num_exemplar`  LIKE :BUSCA OR d.`titulo` LIKE :BUSCA",
				params);
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();
		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setData(emprestimo, rs);
			emprestimos.add(emprestimo);
		}
		SQL.closeConnection();
		return emprestimos;
	}

}
