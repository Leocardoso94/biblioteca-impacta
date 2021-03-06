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

public class Reserva implements Crud {
	private long idreserva;
	private long idpessoa;
	private Date data_reserva;
	private Date data_retirada;
	private long num_exemplar;
	private Map<String, String> params = new HashMap<>();
	private final Sql SQL = new Sql();

	public long getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(long idreserva) {
		this.idreserva = idreserva;
	}

	public long getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(long idpessoa) {
		this.idpessoa = idpessoa;
	}

	public Date getData_reserva() {
		return data_reserva;
	}

	public void setData_reserva(Date data_reserva) {
		this.data_reserva = data_reserva;
	}

	public Date getData_retirada() {
		return data_retirada;
	}

	public void setData_retirada(Date data_retirada) {
		this.data_retirada = data_retirada;
	}

	public static int count() throws ClassNotFoundException, SQLException {
		Sql sql = new Sql();
		ResultSet rs = sql.select("SELECT COUNT(`idreserva`) FROM `tb_reservas`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}
		sql.closeConnection();
		return rows;
	}

	public long getNum_exemplar() {
		return num_exemplar;
	}

	public void setNum_exemplar(long num_exemplar) {
		this.num_exemplar = num_exemplar;
	}

	@Override
	public void insert() throws SQLException {
		params.clear();
		params.put("NUM", Long.toString(this.getNum_exemplar()));
		ResultSet rs = SQL.select("select data_prevista_retorno from tb_emprestimos where num_exemplar = :NUM", params);
		rs.next();

		Calendar c1 = Calendar.getInstance();

		c1.setTime(rs.getDate("data_prevista_retorno"));
		c1.add(Calendar.DATE, +1);
		Date data = c1.getTime();

		params.put("DATA", new SimpleDateFormat("yyyy/MM/dd").format(data));
		params.put("PESSOA", Long.toString(this.getIdpessoa()));
		params.put("NUM", Long.toString(this.getNum_exemplar()));
		SQL.query("INSERT INTO tb_reservas (idpessoa, num_exemplar, data_retirada)" + "VALUES(:PESSOA, :NUM, :DATA)",
				params);
		SQL.closeConnection();
	}

	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadById() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub

	}

	public void cancelar() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdreserva()));
		SQL.query("DELETE FROM tb_reservas where idreserva = :ID", params);
	}

	public String getNomeExemplar() throws SQLException {
		params.clear();
		params.put("NUM", Long.toString(this.getNum_exemplar()));
		ResultSet rs = SQL.select(
				"SELECT   d.`titulo` FROM  tb_exemplares a   INNER JOIN `tb_reservas` b     ON a.`num_exemplar` = b.`num_exemplar`   INNER JOIN `tb_obras` d     ON a.`idobra` = d.`idobra` WHERE a.num_exemplar = :NUM",
				params);
		rs.next();
		return rs.getString(1);
	}

	public String getNomePessoa() throws SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdreserva()));
		ResultSet rs = SQL.select(
				"SELECT b.`nome` FROM `tb_reservas` a INNER JOIN `tb_pessoas` b ON a.`idpessoa` = b.`idpessoa` WHERE a.`idreserva` = :ID",
				params);
		while (rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public void setData(Reserva reserva, ResultSet rs) throws SQLException {
		this.setData_reserva(rs.getDate("data_reserva"));
		this.setData_retirada(rs.getDate("data_retirada"));
		this.setIdpessoa(rs.getLong("idpessoa"));
		this.setIdreserva(rs.getLong("idreserva"));
		this.setNum_exemplar(rs.getLong("num_exemplar"));
	}

	public ArrayList<Reserva> getList() throws SQLException {
		ArrayList<Reserva> reservas = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_reservas", null);
		while (rs.next()) {
			Reserva reserva = new Reserva();
			reserva.setData(reserva, rs);
			reservas.add(reserva);
		}
		SQL.closeConnection();
		return reservas;
	}

	public ArrayList<Reserva> busca(String busca) throws SQLException {
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT a.`data_reserva`,a.`data_retirada`, a.`idreserva`,a.`idpessoa`,a.`num_exemplar` FROM `tb_reservas` a INNER JOIN `tb_pessoas` b ON b.`idpessoa` = a.`idpessoa` INNER JOIN `tb_exemplares` c ON c.`num_exemplar` = a.`num_exemplar` INNER JOIN `tb_obras` d ON d.`idobra` = c.`idobra` WHERE a.`idpessoa` LIKE :BUSCA OR b.`nome`  LIKE :BUSCA OR a.`idreserva` LIKE :BUSCA OR a.`num_exemplar`  LIKE :BUSCA OR d.`titulo` LIKE :BUSCA",
				params);
		ArrayList<Reserva> reservas = new ArrayList<>();
		while (rs.next()) {
			Reserva reserva = new Reserva();
			reserva.setData(reserva, rs);
			reservas.add(reserva);
		}
		SQL.closeConnection();
		return reservas;
	}
}
