
package br.com.impacta.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String carregar(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
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

	@RequestMapping("admin/validaUsuario")
	public void validaUsuario(String senha, Long id, HttpServletResponse response) throws SQLException, IOException {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdpessoa(id);
		pessoa.setSenha(senha);
		if (pessoa.login()) {
			response.getWriter().write("Sucesso");
			
		}else{
		response.getWriter().write("Erro");
		}
		response.setStatus(200);
	}

	@RequestMapping("admin/buscaEmprestimo")
	public String buscaEmprestimo(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT a.`data_emprestimo`,a.`data_prevista_retorno`,a.`finalizado`,a.`idemprestimo`,a.`idpessoa`,a.`num_exemplar` FROM `tb_emprestimos` a INNER JOIN `tb_pessoas` b ON b.`idpessoa` = a.`idpessoa` INNER JOIN `tb_exemplares` c ON c.`num_exemplar` = a.`num_exemplar` INNER JOIN `tb_obras` d ON d.`idobra` = c.`idobra` WHERE a.`idpessoa` LIKE :BUSCA OR b.`nome`  LIKE :BUSCA OR a.`idemprestimo` LIKE :BUSCA OR a.`num_exemplar`  LIKE :BUSCA OR d.`titulo` LIKE :BUSCA",
				params);
		ArrayList<Emprestimo> emprestimos = new ArrayList<>();
		while (rs.next()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setData(emprestimo, rs);
			emprestimos.add(emprestimo);
		}
		model.addAttribute("emprestimos", emprestimos);
		model.addAttribute("page", "emprestimo/form");
		return "admin/index";
	}

	@RequestMapping("admin/devolver")
	public String excluirEmprestimo(Emprestimo emprestimo) throws ClassNotFoundException, SQLException {
		emprestimo.devolucao();
		return "redirect:emprestimo";
	}

}
