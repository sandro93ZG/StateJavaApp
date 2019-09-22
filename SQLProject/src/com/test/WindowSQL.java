package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.sql.*;

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import java.awt.TextField;
import javax.swing.JScrollPane;
import java.awt.Panel;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowSQL {

	private JFrame frmMyJavaApp;
	private JTextField uspParameter;
	private JTable sqlTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowSQL window = new WindowSQL();
					window.frmMyJavaApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowSQL() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMyJavaApp = new JFrame();
		frmMyJavaApp.setTitle("My Java App");
		frmMyJavaApp.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowActivated(WindowEvent e) {
//				
//				// Populate table on form load
//				// 5.0
//		        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2016;integratedSecurity=true;";
//
//		        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
//		            String SQL = "SELECT TOP 10 * FROM Person.Person AS p ORDER BY p.LastName DESC";
//		            ResultSet rs = stmt.executeQuery(SQL);
//
//		            while (rs.next()) {
//		                System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
//		            }
//		        }
//		        catch (SQLException ex) {
//		            ex.printStackTrace();
//		        }
//		        
//			}
	        // END 5.0
			
			// START 5.1
			@Override
			public void windowOpened(WindowEvent e) {
				
		        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2016;integratedSecurity=true;";

		        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
		            String SQL = "SELECT TOP 10 * FROM Person.Person AS p ORDER BY p.LastName DESC";
		            ResultSet rs = stmt.executeQuery(SQL);

		            while (rs.next()) {
		                System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
		            }
		        }
		        catch (SQLException ex) {
		            ex.printStackTrace();
		        }

			}
			// END 5.1
		});
		frmMyJavaApp.setBounds(100, 100, 769, 543);
		frmMyJavaApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyJavaApp.getContentPane().setLayout(null);
		
		JButton returnTopTenButton = new JButton("Get Top 10");
		returnTopTenButton.addActionListener(new ActionListener() {
			
			// return all customers button 
			// START 1.0
			public void actionPerformed(ActionEvent e) {
				
		        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2016;integratedSecurity=true;";

		        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
		            String SQL = "SELECT TOP 10 * FROM Person.Person AS p ORDER BY p.LastName DESC";
		            ResultSet rs = stmt.executeQuery(SQL);

		            while (rs.next()) {
		                System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName"));
		            }
		        }
		        catch (SQLException ex) {
		            ex.printStackTrace();
		        }

			}
			// END 1.0
		});
		returnTopTenButton.setBounds(151, 23, 132, 30);
		frmMyJavaApp.getContentPane().add(returnTopTenButton);
		
		JButton uspButton = new JButton("Get All Employees");
		uspButton.addActionListener(new ActionListener() {
			
			// return values stored procedure - no parameters
			// START 2.0
			public void actionPerformed(ActionEvent e) {
				
		        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2016;integratedSecurity=true;";
		        String sqlStatement = "{call uspGetAllEmployees()}";
		        
		        //try (Connection con = DriverManager.getConnection(connectionUrl);CallableStatement ct = con.prepareCall(sqlStatement);ResultSet rs = ct.executeQuery();) { ... }
		        try {
		        	Connection con = DriverManager.getConnection(connectionUrl);
		        	CallableStatement ct = con.prepareCall(sqlStatement);
		        	ResultSet rs = ct.executeQuery();
		        	
		            while (rs.next()) {
		                System.out.println(rs.getString("FullName"));
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }

			}
			// END 2.0
		});
		uspButton.setBounds(133, 70, 167, 30);
		frmMyJavaApp.getContentPane().add(uspButton);
		
		JButton uspWithParameters = new JButton("Employees By Last Name");
		uspWithParameters.addActionListener(new ActionListener() {
			
			// return values stored procedure - IN parameter(s)
			// START 3.0
			public void actionPerformed(ActionEvent e) {
				
		        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2016;integratedSecurity=true;";
		        String sqlStatement = "{call uspGetAllEmployeesByLastName(?)}";
		        
		        //try (Connection con = DriverManager.getConnection(connectionUrl);CallableStatement ct = con.prepareCall(sqlStatement);ResultSet rs = ct.executeQuery();) { ... }
		        try {
		        	Connection con = DriverManager.getConnection(connectionUrl);
		        	CallableStatement ct = con.prepareCall(sqlStatement);
		        	ct.setString(1, uspParameter.getText());
		        	ResultSet rs = ct.executeQuery();
		        	
		            while (rs.next()) {
		                System.out.println(rs.getString("LastName"));
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
				
			}
			// END 3.0

		});
		uspWithParameters.setBounds(128, 179, 178, 30);
		frmMyJavaApp.getContentPane().add(uspWithParameters);
		
		uspParameter = new JTextField();
		uspParameter.setBounds(169, 136, 96, 30);
		frmMyJavaApp.getContentPane().add(uspParameter);
		uspParameter.setColumns(10);
		
		JScrollPane containerJScrollPane = new JScrollPane();
		containerJScrollPane.setBounds(10, 280, 733, 213);
		frmMyJavaApp.getContentPane().add(containerJScrollPane);
		
		sqlTable = new JTable();
		containerJScrollPane.setViewportView(sqlTable);
		
		JButton dbButtonTable = new JButton("Db Records To Table");
		//dbButtonTable.addActionListener(new ActionListener() {
			
			// return values stored procedure to JTable
			// START 4.0
//			public void actionPerformed(ActionEvent e) {
//				
//		        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2016;integratedSecurity=true;";
//		        String sqlStatement = "{call uspGetAllEmployeesByLastName(?)}";
//		        
//		        try {
//		        	Connection con = DriverManager.getConnection(connectionUrl);
//		        	CallableStatement ct = con.prepareCall(sqlStatement);
//		        	ct.setString(1, uspParameter.getText());
//		        	ResultSet rs = ct.executeQuery();
//		        	
//		        	ListTableModel model = ListTableModel.createModelFromResultSet(rs);
//		        	JTable table = new JTable( model );
//		        	
//		            while (rs.next()) {
//		                System.out.println(rs.getString("LastName"));
//		            }
//		        } catch (SQLException ex) {
//		            ex.printStackTrace();
//		        }
//		        
//			}
			// END 4.0

//		});
		dbButtonTable.setBounds(293, 239, 167, 30);
		frmMyJavaApp.getContentPane().add(dbButtonTable);
	}
}
