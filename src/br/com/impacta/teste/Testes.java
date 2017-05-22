package br.com.impacta.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.impacta.model.Editora;
import br.com.impacta.model.Pessoa;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Pessoa pessoa = new Pessoa();
		
		System.out.println(pessoa.getCpf());
		System.out.println(pessoa.getEmail());
		System.out.println(pessoa.getIdpessoa());
		System.out.println(pessoa.getIdtipo_pessoa());
		System.out.println(pessoa.getNome());
		System.out.println(pessoa.getSenha());
		System.out.println(pessoa.getTelefone());
		System.out.println(Boolean.toString(pessoa.isInadmin()));
		System.out.println(pessoa.getData_registro().toString());
	}
}
