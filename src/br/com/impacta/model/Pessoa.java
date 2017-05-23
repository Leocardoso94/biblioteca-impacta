package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private String tipopessoa;
	private long idpessoa;
	private Calendar data_registro;
	private int idtipo_pessoa;
	private Map<String, String> params = new HashMap<>();

	public Calendar getData_registro() {
		return data_registro;
	}

	public void setData_registro(Calendar data_registro) {
		this.data_registro = data_registro;
	}

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

	public String getTipopessoa() {
		return tipopessoa;
	}

	public void setTipopessoa(String tipopessoa) {
		this.tipopessoa = tipopessoa;
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
		params.clear();
		params.put("TIPO", Long.toString(this.getIdtipo_pessoa()));
		params.put("NOME", this.getNome());
		params.put("EMAIL", this.getEmail());
		params.put("SENHA", this.getSenha());
		params.put("TEL", this.getTelefone());
		params.put("ADMIN", this.isInadmin() ? "1" : "0");
		params.put("CPF", this.getCpf());
		new Sql()
				.query("INSERT INTO `impacta`.`tb_pessoas` (  `idtipo_pessoa`, `nome`, `email`, `senha`, `telefone`, `inadmin`, `cpf` ) VALUES "
						+ "(:TIPO , :NOME , :EMAIL , :SENHA , :TEL , :ADMIN , :CPF )", params);

	}

	@Override
	public void delete() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdpessoa()));
		new Sql().query("DELETE FROM  `impacta`.`tb_pessoas` WHERE `idpessoa` = :ID", params);
	}

	@Override
	public void loadById() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(this.getIdpessoa()));
		ResultSet rs = new Sql().select("SELECT * FROM tb_pessoas where idpessoa = :ID", params);

		this.setData(rs);
	}

	public boolean login() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdpessoa()));
		params.put("SENHA", getSenha());
		ResultSet rs = new Sql().select("SELECT * FROM `tb_pessoas` WHERE idpessoa = :ID AND senha = :SENHA", params);
		return rs.next();
	}

	public boolean loginAdmin() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("ID", Long.toString(getIdpessoa()));
		params.put("SENHA", getSenha());
		ResultSet rs = new Sql()
				.select("SELECT * FROM `tb_pessoas` WHERE idpessoa = :ID AND senha = :SENHA AND `inadmin` = 1", params);
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
			this.setEmail(rs.getString("email"));
			this.setCpf(rs.getString("cpf"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registro"));
			this.setData_registro(data);
		}

	}

	public ArrayList<Pessoa> getList() throws ClassNotFoundException, SQLException {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		ResultSet rs = new Sql()
				.select("SELECT * FROM `tb_pessoas` a, tb_tipo_pessoa b where a.idtipo_pessoa = b.idtipo_pessoa", null);
		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setIdtipo_pessoa(rs.getInt("idtipo_pessoa"));
			pessoa.setIdpessoa(rs.getLong("idpessoa"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setSenha(rs.getString("senha"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setTipopessoa(rs.getString("nome_tipo"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setInadmin(rs.getBoolean("inadmin"));
			pessoa.setCpf(rs.getString("cpf"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data_registro"));
			pessoa.setData_registro(data);
			pessoas.add(pessoa);
		}
		return pessoas;
	}

	public static int count() throws ClassNotFoundException, SQLException {
		ResultSet rs = new Sql().select("SELECT COUNT(`idpessoa`) FROM `tb_pessoas`", null);
		int rows = 0;
		if (rs.last()) {
			rows = rs.getInt(1);
		}

		return rows;
	}

	public void update() throws ClassNotFoundException, SQLException {
		params.clear();
		params.put("CPF", this.getCpf());
		params.put("EMAIL", this.getEmail());
		params.put("NOME", this.getNome());
		params.put("SENHA", this.getSenha());
		params.put("TELEFONE", this.getTelefone());
		params.put("ID", Long.toString(this.getIdpessoa()));
		params.put("IDTIPO", Long.toString(this.getIdtipo_pessoa()));
		params.put("ADMIN", this.isInadmin() ? "1" : "0");

		new Sql().query("UPDATE `impacta`.`tb_pessoas` SET " + "`idtipo_pessoa` = :IDTIPO , " + "`nome` = :NOME , "
				+ "`email` = :EMAIL , " + "`senha` = :SENHA , " + "`telefone` = :TELEFONE, " + "`inadmin` = :ADMIN, "
				+ "`cpf` = :CPF " + "WHERE `idpessoa` = :ID", params);

	}

}
