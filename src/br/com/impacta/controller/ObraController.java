package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.helpers.FormatarDatas;
import br.com.impacta.model.Assunto;
import br.com.impacta.model.Autor;
import br.com.impacta.model.Editora;
import br.com.impacta.model.Obra;
import br.com.impacta.sql.Sql;

@Controller
public class ObraController {
	private String erroObraExiste;

	@RequestMapping("admin/obra")
	public String carregar(Model model) throws ClassNotFoundException, SQLException {
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
				"SELECT * FROM `tb_obras` a INNER JOIN `tb_tipo_obra` b ON a.idtipo_obra = b.idtipo_obra WHERE idobra LIKE :BUSCA OR `nome` LIKE :BUSCA OR `email` LIKE :BUSCA OR `telefone` LIKE :BUSCA OR `cpf` LIKE :BUSCA OR `nome_tipo` LIKE :BUSCA ",
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
