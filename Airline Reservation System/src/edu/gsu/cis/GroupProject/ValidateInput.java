package edu.gsu.cis.GroupProject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ValidateInput {
	public ValidateInput() {
	}
	
	public boolean isValidAddress(String street) {
		String regex = "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(street);
		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Please enter a valid street address.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return m.matches();
	}

	public boolean isValidState(String state) {
		if (state == "Select") {
			JOptionPane.showMessageDialog(null,
					"Please select your state.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean isValidZip(String zip) {
		String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(zip);
		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Please enter a valid zip code.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return m.matches();
	}
	
	public boolean isValidUsername(String username) {
		String regex = "^[a-zA-Z0-9_-]{4,15}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Please enter a valid username", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
		return m.matches();
	}
	
	public boolean isValidPassword(String password) {
		String regex = "((?=.*[a-zA-Z])(?=.*\\d).{4,15})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null,
					"Please enter a valid password \nNote: Passwords "
					+ "must be 4-15 chars \nlong and contain at "
					+ "least one digit", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
		return m.matches();
	}
	
	public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\."
        		+ "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]"
        		+ "+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (m.matches() == false) {
			JOptionPane.showMessageDialog(null, "Please enter a "
					+ "valid email address.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
        return m.matches();
	}
	
	public boolean isValidSSN(String ssn) {
		String regex = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";  
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ssn);
		if (m.matches() == false) {
			JOptionPane.showMessageDialog(null, "Please enter a "
					+ "valid social security number.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}  
		return m.matches();
	}
}