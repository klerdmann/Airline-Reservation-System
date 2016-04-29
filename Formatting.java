package edu.gsu.cis.GroupProject;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
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

	
	public static void getReservationTable() {
		// Format the input dialog box
		// Create JPanel object
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
					            
		// Format the labels
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(1, 11, 2, 2));
		JLabel l2 = new JLabel("Username:");
		JLabel l3 = new JLabel("Flight ID:");
		JLabel l4 = new JLabel("To:");
		JLabel l5 = new JLabel("From:");
		JLabel l6 = new JLabel("Time:");
		JLabel l7 = new JLabel("Date:");
		JLabel l8 = new JLabel("Price:");
		JLabel l9 = new JLabel("Adults:");
		JLabel l10 = new JLabel("Children:");
		JLabel l11 = new JLabel("Infants:");
		JLabel l12 = new JLabel("Total Price:");
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		panel.add(l6);
		panel.add(l7);
		panel.add(l8);
		panel.add(l9);
		panel.add(l10);
		panel.add(l11);
		panel.add(l12);
		
		frame.add(panel);
		
		
		// Format the input fields
		//panel.add(controls, BorderLayout.CENTER);

		
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
		label.add(new JLabel("Price Per Ticket: ", SwingConstants.RIGHT));
		label.add(new JLabel("Total Seats: ", SwingConstants.RIGHT));
		label.add(new JLabel("Available Seats: ", SwingConstants.RIGHT));
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
		label.add(new JLabel("Type: ", SwingConstants.RIGHT));
		label.add(new JLabel("Table: ", SwingConstants.RIGHT));
		label.add(new JLabel("Dest: ", SwingConstants.RIGHT));

		panel.add(label, BorderLayout.WEST);
		
		// Format the input fields
		panel.add(controls, BorderLayout.CENTER);
		
		return panel;
	}
}
