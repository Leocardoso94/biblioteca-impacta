package br.com.impacta.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Exemplar;
import br.com.impacta.model.Pessoa;
import br.com.impacta.model.Reserva;

@Controller
public class ReservaController {

	@RequestMapping("admin/reserva")
	public String carregar(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("reservas", new Reserva().getList());
		model.addAttribute("exemplares", new Exemplar().getListDeExemplaresValidosParaReserva());
		model.addAttribute("pessoas", new Pessoa().getListDePessoasValidas());
		model.addAttribute("page", "reserva/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarReserva")
	public String adicionarReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
		reserva.insert();
		System.out.println(reserva.getIdpessoa() + "\n" + reserva.getNum_exemplar());
		return "redirect:reserva";
	}

	@RequestMapping("admin/buscaReserva")
	public String buscaReserva(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String busca = "%" + request.getParameter("search") + "%";

		model.addAttribute("reservas", new Reserva().busca(busca));
		model.addAttribute("page", "reserva/form");
		return "admin/index";
	}

	@RequestMapping("admin/cancelar")
	public String excluirReserva(Reserva reserva) throws ClassNotFoundException, SQLException {
		reserva.cancelar();
		return "redirect:reserva";
	}

}
