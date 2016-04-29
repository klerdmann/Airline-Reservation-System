package edu.gsu.cis.GroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnection {
	protected static String userType;
	protected static Statement stmt = null;
	protected static Connection conn = null;
	
	// JDBC driver name and database URL
	protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	protected static final String DB_URL = "jdbc:mysql://localhost:3306/airline_system?useSSL=false";

	// Database credentials
	protected static final String USER = "root";
	protected static final String PASS = "password123";
		
	public DBConnection() {
	    connect();
	}	
	
	protected static Connection connect() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER).newInstance();

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			
		} catch (Exception e) {
	        System.out.println(e);
	    }
	    return conn;
	}
	
	
	// Only run once to set up table
	public static void main(String[] args) {
		try {
			connect();
			
			// Create database
			//createDB();

			// Create tables:
			//createUsersTable();
			//createFlightsTable("domestic_flights");
			createFlightsTable("international_flights");
			//createCustomerFlightsTable();
      
			// Insert initial table data:
			//insertUsersTable();			
			//insertDomesticFlightsTable();
			//insertInternationalFlightsTable();
      
			// Select table data:
			selectUsersTable();
			selectFlightsTable("domestic_flights");
			selectFlightsTable("international_flights");
			selectCustomerFlightsTable();
		} catch (Exception e) {
	        System.out.println(e);
	    }
	}
	
	
	/**
	 * =======================================
	 *  Methods used to create the database 
	 *  and tables, and insert initial values
	 * =======================================
	 */
	

	protected static void createDB() throws SQLException {
		String sql = "CREATE DATABASE IF NOT EXISTS airline_system";
		stmt.executeUpdate(sql);
	}
	
	
	protected static void createUsersTable() 
			throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS users " +
				" (id INTEGER NOT NULL AUTO_INCREMENT, " +
				" first VARCHAR(255), " + 
				" middle VARCHAR(255), " + 
				" last VARCHAR(255), " + 
				" street VARCHAR(255), " + 
				" city VARCHAR(255), " + 
				" state VARCHAR(255), " + 
				" zip CHAR(5), " + 
				" username VARCHAR(255), " + 
				" password VARCHAR(255), " + 
				" email VARCHAR(255), " + 
				" SSN VARCHAR(11), " + 
				" sQuestion VARCHAR(255), " + 
				" userType VARCHAR(15), " + 
				" PRIMARY KEY ( id ))";
		stmt.executeUpdate(sql);
		
		// Set user id
		sql = "ALTER TABLE users AUTO_INCREMENT = 1001";
		stmt.executeUpdate(sql);
	}
	
	
	protected static void insertUsersTable() 
			throws SQLException {
		String sql = "INSERT INTO users (`first`, `middle`, "
				+ "`last`, `street`, `city`, `state`, `zip`, `username`, "
				+ "`password`, `email`, `SSN`, `sQuestion`, `userType`) " 
				+ "VALUES ('Kristin', '', 'Erdmann', '100 Main Street', "
				+ "'Atlanta', 'GA', '30303', 'kerdmann', 'pwd1', "
				+ "'kerdmann@gmail.com', '123456789', 'Shadow', 'admin'), "
				+ "('Ruwal', '', 'Sarmad', '1608 W Ashland Ave', 'Atlanta', "
				+ "'GA', '30044', 'rsarmad', 'pwd2', 'rsarmad@yahoo.com', '173456734', "
				+ "'Chance', 'admin'), ('Lisa', 'L', 'Smith', '860 120th Ave', "
				+ "'Kirkland', 'WA', '98033', 'lsmith', 'pwd3', 'lsmith@comcast.net', "
	      		+ "'123456778', 'Sassy', 'customer') " + "ON DUPLICATE KEY UPDATE "
	      		+ "`username` = `username`";
	    stmt.executeUpdate(sql);
	}
	
	
	protected static void selectUsersTable() 
			throws SQLException {
		System.out.println("Selecting users table...");	    
	    String sql = "SELECT id, first, middle, last, street, city, "
	    		+ "state, zip, username, password, email, SSN, "
	    		+ "sQuestion, userType FROM users";
	    ResultSet rs = stmt.executeQuery(sql);

	    printUsersTable(rs);
	    rs.close();
	}
	
	
	protected static void printUsersTable(ResultSet rs) 
			throws SQLException {
		while (rs.next()) {
			// Retrieve by column name
			int id  = rs.getInt("id");
	        String first = rs.getString("first");
	        String middle = rs.getString("middle");
	        String last = rs.getString("last");
	        String street = rs.getString("street");
	        String city = rs.getString("city");
	        String state = rs.getString("state");
	        String zip = rs.getString("zip");
	        String username = rs.getString("username");
	        String password = rs.getString("password");
	        String SSN = rs.getString("SSN");
	        String sQuestion = rs.getString("sQuestion");
	        String userType = rs.getString("userType");
	        
	        // Display values
	        System.out.println("ID: " + id + ", First: " + first + ", "
	        		+ "Middle: " + middle + " Last: " + last + " Address: " 
	        		+ street + " " + city + ", " + state + " " + zip 
	        		+ " Username: " + username + " Password: " + password 
	        		+ " SSN: " + SSN + " Security Question: " + sQuestion 
	        		+ " User Type: " + userType);
		}
	}
	
	
	protected static void createFlightsTable(String tableName) 
			throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName 
				+ " (id INTEGER NOT NULL AUTO_INCREMENT, "
				+ "start VARCHAR(45), dest VARCHAR(45), "
				+ "time VARCHAR(45), pricePerTicket DOUBLE, "
				+ "totalSeats INTEGER NULL, availableSeats INTEGER NULL, "
				+ "PRIMARY KEY ( id ))";
		stmt.executeUpdate(sql);
		
		// Set flight id
		sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1001";
		stmt.executeUpdate(sql);
	}
	
	
	protected static void insertDomesticFlightsTable() 
			throws SQLException {
		String sql = "INSERT INTO domestic_flights (`start`, `dest`, `time`, `pricePerTicket`, "
				+ "`totalSeats`, `availableSeats`) " 
				+ "VALUES ('Atlanta, Georgia', 'Anchorage, Alaska', '4:50a', 375.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Boston, Massachusetts', '9:15a', 205.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Chicago, Illinois', '8:30a', 210.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Cleveland, Ohio', '8:45p', 215.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Los Angeles, California', '10:00a', 315.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Dallas, Texas', '6:30a', 230.00, 100, 100), " 
	      		+ "('Atlanta, Georgia', 'Detroit, Michigan', '4:30p', 290.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Honolulu, Hawaii', '9:15a', 400.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Kansas City, Missouri', '12:25p', 265.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Las Vegas, Nevada', '3:10p', 340.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Miami, Florida', '1:30p', 195.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Minneapolis, Minnesota', '7:30a', 285.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Nashville, Tennessee', '5:35a', 180.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'New York City, New York', '5:35a', 280.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Phoenix, Arizona', '1:50p', 305.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Salt Lake City, Utah', '2:30p', 310.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Seattle, Washington', '11:25a', 325.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Washington D.C.', '1:45p', 260.00, 100, 100) " 
	      		+ "ON DUPLICATE KEY UPDATE `id` = `id`";
	    stmt.executeUpdate(sql);
	}

	
	protected static void insertInternationalFlightsTable() 
			throws SQLException {
		String sql = "INSERT INTO international_flights (`start`, `dest`, `time`, `pricePerTicket`, "
				+ "`totalSeats`, `availableSeats`) " 
				+ "VALUES ('Atlanta, Georgia', 'London, England', '7:30a', 375.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Dubai, UAE', '9:15a', 405.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Tehran, Iran', '8:30a', 410.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Lahore, Pakistan', '6:45p', 415.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Sydney, Australia', '10:00a', 420.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Bombay, India', '3:45a', 470.00, 100, 100), " 
	      		+ "('Atlanta, Georgia', 'Tokyo, Japan', '4:30p', 495.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Shanghai, China', '9:15a', 500.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Berlin, Germany', '12:25p', 365.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Paris, France', '5:35a', 340.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Cancun, Mexico', '1:30p', 215.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Zurich, Switzerland', '5:35a', 385.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Stockholm, Sweden', '3:10p', 400.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Oslo, Norway', '1:50p', 385.00, 100, 100), "
				+ "('Atlanta, Georgia', 'Toronto, Canada', '5:30p', 310.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Dublin, Ireland', '11:45a', 425.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Rio de Janeiro, Brazil', '7:30a', 385.00, 100, 100), "
	      		+ "('Atlanta, Georgia', 'Madrid, Spain', '4:05p', 445.00, 100, 100) " 
	      		+ "ON DUPLICATE KEY UPDATE `id` = `id`";
	    stmt.executeUpdate(sql);
	}
	
	
	protected static void selectFlightsTable(String tableName) 
			throws SQLException {
		System.out.println("\nSelecting " + tableName + " table...");	    
	    String sql = "SELECT id, start, dest, time, pricePerTicket, totalSeats, "
	    		+ "availableSeats FROM " + tableName;
	    ResultSet rs = stmt.executeQuery(sql);

	    printFlightsTable(rs);
	    rs.close();
	}
	
	
	protected static void createCustomerFlightsTable() 
			throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS customer_flights " +
				" (id INTEGER NOT NULL AUTO_INCREMENT, " +
				" username VARCHAR(255), " + 
				" fID VARCHAR(255), " + 
				" fStart VARCHAR(255), " + 
				" fDest VARCHAR(255), " + 
				" fTime VARCHAR(255), " + 
				" fDate DATE NULL, " + 
				" price DOUBLE NULL, " + 
				" adults INTEGER NULL, " + 
				" children INTEGER NULL, " + 
				" infants INTEGER NULL, " + 
				" pTotal DOUBLE NULL, "
				+ "PRIMARY KEY ( id ))";
		stmt.executeUpdate(sql);
		
		// Set user id
		sql = "ALTER TABLE users AUTO_INCREMENT = 1001";
		stmt.executeUpdate(sql);
	}

	
	protected static void selectCustomerFlightsTable() 
			throws SQLException {
		System.out.println("\nSelecting customer_flights table...");	    
	    String sql = "SELECT id, username, fID, fStart, fDest, fTime, "
	    		+ "fDate, price, adults, children, infants, pTotal "
			+ "FROM customer_flights";
	    ResultSet rs = stmt.executeQuery(sql);

	    printCustomerFlightsTable(rs);
	    rs.close();
	}
	
	
	protected static void printCustomerFlightsTable(ResultSet rs) 
			throws SQLException {
		while (rs.next()) {
			// Retrieve by column name
			int id  = rs.getInt("id");
			String username = rs.getString("username");
			int fID  = rs.getInt("fID");
	        String start = rs.getString("fStart");
	        String dest = rs.getString("fDest");
	        String time = rs.getString("fTime");
	        String date = rs.getString("fDate");
	        double pricePerTicket = rs.getDouble("price");
	        int adults = rs.getInt("adults");
	        int children = rs.getInt("children");
	        int infants = rs.getInt("infants");
	        double totalPrice = rs.getDouble("pTotal");
	        
	        // Display values
	        System.out.println("ID: " + id + " Username: " + username 
	        		+ " Flight ID: " + fID + " To: " + start + " From: " 
	        		+ dest + " Time: " + time + " Date: " + date 
	        		+ " Price Per Ticket: " + pricePerTicket + " Adults: " 
	        		+ adults + " Children: " + children + " Infants: " 
	        		+ infants + " Total Price: " + totalPrice);
		}
	}

	
	protected static void printFlightsTable(ResultSet rs) 
			throws SQLException {
		while (rs.next()) {
			// Retrieve by column name
			int id  = rs.getInt("id");
	        String start = rs.getString("start");
	        String dest = rs.getString("dest");
	        String time = rs.getString("time");
	        double pricePerTicket = rs.getDouble("pricePerTicket");
	        int totalSeats = rs.getInt("totalSeats");
	        int availableSeats = rs.getInt("availableSeats");
	        
	        // Display values
	        System.out.println("ID: " + id + ", To: " + start + ", "
	        		+ "From: " + dest + " Time: " + time + " Price Per Ticket: " 
	        		+ pricePerTicket + " Total Seats: " + totalSeats 
	        		+ " Available Seats: " + availableSeats);
		}
	}
	
	
	/**
	 * =================================
	 *  Methods invoked by the program 
	 * =================================
	 */
	
	protected String getUserType() {
		return userType;
	}
	
	
	protected boolean isValidLogin(String username, String password) {
		try {
		    if (username != null && password != null) {
		    	String sql = "SELECT id, username, password, userType FROM "
		    			+ "users WHERE username='" + username + "' AND "
		    			+ "password='" + password + "'";
		    	ResultSet rs = stmt.executeQuery(sql);
		    	if (rs.next()) {
		    		String dbUsername = rs.getString("username");
		    		String dbPassword = rs.getString("password");
		    		userType = rs.getString("userType");

		    		if (dbUsername.equals(username) && dbPassword.equals(password)) {
		    			return true;
		    		}
		    	}
		    }
		} catch (SQLException err) {
			JOptionPane.showMessageDialog(null, err.getMessage());
		}
		return false;					
	}
	
	
	/**
	 * The user registration can only create new customer accounts
	 * New admin accounts can only be created by other admins
	 */
	protected boolean registerNewUser(String[] userInput) {
		try {
			String sql = "INSERT INTO users (`first`, `middle`, `last`, "
					+ "`street`, `city`, `state`, `zip`, `username`, `password`, "
					+ "`email`, `SSN`, `sQuestion`, `userType`) " + "VALUES ('" 
					+ userInput[0] + "', '" + userInput[1] + "', '" + userInput[2] 
					+ "', '" + userInput[3] + "', '" + userInput[4] + "', '" 
					+ userInput[5] + "', '" + userInput[6] + "', '" + userInput[7] 
					+ "', '" + userInput[8] + "', '" + userInput[9] + "', '" 
					+ userInput[10] + "', '" + userInput[11] + "', 'customer')";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Account successfully created");
			return true;
		} catch (SQLException err) {
			JOptionPane.showMessageDialog(null, err.getMessage());
		}
		return false;
	}

	
	protected String getStartColumn(String tableName, String columnName) {
		String departCity = null;
		try {
			String sql = "SELECT " + columnName + " FROM " + tableName;
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if (rs.next()) {
		        departCity = rs.getString("start");
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return departCity;
	}
	
	
	protected String[] getDestColumn(String tableName, String columnName) {
		String[] destCity = new String[19];
		try {
			String sql = "SELECT " + columnName + " FROM " + tableName;
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    destCity[0] = "Select";
		    int counter = 1;
		    while (rs.next()) {
		        destCity[counter] = rs.getString("dest");
		        counter++;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return destCity;
	}
	
	
	protected String[] getFlightInfo(String tableName, String[] flightInput) {
		String[] flight = new String[7];
		try {
			String sql = "SELECT id, start, dest, time, pricePerTicket, "
					+ "totalSeats, availableSeats FROM " + tableName 
					+ " WHERE dest='" + flightInput[1] + "'";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while (rs.next()) {
		        flight[0] = rs.getString("id");
		        flight[1] = rs.getString("start");
		        flight[2] = rs.getString("dest");
		        flight[3] = rs.getString("time");
		        flight[4] = rs.getString("pricePerTicket");
		        flight[5] = rs.getString("totalSeats");
		        flight[6] = rs.getString("availableSeats");		        
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return flight;
	}
	
	
	protected int checkForConflicts(String[] flight, String[] bookingInfo, 
			String username) {
		try {
			// Check for duplicate flight
			String sql = "SELECT * FROM customer_flights WHERE username='" 
					+ username + "' AND fID='" + flight[0] + "' AND fDest='" 
					+ bookingInfo[1] + "' AND fDate='" + bookingInfo[2] + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String user = rs.getString("username");
				String id = rs.getString("fID");
				String dest = rs.getString("fDest");
				String date = rs.getString("fDate");

				if (user.equals(username) && id.equals(flight[0]) 
						&& dest.equals(bookingInfo[1]) 
						&& date.equals(bookingInfo[2])) {
					return 1; 
				}
			}
			
			// Check for scheduling conflict
			sql = "SELECT * FROM customer_flights WHERE fDate='" + bookingInfo[2] + "'";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String user = rs.getString("username");
				String date = rs.getString("fDate");

				if (user.equals(username) && date.equals(bookingInfo[2])) {
					return 2;
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return 0;
	}
	
	
	protected boolean bookNewFlight(String tableName, String[] flight, String date, 
			int totalCost, int nAdults, int nChildren, int nInfants, String username, 
			int available, int seatsWanted) {
		try {
			String sql = "INSERT INTO customer_flights (`username`, `fID`, `fStart`, "
					+ "`fDest`, `fTime`, `fDate`, `price`, `adults`, `children`, "
					+ "`infants`, `pTotal`) " + "VALUES ('" + username + "', '" + flight[0] 
					+ "', '" + flight[1]  + "', '" + flight[2] + "', '" + flight[3] + "', '" 
					+ date + "', '" + flight[4] + "', '" + nAdults + "', '" + nChildren 
					+ "', '" + nInfants + "', '" + totalCost + "')";			stmt.executeUpdate(sql);
			
			available -= seatsWanted;
			sql = "UPDATE " + tableName + " SET availableSeats = '" + available 
					+ "' WHERE id = '" + flight[0] + "'";
			stmt.executeUpdate(sql);
			
			JOptionPane.showMessageDialog(null, "Flight successfully booked");
			return true;		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}
	
	
	protected boolean addNewFlight(String tableName, String[] flight) {
		try {
			String sql = "INSERT INTO " + tableName + " (`start`, `dest`, `time`, "
					+ "`pricePerTicket`, `totalSeats`, `availableSeats`) " + 
					"VALUES ('" + flight[0] + "', '" + flight[1] + "', '" + 
					flight[2] + "', '" + flight[3] + "', '" + flight[4] + 
					"', '" + flight[5] + "')";
			
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Flight successfully added");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}
	
	
	protected boolean updateFlight(String tableName, String[] flight) {
		try {
			String sql = "INSERT INTO " + tableName + " (`start`, `dest`, `time`, "
					+ "`pricePerTicket`, `totalSeats`, `availableSeats`) " + 
					"VALUES ('" + flight[0] + "', '" + flight[1] + "', '" + 
					flight[2] + "', '" + flight[3] + "', '" + flight[4] + 
					"', '" + flight[5] + "')";
			
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Flight successfully updated");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}
	
	
	protected boolean deleteFlight(String tableName, String[] flight) {
		try {
			String sql = "INSERT INTO " + tableName + " (`start`, `dest`, `time`, "
					+ "`pricePerTicket`, `totalSeats`, `availableSeats`) " + 
					"VALUES ('" + flight[0] + "', '" + flight[1] + "', '" + 
					flight[2] + "', '" + flight[3] + "', '" + flight[4] + 
					"', '" + flight[5] + "')";
			
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Flight successfully deleted");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return false;
	}
	
	public void updateTableData(String tableName, String[] flight) {
		try {
			String sql = "UPDATE registration SET "
					+ "age = 30 WHERE id in (100, 101)";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}//incomplete
	}
	
	public void deleteTableData(String tableName, String[] flight) {
		try {
			String sql = "DELETE FROM " + tableName + " WHERE id = '" 
					+ flight[0] + "'";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Flight successfully deleted");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
