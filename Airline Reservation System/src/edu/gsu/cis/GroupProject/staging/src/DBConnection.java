import java.sql.*;

public class DBConnection 
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/airlinedb";
	
	private static final String USER = "root";
	private static final String PASS = "ruweldatabase";
	   
	public static Connection connect() 
	{
		Connection conn = null;
		
		try 
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		} catch(SQLException se) {
		      se.printStackTrace();
		} catch(Exception e) {
		      e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void createTables()
	{
		try
		{
			createUsersTable();
			createDomesticFlightsTable();
			createInternationalFlightsTable();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}
		
	public static void updateTable(String sql) throws SQLException
	{
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate(sql);
		
		stmt.close();
		conn.close();		
	}
	
	public static ResultSet queryTable(String sql) throws SQLException
	{
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		return rs;
	}
	
	public static void printUsersTable() throws SQLException 
	{
		String sql = "SELECT id, first, middle, last, street, city, "
	    		+ "state, zip, username, password, email, SSN, "
	    		+ "sQuestion, userType FROM users";
		
		ResultSet rs = queryTable(sql);
		
		System.out.println(rs.toString());
		while (rs.next()) 
		{
			System.out.println(rs.toString());
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

	private static void createUsersTable() throws SQLException 
	{
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
		
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate(sql);
		
		// Set user id
		sql = "ALTER TABLE users AUTO_INCREMENT = 1001";
		stmt.executeUpdate(sql);
		
		stmt.close();
		conn.close();
	}
	
	private static void createDomesticFlightsTable() throws SQLException 
	{
		String sql = "CREATE TABLE IF NOT EXISTS domestic_flights "
				+ "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ "start VARCHAR(45), dest VARCHAR(45), "
				+ "time VARCHAR(45), pricePerTicket DOUBLE, "
				+ "totalSeats INTEGER NULL, availableSeats INTEGER NULL, "
				+ "PRIMARY KEY ( id ))";
		
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate(sql);
		
		// Set flight id
		sql = "ALTER TABLE domestic_flights AUTO_INCREMENT = 1001";
		stmt.executeUpdate(sql);
		
		stmt.close();
		conn.close();
	}
	
	private static void createInternationalFlightsTable() throws SQLException 
	{
		String sql = "CREATE TABLE IF NOT EXISTS International_flights "
				+ "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ "start VARCHAR(45), dest VARCHAR(45), "
				+ "time VARCHAR(45), pricePerTicket DOUBLE, "
				+ "totalSeats INTEGER NULL, availableSeats INTEGER NULL, "
				+ "PRIMARY KEY ( id ))";
		
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate(sql);
		
		// Set flight id
		sql = "ALTER TABLE International_flights AUTO_INCREMENT = 1001";
		stmt.executeUpdate(sql);
		
		stmt.close();
		conn.close();
	}
}
