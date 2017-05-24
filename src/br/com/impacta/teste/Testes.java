package br.com.impacta.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.impacta.model.Obra;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Obra obra = new Obra();
		ArrayList<Obra> obras = obra.getList();
		for (Obra obra2 : obras) {
			System.out.println(obra2.getAssunto() + obra2.getEditora());
		}
	}
}
