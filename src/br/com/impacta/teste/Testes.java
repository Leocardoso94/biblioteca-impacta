package br.com.impacta.teste;

import java.sql.SQLException;
import java.text.ParseException;

import br.com.impacta.model.Emprestimo;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setIdemprestimo(1);
		emprestimo.loadById();
		emprestimo.devolucao();
		System.out.println(emprestimo.getNum_exemplar());

	}
}
