package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerFrame extends JFrame implements ActionListener {

	JLabel mainLabel = new JLabel("Auto Repair");

	JMenuBar toolBar = new JMenuBar();
	Container container = getContentPane();
	AppRunner app;

	JMenu repairs = new JMenu("Repairs");
	JMenuItem viewRepairs = new JMenuItem("View Repairs");
	JMenu vehicles = new JMenu("Vehicles");
	JMenuItem viewVehicles = new JMenuItem("View Vehicles");
	private String username = "";

	public CustomerFrame(AppRunner application, String username) {
		this.username = username;
		this.app = application;
		mainLabel.setFont(new Font("Courier", Font.PLAIN, 30));
		setLayoutManager();
		addMenuItemsToToolBar();
		setLocationAndSize();
		addActionEvent();
	}

	public void addMenuItemsToToolBar() {

		toolBar.add(repairs);
		toolBar.add(vehicles);

		repairs.add(viewRepairs);
		vehicles.add(viewVehicles);

	}

	private void addActionEvent() {
		viewRepairs.addActionListener(this);
		viewVehicles.addActionListener(this);

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		toolBar.setBackground(Color.RED);
		toolBar.setBounds(10, 10, 600, 10);
		this.setJMenuBar(toolBar);

		container.add(toolBar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewRepairs) {
			this.viewRepairs();
		}
		if (e.getSource() == viewVehicles) {
			this.viewVehicles();
		}
	}

	private void viewVehicles() {
		container.removeAll();
		container.add(toolBar);

		JScrollPane myPane = new JScrollPane();

		ArrayList<Vehicle> myRepairs = app.getPaidByVehicleInfo(this.username);

		if (myRepairs == null) {
			JOptionPane.showMessageDialog(this, "No vehicles in system!");
			return;
		}
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();

		String[] myColumns = { "VIN", "Model", "Year" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[3];

		for (int i = 0; i < myRepairs.size(); i++) {

			myRow[0] = myRepairs.get(i).getVin();
			myRow[1] = myRepairs.get(i).getModel();
			myRow[2] = myRepairs.get(i).getYear();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		myTable.getColumnModel().getColumn(2).setPreferredWidth(130);

		myPane.setBounds(30, 30, 540, 300);
		container.add(myPane);

		this.setTitle("Vehicles");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

	}

	private void viewRepairs() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();
		ArrayList<PaidBy> myPaidBy = app.getPaidBy(this.username);
		if (myPaidBy == null) {
			JOptionPane.showMessageDialog(this, "No repairs in system!");
			return;
		}
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "VehicleVIN", "RepairID", "CustomerUserName", "Receipt" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[4];
		for (int i = 0; i < myPaidBy.size(); i++) {
			myRow[0] = myPaidBy.get(i).getVehicleVIN();
			myRow[1] = myPaidBy.get(i).getRepairID();
			myRow[2] = myPaidBy.get(i).getCustomerName();
			myRow[3] = myPaidBy.get(i).getReceipt();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 400, 300);
		container.add(myPane);


		this.setTitle("Repairs");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

}
