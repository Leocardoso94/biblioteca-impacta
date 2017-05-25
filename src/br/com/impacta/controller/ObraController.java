package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

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
import br.com.impacta.sql.Sql;

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
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT * FROM tb_obras tbo INNER JOIN tb_assuntos tba ON tba.`idassunto` = tbo.`idassunto` INNER JOIN tb_autores tbat ON tbat.`idautor` = tbo.`idautor` INNER JOIN tb_editoras tbe ON tbe.`ideditora` = tbo.`ideditora` WHERE tbo.`titulo` LIKE :BUSCA OR tba.`nome_assunto` LIKE :BUSCA OR tbat.`nome_autor` LIKE :BUSCA OR tbe.`nome_editora` LIKE :BUSCA OR tbo.`ano_publicacao` LIKE :BUSCA OR tbo.`idobra` LIKE :BUSCA",
				params);
		ArrayList<Obra> obras = new ArrayList<>();
		while (rs.next()) {
			Obra obra = new Obra();
			obra.setData(obra, rs);
			obras.add(obra);
		}
		model.addAttribute("obras", obras);
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
