package br.com.impacta.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Reserva {
	private long idreserva;
	private long idpessoa;
	private Calendar data_reserva;
	private Calendar data_retirada;
	private ArrayList<Obra> obras = new ArrayList<>();

	public void addObra(Obra obra) {
		this.obras.add(obra);
	}

	public ArrayList<Obra> getObras() {
		return this.obras;
	}

	public long getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(long idreserva) {
		this.idreserva = idreserva;
	}

	public long getIdpessoa() {
		return idpessoa;
	}

	public void setIdpessoa(long idpessoa) {
		this.idpessoa = idpessoa;
	}

	public Calendar getData_reserva() {
		return data_reserva;
	}

	public void setData_reserva(Calendar data_reserva) {
		this.data_reserva = data_reserva;
	}

	public Calendar getData_retirada() {
		return data_retirada;
	}

	public void setData_retirada(Calendar data_retirada) {
		this.data_retirada = data_retirada;
	}
}
