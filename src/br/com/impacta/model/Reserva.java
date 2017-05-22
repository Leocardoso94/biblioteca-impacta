package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.impacta.sql.Sql;

public class Reserva {
	private long idreserva;
	private long idpessoa;
	private Calendar data_reserva;
	private Calendar data_retirada;
	private ArrayList<Obra> obras = new ArrayList<>();

	public void addObra(Obra obra) {
		this.obras.add(obra);
	}

	public ArrayList<Obra> getObras() {
		return this.obras;
	}

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

	public Calendar getData_reserva() {
		return data_reserva;
	}

	public void setData_reserva(Calendar data_reserva) {
		this.data_reserva = data_reserva;
	}

	public Calendar getData_retirada() {
		return data_retirada;
	}

	public void setData_retirada(Calendar data_retirada) {
		this.data_retirada = data_retirada;
	}
	
	public static int count() throws ClassNotFoundException, SQLException {
		ResultSet rs = new Sql().select("SELECT COUNT(`idreserva`) FROM `tb_reservas`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}

		return rows;
	}
}
