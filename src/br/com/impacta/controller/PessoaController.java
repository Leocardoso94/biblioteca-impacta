package br.com.impacta.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Pessoa;
import br.com.impacta.model.TipoPessoa;

@Controller
public class PessoaController {
	private String erroPessoaExiste;

	@RequestMapping("admin/pessoa")
	public String carregar(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
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

	@RequestMapping("admin/buscaPessoa")
	public String buscaPessoa(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		String busca = "%" + request.getParameter("search") + "%";

		model.addAttribute("pessoas", new Pessoa().busca(busca));
		model.addAttribute("tiposPessoa", new TipoPessoa().getList());
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
