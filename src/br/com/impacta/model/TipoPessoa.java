package br.com.impacta.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import br.com.impacta.sql.Sql;

public class TipoPessoa {
	
	private  HashMap<Integer, String> tipos = new HashMap<Integer, String>();

	public TipoPessoa() throws ClassNotFoundException, SQLException {
		ResultSet rs = new Sql().select("SELECT * FROM `tb_tipo_pessoa`", null);
		while (rs.next()) {
			tipos.put(rs.getInt(1), rs.getString(2));			
		}
	}
	
	public  String getTipoPessoa(int key){
		return tipos.get(key);
	}
	
	public HashMap<Integer, String> getList(){
		return tipos;		
	}
}
