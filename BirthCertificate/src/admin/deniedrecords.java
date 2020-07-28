package admin;
import java.sql.*;
import hospital.dbconfig;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
public class deniedrecords {

	public JFrame frame;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deniedrecords window = new deniedrecords();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con=null;
	/**
	 * Create the application.
	 */
	public deniedrecords() {
		initialize();
		con=dbconfig.dbconf();
		showrecords();
	}
	
	public ArrayList<denied> deniedList()
	{
		ArrayList<denied> deniedList=new ArrayList<>();
		try {
			String query="SELECT * FROM denied";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			denied den;
			while(rs.next())
			{
				den=new denied(rs.getString("name"),rs.getString("birth_place"),rs.getString("dob"),rs.getString("gender"),rs.getString("address"),rs.getString("phone"),rs.getString("email"));
				deniedList.add(den);
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return deniedList;
	}
	public void showrecords()
	{
		ArrayList<denied> list= deniedList();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 848, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 830, 349);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Cambria", Font.BOLD, 20));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Place Of Birth", "Date Of Birth", "Gender", "Address", "Phone ", "Email"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JButton btnrefresh = new JButton("Refresh");
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				deniedrecords drec =new deniedrecords();
				drec.frame.setVisible(true);
			}
		});
		btnrefresh.setBounds(312, 395, 97, 25);
		frame.getContentPane().add(btnrefresh);
	}
}
