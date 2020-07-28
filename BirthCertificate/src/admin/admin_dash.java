package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_dash {

	public  JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_dash window = new admin_dash();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public admin_dash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnapproverecords = new JButton("Approved Records");
		btnapproverecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				approvedrecords appr = new approvedrecords();
				appr.frame.setVisible(true);
			}
		});
		btnapproverecords.setBounds(62, 70, 143, 90);
		frame.getContentPane().add(btnapproverecords);
		
		JButton btnRequests = new JButton("requests");
		btnRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				requestedrecords rq = new requestedrecords();
				rq.frame.setVisible(true);
			}
		});
		btnRequests.setBounds(255, 70, 123, 90);
		frame.getContentPane().add(btnRequests);
		
		JButton btnRejected = new JButton("rejected");
		btnRejected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				deniedrecords dnr = new deniedrecords();
				dnr.frame.setVisible(true);
			}
		});
		btnRejected.setBounds(405, 70, 107, 90);
		frame.getContentPane().add(btnRejected);
		
		JButton btnNewButton_1 = new JButton("Hospital Management");
		btnNewButton_1.setBounds(62, 209, 161, 112);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Admin Management");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				admin_managedash dash = new admin_managedash();
				dash.frame.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(255, 209, 161, 112);
		frame.getContentPane().add(btnNewButton_2);
	}
}
