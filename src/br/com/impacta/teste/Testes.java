package br.com.impacta.teste;

import java.sql.SQLException;
import java.text.ParseException;

import br.com.impacta.model.Reserva;

public class Testes {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

		Reserva reserva  = new Reserva();
	reserva.setIdpessoa(1600561);
	reserva.setNum_exemplar(548232035);
	reserva.insert();
	}
}
