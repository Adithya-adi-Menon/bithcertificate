package admin;

import java.util.ArrayList;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import hospital.dbconfig;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class admin_updateadmin {

	public JFrame frame;
	private JTable table;
	private JTextField email;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_updateadmin window = new admin_updateadmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection con=null;
	
	public admin_updateadmin() {
		initialize();
		con=dbconfig.dbconf();
		showrecords();
		
	}
	
	ArrayList<add> updateadmin()
	{
		ArrayList<add> updateadmin = new ArrayList<>();
		try {
			String query="SELECT * FROM admin";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			add ad;
			while(rs.next())
			{
				ad=new add(rs.getString("email"),rs.getString("password"));
				updateadmin.add(ad);
			}
			
		}
		catch(Exception exc){
			JOptionPane.showMessageDialog(null, exc);
			
		}
		return updateadmin;
	}
	public void showrecords()
	{
		ArrayList<add> list =updateadmin();
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		Object[] row = new Object[2];
		for(int i=0;i<list.size();i++)
		{
			row[0]=list.get(i).getEmail();
			row[1]=list.get(i).getPassword();
			model.addRow(row);
			
		}
	}

	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 881, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				
			}
		});
		scrollPane.setBounds(362, 25, 489, 341);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i=table.getSelectedRow();
				TableModel model = table.getModel();
				email.setText(model.getValueAt(i, 0).toString());
				password.setText(model.getValueAt(i, 1).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Email", "Password"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				admin_updateadmin update = new admin_updateadmin();
				update.frame.setVisible(true);
			}
		});
		btnrefresh.setBounds(598, 392, 97, 25);
		frame.getContentPane().add(btnrefresh);
		
		email = new JTextField();
		email.setBounds(28, 120, 230, 35);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(28, 238, 239, 35);
		frame.getContentPane().add(password);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="UPDATE admin SET Email=?,Password=? WHERE id=?";
					PreparedStatement pstmt = con.prepareStatement(query);
					String query2="SELECT id FROM admin WHERE email='"+email.getText()+"'";
					PreparedStatement pstmt2 =con.prepareStatement(query2);
					ResultSet rs = pstmt2.executeQuery();
					pstmt.setString(1, email.getText());
					pstmt.setString(2, password.getText());
					while(rs.next())
					{
						String id;
						id=rs.getString("id");
						pstmt.setString(3, id);
					}
					
					pstmt.executeUpdate();
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton_1.setBounds(53, 310, 102, 56);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(126, 82, 56, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(109, 196, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(167, 310, 100, 54);
		frame.getContentPane().add(btnNewButton_2);
	}

}
