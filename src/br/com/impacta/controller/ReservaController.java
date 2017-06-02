package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Exemplar;
import br.com.impacta.model.Pessoa;
import br.com.impacta.model.Reserva;
import br.com.impacta.sql.Sql;

@Controller
public class ReservaController {

	@RequestMapping("admin/reserva")
	public String carregar(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("reservas", new Reserva().getList());
		model.addAttribute("exemplares", new Exemplar().getListDeExemplaresValidos());
		model.addAttribute("pessoas", new Pessoa().getListDePessoasValidas());
		model.addAttribute("page", "reserva/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarReserva")
	public String adicionarReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
		reserva.insert();
		return "redirect:reserva";
	}

	@RequestMapping("admin/buscaReserva")
	public String buscaReserva(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT a.`data_reserva`,a.`data_prevista_retorno`,a.`finalizado`,a.`idreserva`,a.`idpessoa`,a.`num_exemplar` FROM `tb_reservas` a INNER JOIN `tb_pessoas` b ON b.`idpessoa` = a.`idpessoa` INNER JOIN `tb_exemplares` c ON c.`num_exemplar` = a.`num_exemplar` INNER JOIN `tb_obras` d ON d.`idobra` = c.`idobra` WHERE a.`idpessoa` LIKE :BUSCA OR b.`nome`  LIKE :BUSCA OR a.`idreserva` LIKE :BUSCA OR a.`num_exemplar`  LIKE :BUSCA OR d.`titulo` LIKE :BUSCA",
				params);
		ArrayList<Reserva> reservas = new ArrayList<>();
		while (rs.next()) {
			Reserva reserva = new Reserva();
			reserva.setData(reserva, rs);
			reservas.add(reserva);
		}
		model.addAttribute("reservas", reservas);
		model.addAttribute("page", "reserva/form");
		return "admin/index";
	}

	@RequestMapping("admin/cancelar")
	public String excluirReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
		reserva.cancelar();
		return "redirect:reserva";
	}

}
