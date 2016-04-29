import java.sql.SQLException;

public class AirlineReservation implements Reservation 
{	
	private String start;
	private String dest;
	private String time;
	private double pricePerTicket;
	private int totalSeats;
	private int availableSeats;
	
	public AirlineReservation(String start, String dest, String time, double pricePerTicket,
			int totalSeats, int availableSeats)
	{
		this.start = start;
		this.dest = dest;
		this.time = time;
		this.pricePerTicket = pricePerTicket;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		
		String sql = "INSERT INTO domestic_flights (`start`, `dest`, `time`, `pricePerTicket`, "
				+ "`totalSeats`, `availableSeats`) " 
				+ "VALUES ('" + start + "', '" + dest + "', '" + time + "', '" + pricePerTicket + "', '" + totalSeats 
				+ "', '" + availableSeats +
				"')";
		try
		{
			DBConnection.updateTable(sql);
		} catch (SQLException se) {
			System.out.println(se);
		}
	}
	
	@Override
	public void update() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() 
	{
		// TODO Auto-generated method stub

	}

}
