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
import br.com.impacta.model.Exemplar;
import br.com.impacta.model.Obra;
import br.com.impacta.model.Pessoa;
import br.com.impacta.sql.Sql;

@Controller
public class ExemplarController {
	private String erroExemplarExiste;

	@RequestMapping("admin/exemplar")
	public String carregar(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("exemplares", new Exemplar().getList());
		if (erroExemplarExiste != null) {
			model.addAttribute("erroExemplarExiste", erroExemplarExiste);
		}
		model.addAttribute("obras", new Obra().getList());
		model.addAttribute("page", "exemplar/form");
		return "admin/index";
	}

	@RequestMapping("admin/buscaExemplar")
	public String buscaExemplar(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT * FROM `tb_exemplares` b INNER JOIN `tb_obras` a ON b.`idobra` = a.`idobra` WHERE titulo LIKE :BUSCA OR `num_exemplar` LIKE :BUSCA",
				params);
		ArrayList<Exemplar> exemplares = new ArrayList<>();
		while (rs.next()) {
			Exemplar exemplar = new Exemplar();
			exemplar.setData(exemplar, rs);
			exemplares.add(exemplar);
		}
		model.addAttribute("exemplares", exemplares);
		model.addAttribute("page", "exemplar/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarExemplar")
	public String adicionarExemplar(Exemplar exemplar, HttpServletRequest req)
			throws ClassNotFoundException, SQLException, ParseException {
		exemplar.setData_aquisicao(FormatarDatas.formatar(req.getParameter("data"), "yyyy-MM-dd"));
		exemplar.insert();
		return "redirect:exemplar";
	}

	@RequestMapping("admin/excluirExemplar")
	public String excluirExemplar(Exemplar exemplar) throws ClassNotFoundException, SQLException {
		exemplar.delete();
		return "redirect:exemplar";
	}

	@RequestMapping("admin/alterarExemplar")
	public String alterarExemplar(Exemplar exemplar, HttpServletRequest req)
			throws ClassNotFoundException, SQLException, ParseException {
		exemplar.setData_aquisicao(FormatarDatas.formatar(req.getParameter("data"), "yyyy-MM-dd"));
		exemplar.update();
		return "redirect:exemplar";
	}
}
