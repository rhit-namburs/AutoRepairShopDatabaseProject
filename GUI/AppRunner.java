package gui;

import java.awt.Container;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import services.DatabaseConnectionService;

class AppRunner {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	public DatabaseConnectionService dbService = null;

	public AppRunner(DatabaseConnectionService dbService) {
		this.dbService = dbService;

	}

	public void openManagerFrame(String userText) { // 800x800
		System.out.println("Manager Frame method called!");
		ManagerFrame managerFrame = new ManagerFrame(this, userText);
		managerFrame.setTitle("AutoRepairDatabase - Manager");
		managerFrame.setVisible(true);
		managerFrame.setBounds(10, 10, 800, 800);
		managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerFrame.setResizable(false);

	}

	protected void openCustomerFrame(String userText) {
		CustomerFrame managerFrame = new CustomerFrame(this, userText);
		managerFrame.setTitle("AutoRepair - Customer");
		managerFrame.setVisible(true);
		managerFrame.setBounds(10, 10, 400, 600);
		managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerFrame.setResizable(false);
	}

	protected void openEmployeeFrame(String userText) {
		EmployeeFrame managerFrame = new EmployeeFrame(this, userText);
		managerFrame.setTitle("AutoRepair - Employee");
		managerFrame.setVisible(true);
		managerFrame.setBounds(10, 10, 400, 600);
		managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerFrame.setResizable(false);
	}

	public void startRegistration() {
		RegisterFrame registerFrame = new RegisterFrame(this);
		registerFrame.setTitle("Registration Form");
		registerFrame.setVisible(true);
		registerFrame.setBounds(10, 10, 370, 600);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.setResizable(false);

	}

