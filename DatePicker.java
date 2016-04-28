package edu.gsu.cis.GroupProject;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class DatePicker extends JFrame {
	private static final long serialVersionUID = 1L;
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	
	JLabel label = new JLabel("", JLabel.CENTER);
	String day = "";
	JDialog dialog;
	JButton[] button = new JButton[49];

	public DatePicker(JFrame parent) {
		dialog = new JDialog();
        dialog.setModal(true);
        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(420, 120));

        // Format calendar
        for (int x = 0; x < button.length; x++) {		
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);

            if (x > 6) {
            	// Add action listener
                button[x].addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent ae) {
                		day = button[selection].getActionCommand();
                		dialog.dispose();
                    }
                });
            }
            if (x < 7) {
            	button[x].setText(header[x]);
            	button[x].setForeground(Color.red);
            }
            p1.add(button[x]);
        }
        
        // Add next and previous month buttons
        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        JButton next = new JButton("Next >>");
        
        // Add action listener
        previous.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		next.setEnabled(true);
        		month--;
        		if (month == 0) {
        			previous.setEnabled(false);
            	}
        		displayDate();
        	}
        });
        p2.add(previous);
        p2.add(label);
        
        // Add action listener
        next.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		previous.setEnabled(true);
        		month++;
        		if (month == 11) {
        			next.setEnabled(false);
            	}
        		
        		// Invoke method
        		displayDate();
        	}        	
        });
        p2.add(next);
        dialog.add(p1, BorderLayout.CENTER);
        dialog.add(p2, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        
        // Invoke method
        displayDate();
        dialog.setVisible(true);
	}

	public void displayDate() {
		Calendar cal = null;
		for (int x = 7; x < button.length; x++) {
			button[x].setText("");
            cal = Calendar.getInstance();
		}
		
		cal.set(year, month, 1);
      	String monthString = new DateFormatSymbols().getMonths()[month];
      	String displayDate = "" + monthString + " " + dayOfMonth + ", " + year;
      	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
      	int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
      	int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

      	for (int x = 6 + dayOfWeek, day = dayOfMonth; day <= 
      			daysInMonth; x++, day++) {
      		button[x].setText("" + day);
      	}
      	label.setText(displayDate);
      	dialog.setTitle("Select a Date");
	}

	public String setPickedDate() {
      	if (day.equals("")) {
      		return day;
      	}
      	
      	// Format selected date
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
	}
}
