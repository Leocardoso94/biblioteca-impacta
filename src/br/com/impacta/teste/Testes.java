package br.com.impacta.teste;

import java.sql.SQLException;

import br.com.impacta.model.TipoPessoa;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		System.out.println(new TipoPessoa().getTipoPessoa(3));
	}
}
