package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.impacta.model.Assunto;
import br.com.impacta.sql.Sql;

@Controller
public class AssuntoController {
	private String erroAssuntoExiste;

	@RequestMapping("admin/assunto")
	public String carregar(Model model) throws ClassNotFoundException, SQLException {
		model.addAttribute("assuntos", new Assunto().getList());
		if (erroAssuntoExiste != null) {
			model.addAttribute("erroAssuntoExiste", erroAssuntoExiste);
		}
		model.addAttribute("page", "assunto/form");
		return "admin/index";
	}
	
	@RequestMapping("admin/buscaAssunto")
	public String buscaAssunto(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select("SELECT * From tb_assuntos WHERE nome_assunto LIKE :BUSCA", params);
		ArrayList<Assunto> assuntos = new ArrayList<>();
		while (rs.next()) {			
			Assunto assunto = new Assunto();
			assunto.setData(assunto, rs);
			assuntos.add(assunto);
		}
		model.addAttribute("assuntos",assuntos);
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
