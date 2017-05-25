package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.helpers.FormatarDatas;
import br.com.impacta.model.Exemplar;
import br.com.impacta.model.Obra;
import br.com.impacta.sql.Sql;

@Controller
public class ExemplarController {
	private String erroExemplarExiste;

	@RequestMapping("admin/exemplar")
	public String carregar(Model model) throws ClassNotFoundException, SQLException {
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
		ResultSet rs = new Sql().select("SELECT * From tb_exemplares WHERE nome_exemplar LIKE :BUSCA", params);
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
