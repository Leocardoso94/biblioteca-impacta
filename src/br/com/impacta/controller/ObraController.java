package br.com.impacta.controller;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.helpers.FormatarDatas;
import br.com.impacta.model.Assunto;
import br.com.impacta.model.Autor;
import br.com.impacta.model.Editora;
import br.com.impacta.model.Obra;
import br.com.impacta.model.Pessoa;

@Controller
public class ObraController {
	private String erroObraExiste;

	@RequestMapping("admin/obra")
	public String carregar(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("obras", new Obra().getList());
		if (erroObraExiste != null) {
			model.addAttribute("erroObraExiste", erroObraExiste);
		}
		model.addAttribute("autores", new Autor().getList());
		model.addAttribute("assuntos", new Assunto().getList());
		model.addAttribute("editoras", new Editora().getList());
		model.addAttribute("page", "obra/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarObra")
	public String adicionarObra(Obra obra, HttpServletRequest req)
			throws ClassNotFoundException, SQLException, ParseException {
		obra.setAno_publicacao(FormatarDatas.formatar(req.getParameter("data"), "yyyy"));
		obra.insert();
		return "redirect:obra";
	}

	@RequestMapping("admin/buscaObra")
	public String buscaObra(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String busca = "%" + request.getParameter("search") + "%";

		model.addAttribute("obras", new Obra().busca(busca));
		model.addAttribute("page", "obra/form");
		return "admin/index";
	}

	@RequestMapping("admin/excluirObra")
	public String excluirObra(Obra obra) throws ClassNotFoundException, SQLException {
		obra.delete();
		return "redirect:obra";
	}

	@RequestMapping("admin/alterarObra")
	public String alterarObra(Obra obra, HttpServletRequest req)
			throws ClassNotFoundException, SQLException, ParseException {
		obra.setAno_publicacao(FormatarDatas.formatar(req.getParameter("data"), "yyyy"));
		obra.update();
		return "redirect:obra";
	}
}
