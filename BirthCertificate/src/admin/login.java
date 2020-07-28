package admin;
import hospital.dbconfig;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import hospital.hospital_login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class login {

	 public JFrame framelogin;
	 private JTextField email;
	 private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.framelogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con =null;
	public login() {
		initialize();
		con=dbconfig.dbconf();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framelogin = new JFrame();
		framelogin.setBounds(100, 100, 450, 300);
		framelogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framelogin.getContentPane().setLayout(null);
		
		email = new JTextField();
		email.setBounds(165, 47, 116, 22);
		framelogin.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(58, 50, 56, 16);
		framelogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(58, 117, 56, 16);
		framelogin.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="SELECT * FROM admin WHERE Email=? AND Password=?";
					PreparedStatement stmt =con.prepareStatement(query);
					stmt.setString(1,email.getText());
					stmt.setString(2,password.getText());
					ResultSet rs =stmt.executeQuery();
					int count=0;
					while(rs.next())
					{
						count+=1;
						
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null, "Success");
						framelogin.dispose();
						admin_dash admin = new admin_dash();
						admin.frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username And Password is Incorrect");

					}
					
					
					
				}
				catch(Exception ead1)
				{
					JOptionPane.showMessageDialog(null, ead1);
				}
				
			}
		});
		btnNewButton.setBounds(184, 165, 97, 25);
		framelogin.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				framelogin.dispose();
				hospital_login lgn = new hospital_login();
				lgn.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(58, 165, 97, 25);
		framelogin.getContentPane().add(btnNewButton_1);
		
		password = new JPasswordField();
		password.setEchoChar('#');
		password.setBounds(165, 114, 122, 22);
		framelogin.getContentPane().add(password);
		
		JLabel lblNewLabel_2 = new JLabel("This is admin Login");
		lblNewLabel_2.setBounds(297, 50, 123, 60);
		framelogin.getContentPane().add(lblNewLabel_2);
	}
}
