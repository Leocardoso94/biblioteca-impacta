package br.com.impacta.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.impacta.model.Autor;
import br.com.impacta.model.Pessoa;

@Controller
public class AutorController {
	private String erroAutorExiste;

	@RequestMapping("admin/autor")
	public String carregar(Model model,HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("autores", new Autor().getList());
		if (erroAutorExiste != null) {
			model.addAttribute("erroAutorExiste", erroAutorExiste);
		}
		model.addAttribute("page", "autor/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarAutor")
	public String adicionarAutor(Autor autor) throws ClassNotFoundException, SQLException {
		try {
			this.erroAutorExiste = null;
			autor.insert();
		} catch (MySQLIntegrityConstraintViolationException e) {
			erroAutorExiste = "<p class=\"col-sm-12\" style=\"color: red;\">O autor que você quer inserir já existe!</p>";
		}
		return "redirect:autor";
	}

	@RequestMapping("admin/buscarAutor")
	public String buscarAutor(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String busca = "%" + request.getParameter("search") + "%";
		
		model.addAttribute("autores", new Autor().busca(busca));
		model.addAttribute("page", "autor/form");
		
		return "admin/index";
	}

	@RequestMapping("admin/excluirAutor")
	public String excluirAutor(Autor autor) throws ClassNotFoundException, SQLException {
		autor.delete();
		return "redirect:autor";
	}

	@RequestMapping("admin/alterarAutor")
	public String alterarAutor(Autor autor) throws ClassNotFoundException, SQLException {
		autor.update();
		return "redirect:autor";
	}
}