	public boolean completeRegistration(String firstName, String lastName, String username, String password,
			String userType) {

		byte[] saltPass = this.getNewSalt();
		String hashedPass = this.hashPassword(saltPass, password);
		System.out.println(hashedPass + " is hashpass");

		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL Register(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, hashedPass);
			cs.setBytes(4, saltPass);
			cs.setString(5, userType);
			cs.setString(6, firstName);
			cs.setString(7, lastName);

			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println(returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Registration Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Registration Failed");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public LoginInfo startLogin(String username, String password) {

		String type = "";
		byte[] saltPass = null;
		String hashPass = "";
		String newHashPass = "";
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {? = CALL LoginChecker(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				saltPass = rs.getBytes("SaltPass");
				hashPass = rs.getString("HashPass");
				type = rs.getString("UserType");
			}
			newHashPass = this.hashPassword(saltPass, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return new LoginInfo(false, type);
		} catch (NullPointerException e) {

			return new LoginInfo(false, type);
		}

		if (newHashPass.equals(hashPass)) {
			return new LoginInfo(true, type);
		}

		System.out.println(newHashPass + " " + hashPass);
		JOptionPane.showMessageDialog(null, "Login Failed");
		return new LoginInfo(false, type);

	}

	public boolean useApplicationLogins() {
		return true;
	}

	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

	public boolean assign(String text, String employeeUsername, int theTaskNumber) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL AssignTask(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, text);
			cs.setInt(3, theTaskNumber);
			cs.setString(4, employeeUsername);

			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Assign Task Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Assign Task Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean completeTaskNew(int ID) {
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL CompleteTask(?)}");

			System.out.println("task completed!");
			cs.setInt(1, ID);
			cs.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean completeTask(int ID) {
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {? = CALL CompleteTask(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, ID);
			cs.execute();

			System.out.println(cs.getInt(1));
			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Complete Task Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Complete Task Failed ");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addCustomer(String username, String firstName, String lastName, int NumberOfVisits) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertCustomer(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, firstName);
			cs.setString(4, lastName);
			System.out.println(NumberOfVisits);
			cs.setInt(5, NumberOfVisits);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("Customer Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Customer Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Customer Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addEmployee(String username, String firstName, String lastName) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertEmployee(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, firstName);
			cs.setString(4, lastName);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("Employee Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Employee Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Employee Failed");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addFor(int PartNumber, int TaskID) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertFor(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, PartNumber);
			cs.setInt(3, TaskID);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add For Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add For Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addGets(String VIN, int ID) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertGets(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VIN);
			cs.setInt(3, ID);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For Gets Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Gets Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Gets Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addHas(int RepairID, String VehicleVIN, int TaskID) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertHas(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, RepairID);
			cs.setString(3, VehicleVIN);
			cs.setInt(4, TaskID);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For Has Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Has Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Has Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addInsurance(String ClaimNumber, String PolicyNumber, int deductible) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertInsurance(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ClaimNumber);
			cs.setString(3, PolicyNumber);
			cs.setInt(4, deductible);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For Insurance Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Insurance Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Insurance Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addOrder(String ManagerUserName, int PartNumber) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertOrder(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ManagerUserName);
			cs.setInt(3, PartNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For Order Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Order Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Order Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addPaidBy(String VehicleVIN, int RepairID, String CustomerUserName, String Receipt) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertPaidBy(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VehicleVIN);
			cs.setInt(3, RepairID);
			cs.setString(4, CustomerUserName);
			cs.setString(5, Receipt);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For PaidBy Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add PaidBy Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add PaidBy Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addPaidFor(String VehicleVIN, int RepairID, String InsuranceClaimNumber, String Receipt) {
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertPaidFor(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VehicleVIN);
			cs.setInt(3, RepairID);
			cs.setString(4, InsuranceClaimNumber);
			cs.setString(5, Receipt);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("For PaidFor Insert Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add PaidFor Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add PaidFor Failed Successfully");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addRepair(String startDate, String endDate, int discount, int totalCost, String description) {

		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			System.out.println(c);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertRepair(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, startDate);
			cs.setString(3, endDate);
			cs.setString(4, description);
			cs.setInt(5, discount);
			cs.setInt(6, totalCost);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("Repair Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Repair Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Repair Failed");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

	public boolean addVehicle(String VIN, int year, String model, int mileage, String bodyType) {

		if (VIN.length() == 0 || model.length() == 0) {
			JOptionPane.showMessageDialog(null, "fields can't be left empty");
			return false;
		}
		try {
			Connection c = dbService.getConnection();
			System.out.println(dbService);
			CallableStatement cs = c.prepareCall(" {? = CALL InsertVehicle(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VIN);
			cs.setInt(3, year);
			cs.setString(4, model);
			cs.setInt(5, mileage);
			cs.setString(6, bodyType);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("Vehicle Code Insert: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Vehicle Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Vehicle Failed Failed");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addTask(String Name, String Description, int Price) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL InsertTask(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, Name);
			cs.setString(3, Description);
			cs.setInt(4, Price);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("Task Insert Return Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Task Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Task Failed Failed");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addPart(int PartNumber, String Name, int Price) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL InsertPart(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, PartNumber);
			cs.setString(3, Name);
			cs.setInt(4, Price);
			cs.execute();

			int returnCode = cs.getInt(1);
			System.out.println("Part Insert Return Code: " + returnCode);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Add Part Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Add Part Failed");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// Reads

	public ArrayList<String> getVehicles() {
		ArrayList<String> vehicles = new ArrayList<String>();
		try {

			PreparedStatement s = dbService.getConnection().prepareStatement("SELECT VIN FROM VEHICLE");

			ResultSet rs = s.executeQuery();
			while (rs.next()) {

				vehicles.add(rs.getString("VIN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicles;
	}

	public ArrayList<Repair> getRepairs() {
		ArrayList<Repair> myRepairs = new ArrayList<Repair>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadRepair}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Repair row = new Repair(rs.getInt("ID"), rs.getString("StartDate"), rs.getString("EndDate"),
						rs.getString("Description"), rs.getInt("Discount"), rs.getInt("TotalCost"),
						rs.getInt("Completion"));
				row.priceCompleted = rs.getInt("PriceCompleted");
				myRepairs.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myRepairs;
	}

	public ArrayList<Vehicle> getAllVehicles() {
		ArrayList<Vehicle> myVehiclesList = new ArrayList<Vehicle>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadVehicle}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Vehicle row = new Vehicle(rs.getString("VIN"), rs.getInt("Year"), rs.getString("Model"),
						rs.getInt("Mileage"), rs.getString("BodyType"));
				myVehiclesList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myVehiclesList;
	}

	public ArrayList<Task> getTasks() {
		ArrayList<Task> myTaskList = new ArrayList<Task>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadTask}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Task row = new Task(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"),
						rs.getInt("Price"), rs.getInt("Completion"));
				myTaskList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myTaskList;
	}

	public ArrayList<Insurance> getInsurance() {

		ArrayList<Insurance> myInsurance = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadInsurance}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Insurance row = new Insurance(rs.getString("ClaimNumber"), rs.getString("PolicyNumber"),
						rs.getInt("Deductible"));
				myInsurance.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myInsurance;
	}

	public ArrayList<Order> getOrders() {

		ArrayList<Order> myOrders = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadOrders}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Order row = new Order(rs.getString("ManagerUserName"), rs.getInt("PartNumber"));
				myOrders.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myOrders;
	}

	public ArrayList<Get> getGets() {
		ArrayList<Get> myOrders = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadGets}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Get row = new Get(rs.getString("VIN"), rs.getInt("RepairID"));
				myOrders.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myOrders;
	}

	public ArrayList<PaidFor> getPaidFor() {

		ArrayList<PaidFor> myPaidfor = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall("{CALL ReadPaidFor}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				PaidFor row = new PaidFor(rs.getString("VehicleVIN"), rs.getInt("RepairID"),
						rs.getString("InsuranceClaimNumber"), rs.getString("Receipt"));
				myPaidfor.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myPaidfor;
	}

	public ArrayList<PaidBy> getFinalPaidBy() {
		ArrayList<PaidBy> myPaidBys = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall("{CALL ReadFinalPaidBy}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				PaidBy row = new PaidBy(rs.getString("VehicleVIN"), rs.getInt("RepairID"),
						rs.getString("CustomerUserName"), rs.getString("Receipt"));
				myPaidBys.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myPaidBys;
	}

	public ArrayList<PaidBy> getPaidBy(String username) {

		ArrayList<PaidBy> myPaidBys = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall("{CALL ReadPaidBy(?)}");
			cs.setString(1, username);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				PaidBy row = new PaidBy(rs.getString("VehicleVIN"), rs.getInt("RepairID"),
						rs.getString("CustomerUserName"), rs.getString("Receipt"));
				myPaidBys.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myPaidBys;
	}

	public ArrayList<For> getAllFor() {

		ArrayList<For> myFor = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadFor}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				For row = new For(rs.getInt("PartNumber"), rs.getInt("TaskID"));
				myFor.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myFor;
	}

	public ArrayList<Has> getAllHas() {

		ArrayList<Has> myHas = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadHas}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Has row = new Has(rs.getString("VehicleVIN"), rs.getInt("RepairID"), rs.getInt("TaskID"));
				myHas.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myHas;
	}

	public ArrayList<Manager> getManagers() {

		ArrayList<Manager> myManagers = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadManagers}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Manager row = new Manager(rs.getString("UserName"));
				myManagers.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myManagers;
	}

	public ArrayList<Part> getParts() {
		ArrayList<Part> myPartsList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadPart}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Part row = new Part(rs.getInt("PartNumber"), rs.getString("Name"), rs.getInt("Price"));
				myPartsList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myPartsList;
	}

	public ArrayList<Employee> getEmployees() {

		ArrayList<Employee> myEmployeesList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadEmployee}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Employee row = new Employee(rs.getString("Username"));
				myEmployeesList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myEmployeesList;
	}

	public ArrayList<ManagerView> getManagerView() {
		// TODO Auto-generated method stub
		ArrayList<ManagerView> myEmployeesList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL [ReadManagerCustomerView]}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				ManagerView row = new ManagerView(rs.getString("Username"), rs.getString("FirstName"),
						rs.getString("LastName"));
				myEmployeesList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myEmployeesList;
	}

	public ArrayList<Customer> getCustomers() {

		ArrayList<Customer> myCustomerList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadCustomer}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Customer row = new Customer(rs.getString("Username"), rs.getInt("NumberOfVisits"));
				myCustomerList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myCustomerList;
	}

	public ArrayList<CustomerView> getCustomerViews() {

		ArrayList<CustomerView> myCustomerList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadCustomerView}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				CustomerView row = new CustomerView(rs.getString("Username"), rs.getInt("NumberOfVisits"),
						rs.getString("FirstName"), rs.getString("LastName"));
				myCustomerList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myCustomerList;
	}

	public ArrayList<EmployeeView> getEmployeeViews() {

		ArrayList<EmployeeView> myEmployeeList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadEmployeeView}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				EmployeeView row = new EmployeeView(rs.getString("Username"), rs.getString("FirstName"),
						rs.getString("LastName"));
				myEmployeeList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myEmployeeList;
	}

