package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.impacta.sql.Sql;

public class Pessoa implements Crud {
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private boolean inadmin;
	private String cpf;

	public Calendar getData_registro() {
		return data_registro;
	}

	public void setData_registro(Calendar data_registro) {
		this.data_registro = data_registro;
	}

	private long idpessoa;
	private Calendar data_registro;
	private int idtipo_pessoa;
	private Map<String, String> params = new HashMap<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isInadmin() {
		return inadmin;
	}

	public void setInadmin(boolean inadmin) {
		this.inadmin = inadmin;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public long getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(long idpessoa) {
		this.idpessoa = idpessoa;
	}

	public int getIdtipo_pessoa() {
		return idtipo_pessoa;
	}

	public void setIdtipo_pessoa(int idtipo_pessoa) {
		this.idtipo_pessoa = idtipo_pessoa;
	}

	@Override
	public void insert() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadById() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdpessoa()));
		ResultSet rs = new Sql().select("SELECT * FROM tb_pessoas where idpessoa = :ID", params);

		this.setData(rs);

	}

	public boolean login() throws ClassNotFoundException, SQLException{
		params.clear();
		params.put("ID", Long.toString(getIdpessoa()));
		params.put("SENHA", getSenha());
		ResultSet rs = new Sql().select("SELECT * FROM `tb_pessoas` WHERE idpessoa = :ID AND senha = :SENHA AND `inadmin` = 1", params);
		return rs.next();
	}

	@Override
	public void setData(ResultSet rs) throws SQLException {
		while (rs.next()) {
			this.setIdtipo_pessoa(rs.getInt("idtipo_pessoa"));
			this.setIdpessoa(rs.getLong("idpessoa"));
			this.setNome(rs.getString("nome"));
			this.setSenha(rs.getString("senha"));
			this.setTelefone(rs.getString("telefone"));
			this.setInadmin(rs.getBoolean("inadmin"));
			this.setCpf(rs.getString("cpf"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registro"));
			this.setData_registro(data);;
		}
		
	}

}
