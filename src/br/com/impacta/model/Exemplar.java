package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Exemplar implements Crud {
	private long num_exemplar;
	private long idobra;
	private boolean emprestado;
	private Calendar data_aquisicao;
	private Map<String, String> params = new HashMap<>();

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

	public Calendar getData_aquisicao() {
		return data_aquisicao;
	}

	public void setData_aquisicao(Calendar data_aquisicao) {
		this.data_aquisicao = data_aquisicao;
	}

	public void insert() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(getNum_exemplar()));
		this.setData(new Sql().select("CALL sp_exemplar_insert( :ID )", params));

	}

	public void delete() throws ClassNotFoundException, SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getNum_exemplar()));
		new Sql().query("DELETE FROM tb_exemplares WHERE idexemplar = :ID", params);
		this.setNum_exemplar(0);
		this.setData_aquisicao(null);
		this.setEmprestado(false);
		this.setIdobra(0);
	}

	public void loadById() throws ClassNotFoundException, SQLException {
		this.params.clear();
		params.put("ID", Long.toString(getNum_exemplar()));
		this.setData(new Sql().select("SELECT FROM tb_exemplares WHERE idexemplar = :ID", params));

	}

	public void setData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this.setNum_exemplar(rs.getInt("idexemplar"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_aquisicao"));
			this.setData_aquisicao(data);
			this.setNum_exemplar(rs.getLong("num_exemplar"));
			this.setIdobra(rs.getLong("idobra"));
			this.setEmprestado(rs.getBoolean("emprestado"));
		}
	}

	public static ArrayList<Exemplar> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Exemplar> exemplares = new ArrayList<>();
		ResultSet rs = new Sql().select("SELECT * FROM tb_exemplares ORDER BY num_exemplar", null);
		while (rs.next()) {
			Exemplar exemplar = new Exemplar();
			exemplar.setNum_exemplar(rs.getInt("idexemplar"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_aquisicao"));
			exemplar.setData_aquisicao(data);
			exemplar.setNum_exemplar(rs.getLong("num_exemplar"));
			exemplar.setIdobra(rs.getLong("idobra"));
			exemplar.setEmprestado(rs.getBoolean("emprestado"));
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
	public void update() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
