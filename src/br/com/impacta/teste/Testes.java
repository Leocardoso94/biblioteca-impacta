package br.com.impacta.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.impacta.model.Editora;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		ArrayList<Editora> ed = new Editora().getList();
		
		for (Editora editora : ed) {
			System.out.println(editora.getNome_editora());
		}
		
		
	}
}
