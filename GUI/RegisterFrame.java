package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame implements ActionListener {

	Container container = getContentPane();
	JLabel userNameLabel = new JLabel("USERNAME");
	JLabel userTypeLabel = new JLabel("USERTYPE");
	JLabel newPasswordLabel = new JLabel("PASSWORD");
	JLabel confirmPasswordLabel = new JLabel("CONFIRM PASS");
	JLabel firstName = new JLabel("FIRST NAME");
	JLabel lastName = new JLabel("LAST NAME");
	

	JTextField userFirstName= new JTextField();
	JTextField userLastName= new JTextField();
	JTextField userTextField = new JTextField();
	JComboBox<String> comboTextField;
	JPasswordField newPasswordField = new JPasswordField();
	JPasswordField confirmPasswordField = new JPasswordField();
	
	
	

	JButton backToLoginButton = new JButton("BACK TO LOGIN");
	JButton completeButton = new JButton("COMPLETE");
	JButton resetButton = new JButton("RESET");
	JCheckBox showPassword = new JCheckBox("Show Password");
	AppRunner app;

	

	public RegisterFrame(AppRunner application) {
		this.app = application;
		
		String[] myUserTypes = new String[3];
		myUserTypes[0] = "Manager";
		myUserTypes[1] = "Employee";
		myUserTypes[2] = "Customer";
		this.comboTextField = new JComboBox<String>(myUserTypes);
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		
		
		firstName.setBounds(50, 50, 100, 30);
		userFirstName.setBounds(150, 50, 150, 30);
		
		lastName.setBounds(50,100,100,30);
		userLastName.setBounds(150,100,150,30);
	
		userNameLabel.setBounds(50, 150, 100, 30);
		userTextField.setBounds(150, 150, 150, 30);
		
		userTypeLabel.setBounds(50, 200, 100, 30);
		comboTextField.setBounds(150, 200, 150, 30);
		
		newPasswordLabel.setBounds(50, 250, 100, 30);
		newPasswordField.setBounds(150, 250, 150, 30);
		showPassword.setBounds(150, 300, 150, 30);
		
		confirmPasswordLabel.setBounds(50, 350, 150, 30);
		confirmPasswordField.setBounds(150, 350, 150, 30);
		
		
	
	
		
		backToLoginButton.setBounds(50, 500, 150, 30);
		completeButton.setBounds(200, 500, 100, 30);
		resetButton.setBounds(200, 400, 100, 30);

	}

	public void addComponentsToContainer() {

		container.add(firstName);
		container.add(lastName);
		container.add(userFirstName);
		container.add(userLastName);
		container.add(userNameLabel);
		container.add(newPasswordLabel);
		container.add(confirmPasswordLabel);
		container.add(userTextField);
		container.add(newPasswordField);
		container.add(confirmPasswordField);
		container.add(showPassword);
		container.add(completeButton);
		container.add(resetButton);
		container.add(backToLoginButton);
		container.add(comboTextField);
		container.add(userTypeLabel);

	}

	public void addActionEvent() {
		comboTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		completeButton.addActionListener(this);
		resetButton.addActionListener(this);
		showPassword.addActionListener(this);
		backToLoginButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == completeButton) {
			String myfirstname,mylastname;
			String userText;
			char[] newPwdText;
			char[] confirmPwdText;
			String newPass = "";
			String confirmPass = "";
			String userType = "";
			
			myfirstname = userFirstName.getText();
			mylastname = userLastName.getText();
			userText = userTextField.getText();
			userType  = (String)comboTextField.getSelectedItem();
			newPwdText = newPasswordField.getPassword();
			confirmPwdText = confirmPasswordField.getPassword();

			if (newPwdText.length != confirmPwdText.length)
				JOptionPane.showMessageDialog(this, "Passwords Don't Match");

			for (int i = 0; i < newPwdText.length; i++) {
				newPass += newPwdText[i];
				confirmPass += confirmPwdText[i];

			}

			if (!newPass.equals(confirmPass)) {
				JOptionPane.showMessageDialog(this, "Passwords Don't Match");
			} else if (newPass.equals("") || userText.equals("") || myfirstname.equals("") || mylastname.equals("")) {
				JOptionPane.showMessageDialog(this, "Please fill out all forms before continuing.");
			} else {

				/*
				 * // Create user
				String sql = "CREATE USER ? @localhost";
				query.setParameter(1, user.getUserName());


				// Set password
				sql = "SET PASSWORD FOR ? @localhost = PASSWORD(?)";
				query.setParameter(1, user.getUserName()); 
				query.setParameter(2, user.getPassword());
				 * 
				 * 
				 */
				boolean registered = app.completeRegistration(myfirstname,mylastname, userText, newPass, userType);
				if(registered) {
					JOptionPane.showMessageDialog(this, "Registration Successful. You may now exit this page.");
				}
			}
		}

		if (e.getSource() == backToLoginButton) {
			this.setVisible(false);
		}
	
		if (e.getSource() == resetButton) {
			userTextField.setText("");
			newPasswordField.setText("");
			confirmPasswordField.setText("");
		}
		
		if (e.getSource() == showPassword) {
			if (showPassword.isSelected()) {
				newPasswordField.setEchoChar((char) 0);
			} else {
				newPasswordField.setEchoChar('*');
			}

		}
	}

}
