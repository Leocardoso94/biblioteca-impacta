package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.impacta.sql.Sql;

public class Emprestimo {
	private long idemprestimo;
	private long idpessoa;
	private long num_exemplar;
	private Calendar data_emprestimo;
	private Calendar data_prevista_retorno;

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

	public Calendar getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Calendar data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public Calendar getData_prevista_retorno() {
		return data_prevista_retorno;
	}

	public void setData_prevista_retorno(Calendar data_prevista_retorno) {
		this.data_prevista_retorno = data_prevista_retorno;
	}

	public static int count() throws ClassNotFoundException, SQLException {
		ResultSet rs = new Sql().select("SELECT COUNT(`idemprestimo`) FROM `tb_emprestimos`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}

		return rows;
	}

}
