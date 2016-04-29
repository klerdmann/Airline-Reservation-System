import java.sql.SQLException;

public abstract class User 
{
	protected String firstName;
	protected String lastName;
	protected String street;
	protected String city;
	protected String state;
	protected String zipCode;
	protected String email;
	protected String userName;
	protected String ssn;
	protected String userType;

	public User(String firstName, String middleName, String lastName, String street, String city, String state, String zipCode,
			String email, String userName, String password, String ssn, String secQuestion, String userType)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.email = email;
		this.userName = userName;
		this.ssn = ssn;
		this.userType = userType;
		
		try
		{
			String sql = "INSERT INTO users (`first`, `middle`, `last`, "
					+ "`street`, `city`, `state`, `zip`, `username`, `password`, "
					+ "`email`, `SSN`, `sQuestion`, `userType`) " + "VALUES (" 
					+ firstName + ", " + middleName + ", " + lastName 
					+ ", " + street + ", " + city + ", " 
					+ state + ", " + zipCode + ", " + userName 
					+ ", " + password + ", " + email + ", " 
					+ ssn + ", " + secQuestion + ", " + userType +")";
			
			DBConnection.updateTable(sql);
		} catch (SQLException se) {
			System.out.println(se);
		}
	}
	
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getStreet() 
	{
		return street;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getZipCode() 
	{
		return zipCode;
	}

	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getSsn() 
	{
		return ssn;
	}

	public void setSsn(String ssn) 
	{
		this.ssn = ssn;
	}

	public String getUserType() 
	{
		return userType;
	}

	public void setUserType(String userType) 
	{
		this.userType = userType;
	}
}
