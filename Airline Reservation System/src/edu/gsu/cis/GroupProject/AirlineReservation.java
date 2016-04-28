package edu.gsu.cis.GroupProject;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class AirlineReservation {
	protected String username;
	protected String password;
	protected static String userType;
		
	// JDBC driver name and database URL
	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	protected static final String DB_URL = "jdbc:mysql://localhost:3306/airline_system?useSSL=false";

	// Database credentials
	protected static final String USER = "root";
	protected static final String PASS = "password123";
		
	public AirlineReservation() throws Exception {

	}
	
	public void splashScreen() throws SQLException, ParseException {
		Object[] choices = { "Login", "Register" };
		Object defaultChoice = choices[0];
		int option = JOptionPane.showOptionDialog(null, 
				"Welcome to the Airline Reservation System",
		        null, JOptionPane.YES_NO_OPTION,
		        JOptionPane.PLAIN_MESSAGE, null,
		        choices, defaultChoice);
		if (option == JOptionPane.YES_OPTION) {
			procUserLogin();
		}
		if (option == JOptionPane.NO_OPTION) {
			getUserRegistration();
		}
		if (option == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
	}

	
	/** 
	 * Receive user login and determine authorized accounts
	 */
	public void procUserLogin() {
		// Authenticate the user first and find out which accounts the user
		// is authorized to access
		String loginInfo;			
		boolean cont = true;
		
		do {
			loginInfo = getUserLogin();
			if (loginInfo == null) {
				JOptionPane.showMessageDialog(null,
						"Invalid user ID/password. Please try again.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String[] userLogin = loginInfo.split(" ");
				username = userLogin[0];
				password = userLogin[1];

				// Compare input against the database
				DBConnection db = new DBConnection();
				boolean valid = db.isValidLogin(username, password);
				userType = db.getUserType();

				if (valid == false) {
					JOptionPane.showMessageDialog(null,
							"The user ID or the password is incorrect. Please "
							+ "try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Welcome back " + username + "!");
					cont = false;
					manageMainMenu();
				}
			}
		} while (cont == true);
	}
	
		
	/** 
	 * Format a JOptionPane dialog box to have two input data fields (User ID and Password), 
	 * validate the login info, and then return it to the procUserLogin method
	 */
	private String getUserLogin() {
		String loginInfo = "";
		// Format the input dialog box
		// Create JPanel object
	    JPanel panel = new JPanel(new BorderLayout(3, 3));
	        
	    // Format the header and display the welcome message
	    JPanel header = new JPanel(new GridLayout(1, 3));
	    header.add(new JLabel("Enter Your Account", SwingConstants.CENTER));
	    panel.add(header, BorderLayout.NORTH);
	        
	    // Format the labels
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("Username: ", SwingConstants.RIGHT));
	    label.add(new JLabel("Password: ", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	        
	    // Format the fields and get the login id and password
	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField inputUsername = new JTextField(15);
	    controls.add(inputUsername);        
	    JTextField inputPassword = new JPasswordField(15);
	    controls.add(inputPassword);
	    panel.add(controls, BorderLayout.CENTER);
	        
	    // Display the dialog box
	    Object[] choices = { "Sign In" };
		Object defaultChoice = choices[0];
		int result = JOptionPane.showOptionDialog(null, panel, "User Login", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
				null, choices, defaultChoice);
	        
	    // Validate the user's selection and input
	    if (result == JOptionPane.OK_OPTION) {
	    	username = inputUsername.getText();
	    	password = inputPassword.getText();

	    	if (username.isEmpty() || password.isEmpty()) { 
	    		// Return a value of null
	    		loginInfo = null;
	    	}
	    	else {
	    		// Return userID and password
	    		loginInfo = username + " " + password;
	    	}
	    }

	    // If user clicks close
	    if (result == JOptionPane.CLOSED_OPTION) {
	        // Terminate the application
	        System.exit(0);
	    }
	    return loginInfo;
	}
	
	
	/** 
	 * Format a JOptionPane dialog box to have multiple user info data fields, 
	 * validate the input, and then send the input to the registerNewUser method 
	 * to add it to the database 
	 */
	private void getUserRegistration() throws SQLException, ParseException {
		String[] states = { "Select", "Alabama", "Alaska", "Arizona", "Arkansas", 
				"California", "Colorado", "Connecticut", "Delaware", 
				"Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
				"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", 
				"Maine", "Maryland", "Massachusetts", "Michigan", 
				"Minnesota", "Mississippi", "Missouri", "Montana", 
				"Nebraska", "Nevada", "New Hampshire", "New Jersey", 
				"New Mexico", "New York", "North Carolina", "North Dakota", 
				"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", 
				"South Carolina", "South Dakota", "Tennessee", "Texas", 
				"Utah", "Vermont", "Virginia", "Washington", "West Virginia", 
				"Wisconsin", "Wyoming" };
				
		// Format the input dialog box
		// Create JPanel object
	    JPanel panel = new JPanel(new BorderLayout(3, 3));
	        
	    // Format the header and display the welcome message
	    JPanel header = new JPanel(new GridLayout(1, 3));
	    header.add(new JLabel("Create An Account", SwingConstants.CENTER));
	    panel.add(header, BorderLayout.NORTH);
	            
	    // Format the labels
	    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
	    label.add(new JLabel("*First: ", SwingConstants.RIGHT));
	    label.add(new JLabel("Middle: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Last: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Street: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*City: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*State: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Zip: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Username: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Password: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Email: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*SSN: ", SwingConstants.RIGHT));
	    label.add(new JLabel("Security Question: ", SwingConstants.RIGHT));
	    label.add(new JLabel("*Security Answer: ", SwingConstants.RIGHT));
	    panel.add(label, BorderLayout.WEST);
	        
	    // Format the fields and get the login id and password
	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField inputFirst = new JTextField(30);
	    controls.add(inputFirst);
	    JTextField inputMiddle = new JTextField(30);
	    controls.add(inputMiddle);
	    JTextField inputLast = new JTextField(30);
	    controls.add(inputLast);
	    JTextField inputStreet = new JTextField(30);
	    controls.add(inputStreet);
	    JTextField inputCity = new JTextField(30);
	    controls.add(inputCity);
	    JComboBox<?> selectState = new JComboBox<Object>(states);
	    controls.add(selectState);
	    JTextField inputZip = new JTextField(30);
	    controls.add(inputZip);
	    JTextField inputUsername = new JTextField(20);
	    controls.add(inputUsername);
	    JTextField inputPassword = new JPasswordField(20);
	    controls.add(inputPassword);
	    JTextField inputEmail = new JTextField(30);
	    controls.add(inputEmail);
	    MaskFormatter mf = new MaskFormatter("###-##-####");
	    JFormattedTextField inputSSN = new JFormattedTextField(mf);
	    controls.add(inputSSN);	    
	    JTextField showSQ = new JTextField("What was the name of your first pet?");
	    showSQ.setEditable(false);
	    controls.add(showSQ);
	    JTextField inputANS = new JTextField();
	    controls.add(inputANS); 
	    panel.add(controls, BorderLayout.CENTER);
	    
	    // Display registration form
	    boolean cont = true;
	    int result;
		do {
			// Display the dialog box
		    Object[] choices = { "Register" };
			Object defaultChoice = choices[0];
			result = JOptionPane.showOptionDialog(null, panel, "User Registration", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, choices, defaultChoice);
			
			String[] userInput = new String[12];
			// Validate the user's selection and input
			if (result == JOptionPane.OK_OPTION) {
				userInput[0] = inputFirst.getText();
				userInput[1] = inputMiddle.getText();
				userInput[2] = inputLast.getText();
				userInput[3] = inputStreet.getText();
				userInput[4] = inputCity.getText();
				userInput[5] = (String) selectState.getSelectedItem();
				userInput[6] = inputZip.getText();
				userInput[7] = inputUsername.getText();
				userInput[8] = inputPassword.getText();
				userInput[9] = inputEmail.getText();
				userInput[10] = inputSSN.getText();
				userInput[11] = inputANS.getText();

				int count = 0;
				for (int i = 0; i < userInput.length; i++) {
					if (userInput[i].isEmpty() && i != 1) {
						// All fields are required except middle name
						count++;
					}
				}				
				if (count != 0) {
					JOptionPane.showMessageDialog(null,
							"Please fill in all of the data fields.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ValidateInput vi = new ValidateInput();
					if (vi.isValidAddress(userInput[3]) && 
							vi.isValidState(userInput[5]) && 
							vi.isValidZip(userInput[6]) &&
							vi.isValidUsername(userInput[7]) &&
							vi.isValidPassword(userInput[8]) &&
							vi.isValidEmail(userInput[9]) &&
							vi.isValidSSN(userInput[10])) {
						cont = false;
						DBConnection db = new DBConnection();
						boolean success = db.registerNewUser(userInput);
						if (success == true) {
							// Go to login screen
							procUserLogin();
						}
					}
				}
			}
			// If user clicks close
	    	if (result == JOptionPane.CLOSED_OPTION) {
	    		// Terminate the application
	    		System.exit(0);
	    	}
		} while (cont == true);
 	}	
		
		
	/**
	 * Display main menu and handle user's choices
	 */
	public void manageMainMenu() {
		// Loop forever until the user select to end the application
		int choice = 0;
		try {
			if (userType.equals("customer")) {
				choice = readMenuChoice("Select one of the following options:\n"
						+ "\t1: Book a Domestic Flight\n" 
						+ "\t2: Book an International Flight\n"
						+ "\t3: Manage Reservations\n" 
						+ "\t4: Log Out\n", 4);

				switch (choice) {
					case 1:	bookDomesticFlight(); break;
					case 2: bookInternationalFlight(); break;
					case 3: manageReservations(); break;
					case 4: 
						// Display a farewell message
						JOptionPane.showMessageDialog(null, 
							"Thank you for using the airline reservation "
							+ "system. Have a nice day!", null, 
							JOptionPane.PLAIN_MESSAGE);
						System.exit(0);	// Terminate the application
					default:
						JOptionPane.showMessageDialog(null, "Unknown choice",
								"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (userType.equals("admin")) {
				choice = readMenuChoice("Select one of the following options:\n"
						+ "\t1: Book a Domestic Flight\n" 
						+ "\t2: Book an International Flight\n"
						+ "\t3: Manage Reservations\n" 
						+ "\t4: Add a Flight\n" 
						+ "\t5: Update a Flight\n"
						+ "\t6: Delete a Flight\n"
						+ "\t7: Log Out\n", 7);

				switch (choice) {
					case 1:	bookDomesticFlight(); break;
					case 2: bookInternationalFlight(); break;
					case 3: manageReservations(); break;
					case 4: addFlight(); break;
					case 5: updateFlight(); break;
					case 6: deleteFlight(); break;
					case 7: 
						// Display a farewell message
						JOptionPane.showMessageDialog(null, 
							"Thank you for using the airline reservation "
							+ "system. Have a nice day!", null, 
							JOptionPane.PLAIN_MESSAGE);
						System.exit(0);	// Terminate the application
					default:
						JOptionPane.showMessageDialog(null, "Unknown choice",
								"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception ex) {
			// Display fatal error message
			JOptionPane.showMessageDialog(null, "Fatal Error", "Error", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
		
		
	/**
	 * Here is an example to start the application. You should modify it
	 * to be your implementation such as using GUI instead of the console
	 */
	public void startApp() {
		// Authenticate the user first and find out which accounts the user
		// Is authorized to access
		procUserLogin();

		// Loop forever until the user select to end the application
		//manageMainMenu();
	}

	
	/**
	 * Display a number of options and return a choice of one of the options
	 */
	public int readMenuChoice(String message, int lastOption) {
		// loop forever until the user enter a positive integer
		int menuChoice = 0;	
		do {
			try {
				// Display the menu options and get the user's choice
				String inputStr = JOptionPane.showInputDialog(null, 
						message, "Airline Flight Reservation", 
						JOptionPane.PLAIN_MESSAGE);						
				menuChoice = Integer.parseInt(inputStr);
					
				// Make sure the choice is a valid option
				if (menuChoice < 1 || menuChoice > lastOption) {
					JOptionPane.showMessageDialog(null, 
							"The choice must be between 1 and " + lastOption,
							"Error", JOptionPane.ERROR_MESSAGE);
					menuChoice = 0;	// Loop control value
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, 
						"The menu choice must be an integer between 1 and " + lastOption,
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} while (menuChoice <= 0);
		return menuChoice;	// Return the entered integer
	}

		
	/**
	 * Process domestic flight booking request
	 */
	public void bookDomesticFlight() throws SQLException, ParseException {

			String[] destCity = new String[19];
			
			List<Integer> adults = new ArrayList<Integer>(6);
			List<Integer> children = new ArrayList<Integer>(6);
			List<Integer> infants = new ArrayList<Integer>(6);
			for (int i = 0; i <= 5; i++) {
			    adults.add(i);
			    children.add(i);			    
			    if (i <= 3) {
			    	infants.add(i);
			    }
			}

			// Format the input dialog box
			// Create JPanel object
		    JPanel panel = new JPanel(new BorderLayout(6, 6));
		        
		    // Format the header and display the welcome message
		    JPanel header = new JPanel(new GridLayout(1, 6));
		    header.add(new JLabel("Booking & Passenger Details: ", 
		    		SwingConstants.CENTER));
		    panel.add(header, BorderLayout.NORTH);
		            
		    // Format the labels
		    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		    label.add(new JLabel("From: ", SwingConstants.RIGHT));
		    label.add(new JLabel("To: ", SwingConstants.RIGHT));
		    label.add(new JLabel("Date: ", SwingConstants.RIGHT));
		    label.add(new JLabel("Adults: ", SwingConstants.RIGHT));
		    label.add(new JLabel("Children: ", SwingConstants.RIGHT));
		    label.add(new JLabel("Infants: ", SwingConstants.RIGHT));
		    panel.add(label, BorderLayout.WEST);
		    
		    DBConnection db = new DBConnection();
		    String departCity = db.getStartColumn("domestic_flights", "start");
		    destCity = db.getDestColumn("domestic_flights", "dest");

		    // Format the fields and get the login id and password
		 	JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		 	JTextField depart = new JTextField(departCity);
		 	depart.setEditable(false);
		 	controls.add(depart);
		    JComboBox<?> selectDest = new JComboBox<Object>(destCity);
		    controls.add(selectDest);
		    
		    JTextField selectDate = new JTextField();
		 	controls.add(selectDate);
		    JComboBox<?> selectAdults = new JComboBox<Object>(adults.toArray());
		    controls.add(selectAdults);
		    JComboBox<?> selectChildren = new JComboBox<Object>(children.toArray());
		    controls.add(selectChildren);
		    JComboBox<?> selectInfants = new JComboBox<Object>(infants.toArray());
		    controls.add(selectInfants);
		    panel.add(controls, BorderLayout.CENTER);

 		    // Display booking form
		    boolean cont = true;
		    int result;
			do {
				// Display the dialog box
			    Object[] choices = { "Search Flights", "Back To Menu" };
				Object defaultChoice = choices[0];
				result = JOptionPane.showOptionDialog(null, panel, "Book a Flight", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
						null, choices, defaultChoice);
	
				String[] flightInput = new String[6];
				// Validate the user's selection and input
				if (result == JOptionPane.OK_OPTION) {
					flightInput[0] = depart.getText();
					flightInput[1] = (String) selectDest.getSelectedItem();
					flightInput[2] = selectDate.getText();
					flightInput[3] = (String) selectAdults.getSelectedItem();
					flightInput[4] = (String) selectChildren.getSelectedItem();
					flightInput[5] = (String) selectInfants.getSelectedItem();

					int count = 0;
					for (int i = 0; i < flightInput.length; i++) {
						if (flightInput[i].isEmpty() || flightInput[i] == "Select") {
							// All fields are required except middle name
							count++;
						}
					}
					if (count != 0) {
						JOptionPane.showMessageDialog(null,
								"Please fill in all of the data fields.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String[] flight = new String[7];
						flight = db.getFlightInfo("domestic_flights", flightInput);					
						int price = Integer.parseInt(flight[4]);
					    int available = Integer.parseInt(flight[6]);		    
					    int nAdults = Integer.parseInt(flightInput[4]);
					    int nChildren = Integer.parseInt(flightInput[5]);
					    int nInfants = Integer.parseInt(flightInput[6]);
					    int seatsWanted = nAdults + nChildren;  // Infants sit in laps
					    int totalCost = 0;
					    
					    if (available == 0) {
					    	JOptionPane.showMessageDialog(null, 
									"This flight is full", "Error", 
									JOptionPane.ERROR_MESSAGE);
					    }
					    
					    if ((available - seatsWanted) >= 0) {
					    	String message = "There are " + seatsWanted + " seats "
					    			+ "available on " + flightInput[2] + " at " 
					    			+ flight[3] + " for $" + price + " per ticket. "
					    			+ "Do you want to book now?";
					    	int option = JOptionPane.showConfirmDialog(null, message);
					    	JOptionPane.showMessageDialog(null, option);
					    	if (option == 0) {
					    		totalCost = seatsWanted * price;
					    		boolean success = db.bookNewFlight(flight, flightInput[2], 
					    				totalCost, nAdults, nChildren, nInfants, username);
					    		if (success == true) {
					    			manageMainMenu();
					    		}
					    	}
					    	if (option == 1) {
					    		manageMainMenu();
					    	}
					    }
						//boolean success = db.bookNewFlight(flightInput);
						//if (success == true) {
							// Go to login screen
							//procUserLogin();
						//}
					}
				}
				if (result == JOptionPane.CANCEL_OPTION || result == 1) {
					// Go back to main menu
					manageMainMenu();
				}
		    	if (result == JOptionPane.CLOSED_OPTION) {
		    		// Terminate the application
		    		System.exit(0);
		    	}
			} while (cont == true);
			// Loop until the user select an option


		// Continue the application
		manageMainMenu();
	}
		
		
	/**
	 * Process domestic flight booking request
	 */
	public void	bookInternationalFlight() {	
		try {


			// Loop until the user select an option
		} catch (Exception ex) {
			throw ex;
		}
			
		// Continue the application
		manageMainMenu();
	}
		
		
	/**
	 * Process reservation management request
	 */
	public void	manageReservations() {
		try {
			

			// Loop until the user select an option
		} catch (Exception ex) {
			throw ex;
		}

		// Continue the application
		manageMainMenu();
	}
	
	
	/**
	 * ======================
	 *  FOR ADMIN USERS ONLY
	 * ======================
	 */
	public void	addFlight() {
		try {
			
		// Loop until the user select an option
		} catch (Exception ex) {
			throw ex;
		}

		// Continue the application
		manageMainMenu();
	}
	
	
	public void	updateFlight() {
		try {
			
			// Loop until the user select an option
		} catch (Exception ex) {
			throw ex;
		}

		// Continue the application
		manageMainMenu();
	}
	
	
	public void	deleteFlight() {
		try {
			
			// Loop until the user select an option
		} catch (Exception ex) {
			throw ex;
		}

		// Continue the application
		manageMainMenu();
	}
	

	/**
	 * The drive of the online application. It first creates an OnlineBanking
	 * object. It should then call the main method to start the application
	 */
	public static void main(String[] args) throws Exception {
		// Create the OnlineBanking application object
		AirlineReservation app = new AirlineReservation();
			
		// Start the application
		app.splashScreen();
	}
}