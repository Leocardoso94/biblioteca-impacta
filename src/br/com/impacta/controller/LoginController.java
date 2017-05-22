package br.com.impacta.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Pessoa;

@Controller
public class LoginController {

	private boolean erro;

	@RequestMapping("admin/login")
	public String loginAdmin(HttpSession session, Model model) {
		if (this.erro) {
			model.addAttribute("erro", "Login e/ou senha incorretos");
		}
		return "admin/login";
	}

	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:admin/login";
	}

	@RequestMapping("admin/efetuaLogin")
	public String efetuaLogin(Pessoa pessoa, HttpSession session, Model model)
			throws ClassNotFoundException, SQLException {
		if (pessoa.login()) {
			session.setAttribute("usuarioLogado", pessoa);
			this.erro = false;
			return "redirect:/admin";
		}
		this.erro = true;
		return "redirect:login";
	}
}