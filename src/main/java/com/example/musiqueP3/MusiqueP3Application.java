package com.example.musiqueP3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@SpringBootApplication
@EnableJdbcRepositories
public class MusiqueP3Application {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MusiqueP3Application.class, args);
		DataSource ds = context.getBean(DataSource.class);
		Connection connection = null;

		try {
			connection = ds.getConnection();
			System.out.println("Connection ok");
			ResultSet rs = connection.createStatement().executeQuery("SELECT MUSIC_ID, TITLE, DESCRIPTION FROM MUSIC");
			while (rs.next()) {
				System.out.println(rs.getLong("MUSIC_ID")+ " | " +rs.getString("TITLE")+ " | " +rs.getString("DESCRIPTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
					System.out.println("connection close");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
