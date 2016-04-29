import java.sql.SQLException;

public class Customer extends User
{
	public Customer(String firstName, String middleName, String lastName, String street, String city, String state, String zipCode,
			String email, String userName, String password, String ssn, String secQuestion)
	{
		super(firstName, middleName, lastName, street, city, state, zipCode,
			email, userName, password, ssn, secQuestion, "'customer'");
	}
}
