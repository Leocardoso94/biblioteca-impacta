package br.com.impacta.teste;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import br.com.impacta.helpers.FormatarDatas;
import br.com.impacta.model.Obra;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

		Obra obra = new Obra();
		ArrayList<Obra> obras = obra.getList();
		for (Obra obra2 : obras) {
			obra2.setAno_publicacao(FormatarDatas.formatar("2010", "yyyy"));
			System.out.println(obra2.getAno_publicacao());
		}
	}
}
