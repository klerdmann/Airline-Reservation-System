package edu.gsu.cis.GroupProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Formatting {
	protected String[] bookingDetails = new String[3];
	protected int[] passengerDetails = new int[3];
	
	public Formatting() {
	}
	
	public JPanel getLoginForm(JPanel controls) {
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
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel getRegistrationForm(JPanel controls) {
		// Format the input dialog box
		// Create JPanel object
		JPanel panel = new JPanel(new BorderLayout(8, 8));
			        
		// Format the header and display the welcome message
		JPanel header = new JPanel(new GridLayout(1, 8));
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
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel getBookingForm(JPanel controls, JButton b) {
		// Format the input dialog box
		// Create JPanel object
		JPanel panel = new JPanel(new BorderLayout(15, 12));
					        
		// Format the header and display the welcome message
		JPanel header = new JPanel(new GridLayout(1, 15));
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

		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
					
		// Format the spacing to left where the date button is
		JPanel space = new JPanel(new GridLayout(0, 1, 2, 2));
		space.add(javax.swing.Box.createGlue());
		space.add(javax.swing.Box.createGlue());
		space.add(b);
		space.add(javax.swing.Box.createGlue());
		space.add(javax.swing.Box.createGlue());
		space.add(javax.swing.Box.createGlue());
		panel.add(space, BorderLayout.EAST);
	
		return panel;
	}

	
	public void setupDateSelectButton(JPanel controls, JButton b, 
			JTextField selectDate) {
		JFrame f = new JFrame();
      	f.getContentPane().add(controls);
      	
      	// Add action listener
      	b.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent ae) {
      			selectDate.setText(new DatePicker(f).setPickedDate());
      		}
        });
	}
	
	
	public JPanel getReservationsTable(JPanel controls) {
		// Format the input dialog box
		// Create JPanel object
		JPanel panel = new JPanel(new BorderLayout(6, 6));
					        
		// Format the header and display the welcome message
		JPanel header = new JPanel(new GridLayout(1, 6));
		header.add(new JLabel("Manage Your Flights", SwingConstants.CENTER));
		panel.add(header, BorderLayout.NORTH);
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);		
		
		return panel;
	}
	

	public JPanel getNewFlightForm(JPanel controls) {
		// Format the input dialog box
		// Create JPanel object
		JPanel panel = new JPanel(new BorderLayout(8, 8));
			        
		// Format the header and display the welcome message
		JPanel header = new JPanel(new GridLayout(1, 8));
		header.add(new JLabel("Add a Flight", SwingConstants.CENTER));
		panel.add(header, BorderLayout.NORTH);
			            
		// Format the labels
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Type: ", SwingConstants.RIGHT));
		label.add(new JLabel("From: ", SwingConstants.RIGHT));
		label.add(new JLabel("To: ", SwingConstants.RIGHT));
		label.add(new JLabel("Time: ", SwingConstants.RIGHT));
		label.add(new JLabel("Ticket Price: ", SwingConstants.RIGHT));
		label.add(new JLabel("Total Seats: ", SwingConstants.RIGHT));
		label.add(new JLabel("Available Seats: ", SwingConstants.RIGHT));
		panel.add(label, BorderLayout.WEST);
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	public JPanel getUpdateFlightForm(JPanel controls) {
		// Format the input dialog box
		// Create JPanel object
		JPanel panel = new JPanel(new BorderLayout(8, 8));
			        
		// Format the header and display the welcome message
		JPanel header = new JPanel(new GridLayout(2, 8));
		header.add(new JLabel("Update a Flight", SwingConstants.CENTER));
		header.add(new JLabel("(Fill in only the fields you wish to update)", 
				SwingConstants.CENTER));
		panel.add(header, BorderLayout.NORTH);
			            
		// Format the labels
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Select Flight: ", SwingConstants.RIGHT));
		label.add(new JLabel("Select Field: ", SwingConstants.RIGHT));
		label.add(new JLabel("Input Field: ", SwingConstants.RIGHT));

		panel.add(label, BorderLayout.WEST);
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	public JPanel getDeleteFlightForm(JPanel controls) {
		// Format the input dialog box
		// Create JPanel object
		JPanel panel = new JPanel(new BorderLayout(8, 8));
			        
		// Format the header and display the welcome message
		JPanel header = new JPanel(new GridLayout(1, 8));
		header.add(new JLabel("Delete a Flight", SwingConstants.CENTER));
		panel.add(header, BorderLayout.NORTH);
			            
		// Format the labels
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("Select Flight: ", SwingConstants.RIGHT));

		panel.add(label, BorderLayout.WEST);
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
		
		return panel;
	}
}
