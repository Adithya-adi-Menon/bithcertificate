package hospital;
import java.sql.*;

import javax.swing.JOptionPane;
public class dbconfig {
	
Connection con=null;
	public static Connection dbconf(){
	try {
		 Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/birth","root","");
		System.out.println("Connection succss");
		return con;
	}
	catch(Exception e){
		JOptionPane.showConfirmDialog(null, e);
		return null;
	}
	}

}
