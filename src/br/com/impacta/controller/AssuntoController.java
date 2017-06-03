package br.com.impacta.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.impacta.model.Assunto;
import br.com.impacta.model.Pessoa;

@Controller
public class AssuntoController {
	private String erroAssuntoExiste;

	@RequestMapping("admin/assunto")
	public String carregar(Model model,HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("assuntos", new Assunto().getList());
		if (erroAssuntoExiste != null) {
			model.addAttribute("erroAssuntoExiste", erroAssuntoExiste);
		}
		model.addAttribute("page", "assunto/form");
		return "admin/index";
	}
	
	@RequestMapping("admin/buscaAssunto")
	public String buscaAssunto(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String busca = "%" + request.getParameter("search") + "%";
		model.addAttribute("assuntos",new Assunto().busca(busca));
		model.addAttribute("page", "assunto/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarAssunto")
	public String adicionarAssunto(Assunto assunto) throws ClassNotFoundException, SQLException {
		try {
			this.erroAssuntoExiste = null;
			assunto.insert();
		} catch (MySQLIntegrityConstraintViolationException e) {
			erroAssuntoExiste = "<p class=\"col-sm-12\" style=\"color: red;\">O assunto que você quer inserir já existe!</p>";
		}
		return "redirect:assunto";
	}
	
	@RequestMapping("admin/excluirAssunto")
	public String excluirAssunto(Assunto assunto) throws ClassNotFoundException, SQLException {
		assunto.delete();
		return "redirect:assunto";
	}

	@RequestMapping("admin/alterarAssunto")
	public String alterarAssunto(Assunto assunto) throws ClassNotFoundException, SQLException {
		assunto.update();
		return "redirect:assunto";
	}
}
