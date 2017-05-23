package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Pessoa;
import br.com.impacta.model.TipoPessoa;
import br.com.impacta.sql.Sql;

@Controller
public class PessoaController {
	private String erroPessoaExiste;

	@RequestMapping("admin/pessoa")
	public String carregar(Model model) throws ClassNotFoundException, SQLException {
		model.addAttribute("pessoas", new Pessoa().getList());
		if (erroPessoaExiste != null) {
			model.addAttribute("erroPessoaExiste", erroPessoaExiste);
		}
		model.addAttribute("tiposPessoa", new TipoPessoa().getList());
		model.addAttribute("page", "pessoa/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarPessoa")
	public String adicionarPessoa(Pessoa pessoa) throws ClassNotFoundException, SQLException {
		pessoa.insert();
		return "redirect:pessoa";
	}

	@RequestMapping("admin/buscarPessoa")
	public String buscarPessoa(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select("SELECT * From tb_pessoas WHERE nome_pessoa LIKE :BUSCA", params);
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setIdpessoa(rs.getLong("idpessoa"));
			pessoa.setNome(rs.getString("nome"));
			pessoas.add(pessoa);
		}
		model.addAttribute("pessoas", pessoas);
		model.addAttribute("page", "pessoa/form");
		return "admin/index";
	}

	@RequestMapping("admin/excluirPessoa")
	public String excluirPessoa(Pessoa pessoa) throws ClassNotFoundException, SQLException {
		pessoa.delete();
		return "redirect:pessoa";
	}

	@RequestMapping("admin/alterarPessoa")
	public String alterarPessoa(Pessoa pessoa) throws ClassNotFoundException, SQLException {
		pessoa.update();
		return "redirect:pessoa";
	}
}
