package br.com.impacta.teste;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

		// Usu�rio informa uma data
		Date dataDoUsuario = new Date();

		// Atrav�s do Calendar, trabalhamos a data informada e adicionamos 1 dia nela
		Calendar c = Calendar.getInstance();
		c.setTime(dataDoUsuario);
		c.add(Calendar.DATE, -1);

		// Obtemos a data alterada
		Date data = c.getTime();
		
		System.out.println(dataDoUsuario.compareTo(data));

	}
}
