package com.arthur.ipede.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAORestaurante {
	private Connection con = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	public Connection abreConexao() {

		String nomeDrive = "com.mysql.jdbc.Driver";

		try {
			Class.forName(nomeDrive);
			String serverName = "localhost"; // caminho do servidor do BD

			String mydatabase = "iPedeData"; // nome do seu banco de dados

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "root"; // nome de um usuário de seu BD

			String password = "f4XeB33R"; // sua senha de acesso

			con = DriverManager.getConnection(url, username, password);

			return con;

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public ResultSet realizaQuery(String query) {
		try {
			statement = abreConexao().createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void closeAll() {
		try {
			preparedStatement.close();
			resultSet.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
