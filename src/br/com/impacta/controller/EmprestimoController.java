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

import br.com.impacta.model.Emprestimo;
import br.com.impacta.model.Exemplar;
import br.com.impacta.model.Pessoa;
import br.com.impacta.sql.Sql;

@Controller
public class EmprestimoController {
	
	@RequestMapping("admin/emprestimo")
	public String carregar(Model model,HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("emprestimos", new Emprestimo().getList());
		model.addAttribute("exemplares", new Exemplar().getListDeExemplaresValidos());
		model.addAttribute("pessoas", new Pessoa().getListDePessoasValidas());
		model.addAttribute("page", "emprestimo/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarEmprestimo")
	public String adicionarEmprestimo(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
		emprestimo.insert();
		return "redirect:emprestimo";
	}

	@RequestMapping("admin/buscaEmprestimo")
	public String buscaEmprestimo(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT * FROM `tb_Emprestimos` a INNER JOIN `tb_tipo_Emprestimo` b ON a.idtipo_Emprestimo = b.idtipo_Emprestimo WHERE idEmprestimo LIKE :BUSCA OR `nome` LIKE :BUSCA OR `email` LIKE :BUSCA OR `telefone` LIKE :BUSCA OR `cpf` LIKE :BUSCA OR `nome_tipo` LIKE :BUSCA ",
				params);
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();
		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setData(emprestimo, rs);
			emprestimos.add(emprestimo);
		}
		model.addAttribute("Emprestimos", emprestimos);
		model.addAttribute("page", "emprestimo/form");
		return "admin/index";
	}

	@RequestMapping("admin/devolver")
	public String excluirEmprestimo(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
		emprestimo.devolucao();
		return "redirect:emprestimo";
	}

}
