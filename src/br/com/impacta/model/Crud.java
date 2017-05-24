package br.com.impacta.model;

import java.sql.SQLException;

public interface Crud {
	public void insert() throws SQLException;

	public void delete() throws SQLException;

	public void loadById() throws SQLException;

	public void update() throws SQLException;

}
