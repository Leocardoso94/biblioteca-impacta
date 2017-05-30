package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Emprestimo implements Crud{
	private long idemprestimo;
	private long idpessoa;
	private long num_exemplar;
	private Date data_emprestimo;
	private Date data_prevista_retorno;
	private boolean finalizado;
	private final Sql SQL = new Sql();
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
		ResultSet rs = new Sql().select("SELECT COUNT(`idemprestimo`) FROM `tb_emprestimos`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}

		return rows;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public void loadById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Emprestimo> getList() throws SQLException{
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();
		ResultSet rs = SQL.select("SELECT * FROM tb_emprestimos ORDER BY idemprestimo", null);
		while(rs.next()){
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setData(emprestimo, rs);
			emprestimos.add(emprestimo);
		}
		return emprestimos;
	}

	public void setData(Emprestimo emprestimo, ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

}
