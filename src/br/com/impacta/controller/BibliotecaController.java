package br.com.impacta.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.impacta.model.Pessoa;
import br.com.impacta.sql.Sql;

@Controller
public class BibliotecaController extends WebMvcConfigurerAdapter {

	@RequestMapping("admin")
	public String homeAdmin(Model model, HttpSession session) throws ClassNotFoundException, SQLException {
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		pessoa.loadById();
		model.addAttribute("pessoa", pessoa);
		ResultSet rs = new Sql().select(
				"SELECT c.`idemprestimo`, b.`titulo`, d.`nome`, CASE WHEN c.`data_prevista_retorno` > NOW() THEN 'Ok' ELSE 'Atraso'END AS atraso, c.`data_prevista_retorno` FROM `tb_exemplares` a, tb_obras b, `tb_emprestimos` c, `tb_pessoas` d WHERE 1 = 1 AND c.`num_exemplar` = a.`num_exemplar` AND c.`idpessoa` = d.`idpessoa` AND a.`idobra` = b.`idobra` ORDER BY c.`idemprestimo` DESC LIMIT 10 ;",
				null);
		ArrayList<String[]> emprestimos = new ArrayList<>();

		while (rs.next()) {
			String[] emprestimo = new String[5];
			emprestimo[0] = rs.getString(1);
			emprestimo[1] = rs.getString(2);
			emprestimo[2] = rs.getString(4);
			emprestimo[3] = rs.getString(3);
			emprestimo[4] = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(5));
			emprestimos.add(emprestimo);
		}
		model.addAttribute("page", "dashboard");
		model.addAttribute("emprestimos", emprestimos);
		return "admin/index";
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