	public ArrayList<Assign> getAssignments() {

		ArrayList<Assign> myRepairs = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL ReadAssign}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Assign row = new Assign(rs.getString("ManagerUserName"), rs.getString("EmployeeUserName"),
						rs.getInt("TaskID"));
				myRepairs.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myRepairs;
	}

	public ArrayList<Task> getAllEmployeeTasks(String username) {
		ArrayList<Task> myTaskList = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {? = CALL ReadEmployeeTasks(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Task row = new Task(rs.getInt("ID"), rs.getString("Name"), rs.getString("Description"),
						rs.getInt("Price"), rs.getInt("Completion"));
				myTaskList.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myTaskList;

	}

	public ArrayList<EmployeeTaskAssignments> getRepairTaskForEmployee(String username) {
		ArrayList<EmployeeTaskAssignments> myRepairs = new ArrayList<>();
		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {? = CALL ReadgetRepairTaskForEmployee(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				EmployeeTaskAssignments row = new EmployeeTaskAssignments(rs.getInt("RepairID"), rs.getInt("TaskID"),
						rs.getString("Name"), rs.getString("Description"), rs.getInt("Completion"), username);
				myRepairs.add(row);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return myRepairs;

	}

	public boolean deleteAssign(String ManagerUserName, int TaskID, String EmployeeUserName) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteAssign(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ManagerUserName);
			cs.setInt(3, TaskID);
			cs.setString(4, EmployeeUserName);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete ManagerUserName Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete ManagerUserName Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteCustomer(String UserName) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteCustomer(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, UserName);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Customer Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Customer Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteFor(int PartNumber, int TaskID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteFor(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, PartNumber);
			cs.setInt(3, TaskID);

			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete For Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete For Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteGets(String VIN, int repairID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteGets(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VIN);
			cs.setInt(3, repairID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Gets Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Gets Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteHas(String VIN, int repairID, int taskID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteHas(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VIN);
			cs.setInt(3, repairID);
			cs.setInt(4, taskID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Has Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Has Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteInsurance(String ClaimNumber) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteInsurance(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ClaimNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Insurance Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Insurance Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteOrders(String ManagerUserName, int PartNumber) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteOrders(?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ManagerUserName);
			cs.setInt(3, PartNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Orders Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Orders Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deletePaidFor(String VehicleVIN, int RepairID, String InsuranceClaimNumber) {
		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeletePaidFor(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VehicleVIN);
			cs.setInt(3, RepairID);
			cs.setString(4, InsuranceClaimNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete PaidFor Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete PaidFor Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deletePaidBy(String VehicleVIN, int RepairID, String CustomerUserName) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeletePaidBy(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VehicleVIN);
			cs.setInt(3, RepairID);
			cs.setString(4, CustomerUserName);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete PaidBy Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete PaidBy Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteTask(int ID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteTask(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, ID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Task Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Task Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteRepair(int repairID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteRepair(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, repairID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Repair Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Repair Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteVehicle(String vinNumber) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteVehicle(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, vinNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Vehicle Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Vehicle Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deletePart(int partNumber) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeletePart(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, partNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Part Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Part Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteEmployee(String employeeUsername) {
		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL DeleteEmployee(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, employeeUsername);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Delete Employee Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Delete Employee Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateAssign(String BeforeManagerUserName, String BeforeEmployeeUserName, int BeforeTaskID,
			String AfterManagerUserName, String AfterEmployeeUserName, int AfterTaskID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateAssign(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BeforeManagerUserName);
			cs.setString(3, BeforeEmployeeUserName);
			cs.setInt(4, BeforeTaskID);
			cs.setString(5, AfterManagerUserName);
			cs.setString(6, AfterEmployeeUserName);
			cs.setInt(7, AfterTaskID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Assign Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Assign Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateCustomer(String Username, String FirstName, String LastName, int NumberOfVists) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateCustomer(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, Username);
			cs.setString(3, FirstName);
			cs.setString(4, LastName);
			cs.setInt(5, NumberOfVists);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Customer Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Customer Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateEmployee(String Username, String FirstName, String LastName) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateEmployee(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, Username);
			cs.setString(3, FirstName);
			cs.setString(4, LastName);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Employee Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Employee Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean UpdateFor(int BeforePartNumber, int BeforeTaskId, int AfterPartNumber, int AfterTaskID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateFor(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, BeforePartNumber);
			cs.setInt(3, BeforeTaskId);
			cs.setInt(4, AfterPartNumber);
			cs.setInt(5, AfterTaskID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update For Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update For Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean UpdateGets(String BeforeVIN, int BeforeRepairID, String AfterVIN, int AfterRepairID) {

		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {? = CALL UpdateGets(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BeforeVIN);
			cs.setInt(3, BeforeRepairID);
			cs.setString(4, AfterVIN);
			cs.setInt(5, AfterRepairID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Gets Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Gets Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateHas(int BeforeRepairID, String BeforeVehicleVIN, int BeforeTaskID, int AfterRepairID,
			String afterVehicleVIN, int AfterTaskID) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateHas(?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, BeforeRepairID);
			cs.setString(3, BeforeVehicleVIN);
			cs.setInt(4, BeforeTaskID);
			cs.setInt(5, AfterRepairID);
			cs.setString(6, afterVehicleVIN);
			cs.setInt(7, AfterTaskID);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Has Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Has Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateInsurance(String ClaimNumber, String PolicyNumber, int deductible) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateInsurance(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ClaimNumber);
			cs.setString(3, PolicyNumber);
			cs.setInt(4, deductible);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Insurance Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Insurance Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateManager(String Username, String FirstName, String LastName) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateManager(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, Username);
			cs.setString(3, FirstName);
			cs.setString(4, LastName);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Manager Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Manager Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateOrders(String BeforeManagerUsername, int BeforePartNumber, String afterManagerUserNamer,
			int AfterPartNumber) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateOrders(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BeforeManagerUsername);
			cs.setInt(3, BeforePartNumber);
			cs.setString(4, afterManagerUserNamer);
			cs.setInt(5, AfterPartNumber);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Orders Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Orders Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updatePaidBy(String BeforeVehicleVIN, int BeforeRepairID, String beforeCustomerUserName,
			String AfterReceipt) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdatePaidBy(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BeforeVehicleVIN);
			cs.setInt(3, BeforeRepairID);
			cs.setString(4, beforeCustomerUserName);
			cs.setString(5, AfterReceipt);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update PaidBy Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update PaidBy Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updatePaidFor(String BeforeVehicleVIN, int BeforeRepairID, String beforeInsuranceClaimNumber,
			String AfterReceipt) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdatePaidFor(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BeforeVehicleVIN);
			cs.setInt(3, BeforeRepairID);
			cs.setString(4, beforeInsuranceClaimNumber);
			cs.setString(5, AfterReceipt);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update PaidFor Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update PaidFor Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updatePart(int PartNumber, String Name, int Price) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdatePart(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, PartNumber);
			cs.setString(3, Name);
			cs.setInt(4, Price);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Part Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Part Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateRepair(int ID, String StartDate, String EndDate, String Description, int Discount,
			int TotalCost, int Completion) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateRepair(?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, ID);
			cs.setString(3, StartDate);
			cs.setString(4, EndDate);
			cs.setString(5, Description);
			cs.setInt(6, Discount);
			cs.setInt(7, TotalCost);
			cs.setInt(8, Completion);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Repair Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Repair Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateTask(int ID, String Name, String Description, int Price, int Completion) {

		try {
			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {? = CALL UpdateTask(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, ID);
			cs.setString(3, Name);
			cs.setString(4, Description);
			cs.setInt(5, Price);
			cs.setInt(6, Completion);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Task Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Task Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateVehicle(String VIN, int Year, String Model, int Mileage, String bodyType) {

		try {
			Connection c = dbService.getConnection();

			CallableStatement cs = c.prepareCall(" {? = CALL UpdateVehicle(?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, VIN);
			cs.setInt(3, Year);
			cs.setString(4, Model);
			cs.setInt(5, Mileage);
			cs.setString(6, bodyType);
			cs.execute();

			int returnCode = cs.getInt(1);
			if (returnCode == 0) {
				JOptionPane.showMessageDialog(null, "Update Vehicle Successful");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Update Vehicle Failed Successfully");
				System.out.println(returnCode);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<Vehicle> getPaidByVehicleInfo(String username) {

		ArrayList<PaidBy> repairs = this.getPaidBy(username);

		ArrayList<String> vehicles = new ArrayList<>();

		if (repairs == null) {
			return null;
		}
		for (int i = 0; i < repairs.size(); i++) {
			String vin = repairs.get(i).getVehicleVIN();
			if (!vehicles.contains(vin)) {
				vehicles.add(vin);
			}
		}
		ArrayList<Vehicle> cars = new ArrayList<>();

		for (String vehicle : vehicles) {
			cars.add(this.getVehicleByVIN(vehicle));
		}
		return cars;
	}

	private Vehicle getVehicleByVIN(String vehicle) {

		Vehicle car = null;
		try {

			Connection c = dbService.getConnection();
			CallableStatement cs = c.prepareCall(" {CALL getVehicleByVIN(?)}");
			cs.setString(1, vehicle);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				// String VIN, int Year,String Model, int Mileage, String BodyType
				car = new Vehicle(rs.getString("VIN"), rs.getInt("Year"), rs.getString("Model"), rs.getInt("Mileage"),
						rs.getString("BodyType"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return car;
	}

	public ArrayList<Repair> searchByID(int id) {

		ArrayList<Repair> allRepairs = this.getRepairs();
		ArrayList<Repair> repairsByID = new ArrayList<>();
		for (Repair r : allRepairs) {
			if (r.getID() == id)
				repairsByID.add(r);
		}

		return repairsByID;
	}

	public ArrayList<Task> getTaskByRepairID(int id) {
		
		ArrayList<Has> allHas = this.getAllHas();
		ArrayList<Task> allTasks = this.getTasks();
		ArrayList<Task> tasksByID = new ArrayList<>();
		
		for(Has h : allHas) {
			
			if(h.getRepairID() == id) {
				int taskID = h.getTaskID();
			
				for(Task t : allTasks) {
					if(t.getID() == taskID) {
						tasksByID.add(t);
					}
				}
			}
		
		}return tasksByID;
}

	public ArrayList<Part> searchByPartNumber(int number) {
		ArrayList<Part> allParts = this.getParts();
		ArrayList<Part> partsByNum = new ArrayList<>();
		for (Part r : allParts) {
			if (r.getPartNumber() == number)
				partsByNum.add(r);
		}

		return partsByNum;
	}

}
