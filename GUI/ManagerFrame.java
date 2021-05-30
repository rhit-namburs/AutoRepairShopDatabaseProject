package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javafx.scene.control.DatePicker;

public class ManagerFrame extends JFrame implements ActionListener {

	JMenuBar toolBar = new JMenuBar();

	JMenu assign = new JMenu("Assign");
	JMenu employees = new JMenu("Employees");
	JMenu customers = new JMenu("Customers");
	JMenu repairs = new JMenu("Repairs");
	JMenu vehicles = new JMenu("Vehicles");
	JMenu tasks = new JMenu("Tasks");
	JMenu parts = new JMenu("Parts");
	JMenu insurance = new JMenu("Insurance");
	JMenu orders = new JMenu("Orders");
	JMenu theFor = new JMenu("For");
	JMenu theGets = new JMenu("Gets");
	JMenu theHas = new JMenu("Has");
	JMenu thePaidBy = new JMenu("PaidBy");
	JMenu thePaidFor = new JMenu("PaidFor");
	JMenu settings = new JMenu("Settings");
	
	JMenuItem searchByID = new JMenuItem("Search by RepairID");
	JMenuItem searchByPartNumber = new JMenuItem("Search by PartNumber");

	JMenuItem addAssign = new JMenuItem("Assign Task to Employee");
	JMenuItem updateAssign = new JMenuItem("Update Task to Employee");
	JMenuItem deleteAssign = new JMenuItem("Delete Task to Employee");
	JMenuItem readAssign = new JMenuItem("View All Tasks assigned to Employees");

//	JMenuItem addEmployee = new JMenuItem("Add Employee");
	JMenuItem deleteEmployee = new JMenuItem("Delete Employee");
	JMenuItem readEmployee = new JMenuItem("View All Employees");
	JMenuItem updateEmployee = new JMenuItem("Update Employee");

//	JMenuItem addCustomer = new JMenuItem("Add Customer");
	JMenuItem deleteCustomer = new JMenuItem("Delete Customer");
	JMenuItem readCustomer = new JMenuItem("Read All Customer");
	JMenuItem updateCustomer = new JMenuItem("Update Customer");

	JMenuItem addInsurance = new JMenuItem("Add Insurance");
	JMenuItem deleteInsurance = new JMenuItem("Delete Insurance");
	JMenuItem readInsurance = new JMenuItem("Read All Insurance");
	JMenuItem updateInsurance = new JMenuItem("Update Insurance");

	JMenuItem addOrder = new JMenuItem("Add Order");
	JMenuItem deleteOrder = new JMenuItem("Delete Order");
	JMenuItem readOrder = new JMenuItem("Read All Order");
	JMenuItem updateOrder = new JMenuItem("Update Order");

	JMenuItem addRepair = new JMenuItem("Add Repair");
	JMenuItem updateRepair = new JMenuItem("Update Repair");
	JMenuItem deleteRepair = new JMenuItem("Delete Repair");
	JMenuItem readRepair = new JMenuItem("Read All Repair");

	JMenuItem addVehicle = new JMenuItem("Add Vehicle");
	JMenuItem updateVehicle = new JMenuItem("Update Vehicle");
	JMenuItem deleteVehicle = new JMenuItem("Delete Vehicle");
	JMenuItem readVehicles = new JMenuItem("Read All Vehicles");

	JMenuItem addfor = new JMenuItem("Add Part to Task");
	JMenuItem updatefor = new JMenuItem("Update Part for Task");
	JMenuItem deletefor = new JMenuItem("Delete Part for Task");
	JMenuItem readfor = new JMenuItem("Read All Parts for Tasks");

	JMenuItem addGets = new JMenuItem("Add Vehicle to Repair");
	JMenuItem deleteGets = new JMenuItem("Delete Vehicle for Repair");
	JMenuItem readGets = new JMenuItem("Read All Vehicle for Repair");

	JMenuItem addHas = new JMenuItem("Add Task to Repair");
	JMenuItem deleteHas = new JMenuItem("Delete Task for Repair");
	JMenuItem readHas = new JMenuItem("Read All Task for Repair");

	JMenuItem addPaidBy = new JMenuItem("Add Customer Payment");
	JMenuItem updatePaidBy = new JMenuItem("Update Customer Payment");
	JMenuItem deletePaidBy = new JMenuItem("Delete Customer Payment");
	JMenuItem readPaidBy = new JMenuItem("Read All Customer Payment");

	JMenuItem addPaidFor = new JMenuItem("Add Insurance Payment");
	JMenuItem updatePaidFor = new JMenuItem("Update Insurance Payment");
	JMenuItem deletePaidFor = new JMenuItem("Delete Insurance Payment");
	JMenuItem readPaidFor = new JMenuItem("Read All Insurance Payments");

	JMenuItem addTask = new JMenuItem("Add Task");
	JMenuItem updateTask = new JMenuItem("Update Task");
	JMenuItem deleteTask = new JMenuItem("Delete Task");
	JMenuItem readTask = new JMenuItem("Read All Task");

	JMenuItem addPart = new JMenuItem("Add Part");
	JMenuItem updatePart = new JMenuItem("Update Part");
	JMenuItem deletePart = new JMenuItem("Delete Part");
	JMenuItem readPart = new JMenuItem("Read All Part");

	JMenuItem updateSettings = new JMenuItem("Update Your Info");
	JMenuItem readMangerInfo = new JMenuItem("Read All Managers");

	Container container = getContentPane();
	AppRunner app;

	String userName = new String();

	public ManagerFrame(AppRunner application, String userText) {
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
		container.setBounds(0, 0, 100, 100);
		container.setBackground(Color.YELLOW);
	}

	public void setLocationAndSize() {
		this.setJMenuBar(toolBar);
	}

	public void addMenuItemsToToolBar() {

		toolBar.add(employees);
		toolBar.add(customers);
		toolBar.add(repairs);
		toolBar.add(vehicles);
		toolBar.add(tasks);
		toolBar.add(parts);
		toolBar.add(insurance);
		toolBar.add(settings);

		employees.add(updateEmployee);
		employees.add(deleteEmployee);
		employees.add(readEmployee);

		customers.add(addPaidBy);
		customers.add(updatePaidBy);
		customers.add(deletePaidBy);
		customers.add(readPaidBy);
		customers.add(deleteCustomer);
		customers.add(readCustomer);
		customers.add(updateCustomer);

		insurance.add(addPaidFor);
		insurance.add(updatePaidFor);
		insurance.add(deletePaidFor);
		insurance.add(readPaidFor);
		insurance.add(addInsurance);
		insurance.add(deleteInsurance);
		insurance.add(readInsurance);
		insurance.add(updateInsurance);

		orders.add(addOrder);
		orders.add(deleteOrder);
		orders.add(readOrder);
		orders.add(updateOrder);

		parts.add(addfor);
		parts.add(updatefor);
		parts.add(deletefor);
		parts.add(readfor);
		parts.add(addPart);
		parts.add(updatePart);
		parts.add(deletePart);
		parts.add(readPart);
		parts.add(searchByPartNumber);

		repairs.add(addGets);
		repairs.add(deleteGets);
		repairs.add(readGets);
		repairs.add(addRepair);
		repairs.add(updateRepair);
		repairs.add(deleteRepair);
		repairs.add(readRepair);
		repairs.add(addHas);
		repairs.add(deleteHas);
		repairs.add(readHas);
		repairs.add(searchByID);

		vehicles.add(addVehicle);
		vehicles.add(updateVehicle);
		vehicles.add(deleteVehicle);
		vehicles.add(readVehicles);

		tasks.add(addTask);
		tasks.add(updateTask);
		tasks.add(deleteTask);
		tasks.add(readTask);
		tasks.add(addAssign);
		tasks.add(updateAssign);
		tasks.add(deleteAssign);
		tasks.add(readAssign);

		settings.add(updateSettings);
		settings.add(readMangerInfo);

	}

	public void addComponentsToContainer() {

		container.add(toolBar);

	}

	public void addActionEvent() {
		addAssign.addActionListener(this);
		updateAssign.addActionListener(this);
		deleteAssign.addActionListener(this);
		readAssign.addActionListener(this);

		deleteEmployee.addActionListener(this);
		readEmployee.addActionListener(this);
		updateEmployee.addActionListener(this);

		deleteCustomer.addActionListener(this);
		readCustomer.addActionListener(this);
		updateCustomer.addActionListener(this);

		addInsurance.addActionListener(this);
		deleteInsurance.addActionListener(this);
		readInsurance.addActionListener(this);
		updateInsurance.addActionListener(this);

		addOrder.addActionListener(this);
		deleteOrder.addActionListener(this);
		readOrder.addActionListener(this);
		updateOrder.addActionListener(this);

		addRepair.addActionListener(this);
		readRepair.addActionListener(this);
		updateRepair.addActionListener(this);
		deleteRepair.addActionListener(this);

		addVehicle.addActionListener(this);
		updateVehicle.addActionListener(this);
		deleteVehicle.addActionListener(this);
		readVehicles.addActionListener(this);

		addfor.addActionListener(this);
		updatefor.addActionListener(this);
		deletefor.addActionListener(this);
		readfor.addActionListener(this);

		addGets.addActionListener(this);
		deleteGets.addActionListener(this);
		readGets.addActionListener(this);

		addHas.addActionListener(this);
		deleteHas.addActionListener(this);
		readHas.addActionListener(this);

		addPaidBy.addActionListener(this);
		updatePaidBy.addActionListener(this);
		deletePaidBy.addActionListener(this);
		readPaidBy.addActionListener(this);

		addPaidFor.addActionListener(this);
		updatePaidFor.addActionListener(this);
		readPaidFor.addActionListener(this);
		deletePaidFor.addActionListener(this);

		addTask.addActionListener(this);
		updateTask.addActionListener(this);
		deleteTask.addActionListener(this);
		readTask.addActionListener(this);

		addPart.addActionListener(this);
		updatePart.addActionListener(this);
		deletePart.addActionListener(this);
		readPart.addActionListener(this);

		updateSettings.addActionListener(this);
		readMangerInfo.addActionListener(this);
		
		searchByID.addActionListener(this);
		searchByPartNumber.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// ASSIGN
		if (e.getSource() == addAssign) {
			this.assignTaskToEmployee();
		}
		if (e.getSource() == updateAssign) {
			this.updateAssignTaskToEmployee();
		}
		if (e.getSource() == deleteAssign) {
			this.deleteAssignTaskToEmployee();
		}
		if (e.getSource() == readAssign) {
			this.readAssignTaskToEmployee();
		}
		// EMPLOYEES
		if (e.getSource() == updateEmployee) {
			this.updateEmployee();
		}
		if (e.getSource() == readEmployee) {
			this.readAllEmployees();
		}
		if (e.getSource() == deleteEmployee) {
			this.deleteEmployee();
		}
		// GETS
		if (e.getSource() == addGets) {
			this.addGets();
		}

		if (e.getSource() == deleteGets) {
			this.deleteGets();
		}
		if (e.getSource() == readGets) {
			this.readGets();
		}

		// CUSTOMER
		if (e.getSource() == updateCustomer) {
			this.updateCustomer();
		}
		if (e.getSource() == deleteCustomer) {
			this.deleteCustomer();
		}
		if (e.getSource() == readCustomer) {
			this.readAllCustomers();
		}
		// INSURANCE
		if (e.getSource() == addInsurance) {
			this.addInsurance();
		}
		if (e.getSource() == updateInsurance) {
			this.updateInsurance();
		}
		if (e.getSource() == deleteInsurance) {
			this.deleteInsurance();
		}
		if (e.getSource() == readInsurance) {
			this.readAllInsurances();
		}
		// Orders
		if (e.getSource() == addOrder) {
			this.addOrder();
		}
		if (e.getSource() == updateOrder) {
			this.updateOrder();
		}
		if (e.getSource() == readOrder) {
			this.readOrders();
		}
		if (e.getSource() == deleteOrder) {
			this.deleteOrder();
		}
		// theFor
		if (e.getSource() == addfor) {
			this.addFor();
		}
		if (e.getSource() == updatefor) {
			this.updateFor();
		}
		if (e.getSource() == deletefor) {
			this.deleteFor();
		}
		if (e.getSource() == readfor) {
			this.readAllFor();
		}
		// theHas
		if (e.getSource() == addHas) {
			this.addHas();
		}
		if (e.getSource() == deleteHas) {
			this.deleteHas();
		}
		if (e.getSource() == readHas) {
			this.readAllHas();
		}
		// thePaidBy
		if (e.getSource() == addPaidBy) {
			this.addPaidBy();
		}
		if (e.getSource() == updatePaidBy) {
			this.updatePaidBy();
		}
		if (e.getSource() == deletePaidBy) {
			this.deletePaidBy();
		}
		if (e.getSource() == readPaidBy) {
			this.readPaidBy();
		}
		// thePaidFor
		if (e.getSource() == addPaidFor) {
			this.addPaidFor();
		}
		if (e.getSource() == updatePaidFor) {
			this.updatePaidFor();
		}
		if (e.getSource() == deletePaidFor) {
			this.deletePaidFor();
		}
		if (e.getSource() == readPaidFor) {
			this.readPaidFor();
		}
		// REPAIRS
		if (e.getSource() == addRepair) {
			this.addRepair();
		}
		if (e.getSource() == updateRepair) {
			this.updateRepair();
		}
		if (e.getSource() == deleteRepair) {
			this.deleteRepair();
		}
		if (e.getSource() == readRepair) {
			this.readAllRepairs();
		}
		
		if(e.getSource() == searchByID) {
			this.searchByID();
		}
		
		// VEHICLES
		if (e.getSource() == addVehicle) {
			this.addVehicle();
		}
		if (e.getSource() == updateVehicle) {
			this.updateVehicle();
		}
		if (e.getSource() == deleteVehicle) {
			this.deleteVehicle();
		}
		if (e.getSource() == readVehicles) {
			this.readAllVehicle();
		}
		// TASKS
		if (e.getSource() == addTask) {
			this.addTask();
		}
		if (e.getSource() == updateTask) {
			this.updateTask();
		}
		if (e.getSource() == deleteTask) {
			this.deleteTask();
		}
		if (e.getSource() == readTask) {
			this.readAllTasks();
		}
		// PARTS
		if (e.getSource() == addPart) {
			this.addPart();
		}
		if (e.getSource() == updatePart) {
			this.updatePart();
		}
		if (e.getSource() == deletePart) {
			this.deletePart();
		}
		if (e.getSource() == readPart) {
			this.readAllParts();
		}
		if(e.getSource() == searchByPartNumber) {
			this.searchByPartNumber();
		}

		// Manager
		if (e.getSource() == updateSettings) {
			this.updatemanager();
		}
		if (e.getSource() == readMangerInfo) {
			this.readallManagers();
		}
	}

