package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.impacta.model.Editora;
import br.com.impacta.sql.Sql;

@Controller
public class EditoraController {
	private String erroEditoraExiste;

	@RequestMapping("admin/editora")
	public String carregar(Model model) throws ClassNotFoundException, SQLException {
		model.addAttribute("editoras", new Editora().getList());
		if (erroEditoraExiste != null) {
			model.addAttribute("erroEditoraExiste", erroEditoraExiste);
		}
		model.addAttribute("page", "editora/form");
		return "admin/index";
	}
	
	@RequestMapping("admin/buscaEditora")
	public String buscaEditora(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		HashMap<String, String> params = new HashMap<>();
		String busca = "%" + request.getParameter("search") + "%";
		params.put("BUSCA", busca);
		ResultSet rs = new Sql().select("SELECT * From tb_editoras WHERE nome_editora LIKE :BUSCA", params);
		ArrayList<Editora> editoras = new ArrayList<>();
		while (rs.next()) {			
			Editora editora = new Editora();
			editora.setData(editora, rs);
			editoras.add(editora);
		}
		model.addAttribute("editoras",editoras);
		model.addAttribute("page", "editora/form");
		return "admin/index";
	}

	@RequestMapping("admin/adicionarEditora")
	public String adicionarEditora(Editora editora) throws ClassNotFoundException, SQLException {
		try {
			this.erroEditoraExiste = null;
			editora.insert();
		} catch (MySQLIntegrityConstraintViolationException e) {
			erroEditoraExiste = "<p class=\"col-sm-12\" style=\"color: red;\">A editora que você quer inserir já existe!</p>";
		}
		return "redirect:editora";
	}
	
	@RequestMapping("admin/excluirEditora")
	public String excluirEditora(Editora editora) throws ClassNotFoundException, SQLException {
		editora.delete();
		return "redirect:editora";
	}

	@RequestMapping("admin/alterarEditora")
	public String alterarEditora(Editora editora) throws ClassNotFoundException, SQLException {
		editora.update();
		return "redirect:editora";
	}
}
