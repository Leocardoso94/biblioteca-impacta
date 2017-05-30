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
import br.com.impacta.sql.Sql;

@Controller
public class EmprestimoController {
	private String erroEmprestimoExiste;

	@RequestMapping("admin/Emprestimo")
	public String carregar(Model model,HttpSession session) throws ClassNotFoundException, SQLException {
		Emprestimo Emprestimo = (Emprestimo) session.getAttribute("usuarioLogado");
		Emprestimo.loadById();
		model.addAttribute("Emprestimo", Emprestimo);
		model.addAttribute("Emprestimos", new Emprestimo().getList());
		if (erroEmprestimoExiste != null) {
			model.addAttribute("erroEmprestimoExiste", erroEmprestimoExiste);
		}
		model.addAttribute("tiposEmprestimo", new TipoEmprestimo().getList());
		model.addAttribute("page", "Emprestimo/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarEmprestimo")
	public String adicionarEmprestimo(Emprestimo Emprestimo) throws ClassNotFoundException, SQLException {
		Emprestimo.insert();
		return "redirect:Emprestimo";
	}

	@RequestMapping("admin/buscaEmprestimo")
	public String buscaEmprestimo(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select(
				"SELECT * FROM `tb_Emprestimos` a INNER JOIN `tb_tipo_Emprestimo` b ON a.idtipo_Emprestimo = b.idtipo_Emprestimo WHERE idEmprestimo LIKE :BUSCA OR `nome` LIKE :BUSCA OR `email` LIKE :BUSCA OR `telefone` LIKE :BUSCA OR `cpf` LIKE :BUSCA OR `nome_tipo` LIKE :BUSCA ",
				params);
		ArrayList<Emprestimo> Emprestimos = new ArrayList<>();
		while (rs.next()) {
			Emprestimo Emprestimo = new Emprestimo();
			Emprestimo.setData(Emprestimo, rs);
			Emprestimos.add(Emprestimo);
		}
		model.addAttribute("Emprestimos", Emprestimos);
		model.addAttribute("page", "Emprestimo/form");
		return "admin/index";
	}

	@RequestMapping("admin/excluirEmprestimo")
	public String excluirEmprestimo(Emprestimo Emprestimo) throws ClassNotFoundException, SQLException {
		Emprestimo.delete();
		return "redirect:Emprestimo";
	}

	@RequestMapping("admin/alterarEmprestimo")
	public String alterarEmprestimo(Emprestimo Emprestimo) throws ClassNotFoundException, SQLException {
		Emprestimo.update();
		return "redirect:Emprestimo";
	}
}