	private void searchByID() {
	
		container.removeAll();
		container.add(toolBar);

		JLabel searchLabel = new JLabel("Enter Repair ID");
		JTextField searchField = new JTextField();
		JButton addButton = new JButton("Search");

		searchLabel.setBounds(25, 100, 100, 30);
		searchField.setBounds(150, 100, 100, 30);
		addButton.setBounds(100, 150, 100, 30);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {

					int id = Integer.parseInt(searchField.getText());
					
					JScrollPane myPane = new JScrollPane();

					ArrayList<Repair> repairs = app.searchByID(id);
					
					ArrayList<Task> taskList = app.getTaskByRepairID(id);
					
					
					JTable myTable = new JTable();
					DefaultTableModel myModel = new DefaultTableModel();
					String[] myColumns = { "ID" , "Description" , "Completion" , "PaidStatus"};
					myTable.setModel(myModel);
					myModel.setColumnIdentifiers(myColumns);
					myPane.setViewportView(myTable);
					Object[] myRow = new Object[4];
					for (int i = 0; i < repairs.size(); i++) {
						myRow[0] = repairs.get(i).getID();
						myRow[1] = repairs.get(i).Description;
						myRow[2] = repairs.get(i).Completion;
						myRow[3] = repairs.get(i).priceCompleted;
						myModel.addRow(myRow);
					}
					myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

					for (int i = 0; i < myRow.length; i++) {
						myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
					}
					
					
					
					JScrollPane taskPane = new JScrollPane();

					JTable tasks = new JTable();
					DefaultTableModel taskModel = new DefaultTableModel();
					String[] taskColumn = { "ID" , "Description" , "Completion", "Name", "Price"};
					tasks.setModel(taskModel);
					taskModel.setColumnIdentifiers(taskColumn);
					taskPane.setViewportView(tasks);
					Object[] taskRow = new Object[5];
					for (int i = 0; i < taskList.size(); i++) {
						taskRow[0] = taskList.get(i).getID();
						taskRow[1] = taskList.get(i).Description;
						taskRow[2] = taskList.get(i).Completion;
						taskRow[3] = taskList.get(i).Name;
						taskRow[4] = taskList.get(i).Price;
						taskModel.addRow(taskRow);
					}
					tasks.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

					for (int i = 0; i < taskRow.length; i++) {
						tasks.getColumnModel().getColumn(i).setPreferredWidth(100);
					}
					
					JLabel myRepairs = new JLabel("MY Repairs");
					JLabel myTasks = new JLabel("My Tasks");
					myRepairs.setBounds(50, 350, 100, 50);
					myTasks.setBounds(550, 350, 100, 50);
					myPane.setBounds(25, 400, 300, 300);
					taskPane.setBounds(400, 400, 300, 300);
					
					container.add(myRepairs);
					container.add(myTasks);
					container.add(myPane);
					container.add(taskPane);
					


				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The Fields are incorrect");
				}
			}
		});
		container.add(searchLabel);
		container.add(searchField);
		container.add(addButton);

		this.setTitle("Assigning task to employee");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		
	}

	private void searchByPartNumber() {
		System.out.println("searchbyID called");
		container.removeAll();
		container.add(toolBar);

		JLabel searchLabel = new JLabel("Enter Part Number");
		JTextField searchField = new JTextField();
		JButton addButton = new JButton("Search");

		searchLabel.setBounds(25, 100, 150, 30);
		searchField.setBounds(150, 100, 150, 30);
		addButton.setBounds(100, 600, 100, 30);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {

					int number = Integer.parseInt(searchField.getText());
					
					JScrollPane myPane = new JScrollPane();

					ArrayList<Part> part = app.searchByPartNumber(number);
					
					
					
					JTable myTable = new JTable();
					DefaultTableModel myModel = new DefaultTableModel();
					String[] myColumns = { "Name" , "PartNumber" , "Price"};
					myTable.setModel(myModel);
					myModel.setColumnIdentifiers(myColumns);
					myPane.setViewportView(myTable);
					Object[] myRow = new Object[3];
					for (int i = 0; i < part.size(); i++) {
						myRow[0] = part.get(i).Name;
						myRow[1] = part.get(i).PartNumber;
						myRow[2] = part.get(i).Price;
						myModel.addRow(myRow);
					}
					myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

					for (int i = 0; i < myRow.length; i++) {
						myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
					}
					
					
					myPane.setBounds(25, 140, 300, 300);
					container.add(myPane);
					
			

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The Fields are incorrect");
				}
			}
		});
		container.add(searchLabel);
		container.add(searchField);
		container.add(addButton);

		this.setTitle("Search by Part Number");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
	}

	private void assignTaskToEmployee() {
		container.removeAll();
		container.add(toolBar);

		JLabel manager = new JLabel(this.userName);

		ArrayList<Task> myTasks = app.getTasks();
		Integer[] tasks = new Integer[myTasks.size()];
		for (int i = 0; i < myTasks.size(); i++) {
			tasks[i] = myTasks.get(i).getID();
		}

		JLabel TaskLabel = new JLabel("Task");
		JComboBox<Integer> TaskChooser = new JComboBox<Integer>(tasks);

		ArrayList<Employee> employees = app.getEmployees();
		String[] emps = new String[employees.size()];
		for (int i = 0; i < employees.size(); i++) {
			emps[i] = employees.get(i).getUsername();
		}

		JLabel empLabel = new JLabel("Employee");
		JComboBox<String> EmployeeChooser = new JComboBox<String>(emps);

		JButton addButton = new JButton("Assign Task to Employee");

		TaskLabel.setBounds(25, 100, 100, 30);
		TaskChooser.setBounds(100, 100, 100, 30);

		empLabel.setBounds(25, 150, 100, 30);
		EmployeeChooser.setBounds(100, 150, 100, 30);
		addButton.setBounds(100, 300, 100, 30);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {

					String managerUserName = manager.getText();
					String employeeUserName = (String) EmployeeChooser.getSelectedItem();
					Integer TaskID = (Integer) TaskChooser.getSelectedItem();
					app.assign(managerUserName, employeeUserName, TaskID);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The Fields are incorrect");
				}
			}
		});
		container.add(TaskChooser);
		container.add(TaskLabel);

		container.add(EmployeeChooser);
		container.add(empLabel);

		container.add(addButton);

		this.setTitle("Assigning task to employee");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

	private void updateAssignTaskToEmployee() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Assign> myAssign = app.getAssignments();
		ArrayList<Employee> myEmployees = app.getEmployees();
		ArrayList<Manager> myManagers = app.getManagers();
		ArrayList<Task> myTasks = app.getTasks();

		String[] beforeAssign = new String[myAssign.size()];
		String[] afterEmployee = new String[myEmployees.size()];
		Integer[] afterTask = new Integer[myTasks.size()];
		
		int sizeCounter = 0;
		for (int i = 0; i < myAssign.size(); i++) {
			if(myAssign.get(i).ManagerUserName.equals(this.userName)) {
				sizeCounter++;
			}
		}
		beforeAssign = new String[sizeCounter];
		int myCounter = 0;
		for (int i = 0; i < myAssign.size(); i++) {
			if(myAssign.get(i).ManagerUserName.equals(this.userName)) {
				beforeAssign[myCounter] = myAssign.get(i).ManagerUserName + "/"+myAssign.get(i).TaskID + "/" + myAssign.get(i).EmployeeUserName;
				myCounter++;
			}
		}
		for (int i = 0; i < myEmployees.size(); i++) {
			afterEmployee[i] = myEmployees.get(i).getUsername();
		}
		for (int i = 0; i < myTasks.size(); i++) {
			afterTask[i] = myTasks.get(i).getID();
		}

		JLabel beforePartNumberLabel = new JLabel("BeforeAssign");
		JComboBox<String> beforePartNumberChooser = new JComboBox<String>(beforeAssign);

		JLabel afterVehicleVIN = new JLabel("AfterEmployee");
		JComboBox<String> afterVehicleVinchooser = new JComboBox<>(afterEmployee);

		JLabel afterRepairLabel = new JLabel("AfterManager");
		JLabel afterRepairChooser = new JLabel(this.userName);

		JLabel afterTaskLabel = new JLabel("AfterTaskID");
		JComboBox<Integer> afterTaskChooser = new JComboBox<>(afterTask);

		JButton addButton = new JButton("UPDATE");

		beforePartNumberLabel.setBounds(25, 50, 150, 30);
		beforePartNumberChooser.setBounds(150, 50, 150, 30);

		afterVehicleVIN.setBounds(25, 100, 150, 30);
		afterVehicleVinchooser.setBounds(150, 100, 150, 30);

		afterRepairLabel.setBounds(25, 150, 150, 30);
		afterRepairChooser.setBounds(150, 150, 150, 30);

		afterTaskLabel.setBounds(25, 200, 150, 30);
		afterTaskChooser.setBounds(150, 200, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String[] splits = ((String)beforePartNumberChooser.getSelectedItem()).split("/") ;
					String BeforeManagerUserName = splits[0];
					String BeforeEmployeeUserName = splits[2];
					Integer BeforeTaskID = Integer.parseInt(splits[1]);
					String AfterManagerUserName = (String) afterRepairChooser.getText();
					String AfterEmployeeUserName = (String) afterVehicleVinchooser.getSelectedItem();
					Integer AfterTaskID = (Integer) afterTaskChooser.getSelectedItem();
					System.out.println(AfterEmployeeUserName);
					if (BeforeManagerUserName != null && BeforeTaskID != null && BeforeEmployeeUserName != null && AfterManagerUserName != null
							&& AfterEmployeeUserName.length() != 0 && AfterTaskID != 0) {
						app.updateAssign(BeforeManagerUserName, BeforeEmployeeUserName, BeforeTaskID, AfterManagerUserName, AfterEmployeeUserName, AfterTaskID);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(beforePartNumberLabel);
		container.add(beforePartNumberChooser);

		container.add(afterVehicleVinchooser);
		container.add(afterVehicleVIN);

		container.add(afterRepairChooser);
		container.add(afterRepairLabel);

		container.add(afterTaskLabel);
		container.add(afterTaskChooser);

		container.add(addButton);

		this.setTitle("Update Assign");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	private void deleteAssignTaskToEmployee() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Assign> myAssign = app.getAssignments();

		String[] beforeAssign = new String[myAssign.size()];
		int sizeCounter = 0;
		for (int i = 0; i < myAssign.size(); i++) {
			if(myAssign.get(i).ManagerUserName.equals(this.userName)) {
				sizeCounter++;
			}
		}
		beforeAssign = new String[sizeCounter];
		int myCounter = 0;
		for (int i = 0; i < myAssign.size(); i++) {
			if(myAssign.get(i).ManagerUserName.equals(this.userName)) {
				beforeAssign[myCounter] = myAssign.get(i).ManagerUserName + "/"+myAssign.get(i).TaskID + "/" + myAssign.get(i).EmployeeUserName;
				myCounter++;
			}
		}
		JLabel beforePartNumberLabel = new JLabel("Assignments");
		JComboBox<String> beforePartNumberChooser = new JComboBox<String>(beforeAssign);

		JButton addButton = new JButton("DELETE");

		beforePartNumberLabel.setBounds(25, 50, 150, 30);
		beforePartNumberChooser.setBounds(150, 50, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String[] splits = ((String)beforePartNumberChooser.getSelectedItem()).split("/") ;
					String BeforeManagerUserName = splits[0];
					String BeforeEmployeeUserName = splits[2];
					Integer BeforeTaskID = Integer.parseInt(splits[1]);
					
					if (BeforeManagerUserName != null && BeforeTaskID != null && BeforeEmployeeUserName != null) {
						app.deleteAssign(BeforeManagerUserName, BeforeTaskID, BeforeEmployeeUserName);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(beforePartNumberLabel);
		container.add(beforePartNumberChooser);

		container.add(addButton);

		this.setTitle("Update Assign");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
	}

	private void readAssignTaskToEmployee() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Assign> myAssigns = app.getAssignments();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "ManagerUserName", "TaskID", "EmployeeUserName"};
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[3];
		for (int i = 0; i < myAssigns.size(); i++) {
			myRow[0] = myAssigns.get(i).ManagerUserName;
			myRow[1] = myAssigns.get(i).TaskID;
			myRow[2] = myAssigns.get(i).EmployeeUserName;
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 300, 300);
		container.add(myPane);

		this.setTitle("Read Assign");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void updateRepair() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Repair> myRepairs = app.getRepairs();
		System.out.println("repairs gotten - update" + myRepairs.size());
		Integer[] repairIdsd = new Integer[myRepairs.size()];
		for (int i = 0; i < myRepairs.size(); i++) {
			repairIdsd[i] = myRepairs.get(i).getID();
			System.out.println("id is " + myRepairs.get(i).getID());
		}

		Integer[] completion = new Integer[2];
		completion[0] = 0;
		completion[1] = 1;

		JLabel IDLabel = new JLabel("ID");
		JComboBox<Integer> IDChooser = new JComboBox<Integer>(repairIdsd);

		JLabel updatestartDateLabel = new JLabel("Start Date");
		JTextField updatestartDateChooser = new JTextField();

		JLabel updateendDateLabel = new JLabel("End Date");
		JTextField updateendDateChooser = new JTextField();

		JLabel updatedescriptionLabel = new JLabel("Description");
		JTextField updatedescriptionBox = new JTextField();

		JLabel updatediscountLabel = new JLabel("Discount");
		JTextField updatediscountField = new JTextField();

		JLabel updatecostLabel = new JLabel("Cost");
		JTextField updatecostField = new JTextField();

		JLabel completionLabel = new JLabel("Completion");
		JComboBox<Integer> updateCompletionChooser = new JComboBox<Integer>(completion);

		JButton addButton = new JButton("UPDATE");

		IDLabel.setBounds(25, 50, 150, 30);
		IDChooser.setBounds(100, 50, 150, 30);

		updatestartDateLabel.setBounds(25, 100, 150, 30);
		updatestartDateChooser.setBounds(100, 100, 150, 30);

		updateendDateLabel.setBounds(25, 150, 150, 30);
		updateendDateChooser.setBounds(100, 150, 150, 30);

		updatedescriptionLabel.setBounds(25, 200, 150, 30);
		updatedescriptionBox.setBounds(100, 200, 150, 30);

		updatecostLabel.setBounds(25, 250, 150, 30);
		updatecostField.setBounds(100, 250, 150, 30);

		updatediscountLabel.setBounds(25, 300, 150, 30);
		updatediscountField.setBounds(100, 300, 150, 30);

		completionLabel.setBounds(25, 350, 150, 30);
		updateCompletionChooser.setBounds(100, 350, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					int ID = (Integer) IDChooser.getSelectedItem();
					String startDate = updatestartDateChooser.getText();
					String endDate = updateendDateChooser.getText();
					String discount = updatediscountField.getText();
					String totalCost = updatecostField.getText();
					String description = updatedescriptionBox.getText();
					int completion = (Integer) updateCompletionChooser.getSelectedItem();
					System.out.println("ID: " + ID);
					System.out.println("startDate" + startDate);
					System.out.println("endDate" + endDate);
					System.out.println("discount" + discount);
					System.out.println("totalCost" + totalCost);
					System.out.println("description" + description);
					System.out.println("Completion" + completion);
					app.updateRepair(ID, startDate, endDate, description, Integer.parseInt(discount),
							Integer.parseInt(totalCost), completion);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "The Fields are incorrect");
				}
			}
		});

		container.add(IDChooser);
		container.add(IDLabel);

		container.add(updateCompletionChooser);
		container.add(completionLabel);

		container.add(updatestartDateChooser);
		container.add(updatestartDateLabel);

		container.add(updateendDateLabel);
		container.add(updateendDateChooser);

		container.add(updatedescriptionLabel);
		container.add(updatedescriptionBox);

		container.add(updatediscountLabel);
		container.add(updatediscountField);

		container.add(updatecostField);
		container.add(updatecostLabel);

		container.add(addButton);

		this.setTitle("Update Repair");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

	public void addRepair() {

		container.removeAll();
		container.add(toolBar);

		// Set up
		JLabel startDateLabel = new JLabel("StartDate");
		JTextField startDateChooser = new JTextField();

		JLabel endDateLabel = new JLabel("EndDate");
		JTextField endDateChooser = new JTextField();

		JLabel descriptionLabel = new JLabel("Description");
		JTextArea descriptionBox = new JTextArea();

		JLabel costLabel = new JLabel("Cost");
		JTextField costField = new JTextField();

		JLabel discountLabel = new JLabel("Discount");
		JTextField discountField = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		startDateLabel.setBounds(25, 50, 150, 30);
		startDateChooser.setBounds(100, 50, 150, 30);

		endDateLabel.setBounds(25, 100, 150, 30);
		endDateChooser.setBounds(100, 100, 150, 30);

		descriptionLabel.setBounds(25, 150, 150, 30);
		descriptionBox.setBounds(100, 150, 150, 60);

		costLabel.setBounds(25, 250, 150, 30);
		costField.setBounds(100, 250, 150, 30);

		discountLabel.setBounds(25, 300, 100, 30);
		discountField.setBounds(100, 300, 100, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String startDate = startDateChooser.getText();
					String endDate = endDateChooser.getText();
					String discount = discountField.getText();
					String totalCost = costField.getText();
					String description = descriptionBox.getText();

					if (ManagerFrame.compareDate(startDate, endDate)) {
						// reject
					}

					if (Integer.parseInt(discount) < 0) {
						JOptionPane.showMessageDialog(null, "Discount can't be less than 0 ");
						return;
					}

					if (Integer.parseInt(discount) > 100) {
						JOptionPane.showMessageDialog(null, "Discount can't be more than 100 ");
						return;
					}

					app.addRepair(startDate, endDate, Integer.parseInt(discount), Integer.parseInt(totalCost),
							description);

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please check your inputted information.");
				}
			}
		});
		container.add(startDateChooser);
		container.add(startDateLabel);

		container.add(endDateLabel);
		container.add(endDateChooser);

		container.add(descriptionLabel);
		container.add(descriptionBox);

		container.add(costField);
		container.add(costLabel);

		container.add(discountField);
		container.add(discountLabel);

		container.add(addButton);

		this.setTitle("Add Repair");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteRepair() {
		container.removeAll();
		container.add(toolBar);
		ArrayList<Repair> myRepairs = app.getRepairs();
		Integer[] repairIdsd = new Integer[myRepairs.size()];
		for (int i = 0; i < myRepairs.size(); i++) {
			repairIdsd[i] = myRepairs.get(i).getID();
		}
		JLabel idLabel = new JLabel("ID");
		JComboBox<Integer> idChooser = new JComboBox<Integer>(repairIdsd);

		JButton deleteButton = new JButton("DELETE");

		idLabel.setBounds(25, 50, 150, 30);
		idChooser.setBounds(100, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					int id = (Integer) idChooser.getSelectedItem();
					app.deleteRepair(id);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}

		});
		container.add(idLabel);
		container.add(idChooser);
		container.add(deleteButton);

		this.setTitle("Delete Repair");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

	public void readAllRepairs() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Repair> myRepairs = app.getRepairs();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "ID", "StartDate", "EndDate", "Description", "Discount", "TotalCost", "Completion" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[7];
		for (int i = 0; i < myRepairs.size(); i++) {
			myRow[0] = myRepairs.get(i).getID();
			myRow[1] = myRepairs.get(i).getstartDate();
			myRow[2] = myRepairs.get(i).getendDate();
			myRow[3] = myRepairs.get(i).getDescription();
			myRow[4] = myRepairs.get(i).getDiscount();
			myRow[5] = myRepairs.get(i).getTotalCost();
			myRow[6] = myRepairs.get(i).getCompletion();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 700, 300);
		container.add(myPane);

		this.setTitle("Read Repairs");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addVehicle() {
		container.removeAll();
		container.add(toolBar);

		// Set up
		JLabel vinLabel = new JLabel("VIN(17)");
		JTextField vinChooser = new JTextField();

		JLabel yearLabel = new JLabel("Year");
		JTextField yearChooser = new JTextField();

		JLabel modelLabel = new JLabel("Model");
		JTextField ModelChooser = new JTextField();

		JLabel mileageLabel = new JLabel("Mileage");
		JTextField mileageChooser = new JTextField();

		JLabel bodyTypeLabel = new JLabel("BodyType");
		JTextField bodyTypeChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(100, 50, 150, 30);

		yearLabel.setBounds(25, 100, 150, 30);
		yearChooser.setBounds(100, 100, 150, 30);

		modelLabel.setBounds(25, 150, 150, 30);
		ModelChooser.setBounds(100, 150, 150, 30);

		mileageLabel.setBounds(25, 250, 150, 30);
		mileageChooser.setBounds(100, 250, 150, 30);

		bodyTypeLabel.setBounds(25, 300, 150, 30);
		bodyTypeChooser.setBounds(100, 300, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String VIN = (String) vinChooser.getText();
					int Year = Integer.parseInt(yearChooser.getText());
					String Model = ModelChooser.getText();
					int Mileage = Integer.parseInt(mileageChooser.getText());
					String BodyType = bodyTypeChooser.getText();

					if (Year > 1800 && Year <= Calendar.getInstance().get(Calendar.YEAR) + 1 && VIN.length() == 17
							&& Model.length() != 0 && Mileage >= 0 && BodyType.length() != 0) {
						app.addVehicle(VIN, Year, Model, Mileage, BodyType);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(vinLabel);
		container.add(vinChooser);

		container.add(yearLabel);
		container.add(yearChooser);

		container.add(modelLabel);
		container.add(ModelChooser);

		container.add(mileageLabel);
		container.add(mileageChooser);

		container.add(bodyTypeLabel);
		container.add(bodyTypeChooser);

		container.add(addButton);

		this.setTitle("Add Vehicle");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateVehicle() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		String[] myVins = new String[myVehicles.size()];
		for (int i = 0; i < myVehicles.size(); i++) {
			myVins[i] = myVehicles.get(i).getVin();
		}

		JLabel vinLabel = new JLabel("VIN");
		JComboBox<String> vinChooser = new JComboBox<String>(myVins);

		JLabel yearLabel = new JLabel("Year");
		JTextField yearChooser = new JTextField();

		JLabel modelLabel = new JLabel("Model");
		JTextField ModelChooser = new JTextField();

		JLabel mileageLabel = new JLabel("Mileage");
		JTextField mileageChooser = new JTextField();

		JLabel bodyTypeLabel = new JLabel("BodyType");
		JTextField bodyTypeChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(100, 50, 250, 30);

		yearLabel.setBounds(25, 100, 150, 30);
		yearChooser.setBounds(100, 100, 150, 30);

		modelLabel.setBounds(25, 150, 150, 30);
		ModelChooser.setBounds(100, 150, 150, 30);

		mileageLabel.setBounds(25, 250, 150, 30);
		mileageChooser.setBounds(100, 250, 150, 30);

		bodyTypeLabel.setBounds(25, 300, 150, 30);
		bodyTypeChooser.setBounds(100, 300, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String VIN = (String) vinChooser.getSelectedItem();

					int Year = Integer.parseInt(yearChooser.getText());
					String Model = ModelChooser.getText();
					int Mileage = Integer.parseInt(mileageChooser.getText());
					String BodyType = bodyTypeChooser.getText();

					if (VIN.length() == 17) {
						app.updateVehicle(VIN, Year, Model, Mileage, BodyType);
					} else {
						JOptionPane.showMessageDialog(null, "Vin is invalid");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(vinLabel);
		container.add(vinChooser);

		container.add(yearLabel);
		container.add(yearChooser);

		container.add(modelLabel);
		container.add(ModelChooser);

		container.add(mileageLabel);
		container.add(mileageChooser);

		container.add(bodyTypeLabel);
		container.add(bodyTypeChooser);

		container.add(addButton);

		this.setTitle("Update Vehicle");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteVehicle() {
		container.removeAll();
		container.add(toolBar);
		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		String[] vinNumbers = new String[myVehicles.size()];
		for (int i = 0; i < myVehicles.size(); i++) {
			vinNumbers[i] = myVehicles.get(i).getVin();
		}
		JLabel vinLabel = new JLabel("VIN");
		JComboBox<String> vinChooser = new JComboBox<String>(vinNumbers);

		JButton deleteButton = new JButton("DELETE");

		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(100, 50, 250, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String id = (String) vinChooser.getSelectedItem();
				if (id.length() != 0) {
					app.deleteVehicle(id);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(vinLabel);
		container.add(vinChooser);
		container.add(deleteButton);

		this.setTitle("Delete Vehicle");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllVehicle() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "VIN", "Year", "Model", "Mileage", "BodyType" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[5];
		for (int i = 0; i < myVehicles.size(); i++) {
			myRow[0] = myVehicles.get(i).getVin();
			myRow[1] = myVehicles.get(i).getYear();
			myRow[2] = myVehicles.get(i).getModel();
			myRow[3] = myVehicles.get(i).getMileage();
			myRow[4] = myVehicles.get(i).getBodyType();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		myPane.setBounds(30, 30, 600, 300);
		container.add(myPane);

		this.setTitle("Read Vehicles");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addTask() {
		container.removeAll();
		container.add(toolBar);

		// Set up
		JLabel nameLabel = new JLabel("Name");
		JTextField nameChooser = new JTextField();

		JLabel descriptionLabel = new JLabel("Description");
		JTextArea descriptionChooser = new JTextArea();

		JLabel priceLabel = new JLabel("Price");
		JTextField priceChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		nameLabel.setBounds(25, 50, 150, 30);
		nameChooser.setBounds(100, 50, 150, 30);

		descriptionLabel.setBounds(25, 100, 150, 30);
		descriptionChooser.setBounds(100, 100, 200, 60);

		priceLabel.setBounds(25, 200, 150, 30);
		priceChooser.setBounds(100, 200, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String Name = (String) nameChooser.getText();
					String Description = (descriptionChooser.getText());
					int Price = Integer.parseInt(priceChooser.getText());

					if (Name.length() != 0 && Price >= 0) {
						app.addTask(Name, Description, Price);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(nameLabel);
		container.add(nameChooser);

		container.add(descriptionLabel);
		container.add(descriptionChooser);

		container.add(priceLabel);
		container.add(priceChooser);

		container.add(addButton);

		this.setTitle("Add Task");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateTask() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Task> myTasks = app.getTasks();
		Integer[] myIDS = new Integer[myTasks.size()];
		for (int i = 0; i < myTasks.size(); i++) {
			myIDS[i] = myTasks.get(i).getID();
		}

		JLabel IDLabel = new JLabel("ID");
		JComboBox<Integer> IDChooser = new JComboBox<>(myIDS);

		JLabel nameLabel = new JLabel("Name");
		JTextField nameChooser = new JTextField();

		JLabel descriptionLabel = new JLabel("Description");
		JTextArea descriptionChooser = new JTextArea();

		JLabel priceLabel = new JLabel("Price");
		JTextField priceChooser = new JTextField();

		JLabel completionLabel = new JLabel("Completion");
		JTextField completionChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		IDLabel.setBounds(25, 50, 150, 30);
		IDChooser.setBounds(100, 50, 150, 30);

		nameLabel.setBounds(25, 100, 150, 30);
		nameChooser.setBounds(100, 100, 150, 30);

		descriptionLabel.setBounds(25, 150, 150, 30);
		descriptionChooser.setBounds(100, 150, 200, 50);

		priceLabel.setBounds(25, 250, 150, 30);
		priceChooser.setBounds(100, 250, 150, 30);

		completionLabel.setBounds(25, 300, 200, 30);
		completionChooser.setBounds(100, 300, 200, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					Integer ID = (Integer) IDChooser.getSelectedItem();
					String Name = (nameChooser.getText());
					String Description = descriptionChooser.getText();
					int Price = Integer.parseInt(priceChooser.getText());
					int Completion = Integer.parseInt(completionChooser.getText());
					if (Name.length() != 0 && Price > 0 && (Completion == 0 || Completion == 1)) {
						app.updateTask(ID, Name, Description, Price, Completion);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(IDLabel);
		container.add(IDChooser);

		container.add(nameLabel);
		container.add(nameChooser);

		container.add(descriptionLabel);
		container.add(descriptionChooser);

		container.add(priceLabel);
		container.add(priceChooser);

		container.add(completionLabel);
		container.add(completionChooser);

		container.add(addButton);

		this.setTitle("Update Task");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteTask() {
		container.removeAll();
		container.add(toolBar);
		ArrayList<Task> myTasks = app.getTasks();
		Integer[] idNumbers = new Integer[myTasks.size()];
		for (int i = 0; i < myTasks.size(); i++) {
			idNumbers[i] = myTasks.get(i).getID();
		}
		JLabel idLabel = new JLabel("ID");
		JComboBox<Integer> idChooser = new JComboBox<>(idNumbers);

		JButton deleteButton = new JButton("DELETE");

		idLabel.setBounds(25, 50, 150, 30);
		idChooser.setBounds(100, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					Integer id = (Integer) idChooser.getSelectedItem();
					app.deleteTask(id);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(idLabel);
		container.add(idChooser);
		container.add(deleteButton);

		this.setTitle("Delete Task");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllTasks() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Task> myTasks = app.getTasks();
		System.out.println(myTasks.get(0).Price);
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "ID", "Name", "Description", "Price", "Completion" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[5];
		for (int i = 0; i < myTasks.size(); i++) {
			myRow[0] = myTasks.get(i).getID();
			myRow[1] = myTasks.get(i).getName();
			myRow[2] = myTasks.get(i).getDescription();
			myRow[3] = myTasks.get(i).getPrice();
			myRow[4] = myTasks.get(i).getCompletion();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 500, 300);
		container.add(myPane);

		this.setTitle("Read Tasks");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addPart() {
		container.removeAll();
		container.add(toolBar);

		// Set up
		JLabel partNumberLabel = new JLabel("PartNumber");
		JTextField partNumberChooser = new JTextField();

		JLabel nameLabel = new JLabel("Name");
		JTextField nameLabelChooser = new JTextField();

		JLabel priceLabel = new JLabel("Price");
		JTextField priceChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		partNumberLabel.setBounds(25, 50, 150, 30);
		partNumberChooser.setBounds(100, 50, 150, 30);

		nameLabel.setBounds(25, 100, 150, 30);
		nameLabelChooser.setBounds(100, 100, 150, 30);

		priceLabel.setBounds(25, 150, 150, 30);
		priceChooser.setBounds(100, 150, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					Integer PartNumber = Integer.parseInt(partNumberChooser.getText());
					String Name = (nameLabelChooser.getText());
					int Price = Integer.parseInt(priceChooser.getText());

					if (Name.length() != 0 && Price >= 0 && PartNumber != null) {
						app.addPart(PartNumber, Name, Price);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(partNumberLabel);
		container.add(partNumberChooser);

		container.add(nameLabel);
		container.add(nameLabelChooser);

		container.add(priceLabel);
		container.add(priceChooser);

		container.add(addButton);

		this.setTitle("Add Part");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updatePart() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Part> myParts = app.getParts();
		Integer[] myPartNumbers = new Integer[myParts.size()];
		for (int i = 0; i < myParts.size(); i++) {
			myPartNumbers[i] = myParts.get(i).getPartNumber();
		}

		JLabel partNumberLabel = new JLabel("PartNumber");
		JComboBox<Integer> partNumberChooser = new JComboBox<Integer>(myPartNumbers);

		JLabel nameLabel = new JLabel("Name");
		JTextField nameChooser = new JTextField();

		JLabel priceLabel = new JLabel("Price");
		JTextField priceChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		partNumberLabel.setBounds(25, 50, 150, 30);
		partNumberChooser.setBounds(100, 50, 150, 30);

		nameLabel.setBounds(25, 100, 150, 30);
		nameChooser.setBounds(100, 100, 150, 30);

		priceLabel.setBounds(25, 150, 150, 30);
		priceChooser.setBounds(100, 150, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Integer PartNumber = (Integer) (partNumberChooser.getSelectedItem());
					int Price = Integer.parseInt(priceChooser.getText());
					String Name = nameChooser.getText();
					System.out.println("Price: " + Price);
					if (Price >= 0 && Name.length() != 0) {
						app.updatePart(PartNumber, Name, Price);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(partNumberLabel);
		container.add(partNumberChooser);

		container.add(nameLabel);
		container.add(nameChooser);

		container.add(priceLabel);
		container.add(priceChooser);

		container.add(addButton);

		this.setTitle("Update Part");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deletePart() {
		container.removeAll();
		container.add(toolBar);
		ArrayList<Part> myParts = app.getParts();
		Integer[] partNumbers = new Integer[myParts.size()];
		for (int i = 0; i < myParts.size(); i++) {
			partNumbers[i] = myParts.get(i).getPartNumber();
		}
		JLabel partNumberLabel = new JLabel("PartNumber");
		JComboBox<Integer> partNumberChooser = new JComboBox<Integer>(partNumbers);

		JButton deleteButton = new JButton("DELETE");

		partNumberLabel.setBounds(25, 50, 150, 30);
		partNumberChooser.setBounds(100, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Integer id = (Integer) partNumberChooser.getSelectedItem();
				if (id != null) {
					app.deletePart(id);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(partNumberLabel);
		container.add(partNumberChooser);
		container.add(deleteButton);

		this.setTitle("Delete Part");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllParts() {
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
		Object[] myRow = new Object[3];
		for (int i = 0; i < myParts.size(); i++) {
			myRow[0] = myParts.get(i).getPartNumber();
			myRow[1] = myParts.get(i).getName();
			myRow[2] = myParts.get(i).getPrice();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 300, 300);
		container.add(myPane);

		this.setTitle("Read Parts");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addEmployee() { // 800x800
		container.removeAll();
		container.add(toolBar);

		// Set up
		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameChooser = new JTextField();

		JLabel firstnameLabel = new JLabel("First Name");
		JTextField firstnameChooser = new JTextField();

		JLabel lastnameLabel = new JLabel("Last Name");
		JTextField lastnameChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		usernameLabel.setBounds(25, 50, 150, 30);
		usernameChooser.setBounds(100, 50, 150, 30);

		firstnameLabel.setBounds(25, 100, 150, 30);
		firstnameChooser.setBounds(100, 100, 150, 30);

		lastnameLabel.setBounds(25, 150, 150, 30);
		lastnameChooser.setBounds(100, 150, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String Username = (String) usernameChooser.getText();
					String FirstName = firstnameChooser.getText();
					String LastName = lastnameChooser.getText();

					if (Username.length() != 0 && FirstName.length() != 0 && LastName.length() != 0) {
						app.addEmployee(Username, FirstName, LastName);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(usernameLabel);
		container.add(usernameChooser);

		container.add(firstnameLabel);
		container.add(firstnameChooser);

		container.add(lastnameLabel);
		container.add(lastnameChooser);

		container.add(addButton);

		this.setTitle("Add Employee");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateEmployee() { // 800x800
		container.removeAll();
		container.add(toolBar);

		ArrayList<Employee> myEmployees = app.getEmployees();
		String[] myUsernames = new String[myEmployees.size()];
		for (int i = 0; i < myEmployees.size(); i++) {
			myUsernames[i] = myEmployees.get(i).getUsername();
		}

		JLabel userNameLabel = new JLabel("Username");
		JComboBox<String> userNameChooser = new JComboBox<String>(myUsernames);

		JLabel firstnameLabel = new JLabel("First Name");
		JTextField firstNameChooser = new JTextField();

		JLabel lastnameLabel = new JLabel("Last Name");
		JTextField lastNameChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		userNameLabel.setBounds(25, 50, 150, 30);
		userNameChooser.setBounds(100, 50, 150, 30);

		firstnameLabel.setBounds(25, 100, 150, 30);
		firstNameChooser.setBounds(100, 100, 150, 30);

		lastnameLabel.setBounds(25, 150, 150, 30);
		lastNameChooser.setBounds(100, 150, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					String Username = (String) userNameChooser.getSelectedItem();
					String FirstName = (firstNameChooser.getText());
					String LastName = lastNameChooser.getText();
					if (Username.length() != 0 && FirstName.length() != 0 && LastName.length() != 0) {
						app.updateEmployee(Username, FirstName, LastName);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(userNameLabel);
		container.add(userNameChooser);

		container.add(firstnameLabel);
		container.add(firstNameChooser);

		container.add(lastnameLabel);
		container.add(lastNameChooser);

		container.add(addButton);

		this.setTitle("Update Employee");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteEmployee() {
		container.removeAll();
		container.add(toolBar);
		ArrayList<Employee> myEmployees = app.getEmployees();

		String[] userNames = new String[myEmployees.size()];

		for (int i = 0; i < myEmployees.size(); i++) {
			userNames[i] = myEmployees.get(i).getUsername();
		}
		JLabel userNameLabel = new JLabel("Username");

		JComboBox<String> userNameChooser = new JComboBox<String>(userNames);

		JButton deleteButton = new JButton("DELETE");

		userNameLabel.setBounds(25, 50, 150, 30);
		userNameChooser.setBounds(100, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = (String) userNameChooser.getSelectedItem();
				if (id != null) {
					app.deleteEmployee(id);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(userNameLabel);
		container.add(userNameChooser);
		container.add(deleteButton);

		this.setTitle("Delete Employee");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllEmployees() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<EmployeeView> myEmployees = app.getEmployeeViews();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "Username","FirstName","LastName" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[3];
		for (int i = 0; i < myEmployees.size(); i++) {
			myRow[0] = myEmployees.get(i).Username;
			myRow[1] = myEmployees.get(i).FirstName;
			myRow[2] = myEmployees.get(i).LastName;
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 300, 300);
		container.add(myPane);

		this.setTitle("Read All Employees");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	

	public void addCustomer() {
		container.removeAll();
		container.add(toolBar);
		// Set up
		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameChooser = new JTextField();

		JLabel firstnameLabel = new JLabel("FirstName");
		JTextField firstnameChooser = new JTextField();

		JLabel lastnameLabel = new JLabel("LastName");
		JTextField lastnameChooser = new JTextField();

		JLabel numberVisitsLabel = new JLabel("NumberOfVisits");
		JTextField numberVisitsChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		usernameLabel.setBounds(25, 50, 150, 30);
		usernameChooser.setBounds(150, 50, 150, 30);

		firstnameLabel.setBounds(25, 100, 150, 30);
		firstnameChooser.setBounds(150, 100, 150, 30);

		lastnameLabel.setBounds(25, 150, 150, 30);
		lastnameChooser.setBounds(150, 150, 150, 30);

		numberVisitsLabel.setBounds(25, 200, 150, 30);
		numberVisitsChooser.setBounds(150, 200, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String Username = (String) usernameChooser.getText();
					String FirstName = firstnameChooser.getText();
					String LastName = lastnameChooser.getText();
					int numberVists = Integer.parseInt(numberVisitsChooser.getText());

					if (Username.length() != 0 && FirstName.length() != 0 && LastName.length() != 0
							&& numberVists >= 0) {
						app.addCustomer(Username, FirstName, LastName, numberVists);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(usernameLabel);
		container.add(usernameChooser);

		container.add(firstnameLabel);
		container.add(firstnameChooser);

		container.add(lastnameLabel);
		container.add(lastnameChooser);

		container.add(numberVisitsLabel);
		container.add(numberVisitsChooser);

		container.add(addButton);

		this.setTitle("Add Customer");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateCustomer() { // 800x800
		container.removeAll();
		container.add(toolBar);

		ArrayList<Customer> myCustomers = app.getCustomers();
		String[] myUsernames = new String[myCustomers.size()];
		for (int i = 0; i < myCustomers.size(); i++) {
			myUsernames[i] = myCustomers.get(i).getusername();
		}

		JLabel userNameLabel = new JLabel("Username");
		JComboBox<String> userNameChooser = new JComboBox<String>(myUsernames);

		JLabel firstnameLabel = new JLabel("FirstName");
		JTextField firstNameChooser = new JTextField();

		JLabel lastnameLabel = new JLabel("LastName");
		JTextField lastNameChooser = new JTextField();

		JLabel numberVisitsLabel = new JLabel("NumberOfVisits");
		JTextField numberVisitsChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		userNameLabel.setBounds(25, 50, 150, 30);
		userNameChooser.setBounds(150, 50, 150, 30);

		firstnameLabel.setBounds(25, 100, 150, 30);
		firstNameChooser.setBounds(150, 100, 150, 30);

		lastnameLabel.setBounds(25, 150, 150, 30);
		lastNameChooser.setBounds(150, 150, 150, 30);

		numberVisitsLabel.setBounds(25, 200, 150, 30);
		numberVisitsChooser.setBounds(150, 200, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String Username = (String) userNameChooser.getSelectedItem();
					String FirstName = (firstNameChooser.getText());
					String LastName = lastNameChooser.getText();
					int numberVisits = Integer.parseInt(numberVisitsChooser.getText());
					if (Username.length() != 0 && FirstName.length() != 0 && LastName.length() != 0
							&& numberVisits >= 0) {
						app.updateCustomer(Username, FirstName, LastName, numberVisits);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(userNameLabel);
		container.add(userNameChooser);

		container.add(firstnameLabel);
		container.add(firstNameChooser);

		container.add(lastnameLabel);
		container.add(lastNameChooser);

		container.add(numberVisitsLabel);
		container.add(numberVisitsChooser);

		container.add(addButton);

		this.setTitle("Update Customer");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteCustomer() { // 800x800
		container.removeAll();
		container.add(toolBar);
		ArrayList<Customer> myCustomers = app.getCustomers();
		String[] userNames = new String[myCustomers.size()];
		for (int i = 0; i < myCustomers.size(); i++) {
			userNames[i] = myCustomers.get(i).getusername();
		}
		JLabel userNameLabel = new JLabel("Username");
		JComboBox<String> userNameChooser = new JComboBox<String>(userNames);

		JButton deleteButton = new JButton("DELETE");

		userNameLabel.setBounds(25, 50, 150, 30);
		userNameChooser.setBounds(100, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String id = (String) userNameChooser.getSelectedItem();
				if (id != null) {
					app.deleteCustomer(id);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(userNameLabel);
		container.add(userNameChooser);
		container.add(deleteButton);

		this.setTitle("Delete Customer");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllCustomers() { // 800x800
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<CustomerView> myCustomers = app.getCustomerViews();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "Username", "NumberOfVisits","FirstName", "LastName"};
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[4];
		for (int i = 0; i < myCustomers.size(); i++) {
			myRow[0] = myCustomers.get(i).Username;
			myRow[1] = myCustomers.get(i).NumberOfVisits;
			myRow[2] = myCustomers.get(i).FirstName;
			myRow[3] = myCustomers.get(i).LastName;
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		myPane.setBounds(30, 30, 400, 300);
		container.add(myPane);

		this.setTitle("Read All Customers");
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addInsurance() {
		container.removeAll();
		container.add(toolBar);

		// Set up
		JLabel claimNumberLabel = new JLabel("ClaimNumber(12)");
		JTextField claimNumberChooser = new JTextField();

		JLabel policyNumberLabel = new JLabel("PolicyNumber(11)");
		JTextField policyNumberChooser = new JTextField();

		JLabel deductibleLabel = new JLabel("Deductible");
		JTextField deductibleChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		claimNumberLabel.setBounds(25, 50, 150, 30);
		claimNumberChooser.setBounds(150, 50, 150, 30);

		policyNumberLabel.setBounds(25, 100, 150, 30);
		policyNumberChooser.setBounds(150, 100, 150, 30);

		deductibleLabel.setBounds(25, 150, 150, 30);
		deductibleChooser.setBounds(150, 150, 150, 60);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String ClaimNumber = (String) claimNumberChooser.getText();
					String PolicyNumber = policyNumberChooser.getText();
					int Deductible = Integer.parseInt(deductibleChooser.getText());

					if (ClaimNumber.length() == 12 && PolicyNumber.length() == 11 && Deductible >= 0) {
						app.addInsurance(ClaimNumber, PolicyNumber, Deductible);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(claimNumberLabel);
		container.add(claimNumberChooser);

		container.add(policyNumberLabel);
		container.add(policyNumberChooser);

		container.add(deductibleLabel);
		container.add(deductibleChooser);

		container.add(addButton);

		this.setTitle("Add Insurance");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateInsurance() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Insurance> myInsurances = app.getInsurance();
		String[] myClaimNumbers = new String[myInsurances.size()];
		for (int i = 0; i < myInsurances.size(); i++) {
			myClaimNumbers[i] = myInsurances.get(i).getClaimNumber();
		}

		JLabel claimNumberLabel = new JLabel("ClaimNumber");
		JComboBox<String> claimNumberChooser = new JComboBox<String>(myClaimNumbers);

		JLabel policyNumberLabel = new JLabel("PolicyNumber");
		JTextField policyNumberChooser = new JTextField();

		JLabel deductibleLabel = new JLabel("Deductible");
		JTextField deductibleChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		claimNumberLabel.setBounds(25, 50, 150, 30);
		claimNumberChooser.setBounds(150, 50, 150, 30);

		policyNumberLabel.setBounds(25, 100, 150, 30);
		policyNumberChooser.setBounds(150, 100, 150, 30);

		deductibleLabel.setBounds(25, 150, 150, 30);
		deductibleChooser.setBounds(150, 150, 150, 60);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String ClaimNumber = (String) claimNumberChooser.getSelectedItem();
					String PolicyNumber = (policyNumberChooser.getText());
					int deductible = Integer.parseInt(deductibleChooser.getText());

					if (ClaimNumber.length() != 0 && PolicyNumber.length() != 0 && deductible >= 0) {
						app.updateInsurance(ClaimNumber, PolicyNumber, deductible);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(claimNumberLabel);
		container.add(claimNumberChooser);

		container.add(policyNumberLabel);
		container.add(policyNumberChooser);

		container.add(deductibleLabel);
		container.add(deductibleChooser);

		container.add(addButton);

		this.setTitle("Update Insurance");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteInsurance() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Insurance> myInsurances = app.getInsurance();
		String[] myClaimNumbers = new String[myInsurances.size()];
		for (int i = 0; i < myInsurances.size(); i++) {
			myClaimNumbers[i] = myInsurances.get(i).getClaimNumber();
		}

		JLabel userNameLabel = new JLabel("ClaimNumber");
		JComboBox<String> userNameChooser = new JComboBox<String>(myClaimNumbers);

		JButton deleteButton = new JButton("DELETE");

		userNameLabel.setBounds(25, 50, 150, 30);
		userNameChooser.setBounds(150, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String id = (String) userNameChooser.getSelectedItem();
				if (id != null) {
					app.deleteInsurance(id);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(userNameLabel);
		container.add(userNameChooser);
		container.add(deleteButton);

		this.setTitle("Delete Insurance");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllInsurances() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Insurance> myInsurances = app.getInsurance();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "ClaimNumber", "PolicyNumber", "NumberOfVisits" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[3];
		for (int i = 0; i < myInsurances.size(); i++) {
			myRow[0] = myInsurances.get(i).getClaimNumber();
			myRow[1] = myInsurances.get(i).getPolicyNumber();
			myRow[2] = myInsurances.get(i).getDeductible();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 300, 300);
		container.add(myPane);

		this.setTitle("Read Insurances");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addOrder() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Manager> myManagers = app.getManagers();
		ArrayList<Part> myParts = app.getParts();
		String[] myManagerUserNames = new String[myManagers.size()];
		Integer[] partNumbers = new Integer[myParts.size()];
		for (int i = 0; i < myManagers.size(); i++) {
			myManagerUserNames[i] = myManagers.get(i).getUsername();
		}
		for (int i = 0; i < myParts.size(); i++) {
			partNumbers[i] = myParts.get(i).getPartNumber();
		}

		// Set up
		JLabel managerUserNameLabel = new JLabel("ManagerUserName");
		JComboBox<String> managerUserNameChooser = new JComboBox<String>();

		JLabel partNumberLabel = new JLabel("PartNumber");
		JComboBox<Integer> partNumberChooser = new JComboBox<Integer>();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		managerUserNameLabel.setBounds(25, 50, 150, 30);
		managerUserNameChooser.setBounds(100, 50, 150, 30);

		partNumberLabel.setBounds(25, 100, 150, 30);
		partNumberChooser.setBounds(100, 100, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String ManagerUserName = (String) managerUserNameChooser.getSelectedItem();
					Integer PartNumber = (Integer) partNumberChooser.getSelectedItem();

					if (ManagerUserName.length() != 0 && PartNumber != null) {
						app.addOrder(ManagerUserName, (int) PartNumber);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(managerUserNameLabel);
		container.add(managerUserNameChooser);

		container.add(partNumberLabel);
		container.add(partNumberChooser);

		container.add(addButton);

		this.setTitle("Add Order");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateOrder() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Order> myOrders = app.getOrders();
		ArrayList<Manager> myManagers = app.getManagers();
		ArrayList<Part> myParts = app.getParts();
		Order[] beforeCool = new Order[myOrders.size()];
		String[] aftermyManagerUserNames = new String[myManagers.size()];
		Integer[] afterpartNumbers = new Integer[myParts.size()];
		for (int i = 0; i < myOrders.size(); i++) {
			beforeCool[i] = myOrders.get(i);
		}
		for (int i = 0; i < myManagers.size(); i++) {
			aftermyManagerUserNames[i] = myManagers.get(i).getUsername();
		}
		for (int i = 0; i < myParts.size(); i++) {
			afterpartNumbers[i] = myParts.get(i).getPartNumber();
		}
		JLabel beforePartNumberLabel = new JLabel("BeforeOrder");
		JComboBox<Order> beforePartNumberChooser = new JComboBox<>(beforeCool);

		JLabel afterManagerUserNameLabel = new JLabel("AfterManagerUserName");
		JComboBox<String> afterManagerUserNameChooser = new JComboBox<String>(aftermyManagerUserNames);

		JLabel afterPartNumberLabel = new JLabel("AfterPartNumber");
		JComboBox<Integer> afterPartNumberChooser = new JComboBox<Integer>(afterpartNumbers);

		JButton addButton = new JButton("UPDATE");

		beforePartNumberLabel.setBounds(25, 50, 150, 30);
		beforePartNumberChooser.setBounds(100, 50, 150, 30);

		afterManagerUserNameLabel.setBounds(25, 100, 150, 30);
		afterManagerUserNameChooser.setBounds(100, 100, 150, 60);

		afterPartNumberLabel.setBounds(25, 150, 150, 30);
		afterPartNumberChooser.setBounds(100, 150, 150, 60);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String BeforeManagerUserName = ((Order) beforePartNumberChooser.getSelectedItem())
							.getManagerUserName();
					Integer BeforePartNumber = ((Order) beforePartNumberChooser.getSelectedItem()).getPartNumber();
					String AfterManagerUserName = (String) afterManagerUserNameChooser.getSelectedItem();
					Integer AfterPartNumber = (Integer) afterPartNumberChooser.getSelectedItem();
					boolean dontProceed = true;
					for (int i = 0; i < myOrders.size(); i++) {
						String currentOrderManagerUserName = myOrders.get(i).getManagerUserName();
						Integer currentPartNumber = myOrders.get(i).getPartNumber();
						if (AfterManagerUserName.equals(currentOrderManagerUserName)
								&& AfterPartNumber.equals(currentPartNumber)) {
							dontProceed = false;
							break;
						}
					}

					if (BeforeManagerUserName.length() != 0 && AfterManagerUserName.length() != 0
							&& BeforePartNumber != null && AfterPartNumber != null
							&& (!BeforeManagerUserName.equals(AfterManagerUserName)
									&& !BeforePartNumber.equals(AfterPartNumber))
							&& dontProceed) {
						app.updateOrders(BeforeManagerUserName, BeforePartNumber, AfterManagerUserName,
								AfterPartNumber);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(beforePartNumberLabel);
		container.add(beforePartNumberChooser);

		container.add(afterManagerUserNameLabel);
		container.add(afterManagerUserNameChooser);

		container.add(afterPartNumberLabel);
		container.add(afterPartNumberChooser);

		container.add(addButton);

		this.setTitle("Update Order");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteOrder() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Order> myOrders = app.getOrders();
		String[] myManagerUserNames = new String[myOrders.size()];
		Integer[] partNumbers = new Integer[myOrders.size()];
		for (int i = 0; i < myOrders.size(); i++) {
			myManagerUserNames[i] = myOrders.get(i).getManagerUserName();
			partNumbers[i] = myOrders.get(i).getPartNumber();
		}

		// Set up
		JLabel managerUserNameLabel = new JLabel("ManagerUserName");
		JComboBox<String> managerUserNameChooser = new JComboBox<String>(myManagerUserNames);

		JLabel partNumberLabel = new JLabel("PartNumber");
		JComboBox<Integer> partNumberChooser = new JComboBox<Integer>(partNumbers);

		JButton deleteButton = new JButton("DELETE");

		partNumberLabel.setBounds(25, 50, 150, 30);
		managerUserNameChooser.setBounds(100, 50, 150, 30);

		managerUserNameLabel.setBounds(25, 50, 150, 30);
		partNumberChooser.setBounds(100, 50, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Integer partNumber = (Integer) partNumberChooser.getSelectedItem();
				String managerUserName = (String) managerUserNameChooser.getSelectedItem();
				if (partNumber != null && managerUserName.length() != 0) {
					app.deleteOrders(managerUserName, partNumber);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(partNumberLabel);
		container.add(partNumberChooser);
		container.add(managerUserNameLabel);
		container.add(managerUserNameChooser);
		container.add(deleteButton);

		this.setTitle("Delete Order");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readOrders() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Order> myInsurances = app.getOrders();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "ManagerUserName", "PartNumber" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[2];
		for (int i = 0; i < myInsurances.size(); i++) {
			myRow[0] = myInsurances.get(i).getManagerUserName();
			myRow[1] = myInsurances.get(i).getPartNumber();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 200, 300);
		container.add(myPane);

		this.setTitle("Read Orders");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void updatemanager() {
		container.removeAll();
		container.add(toolBar);

		JLabel currentManagerlabel = new JLabel("Your Username");
		JLabel currentManagerText = new JLabel(this.userName);

		JLabel managerFirstNameLabel = new JLabel("Manager FirstName");
		JTextField managerFirstNameChooser = new JTextField();

		JLabel managerLastNameLabel = new JLabel("Manager LastName");
		JTextField managerLastNameChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		currentManagerlabel.setBounds(25, 50, 150, 30);
		currentManagerText.setBounds(175, 50, 150, 30);

		managerFirstNameLabel.setBounds(25, 100, 150, 30);
		managerFirstNameChooser.setBounds(175, 100, 150, 30);

		managerLastNameLabel.setBounds(25, 150, 150, 30);
		managerLastNameChooser.setBounds(175, 150, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String currentManager = currentManagerText.getText();
					String FirstName = managerFirstNameChooser.getText();
					String LastName = managerLastNameChooser.getText();
					if (currentManager.length() != 0 && FirstName.length() != 0 && LastName.length() != 0) {
						app.updateManager(currentManager, FirstName, LastName);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(currentManagerlabel);
		container.add(currentManagerText);

		container.add(managerFirstNameLabel);
		container.add(managerFirstNameChooser);

		container.add(managerLastNameLabel);
		container.add(managerLastNameChooser);

		container.add(addButton);

		this.setTitle("Update Order");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

	public void readallManagers() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<ManagerView> myManagers = app.getManagerView();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "UserName","FirstName","LastName"};
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[3];
		for (int i = 0; i < myManagers.size(); i++) {
			myRow[0] = myManagers.get(i).Username;
			myRow[1] = myManagers.get(i).FirstName;
			myRow[2] = myManagers.get(i).LastName;
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 300, 300);
		container.add(myPane);

		this.setTitle("Read Managers");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addFor() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Task> myTasks = app.getTasks();
		ArrayList<Part> myParts = app.getParts();
		Integer[] myTaskID = new Integer[myTasks.size()];
		Integer[] myPartNumber = new Integer[myParts.size()];
		for (int i = 0; i < myTasks.size(); i++) {
			myTaskID[i] = myTasks.get(i).getID();
		}
		for (int i = 0; i < myParts.size(); i++) {
			myPartNumber[i] = myParts.get(i).getPartNumber();
		}

		// Set up
		JLabel partNumberLabel = new JLabel("PartNumber");
		JComboBox<Integer> partNumberChooser = new JComboBox<Integer>(myPartNumber);

		JLabel taskIdLabel = new JLabel("TaskID");
		JComboBox<Integer> taskIdChooser = new JComboBox<Integer>(myTaskID);

		JButton addButton = new JButton("ADD");

		// BOUNDS
		partNumberLabel.setBounds(25, 50, 150, 30);
		partNumberChooser.setBounds(100, 50, 150, 30);

		taskIdLabel.setBounds(25, 100, 150, 30);
		taskIdChooser.setBounds(100, 100, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					Integer TaskID = (Integer) taskIdChooser.getSelectedItem();
					Integer PartNumber = (Integer) partNumberChooser.getSelectedItem();

					// Be careful to not insert soemthing already in the list
					if (TaskID != null && PartNumber != null) {
						app.addFor(PartNumber, TaskID);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(taskIdLabel);
		container.add(taskIdChooser);

		container.add(partNumberLabel);
		container.add(partNumberChooser);

		container.add(addButton);

		this.setTitle("Add For");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updateFor() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<For> myFor = app.getAllFor();
		ArrayList<Task> myTasks = app.getTasks();
		ArrayList<Part> myParts = app.getParts();
		String[] beforeFor = new String[myFor.size()];
		Integer[] afterMyPart = new Integer[myParts.size()];
		Integer[] afterMyTask = new Integer[myTasks.size()];
		for (int i = 0; i < myFor.size(); i++) {
			beforeFor[i] = myFor.get(i).PartNumber + "/" + myFor.get(i).TaskID;
		}
		for (int i = 0; i < myParts.size(); i++) {
			afterMyPart[i] = myParts.get(i).getPartNumber();
		}
		for (int i = 0; i < myTasks.size(); i++) {
			afterMyTask[i] = myTasks.get(i).getID();
		}

		JLabel beforePartNumberLabel = new JLabel("BeforePartNumber");
		JComboBox<String> beforePartNumberChooser = new JComboBox<>(beforeFor);

		JLabel afterPartNumberLabel = new JLabel("AfterPartNumber");
		JComboBox<Integer> afterPartNumberChooser = new JComboBox<>(afterMyPart);

		JLabel afterTaskLabel = new JLabel("AfterTaskID");
		JComboBox<Integer> afterTaskChooser = new JComboBox<>(afterMyTask);

		JButton addButton = new JButton("UPDATE");

		beforePartNumberLabel.setBounds(25, 50, 150, 30);
		beforePartNumberChooser.setBounds(200, 50, 150, 30);

		afterPartNumberLabel.setBounds(25, 100, 150, 30);
		afterPartNumberChooser.setBounds(200, 100, 150, 30);

		afterTaskLabel.setBounds(25, 150, 150, 30);
		afterTaskChooser.setBounds(200, 150, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String[] mySplits = ((String) beforePartNumberChooser.getSelectedItem()).split("/");
					Integer BeforePartNumber = Integer.parseInt(mySplits[1]);
					Integer BeforeTaskID = Integer.parseInt(mySplits[0]);
					Integer AfterPartNumber = (Integer) afterPartNumberChooser.getSelectedItem();
					Integer AfterTaskID = (Integer) afterTaskChooser.getSelectedItem();
					if (BeforePartNumber != null && BeforeTaskID != null && AfterPartNumber != null
							&& AfterTaskID != null
							&& (!BeforePartNumber.equals(AfterPartNumber) && !BeforeTaskID.equals(AfterTaskID))) {
						app.UpdateFor(BeforePartNumber, BeforeTaskID, AfterPartNumber, AfterTaskID);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(beforePartNumberLabel);
		container.add(beforePartNumberChooser);

		container.add(afterPartNumberLabel);
		container.add(afterPartNumberChooser);

		container.add(afterTaskLabel);
		container.add(afterTaskChooser);

		container.add(addButton);

		this.setTitle("Update For");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deleteFor() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<For> myFor = app.getAllFor();
		Integer[] partNumbers = new Integer[myFor.size()];
		Integer[] taskNumbers = new Integer[myFor.size()];
		for (int i = 0; i < myFor.size(); i++) {
			taskNumbers[i] = myFor.get(i).getTaskID();
			partNumbers[i] = myFor.get(i).getPartNumber();
		}

		// Set up
		JLabel partNumberLabel = new JLabel("PartNumber");
		JComboBox<Integer> partNumberChooser = new JComboBox<>(partNumbers);

		JLabel taskNumberLabel = new JLabel("TaskNumber");
		JComboBox<Integer> taskNumberChooser = new JComboBox<>(taskNumbers);

		JButton deleteButton = new JButton("DELETE");

		partNumberLabel.setBounds(25, 50, 150, 30);
		partNumberChooser.setBounds(130, 50, 150, 30);

		taskNumberLabel.setBounds(25, 100, 150, 30);
		taskNumberChooser.setBounds(130, 100, 150, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Integer partNumber = (Integer) partNumberChooser.getSelectedItem();
				Integer taskID = (Integer) taskNumberChooser.getSelectedItem();
				if (partNumber != null && taskID != null) {
					app.deleteFor(partNumber, taskID);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(partNumberLabel);
		container.add(partNumberChooser);
		container.add(taskNumberLabel);
		container.add(taskNumberChooser);
		container.add(deleteButton);

		this.setTitle("Delete For");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllFor() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<For> myFors = app.getAllFor();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "PartNumber", "TaskID" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[2];
		for (int i = 0; i < myFors.size(); i++) {
			myRow[0] = myFors.get(i).getPartNumber();
			myRow[1] = myFors.get(i).getTaskID();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 200, 300);
		container.add(myPane);

		this.setTitle("Read For");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addHas() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		ArrayList<Repair> myRepairs = app.getRepairs();
		ArrayList<Task> myTasks = app.getTasks();
		
		System.out.println(myVehicles);
		System.out.println(myRepairs);
		System.out.println(myTasks);

		String[] myVins = new String[myVehicles.size()];
		Integer[] repairIds = new Integer[myRepairs.size()];
		Integer[] taskIds = new Integer[myTasks.size()];

		for (int i = 0; i < myVehicles.size(); i++) {
			myVins[i] = myVehicles.get(i).getVin();
		}
		for (int i = 0; i < myRepairs.size(); i++) {
			repairIds[i] = myRepairs.get(i).getID();
		}
		for (int i = 0; i < myTasks.size(); i++) {
			taskIds[i] = myTasks.get(i).getID();
		}

		// Set up
		JLabel vinLabel = new JLabel("VIN");
		JComboBox<String> vinChooser = new JComboBox<>(myVins);

		JLabel repairIdLabel = new JLabel("RepairID");
		JComboBox<Integer> repairIdChooser = new JComboBox<>(repairIds);

		JLabel taskIdLabel = new JLabel("TaskID");
		JComboBox<Integer> taskIdChooser = new JComboBox<>(taskIds);

		JButton addButton = new JButton("ADD");

		// BOUNDS
		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(100, 50, 250, 30);

		repairIdLabel.setBounds(25, 100, 150, 30);
		repairIdChooser.setBounds(100, 100, 150, 30);

		taskIdLabel.setBounds(25, 150, 150, 30);
		taskIdChooser.setBounds(100, 150, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String VIN = (String) vinChooser.getSelectedItem();
					Integer RepairID = (Integer) repairIdChooser.getSelectedItem();
					Integer TaskID = (Integer) taskIdChooser.getSelectedItem();

					if (VIN.length() != 0 && RepairID != null && TaskID != null) {
						app.addHas(RepairID, VIN, TaskID);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(vinLabel);
		container.add(vinChooser);

		container.add(repairIdLabel);
		container.add(repairIdChooser);

		container.add(taskIdLabel);
		container.add(taskIdChooser);

		container.add(addButton);

		this.setTitle("Add Has");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	

	public void deleteHas() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Has> myHas = app.getAllHas();
		String[] myListHas = new String[myHas.size()];
		for (int i = 0; i < myHas.size(); i++) {
			myListHas[i] = myHas.get(i).VehicleVIN + "/" + myHas.get(i).RepairID + "/" + myHas.get(i).TaskId;
		}

		// Set up
		JLabel myHasLabel = new JLabel("Has");
		JComboBox<String> myHasChooser = new JComboBox<String>(myListHas);

		JButton deleteButton = new JButton("DELETE");

		myHasLabel.setBounds(25, 50, 150, 30);
		myHasChooser.setBounds(100, 50, 250, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] mySplit = ((String) myHasChooser.getSelectedItem()).split("/");
				String VIN = mySplit[0];
				Integer RepairID = Integer.parseInt(mySplit[1]);
				Integer TaskID = Integer.parseInt(mySplit[2]);
				if (VIN.length() != 0 && RepairID != null && TaskID != null) {
					app.deleteHas(VIN, RepairID, TaskID);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(myHasLabel);
		container.add(myHasChooser);
		container.add(deleteButton);

		this.setTitle("Delete Has");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readAllHas() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Has> myHas = app.getAllHas();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "VehicleVIN", "RepairID", "TaskID" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[3];
		for (int i = 0; i < myHas.size(); i++) {
			myRow[0] = myHas.get(i).getVehicleVin();
			myRow[1] = myHas.get(i).getRepairID();
			myRow[2] = myHas.get(i).getTaskID();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		myPane.setBounds(30, 30, 380, 300);
		container.add(myPane);

		this.setTitle("Read Has");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addPaidBy() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		ArrayList<Repair> myRepairs = app.getRepairs();
		ArrayList<Customer> myCustomers = app.getCustomers();

		String[] myVins = new String[myVehicles.size()];
		Integer[] repairIds = new Integer[myRepairs.size()];
		String[] customerUsrenames = new String[myCustomers.size()];

		for (int i = 0; i < myVehicles.size(); i++) {
			myVins[i] = myVehicles.get(i).getVin();
		}
		for (int i = 0; i < myRepairs.size(); i++) {
			repairIds[i] = myRepairs.get(i).getID();
		}
		for (int i = 0; i < myCustomers.size(); i++) {
			customerUsrenames[i] = myCustomers.get(i).getusername();
		}

		// Set up
		JLabel vinLabel = new JLabel("VehicleVIN");
		JComboBox<String> vinChooser = new JComboBox<String>(myVins);

		JLabel repairIdLabel = new JLabel("RepairID");
		JComboBox<Integer> repairIdChooser = new JComboBox<Integer>(repairIds);

		JLabel customerLabel = new JLabel("CustomerUsername");
		JComboBox<String> customerChooser = new JComboBox<String>(customerUsrenames);

		JLabel receiptLabel = new JLabel("Receipt");
		JTextField receiptChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(200, 50, 250, 30);

		repairIdLabel.setBounds(25, 100, 150, 30);
		repairIdChooser.setBounds(200, 100, 150, 30);

		customerLabel.setBounds(25, 150, 150, 30);
		customerChooser.setBounds(200, 150, 150, 30);

		receiptLabel.setBounds(25, 200, 150, 30);
		receiptChooser.setBounds(200, 200, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String VIN = (String) vinChooser.getSelectedItem();
					Integer RepairID = (Integer) repairIdChooser.getSelectedItem();
					String CustomerUserName = (String) customerChooser.getSelectedItem();
					String Receipt = receiptChooser.getText();

					if (VIN.length() != 0 && RepairID != null && CustomerUserName != null) {
						app.addPaidBy(VIN, RepairID, CustomerUserName, Receipt);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(vinLabel);
		container.add(vinChooser);

		container.add(repairIdLabel);
		container.add(repairIdChooser);

		container.add(customerLabel);
		container.add(customerChooser);

		container.add(receiptLabel);
		container.add(receiptChooser);

		container.add(addButton);

		this.setTitle("Add PaidBy");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updatePaidBy() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<PaidBy> myPaidBy = app.getFinalPaidBy();

		String[] beforePaidBy = new String[myPaidBy.size()];

		for (int i = 0; i < myPaidBy.size(); i++) {
			beforePaidBy[i] = myPaidBy.get(i).VehicleVIN + "/" + myPaidBy.get(i).RepairID + "/" + myPaidBy.get(i).CustomerName;
		}

		JLabel beforePartNumberLabel = new JLabel("BeforePaidBy");
		JComboBox<String> beforePartNumberChooser = new JComboBox<String>(beforePaidBy);

		JLabel afterReceiptLabel = new JLabel("AfterReceipt");
		JTextField afterReceiptChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		beforePartNumberLabel.setBounds(25, 50, 150, 30);
		beforePartNumberChooser.setBounds(150, 50, 250, 30);

		afterReceiptLabel.setBounds(25, 100, 150, 30);
		afterReceiptChooser.setBounds(150, 100, 150, 30);

		addButton.setBounds(100, 200, 100, 50);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String[] mySplits = ((String)beforePartNumberChooser.getSelectedItem()).split("/");
					String BeforeVehicleVIN = mySplits[0];
					Integer BeforeRepairID = Integer.parseInt(mySplits[1]);
					String BeforeCustomer = mySplits[2];

					String AfterReceipt = (String) afterReceiptChooser.getText();
					if (BeforeRepairID != null && BeforeVehicleVIN.length() != 0 && BeforeCustomer.length() != 0
							&& BeforeVehicleVIN.length() != 0) {
						app.updatePaidBy(BeforeVehicleVIN, BeforeRepairID, BeforeCustomer, AfterReceipt);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(beforePartNumberLabel);
		container.add(beforePartNumberChooser);

		container.add(afterReceiptLabel);
		container.add(afterReceiptChooser);

		container.add(addButton);

		this.setTitle("Update MyPaidBy");
		this.setVisible(true);
		this.setBounds(10, 10, 700, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deletePaidBy() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<PaidBy> myPaidBy = app.getFinalPaidBy();

		String[] beforePaidBy = new String[myPaidBy.size()];

		for (int i = 0; i < myPaidBy.size(); i++) {
			beforePaidBy[i] = myPaidBy.get(i).VehicleVIN + "/" + myPaidBy.get(i).RepairID + "/" + myPaidBy.get(i).CustomerName;
		}

		// Set up
		JLabel myPaidByLabel = new JLabel("PaidBy");
		JComboBox<String> paidByChooser = new JComboBox<>(beforePaidBy);

		JButton deleteButton = new JButton("DELETE");

		myPaidByLabel.setBounds(25, 50, 150, 30);
		paidByChooser.setBounds(100, 50, 250, 30);

		deleteButton.setBounds(100, 100, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String[] mySplits = ((String)paidByChooser.getSelectedItem()).split("/");
				String VIN = mySplits[0];
				Integer RepairID = Integer.parseInt(mySplits[1]);
				String CustomerUserName = mySplits[2];
				if (VIN.length() != 0 && RepairID != null && CustomerUserName.length() != 0) {
					app.deletePaidBy(VIN, RepairID, CustomerUserName);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(myPaidByLabel);
		container.add(paidByChooser);
		container.add(deleteButton);

		this.setTitle("Delete PaidBy");
		this.setVisible(true);
		this.setBounds(10, 10, 600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readPaidBy() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<PaidBy> myPaidBy = app.getFinalPaidBy();
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
		myTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		myPane.setBounds(30, 30, 400, 300);
		container.add(myPane);

		this.setTitle("Read PaidBy");
		this.setBounds(10, 10, 680, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void addPaidFor() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		ArrayList<Repair> myRepairs = app.getRepairs();
		ArrayList<Insurance> myInsurance = app.getInsurance();

		String[] myVins = new String[myVehicles.size()];
		Integer[] repairIds = new Integer[myRepairs.size()];
		String[] claimNumbers = new String[myInsurance.size()];

		for (int i = 0; i < myVehicles.size(); i++) {
			myVins[i] = myVehicles.get(i).getVin();
		}
		for (int i = 0; i < myRepairs.size(); i++) {
			repairIds[i] = myRepairs.get(i).getID();
		}
		for (int i = 0; i < myInsurance.size(); i++) {
			claimNumbers[i] = myInsurance.get(i).getClaimNumber();
		}

		// Set up
		JLabel vinLabel = new JLabel("VehicleVIN");
		JComboBox<String> vinChooser = new JComboBox<>(myVins);

		JLabel repairIdLabel = new JLabel("RepairID");
		JComboBox<Integer> repairIdChooser = new JComboBox<>(repairIds);

		JLabel customerLabel = new JLabel("ClaimNum");
		JComboBox<String> customerChooser = new JComboBox<>(claimNumbers);

		JLabel receiptLabel = new JLabel("Receipt");
		JTextField receiptChooser = new JTextField();

		JButton addButton = new JButton("ADD");

		// BOUNDS
		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(100, 50, 250, 30);

		repairIdLabel.setBounds(25, 100, 150, 30);
		repairIdChooser.setBounds(100, 100, 150, 30);

		customerLabel.setBounds(25, 150, 150, 30);
		customerChooser.setBounds(100, 150, 150, 30);

		receiptLabel.setBounds(25, 200, 150, 30);
		receiptChooser.setBounds(100, 200, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String VIN = (String) vinChooser.getSelectedItem();
					Integer RepairID = (Integer) repairIdChooser.getSelectedItem();
					String InsuranceClaimNumber = (String) customerChooser.getSelectedItem();
					String Receipt = (String) receiptChooser.getText();
					System.out.println(VIN);
					System.out.println(RepairID);
					System.out.println(InsuranceClaimNumber);
					System.out.println(Receipt);
					if (VIN.length() != 0 && RepairID != null && InsuranceClaimNumber.length() != 0) {
						app.addPaidFor(VIN, RepairID, InsuranceClaimNumber, Receipt);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(vinLabel);
		container.add(vinChooser);

		container.add(repairIdLabel);
		container.add(repairIdChooser);

		container.add(customerLabel);
		container.add(customerChooser);

		container.add(receiptLabel);
		container.add(receiptChooser);

		container.add(addButton);

		this.setTitle("Add PaidFor");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void updatePaidFor() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<PaidFor> myPaidBy = app.getPaidFor();

		String[] beforePaidBy = new String[myPaidBy.size()];

		for (int i = 0; i < myPaidBy.size(); i++) {
			beforePaidBy[i] = myPaidBy.get(i).VehicleVIN + "/" + myPaidBy.get(i).RepairID + "/"
					+ myPaidBy.get(i).InsuranceClaimNumber + "/" + myPaidBy.get(i).getReceipt();
		}

		JLabel beforePartNumberLabel = new JLabel("BeforePaidFor");
		JComboBox<String> beforePartNumberChooser = new JComboBox<>(beforePaidBy);

		JLabel afterReceiptLabel = new JLabel("AfterReceipt");
		JTextField afterReceiptChooser = new JTextField();

		JButton addButton = new JButton("UPDATE");

		beforePartNumberLabel.setBounds(25, 50, 150, 30);
		beforePartNumberChooser.setBounds(150, 50, 250, 30);

		afterReceiptLabel.setBounds(25, 100, 150, 30);
		afterReceiptChooser.setBounds(100, 100, 150, 30);

		addButton.setBounds(100, 450, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String mySelectedItem = ((String) beforePartNumberChooser.getSelectedItem());

					String[] myWords = mySelectedItem.split("/");
					String BeforeVehicleVIN = myWords[0];
					Integer BeforeRepairID = Integer.parseInt(myWords[1]);
					String BeforeInsuranceClaimNumber = myWords[2];
					String BeforeReceipt = myWords[3];

					String AfterReceipt = (String) afterReceiptChooser.getText();
					if (BeforeRepairID != null) {
						app.updatePaidFor(BeforeVehicleVIN, BeforeRepairID, BeforeInsuranceClaimNumber, AfterReceipt);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});

		container.add(beforePartNumberLabel);
		container.add(beforePartNumberChooser);

		container.add(afterReceiptLabel);
		container.add(afterReceiptChooser);

		container.add(addButton);

		this.setTitle("Update MyPaidFor");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 1200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void deletePaidFor() {
		container.removeAll();
		container.add(toolBar);

		ArrayList<PaidFor> myPaidFor = app.getPaidFor();
		String[] myListPaidFor = new String[myPaidFor.size()];
		for (int i = 0; i < myPaidFor.size(); i++) {
			myListPaidFor[i] = myPaidFor.get(i).VehicleVIN + "/" + myPaidFor.get(i).RepairID + "/"
					+ myPaidFor.get(i).InsuranceClaimNumber;
		}
		// Set up
		JLabel myPaidByLabel = new JLabel("PaidFor");
		JComboBox<String> paidByChooser = new JComboBox<String>(myListPaidFor);
		JButton deleteButton = new JButton("DELETE");

		myPaidByLabel.setBounds(25, 50, 150, 30);
		paidByChooser.setBounds(100, 50, 200, 30);

		deleteButton.setBounds(100, 350, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String SelectedItem = (String) paidByChooser.getSelectedItem();
				String[] myWords = SelectedItem.split("/");
				String VIN = myWords[0];
				Integer RepairID = Integer.parseInt(myWords[1]);
				String InsuranceClaimNumber = myWords[2];

				if (VIN.length() != 0 && RepairID != null && InsuranceClaimNumber.length() != 0) {
					app.deletePaidFor(VIN, RepairID, InsuranceClaimNumber);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(myPaidByLabel);
		container.add(paidByChooser);
		container.add(deleteButton);

		this.setTitle("Delete PaidFor");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public void readPaidFor() {
		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<PaidFor> myPaidBy = app.getPaidFor();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "VehicleVIN", "RepairID", "InsuranceClaimNumber", "Receipt" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[4];
		for (int i = 0; i < myPaidBy.size(); i++) {
			myRow[0] = myPaidBy.get(i).getVehicleVIN();
			myRow[1] = myPaidBy.get(i).getRepairID();
			myRow[2] = myPaidBy.get(i).getInsuranceClaimNumber();
			myRow[3] = myPaidBy.get(i).getReceipt();
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = 0; i < myRow.length; i++) {
			myTable.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		myPane.setBounds(30, 30, 400, 300);
		container.add(myPane);

		this.setTitle("Read PaidFor");
		this.setBounds(10, 10, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void readGets() {

		container.removeAll();
		container.add(toolBar);
		JScrollPane myPane = new JScrollPane();

		ArrayList<Get> myPaidBy = app.getGets();
		JTable myTable = new JTable();
		DefaultTableModel myModel = new DefaultTableModel();
		String[] myColumns = { "VIN", "RepairID" };
		myTable.setModel(myModel);
		myModel.setColumnIdentifiers(myColumns);
		myPane.setViewportView(myTable);
		Object[] myRow = new Object[2];
		for (int i = 0; i < myPaidBy.size(); i++) {
			myRow[0] = myPaidBy.get(i).VIN;
			myRow[1] = myPaidBy.get(i).RepairID;
			myModel.addRow(myRow);
		}
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		myTable.getColumnModel().getColumn(0).setPreferredWidth(250);
		myTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		myPane.setBounds(30, 30, 350, 300);
		container.add(myPane);

		this.setTitle("Read Gets");
		this.setBounds(10, 10, 650, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	private void deleteGets() {

		container.removeAll();
		container.add(toolBar);

		ArrayList<Get> myPaidFor = app.getGets();
		String[] myListPaidFor = new String[myPaidFor.size()];
		for (int i = 0; i < myPaidFor.size(); i++) {
			myListPaidFor[i] = myPaidFor.get(i).VIN + "/" + myPaidFor.get(i).RepairID;
		}
		// Set up
		JLabel myPaidByLabel = new JLabel("MyVehicleAndRep");
		JComboBox<String> paidByChooser = new JComboBox<String>(myListPaidFor);
		JButton deleteButton = new JButton("DELETE");

		myPaidByLabel.setBounds(25, 50, 150, 30);
		paidByChooser.setBounds(150, 50, 250, 30);

		deleteButton.setBounds(100, 100, 150, 30);

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] mySplits = ((String) paidByChooser.getSelectedItem()).split("/");
				String VIN = mySplits[0];
				Integer RepairID = Integer.parseInt(mySplits[1]);

				if (VIN.length() != 0 && RepairID != null) {
					app.deleteGets(VIN, RepairID);
				} else {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}

			}

		});
		container.add(myPaidByLabel);
		container.add(paidByChooser);
		container.add(deleteButton);

		this.setTitle("Delete Gets");
		this.setVisible(true);
		this.setBounds(10, 10, 650, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	

	private void addGets() {

		container.removeAll();
		container.add(toolBar);

		ArrayList<Vehicle> myVehicles = app.getAllVehicles();
		ArrayList<Repair> myRepairs = app.getRepairs();

		String[] myVins = new String[myVehicles.size()];
		Integer[] repairIds = new Integer[myRepairs.size()];

		for (int i = 0; i < myVehicles.size(); i++) {
			myVins[i] = myVehicles.get(i).getVin();
		}
		for (int i = 0; i < myRepairs.size(); i++) {
			repairIds[i] = myRepairs.get(i).getID();
		}

		JLabel vinLabel = new JLabel("VehicleVIN");
		JComboBox<String> vinChooser = new JComboBox<String>(myVins);

		JLabel repairIdLabel = new JLabel("RepairID");
		JComboBox<Integer> repairIdChooser = new JComboBox<Integer>(repairIds);

		JButton addButton = new JButton("ADD");

		vinLabel.setBounds(25, 50, 150, 30);
		vinChooser.setBounds(100, 50, 250, 30);

		repairIdLabel.setBounds(25, 100, 150, 30);
		repairIdChooser.setBounds(100, 100, 150, 30);

		addButton.setBounds(100, 400, 100, 100);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String VIN = (String) vinChooser.getSelectedItem();
					Integer RepairID = (Integer) repairIdChooser.getSelectedItem();

					if (VIN.length() != 0 && RepairID != null) {
						app.addGets(VIN, RepairID);
					} else {
						JOptionPane.showMessageDialog(null, "The Fields are incorrect");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Fields are incorrect");
				}
			}
		});
		container.add(vinLabel);
		container.add(vinChooser);

		container.add(repairIdLabel);
		container.add(repairIdChooser);

		container.add(addButton);

		this.setTitle("Add Gets");
		this.setVisible(true);
		this.setBounds(10, 10, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	public static boolean validDate(String date) {
		String[] myDate = date.split("-");
		if (myDate.length != 3) {
			return false;
		}
		try {
			// Test on year
			if (myDate[0].length() != 4 || Integer.parseInt(myDate[0]) >= Calendar.getInstance().get(Calendar.YEAR)) {
				return false;
			}
			// Test on month
			if (Integer.parseInt(myDate[1]) < 1 || Integer.parseInt(myDate[1]) > 12) {
				return false;
			}
			if (Integer.parseInt(myDate[2]) < 1 || Integer.parseInt(myDate[2]) > 31) {
				return false;
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid Date");
			return false;
		}
		return true;
	}

	public static boolean compareDate(String startdate, String endDate) {

		if (ManagerFrame.validDate(startdate) && ManagerFrame.validDate(endDate)) {
			String[] myStartDate = startdate.split("-");
			String[] myEndDate = endDate.split("-");
			if (Integer.parseInt(myStartDate[0]) < Integer.parseInt(myEndDate[0])) {
				return true;
			} else if (Integer.parseInt(myStartDate[0]) == Integer.parseInt(myEndDate[0])) {
				if (Integer.parseInt(myStartDate[1]) < Integer.parseInt(myEndDate[1])) {
					return true;
				} else if (Integer.parseInt(myStartDate[1]) == Integer.parseInt(myEndDate[1])) {
					if (Integer.parseInt(myStartDate[2]) <= Integer.parseInt(myEndDate[2])) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}
			return false;
		}
		return false;

	}
}
