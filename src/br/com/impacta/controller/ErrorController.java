package br.com.impacta.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.model.Pessoa;

@Controller
public class ErrorController {
	@RequestMapping("errors")
	public String carregar(Model model, HttpSession session,HttpServletRequest httpRequest) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		int httpErrorCode = getErrorCode(httpRequest);
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("page", "errors/" + httpErrorCode);
		return "admin/index";
	}
	
	private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }

}
