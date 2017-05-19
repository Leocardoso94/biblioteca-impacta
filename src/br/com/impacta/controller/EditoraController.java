package br.com.impacta.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Editora;

@Controller
public class EditoraController {
	@RequestMapping("admin/editora") 
	public String carregar(Model model) throws ClassNotFoundException, SQLException{
		model.addAttribute("editoras", new Editora().getList());
		model.addAttribute("page", "editora/form");
		return "admin/index";
	}
	@RequestMapping("admin/adicionarEditora") 
	public String adicionarEditora(Editora editora,Model model) throws ClassNotFoundException, SQLException{
		editora.insert();
		model.addAttribute("page", "editora/form");
		return "redirect:editora";
	}
}
