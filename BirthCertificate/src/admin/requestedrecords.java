package admin;
import hospital.dbconfig;
import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class requestedrecords {

	public JFrame frame;
	private JTable table;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					requestedrecords window = new requestedrecords();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

 
	Connection con=null;
	public requestedrecords() {
		initialize();
		con=dbconfig.dbconf();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 896, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 39, 652, 354);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Retrive Requests");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//				String query="SELECT * FROM request";
//				PreparedStatement pstmt = con.prepareStatement(query);
//				ResultSet rs =pstmt.executeQuery();
//				table.setModel(DbUtils.resultSetToTableModel(rs));
//				}
//				catch (Exception ex)
//				{
//					JOptionPane.showMessageDialog(null, ex);
//				}
			}
		});
		btnNewButton.setBounds(442, 431, 346, 48);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(84, 71, 96, 22);
		frame.getContentPane().add(comboBox);
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection cond =DriverManager.getConnection("jdbc:mysql://localhost/birth","root","");
			String query="SELECT register.*,request.* FROM register INNER JOIN request ON hospita_id =hosp_nameid";
			PreparedStatement pstmt =cond.prepareStatement(query);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next())
			{	
			comboBox.addItem(rs.getString("hosp_name"));
			}
		}
		catch(Exception exx)
		{
			JOptionPane.showMessageDialog(null, exx);
		}
		
		JButton btnapprove = new JButton("Approve");
		btnapprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/birth","root","");
						//query to move data from one table to another
				String query="INSERT INTO birthcerti(hosp_name,name,birth_place,dob,gender,address,phone,email)SELECT hosp_nameid,name,birth_place,dob,gender,address,phone,email FROM request WHERE hosp_nameid=?  AND name=?";
				
				//query to delete data from table after transferring.
				String query3="DELETE FROM request WHERE id=?";
				PreparedStatement pstmt3 = con.prepareStatement(query3);
//				String name =name.getText();
				String query4="SELECT id FROM request WHERE name='"+name.getText()+"'";
				
				PreparedStatement pstmt4 =con.prepareStatement(query4);
				ResultSet rs2= pstmt4.executeQuery();
				while(rs2.next())
				{
					String del_id=rs2.getString("id");
					pstmt3.setString(1, del_id);
				}
		
				
				PreparedStatement pstmt = con.prepareStatement(query);
				String hos;
				hos=comboBox.getSelectedItem().toString();
				String query2="SELECT hospita_id FROM register WHERE hosp_name='"+hos+"'";
				PreparedStatement pstmt2 = con.prepareStatement(query2);
				ResultSet rs = pstmt2.executeQuery();
				while(rs.next())
				{
					String id=rs.getString("hospita_id");
					pstmt.setString(1,id);
				}
				
				pstmt.setString(2, name.getText());
				pstmt.executeUpdate();
				pstmt3.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Approved");
		        
				}
				catch(Exception excep)
				{
					JOptionPane.showMessageDialog(null, excep);
					
				}
				
			}
		});
		btnapprove.setBounds(0, 228, 97, 25);
		frame.getContentPane().add(btnapprove);
		
		JButton btnNewButton_2 = new JButton("Deny");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					String query="INSERT INTO denied(hosp_name,name,birth_place,dob,gender,address,phone,email)SELECT hosp_nameid,name,birth_place,dob,gender,address,phone,email FROM request WHERE hosp_nameid=?  AND name=?";
					PreparedStatement pstmt = con.prepareStatement(query);
					
					String query3="DELETE FROM request WHERE id =?";
					PreparedStatement pstmt3=con.prepareStatement(query3);
					
					String query4="SELECT id FROM request WHERE name='"+name.getText()+"'";
					PreparedStatement pstmt4=con.prepareStatement(query4);
					ResultSet rs2=pstmt4.executeQuery();
					while(rs2.next())
					{
						String del_id;
						del_id=rs2.getString("id");
						pstmt3.setString(1,del_id);
					}
					
					String hos;
					hos=comboBox.getSelectedItem().toString();
					String query2="SELECT hospita_id FROM register WHERE hosp_name='"+hos+"'";
					PreparedStatement pstmt2=con.prepareStatement(query2);
					ResultSet rs = pstmt2.executeQuery();
					while(rs.next())
					{
						String id;
						id=rs.getString("hospita_id");
						pstmt.setString(1, id);
					}
					pstmt.setString(2, name.getText());
					pstmt.executeUpdate();
					pstmt3.executeUpdate();
					JOptionPane.showMessageDialog(null, "Denied");
					
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton_2.setBounds(103, 286, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(12, 146, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(84, 143, 116, 22);
		frame.getContentPane().add(name);
		name.setColumns(10);
	}
}
