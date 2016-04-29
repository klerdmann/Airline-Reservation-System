package edu.gsu.cis.GroupProject;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import java.awt.Dimension;
import java.awt.GridLayout;

public class AirlineReservation {
	protected String username;
	protected String password;
	protected static String userType;
		
	// JDBC driver name, database URL, and database credentials
	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	protected static final String DB_URL = "jdbc:mysql://localhost:3306/airline_system?useSSL=false";
	protected static final String USER = "root";
	protected static final String PASS = "password123";
		
	public AirlineReservation() throws Exception {
	}
	
	public void optionScreen() throws SQLException, ParseException {
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
	 * Process user login and determine the user type
	 */
	public void procUserLogin() {
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
		
		// Format the fields and get the login id and password
	    JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
	    JTextField inputUsername = new JTextField(15);
	    controls.add(inputUsername);        
	    JTextField inputPassword = new JPasswordField(15);
	    controls.add(inputPassword);
	    
	    Formatting form = new Formatting();
	    JPanel panel = form.getLoginForm(controls);
	    
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
	    		loginInfo = username + " " + password;
	    	}
	    }
	    if (result == JOptionPane.CLOSED_OPTION) {
	        System.exit(0);  // Terminate the application
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
				"California", "Colorado", "Connecticut", "Delaware", "Florida", 
				"Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", 
				"Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", 
				"Massachusetts", "Michigan", "Minnesota", "Mississippi", 
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
				"New Jersey", "New Mexico", "New York", "North Carolina", 
				"North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", 
				"Rhode Island", "South Carolina", "South Dakota", "Tennessee", 
				"Texas", "Utah", "Vermont", "Virginia", "Washington", "West "
				+ "Virginia", "Wisconsin", "Wyoming" };

		// Create formatting class object
		Formatting form = new Formatting();
	        
	    // Format the fields
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
	    JTextField showSQ = new JTextField("What was the name "
	    		+ "of your first pet?");
	    showSQ.setEditable(false);
	    controls.add(showSQ);
	    JTextField inputANS = new JTextField();
	    controls.add(inputANS);
	    
