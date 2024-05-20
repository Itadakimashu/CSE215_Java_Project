package GUI;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import Users.*;


public class Gui extends JFrame implements ActionListener{

    // default constructor
    public Gui() {

    }

    public void view_rides(User user) {
        JDialog dialog = new JDialog((Frame) null, "View Ride Details", true); // Create a modal JDialog
    
        String[][] data = user.view_request();
        String auth;
        if (user instanceof Rider) {
            auth = "Customer";
        } else if (user instanceof Customer) {
            auth = "Rider";
        } else {
            auth = "not defined";
        }
    
        String[] columnNames = {"No.", auth, "From", "To", "Fare", "Progress"};
        JTable table = new JTable(data, columnNames);
    
        JScrollPane scrollPane = new JScrollPane(table);
    
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }


    public void edit_user(User user) {
        JDialog dialog = new JDialog((Frame) null, "Edit Ride details", true); // Create a modal JDialog
    
        JLabel nameLabel = new JLabel("Edit Name: ");
        JTextField textFieldName = new JTextField(10); // Pre-fill with current user data
    
        JLabel contactLabel = new JLabel("Edit ContactNumber: ");
        JTextField textFieldContactNumber = new JTextField(10); // Pre-fill with current user data
    
        JLabel emailLabel = new JLabel("Edit Email: ");
        JTextField textFieldEmail = new JTextField(10); // Pre-fill with current user data
    
        JButton button = new JButton("update");
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Position the name label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldName, gbc);
    
        // Position the contact number label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(contactLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldContactNumber, gbc);
    
        // Position the email label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldEmail, gbc);
    
        // Position the button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(button, gbc);
    
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String contactNumber = textFieldContactNumber.getText();
                String email = textFieldEmail.getText();
                User u;
                if (user instanceof Rider)
                    u = new Rider(name, contactNumber, email);
                else if (user instanceof Customer)
                    u = new Customer(name, contactNumber, email);
                else
                    u = null;
                if(contactNumber.isEmpty()) System.out.println(1);
                user.edit_request(u);
                
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }
    
    public void request_rider(Customer customer,ArrayList<Rider> riders) {
        JDialog dialog = new JDialog((Frame) null, "Request Rider", true); // Create a modal JDialog
            
        JLabel fromLabel = new JLabel("From Location: ");
        JTextField textFieldFrom = new JTextField(10); // Pre-fill with current user data
        
        JLabel toLabel = new JLabel("To Location: ");
        JTextField textFieldTo = new JTextField(10);
        
        JButton button = new JButton("Request Rider");
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Position the "From Location" label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(fromLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldFrom, gbc);
    
        // Position the "To Location" label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(toLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldTo, gbc);
        
        // Position the button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(button, gbc);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromLocation = textFieldFrom.getText();
                String toLocation = textFieldTo.getText();
    
                // Perform action to request a rider using the provided locations
                // You might want to call a method like customer.requestRide(fromLocation, toLocation)
                customer.request_ride(riders, fromLocation, toLocation);
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }
    
    public void view_profile(User user) {
        JDialog dialog = new JDialog((Frame) null, "View Profile", true); // Create a modal JDialog
    
        JLabel nameLabel = new JLabel("Name: " + user.getName());
        JLabel contactLabel = new JLabel("Contact: " + user.getContactNumber());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
    
        JButton closeButton = new JButton("Back");
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Position the name label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);
    
        // Position the contact label
        gbc.gridy = 1;
        panel.add(contactLabel, gbc);

        // Position the contact number label
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);
    
        // Position the close button
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(closeButton, gbc);
    
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    
}
