package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

public class EmployeeFrame extends JFrame implements ActionListener {

	JMenuBar toolBar = new JMenuBar();
	JMenu repairs = new JMenu("Repairs");
	JMenu vehicles = new JMenu("Vehicles");
	JMenu tasks = new JMenu("Tasks");
	
	JMenu parts = new JMenu("Parts");

	JMenuItem readRepair = new JMenuItem("Read Repair");

	JMenuItem readVehicles = new JMenuItem("Read Vehicles");

	JMenuItem readTask = new JMenuItem("Read Task");

	JMenuItem readPart = new JMenuItem("Read Part");

	Container container = getContentPane();
	AppRunner app;

	String userName = new String();

	public EmployeeFrame(AppRunner application, String userText) {
		this.app = application;
		this.userName = userText;
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addMenuItemsToToolBar();
		addActionEvent();
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		this.setJMenuBar(toolBar);
	}

	public void addMenuItemsToToolBar() {

		toolBar.add(repairs);
		toolBar.add(vehicles);
		
		toolBar.add(tasks);
		toolBar.add(parts);

		vehicles.add(readVehicles);

		tasks.add(readTask);

		repairs.add(readRepair);

		parts.add(readPart);

	}

	public void addComponentsToContainer() {

		container.add(toolBar);

	}

	public void addActionEvent() {

		readRepair.addActionListener(this);
		readVehicles.addActionListener(this);
		readTask.addActionListener(this);
		readPart.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == readRepair) {
			container.removeAll();
			container.add(toolBar);

			JScrollPane myPane = new JScrollPane();

			ArrayList<EmployeeTaskAssignments> assignments = app.getRepairTaskForEmployee(this.userName);
			JTable myTable = new JTable();
			DefaultTableModel myModel = new DefaultTableModel();
			String[] myColumns = { "RepairID", "TaskID", "Name", "Description", "Status" };
			myTable.setModel(myModel);
			myModel.setColumnIdentifiers(myColumns);
			myPane.setViewportView(myTable);
			Object[] myRow = new Object[5];

			for (EmployeeTaskAssignments assignment : assignments) {

				myRow[0] = assignment.repairID;
				myRow[1] = assignment.taskID;
				myRow[2] = assignment.name;
				myRow[3] = assignment.desc;
				myRow[4] = assignment.status;
				myModel.addRow(myRow);
			}

			myPane.setBounds(30, 30, 540, 300);
			container.add(myPane);

			this.setTitle("Current Assignments");
			this.setBounds(10, 10, 680, 600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setVisible(true);
		}

		if (e.getSource() == readTask) {
			this.readTasks();
		}

		if (e.getSource() == readPart) {
			container.removeAll();
			container.add(toolBar);
			JScrollPane myPane = new JScrollPane();

			ArrayList<Part> myParts = app.getParts();
			JTable myTable = new JTable();
			DefaultTableModel myModel = new DefaultTableModel();
			String[] myColumns = { "PartNumber", "Name", "Price" };
			myTable.setModel(myModel);
			myModel.setColumnIdentifiers(myColumns);
			myPane.setViewportView(myTable);
			Object[] myRow = new Object[7];
			for (int i = 0; i < myParts.size(); i++) {
				myRow[0] = myParts.get(i).getPartNumber();
				myRow[1] = myParts.get(i).getName();
				myRow[2] = myParts.get(i).getPrice();
				myModel.addRow(myRow);
			}
			myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			myTable.getColumnModel().getColumn(1).setPreferredWidth(100);
			myTable.getColumnModel().getColumn(2).setPreferredWidth(100);
			myTable.getColumnModel().getColumn(0).setPreferredWidth(100);
			myPane.setBounds(30, 30, 300, 300);
			container.add(myPane);

			this.setTitle("Read Repair");
			this.setBounds(10, 10, 680, 600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setVisible(true);
		}

	}

	private void readTasks() {

		container.removeAll();
		container.add(toolBar);

		JScrollPane myPane = new JScrollPane();

		ArrayList<Task> myTasks = app.getAllEmployeeTasks(this.userName);

		Integer[] taskID = new Integer[myTasks.size()];
		for (int i = 0; i < myTasks.size(); i++) {
			taskID[i] = myTasks.get(i).getID();
		}

		JLabel completionLabel = new JLabel("Complete task");
		JComboBox<Integer> taskChooser = new JComboBox<Integer>(taskID);
		JButton completeButton = new JButton("Confirm");

		completionLabel.setBounds(25, 400, 100, 30);
		taskChooser.setBounds(125, 400, 100, 30);
		completeButton.setBounds(225, 400, 100, 30);

		completeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {

					Integer taskID = (Integer) taskChooser.getSelectedItem();
					System.out.println(taskID);
					app.completeTask(taskID);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The Fields are incorrect");
				}
			}
		});
		container.add(completionLabel);
		container.add(taskChooser);
		container.add(completeButton);

		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "ID", "Name", "Description", "Price" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[4];
		for (int i = 0; i < myTasks.size(); i++) {
			myRow[0] = myTasks.get(i).getID();
			myRow[1] = myTasks.get(i).getName();
			myRow[2] = myTasks.get(i).getDescription();
			myRow[3] = myTasks.get(i).getPrice();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myTable.getColumnModel().getColumn(0).setPreferredWidth(55);
		myTable.getColumnModel().getColumn(1).setPreferredWidth(125);
		myTable.getColumnModel().getColumn(2).setPreferredWidth(195);
		myTable.getColumnModel().getColumn(3).setPreferredWidth(125);
		myPane.setBounds(30, 30, 500, 300);
		container.add(myPane);

		this.setTitle("Task for Employee " + this.userName);
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

}
