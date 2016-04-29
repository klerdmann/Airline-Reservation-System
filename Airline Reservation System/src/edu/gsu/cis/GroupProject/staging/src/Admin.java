import java.sql.SQLException;


public class Admin extends User 
{
	public Admin(String firstName, String middleName, String lastName, String street, String city, String state, String zipCode,
			String email, String userName, String password, String ssn, String secQuestion)
	{
		super(firstName, middleName, lastName, street, city, state, zipCode,
			email, userName, password, ssn, secQuestion, "'admin'");
	}
}
