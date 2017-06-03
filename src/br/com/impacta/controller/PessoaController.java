package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String carregar(Model model,HttpSession session) throws ClassNotFoundException, SQLException {
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
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT * FROM `tb_pessoas` a INNER JOIN `tb_tipo_pessoa` b ON a.idtipo_pessoa = b.idtipo_pessoa WHERE idpessoa LIKE :BUSCA OR `nome` LIKE :BUSCA OR `email` LIKE :BUSCA OR `telefone` LIKE :BUSCA OR `cpf` LIKE :BUSCA OR `nome_tipo` LIKE :BUSCA ",
				params);
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setData(pessoa, rs);
			pessoas.add(pessoa);
		}
		model.addAttribute("pessoas", pessoas);
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
