package com.arthur.ipede.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriaConexao {
	private Connection con = null;


	public Connection abreConexao() {

		String nomeDrive = "com.mysql.jdbc.Driver";

		try {
			Class.forName(nomeDrive);
			String serverName = "localhost"; // caminho do servidor do BD

			String mydatabase = "ipededata"; // nome do seu banco de dados

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";

			String username = "root"; // nome de um usuário de seu BD

			String password = "qwertt22"; // sua senha de acesso
	
			con = DriverManager.getConnection(url, username, password);

			return con;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	
	public void close() {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
