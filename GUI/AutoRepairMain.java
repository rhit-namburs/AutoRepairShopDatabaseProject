package gui;
import javax.swing.JFrame;

import services.DatabaseConnectionService;

public class AutoRepairMain {
	public static void main(String args[]) {
		
		DatabaseConnectionService db = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "AutoRepairShop_S2G2");
		
		AppRunner application = new AppRunner(db);
		
		//NEED TO ENCRYPT THIS!!!
		db.connect("emilyTestARS", "basicPass!");
		
		LoginFrame loginFrame = new LoginFrame(application);
		
		
		loginFrame.setTitle("Login Form");
		loginFrame.setVisible(true);
		loginFrame.setBounds(10, 10, 370, 600);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setResizable(false);
		
		
	}
}
