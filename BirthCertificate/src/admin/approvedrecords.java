package admin;
import java.sql.*;
import java.util.ArrayList;
import hospital.dbconfig;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class approvedrecords {

	public JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					approvedrecords window = new approvedrecords();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con =null;
	public approvedrecords() {
		initialize();
		con=dbconfig.dbconf();
		showrecords();
	}

	public ArrayList<approved>approvedList()
	{
		ArrayList<approved> approvedList= new ArrayList<>();
		try {
			String query="SELECT * FROM birthcerti";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			approved approve;
			while(rs.next())
			{
				approve=new approved(rs.getString("name"),rs.getString("birth_place"),rs.getString("dob"),rs.getString("gender"),rs.getString("address"),rs.getString("phone"),rs.getString("email"));
				approvedList.add(approve);
			}
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return approvedList;
	}
	
	public void showrecords()
	{
		ArrayList<approved> list= approvedList();
		DefaultTableModel model= (DefaultTableModel)table.getModel();
		Object[] row = new Object[7];
		for(int i=0;i<list.size();i++)
		{
			row[0]=list.get(i).getName();
			row[1]=list.get(i).getBirth_place();
			row[2]=list.get(i).getDob();
			row[3]=list.get(i).getGender();
			row[4]=list.get(i).getAddress();
			row[5]=list.get(i).getPhone();
			row[6]=list.get(i).getEmail();
			model.addRow(row);
			
		}
	}
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 784, 369);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Place of Birth", "Date of Birth", "Gender", "Address", "Phone", "Email"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				approvedrecords app =new approvedrecords();
				app.frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(318, 429, 97, 25);
		frame.getContentPane().add(btnNewButton);
	}
}
