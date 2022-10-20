package com.ScriptaInsight.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnection {

		public static String DeleteDrugid() throws SQLException {

			String url = "jdbc:mysql://scriptaqa2.cspd4fhwpyly.us-east-1.rds.amazonaws.com";
			String username = "root";
			String pass = "mm7m0rr1s";
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con = DriverManager.getConnection(url, username, pass);
			Statement stmt = con.createStatement();
			String query = "DELETE FROM int_plantemoran.saverx_employee_manually_discontinued_meds WHERE drug_id=13325;";
			stmt.execute(query);
			System.out.println("Drug with Id=13325 Has been Successfully Deleted");
			return query;

		}

	}


