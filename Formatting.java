package edu.gsu.cis.GroupProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
	
	public JPanel getBookingForm(JPanel controls, JButton b, String departCity, String[] destCity, 
			List<Integer> adults, List<Integer> children, List<Integer> infants) {
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
	
	public void setBookingDetails(String[] bookingDetails) {
		for (int i = 0; i < bookingDetails.length; i++) {
			this.bookingDetails[i] = bookingDetails[i];
		}
	}
	
	public void setPassengerDetails(int[] passengerDetails) {
		for (int i = 0; i < passengerDetails.length; i++) {
			this.passengerDetails[i] = passengerDetails[i];
		}
	}
	
	public String[] getBookingDetails() {
		return bookingDetails;
	}
	
	public int[] getPassengerDetails() {
		return passengerDetails;
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
}