	    // Display registration form
	    boolean cont = true;
	    int result;
		do {
			JPanel panel = form.getRegistrationForm(controls);
			
			// Display the dialog box
		    Object[] choices = { "Register", "Back To Menu" };
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
			if (result == JOptionPane.CANCEL_OPTION || result == 1) {
				// Go back to main menu
				optionScreen();
			}
	    	if (result == JOptionPane.CLOSED_OPTION) {
	    		System.exit(0);  // Terminate the application
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
						+ "\t3: View Reservations\n"
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
						+ "\t3: View Reservations\n" 
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
		// Authenticate the user first
		procUserLogin();
	}

	
	/**
	 * Display a number of options and return a choice of one of the options
	 */
	public int readMenuChoice(String message, int lastOption) {
		// Loop forever until the user enter a positive integer
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
		
		return menuChoice;
	}

		
	/**
	 * Process domestic flight booking request
	 */
	public void bookDomesticFlight() throws Exception  {
		procFlightBooking("domestic_flights");
	}
	
	
	/**
	 * Process international flight booking request
	 */
	public void	bookInternationalFlight() throws Exception {	
		procFlightBooking("international_flights");
	}
	
	
	/**
	 * Format input form and process flight booking request
	 */
	public void procFlightBooking(String tableName) throws Exception {
		DBConnection db = new DBConnection();
	    String departCity = db.getStartColumn(tableName, "start");
	    List<String> destCity = db.getDestColumn(tableName, "dest");

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
		
		// Create formatting class object
		Formatting form = new Formatting();
		
		// Create input fields and assign values
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField depart = new JTextField(departCity);
		depart.setEditable(false);
		controls.add(depart);
		JComboBox<?> selectDest = new JComboBox<Object>(destCity.toArray());
		controls.add(selectDest);
		JTextField selectDate = new JTextField(12);
		JButton b = new JButton("...");
		selectDate.setColumns(5);
		b.setPreferredSize(new Dimension(40, 10));
		controls.add(selectDate);
		form.setupDateSelectButton(controls, b, selectDate);      	
		JComboBox<?> selectAdults = new JComboBox<Object>(adults.toArray());
		controls.add(selectAdults);
		JComboBox<?> selectChildren = new JComboBox<Object>(children.toArray());
		controls.add(selectChildren);
		JComboBox<?> selectInfants = new JComboBox<Object>(infants.toArray());
		controls.add(selectInfants);
		
		// Display booking form
		boolean cont = true;
		int result;
		do {
			JPanel panel = form.getBookingForm(controls, b);
			
			// Display the dialog box
			Object[] choices = { "Search Flights", "Back To Menu" };
			Object defaultChoice = choices[0];
			result = JOptionPane.showOptionDialog(null, panel, "Book a Flight", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, choices, defaultChoice);
			
			String[] bookingInfo = new String[3];
			int[] passengerInfo = new int[3];
			// Validate the user's selection and input
			if (result == JOptionPane.OK_OPTION) {
				bookingInfo[0] = depart.getText();
				bookingInfo[1] = (String) selectDest.getSelectedItem();
				bookingInfo[2] = selectDate.getText();
				passengerInfo[0] = (int) selectAdults.getSelectedItem();
				passengerInfo[1] = (int) selectChildren.getSelectedItem();
				passengerInfo[2] = (int) selectInfants.getSelectedItem();
				
				int count = 0;
				for (int i = 0; i < 3; i++) {
					if (bookingInfo[i] == "Select" || (passengerInfo[0] 
							== 0 && passengerInfo[1] == 0)) {
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
					flight = db.getFlightInfo(tableName, bookingInfo);
					int conflictCode = db.checkForConflicts(flight, bookingInfo, username);

					if (conflictCode == 1) {
						// Conflict type 1: Duplicate flight
						JOptionPane.showMessageDialog(null, "You cannot book the "
								+ "same flight more than once", "Error", 
								JOptionPane.ERROR_MESSAGE);
					}
					else if (conflictCode == 2) {
						// Conflict type 2: Overlapping flight
						JOptionPane.showMessageDialog(null, "There's a conflict with "
								+ "another flight you have booked that day \nPlease "
								+ "choose a different date or cancel your other flight", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						// Returns 0: No conflict
						int price = Integer.parseInt(flight[4]);
						DecimalFormat df = new DecimalFormat("#.00");
						int available = Integer.parseInt(flight[6]);		    
						int seatsWanted = passengerInfo[0] + passengerInfo[1];  // Infants sit in laps
						int totalCost = 0;
						String year = bookingInfo[2].substring(0, 4);
						String month = bookingInfo[2].substring(6, 7);
						String day = bookingInfo[2].substring(8, 10);
						String date = month + "-" + day  + "-" + year;
						
						if (available == 0) {
							JOptionPane.showMessageDialog(null, 
									"This flight is full", "Error", 
									JOptionPane.ERROR_MESSAGE);
						}
						if ((available - seatsWanted) >= 0) {
							String verb;
							if (seatsWanted == 1) {
								verb = "is";
							}
							else {
								verb = "are";
							}
							String message = "There " + verb + " " + seatsWanted + 
									" seats available on " + date + " at " + flight[3] 
									+ " \nfor $" + df.format(price) + " per ticket. "
									+ "Do you want to book now?";
							int option = JOptionPane.showConfirmDialog(null, message);
							if (option == 0) {
								totalCost = seatsWanted * price;
								boolean success = db.bookNewFlight(tableName, flight, 
										bookingInfo[2], totalCost, passengerInfo[0], 
										passengerInfo[1], passengerInfo[2], username, 
										available, seatsWanted);
								if (success == true) {
									manageMainMenu();
								}
							}
							if (option == 1) {
								manageMainMenu();
							}
							else {
								// Terminate the application
								System.exit(0);
							}
						}
					}
				}
			}
			if (result == JOptionPane.CANCEL_OPTION || result == 1) {
				// User clicked Back To Menu
				// Go back to main menu
				manageMainMenu();
			}
		    if (result == JOptionPane.CLOSED_OPTION) {
		    	// Terminate the application
		    	System.exit(0);
		    }
		} while (cont == true);
		
		// Continue the application
		manageMainMenu();
	}
		
		
	/**
	 * Process reservation management request
	 */
	public void	manageReservations() throws SQLException, ParseException {
		DBConnection db = new DBConnection();
		List<String> sFlight = db.getCustBookings(username);

		// Create formatting class object
		Formatting form = new Formatting();
		        
		// Create input fields and assign values
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JComboBox<?> selectFlight = new JComboBox<Object>(sFlight.toArray());
		controls.add(selectFlight);
		
		// Display input form
		boolean cont = true;
		int result;
		do {
			JPanel panel = form.getReservationsTable(controls);
				
			// Display the dialog box
			Object[] choices = { "Back To Menu", "Delete Selected Flight" };
			Object defaultChoice = choices[0];
			result = JOptionPane.showOptionDialog(null, panel, "Manage Flight "
					+ "Reservations", JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, choices, defaultChoice);
				
			// Validate the user's selection and input
			if (result == JOptionPane.OK_OPTION) {
				// Go back to main menu
				manageMainMenu();
			}
			if (result == JOptionPane.CANCEL_OPTION || result == 1) {
				// Delete selected flight
				String flight = (String) selectFlight.getSelectedItem();				
				String id = flight.substring(flight.length() - 2);
				
				String message = "Are you sure you want to delete this flight?";
				int option = JOptionPane.showConfirmDialog(null, message);
				if (option == 0) {
					db.deleteCustBooking(id);
					manageReservations();				
				}
				if (option == 1) {
					manageReservations();
				}
				else {
					// Terminate the application
					System.exit(0);
				}

			}
		    if (result == JOptionPane.CLOSED_OPTION) {
		    	System.exit(0);  // Terminate the application
		    }
		} while (cont == true);

	// Continue the application
	manageMainMenu();
	}
	
	
	/**
	 * ======================
	 *  FOR ADMIN USERS ONLY
	 * ======================
	 */
	public void	addFlight() throws SQLException, ParseException {
		String[] fType = { "Select", "domestic_flights", "international_flights" };
		String departCity = "Atlanta, Georgia";

		// Create formatting class object
		Formatting form = new Formatting();
		        
		// Create input fields and assign values
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JComboBox<?> selectType = new JComboBox<Object>(fType);
		controls.add(selectType);
		JTextField depart = new JTextField(departCity);
		depart.setEditable(false);
		controls.add(depart);
		JTextField inputDest = new JTextField(30);
		controls.add(inputDest);
		JTextField inputTime = new JTextField(30);
		controls.add(inputTime);
		JTextField inputPrice = new JTextField(30);
		controls.add(inputPrice);
		JTextField inputTSeats = new JTextField(30);
		controls.add(inputTSeats);
		JTextField inputASeats = new JTextField(20);
		controls.add(inputASeats);
		    
		// Display input form
		boolean cont = true;
		int result;
		do {
			JPanel panel = form.getNewFlightForm(controls);
				
			// Display the dialog box
			Object[] choices = { "Add Flight", "Back To Menu" };
			Object defaultChoice = choices[0];
			result = JOptionPane.showOptionDialog(null, panel, "Add a Flight", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, choices, defaultChoice);
				
			String[] flight = new String[6];
			// Validate the user's selection and input
			if (result == JOptionPane.OK_OPTION) {
				String tableName = (String) selectType.getSelectedItem();
				flight[0] = depart.getText();
				flight[1] = inputDest.getText();
				flight[2] = inputTime.getText();
				flight[3] = inputPrice.getText();
				flight[4] = inputTSeats.getText();
				flight[5] = inputASeats.getText();

				int count = 0;
				for (int i = 0; i < flight.length; i++) {
					if (tableName.equals("Select") || flight[i].isEmpty()) {
						// All fields are required
						count++;
					}
				}
				if (count != 0) {
					JOptionPane.showMessageDialog(null,
							"Please fill in all of the data fields.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					cont = false;
					DBConnection db = new DBConnection();
					db.addNewFlight(tableName, flight);
				}
			}
			if (result == JOptionPane.CANCEL_OPTION || result == 1) {
				// Go back to main menu
				manageMainMenu();
			}
		    if (result == JOptionPane.CLOSED_OPTION) {
		    	System.exit(0);  // Terminate the application
		    }
		} while (cont == true);

	// Continue the application
	manageMainMenu();
	}
	
	
	public void	updateFlight() throws SQLException, ParseException {
		DBConnection db = new DBConnection();
		List<String> allFlights = db.getAllFlights();
		String[] field = { "Select", "time", "pricePerTicket", "totalSeats", 
				"availableSeats" };

		// Create formatting class object
		Formatting form = new Formatting();
		        
		// Create input fields and assign values
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JComboBox<?> selectFlight = new JComboBox<Object>(allFlights.toArray());
		controls.add(selectFlight);
		JComboBox<?> selectField = new JComboBox<Object>(field);
		controls.add(selectField);
		JTextField inputField = new JTextField(30);
		controls.add(inputField);

		boolean cont = true;		
		int result;
		do {
			JPanel panel = form.getUpdateFlightForm(controls);
				
			// Display the dialog box
			Object[] choices = { "Update Flight", "Back To Menu" };
			Object defaultChoice = choices[0];
			result = JOptionPane.showOptionDialog(null, panel, "Update a Flight", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, choices, defaultChoice);
				
			String[] flight = new String[4];
			// Validate the user's selection and input
			if (result == JOptionPane.OK_OPTION) {
				flight[0] = (String) selectFlight.getSelectedItem();
				flight[1] = (String) selectField.getSelectedItem();
				flight[2] = inputField.getText();
				
				String tableName = "";
				String id = flight[0].substring(flight[0].length() - 4); // Get flight id

				if (id.charAt(0) == '1') {
					tableName = "domestic_flights";
				}
				if (id.charAt(0) == '2') {
					tableName = "international_flights";
				}

				int count = 0;
				for (int i = 0; i < flight.length; i++) {
					if (flight[0].equals("Select") || (flight[2].isEmpty())) {
						// All fields are required
						count++;
					}
				}
				if (count != 0) {
					JOptionPane.showMessageDialog(null, "Please select a flight and "
							+ "value to update.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					cont = false;
					db.updateFlight(tableName, flight, id);
				}
			}
			if (result == JOptionPane.CANCEL_OPTION || result == 1) {
				// Go back to main menu
				manageMainMenu();
			}
		    if (result == JOptionPane.CLOSED_OPTION) {
		    	System.exit(0);  // Terminate the application
		    }
		} while (cont == true);
		
		// Continue the application
		manageMainMenu();
	}
	
	
	public void	deleteFlight() throws SQLException, ParseException {
		DBConnection db = new DBConnection();
		List<String> allFlights = db.getAllFlights();

		// Create formatting class object
		Formatting form = new Formatting();

		// Create input fields and assign values
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JComboBox<?> selectFlight = new JComboBox<Object>(allFlights.toArray());
		controls.add(selectFlight);

		boolean cont = true;		
		int result;
		do {
			JPanel panel = form.getDeleteFlightForm(controls);
				
			// Display the dialog box
			Object[] choices = { "Delete Flight", "Back To Menu" };
			Object defaultChoice = choices[0];
			result = JOptionPane.showOptionDialog(null, panel, "Delete a Flight", 
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, choices, defaultChoice);
				
			// Validate the user's selection and input
			if (result == JOptionPane.OK_OPTION) {
				String flight = (String) selectFlight.getSelectedItem();
				
				String tableName = "";
				String id = flight.substring(flight.length() - 4); // Get flight id

				if (id.charAt(0) == '1') {
					tableName = "domestic_flights";
				}
				if (id.charAt(0) == '2') {
					tableName = "international_flights";
				}

				int count = 0;
				if (flight.equals("Select")) {
					// Field is required
					count++;
				}
				if (count != 0) {
					JOptionPane.showMessageDialog(null, "Please select a flight "
							+ "to delete.", "Error", 
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					cont = false;
					db.deleteFlight(tableName, id);
				}
			}
			if (result == JOptionPane.CANCEL_OPTION || result == 1) {
				// Go back to main menu
				manageMainMenu();
			}
		    if (result == JOptionPane.CLOSED_OPTION) {
		    	System.exit(0);  // Terminate the application
		    }
		} while (cont == true);		

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
		app.optionScreen();
	}
}