package GUI;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import Users.*;


public class Gui{
    public static String state = "exit";
    public static User staticUser;
    public static Customer staticCustomer;
    public static Rider staticRider;
    
    public String main_menu(User user) {
        state = "main_menu";
        JDialog dialog = new JDialog((Frame) null, "Main Menu", true); // Create a modal JDialog
        dialog.setLayout(new GridLayout(3, 2)); // Grid layout with 3 rows and 2 columns
    
        // Button 1
        JButton button1 = new JButton("Request Ride");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "request_ride";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button1);
        
        if(user instanceof Rider)
            button1.setEnabled(false);
    
        // Button 2
        JButton button2 = new JButton("View Ride");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "view_ride";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button2);
    
        // Button 3
        JButton button3 = new JButton("Edit Profile");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "edit_profile";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button3);
    
        // Button 4
        JButton button4 = new JButton("View Profile");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "view_profile";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button4);
    
        // Button 5
        JButton button5 = new JButton("Logout");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "homepage";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button5);
    
        // Button 6
        JButton button6 = new JButton("Exit");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "exit";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button6);
    
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return state;
    }


    public String view_rides(User user) {
        state = "main_menu";
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

        JButton cancelButton = new JButton("Cancel Ride");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "delete_request";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
    

        JButton finishRideButton = new JButton("Finish Ride");
        finishRideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "finish_ride";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });

        if(!user.ride_status_ongoing()){
            finishRideButton.setEnabled(false); 
            cancelButton.setEnabled(false); 
        }

        // Create a panel for the buttons and add it to the bottom of the main panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); // One row, two columns
        buttonPanel.add(cancelButton);
        buttonPanel.add(finishRideButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return state;
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


    public User login(ArrayList<? extends User> users) {
        staticUser = null;
        JDialog dialog = new JDialog((Frame) null, "Request Rider", true); // Create a modal JDialog
            
        JLabel nameLabel = new JLabel("Name: ");
        JTextField textFieldName = new JTextField(10); // Pre-fill with current user data
        
        JLabel emailLabel = new JLabel("Email: ");
        JTextField textFieldEmail = new JTextField(10);
        
        JButton button = new JButton("Login");
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Position the "From Location" label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldName, gbc);
    
        // Position the "To Location" label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(textFieldEmail, gbc);
        
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
                String name = textFieldName.getText();
                String email = textFieldEmail.getText();
    
                for (User user : users) {
                    if (user.getName().equals(name) && user.getEmail().equals(email)) {
                        System.out.println("Login successful!");
                        staticUser = user;
                    }
                }
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed

        return staticUser;
    }
   
    public String homepage(){
        state = "exit";
        JDialog dialog = new JDialog((Frame) null, "Main Menu", true); // Create a modal JDialog
        dialog.setLayout(new GridLayout(2, 3)); // Grid layout with 3 rows and 2 columns
    
        // Button 1
        JButton button1 = new JButton("Rider Login");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "login_rider";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button1);
    
        // Button 2
        JButton button2 = new JButton("Customer Login");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "login_customer";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button2);
    
        // Button 3
        JButton button3 = new JButton("Delete Rider");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "delete_rider";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button3);
    
        // Button 4
        JButton button4 = new JButton("Rider Create");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "create_rider";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button4);
    
        // Button 5
        JButton button5 = new JButton("Customer Create");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "create_customer";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button5);
    
        // Button 6
        JButton button6 = new JButton("Delete Customer");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "delete_customer";
                dialog.dispose(); // Close the dialog when the button is pressed
            }
        });
        dialog.add(button6);
    
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return state;
    }


    public Customer create_customer() {
        staticCustomer = null;
        JDialog dialog = new JDialog((Frame) null, "Create Customer", true); // Create a modal JDialog
    
        JLabel nameLabel = new JLabel("Name: ");
        JTextField textFieldName = new JTextField(10); // Text field for name
    
        JLabel contactLabel = new JLabel("Contact Number: ");
        JTextField textFieldContactNumber = new JTextField(10); // Text field for contact number
    
        JLabel emailLabel = new JLabel("Email: ");
        JTextField textFieldEmail = new JTextField(10); // Text field for email
    
        JButton createButton = new JButton("Create"); // Button to create user
    
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

    
        // Position the create button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, gbc);
    
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String contactNumber = textFieldContactNumber.getText();
                String email = textFieldEmail.getText();

                if(!name.isEmpty() && !contactNumber.isEmpty() && !email.isEmpty())
                    staticCustomer = new Customer(name,contactNumber,email);
                
                // Assuming User is an abstract class or interface
                // User user = new User(name, contactNumber, email, location);
                // Assuming you have a method to add the user
                // add_user(user);
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return staticCustomer;
    }
    

    public Rider create_rider() {
        staticRider = null;
        JDialog dialog = new JDialog((Frame) null, "Create Rider", true); // Create a modal JDialog
    
        JLabel nameLabel = new JLabel("Name: ");
        JTextField textFieldName = new JTextField(10); // Text field for name
    
        JLabel contactLabel = new JLabel("Contact Number: ");
        JTextField textFieldContactNumber = new JTextField(10); // Text field for contact number
    
        JLabel emailLabel = new JLabel("Email: ");
        JTextField textFieldEmail = new JTextField(10); // Text field for email
        
        JLabel locationLabel = new JLabel("Location: ");
        String[] locations = {"Gaza", "West Bank", "Rafa", "Al Aqsa","Haifa"};
        JComboBox<String> locationComboBox = new JComboBox<>(locations); // Dropdown list for location selection

    
        JButton createButton = new JButton("Create"); // Button to create user
    
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
        
        // Position the location label and text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(locationLabel, gbc);

        gbc.gridx = 1;
        panel.add(locationComboBox, gbc);
    
        // Position the create button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, gbc);
    
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText();
                String contactNumber = textFieldContactNumber.getText();
                String email = textFieldEmail.getText();
                String location = (String) locationComboBox.getSelectedItem();

                if(!name.isEmpty() && !contactNumber.isEmpty() && !email.isEmpty() && !location.isEmpty())
                    staticRider = new Rider(name,contactNumber,email,location);
                
                // Assuming User is an abstract class or interface
                // User user = new User(name, contactNumber, email, location);
                // Assuming you have a method to add the user
                // add_user(user);
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return staticRider;
    }


    public String delete_user(ArrayList<? extends User> users) {
        state = "homepage";
        JDialog dialog = new JDialog((Frame) null, "View Ride Details", true); // Create a modal JDialog
    
        String[][] data = new String[users.size()][5];

        String userType;
        if (users.get(0) instanceof Rider) {
            userType = "Rider";
        } else if (users.get(0) instanceof Customer) {
            userType = "Customer";
        } else {
            userType = "not defined";
        }
        int i = 0;
        for(User user: users){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = userType;
            data[i][2] = user.getName();
            data[i][3] = user.getContactNumber();
            data[i][4] = user.getEmail();
            i++;

        }
    
        String[] columnNames = {"no", "User Type", "Name", "ContactNumber", "Email"};
        JTable table = new JTable(data, columnNames);
    
        JScrollPane scrollPane = new JScrollPane(table);
    

        // Create a dropdown with user indices
        Integer[] indices = new Integer[users.size()];
        for (int j = 0; j < users.size(); j++) {
            indices[j] = j + 1;
        }
        JComboBox<Integer> indexComboBox = new JComboBox<>(indices);

        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = (Integer) indexComboBox.getSelectedItem() - 1; // Get selected index
                if (selectedIndex >= 0 && selectedIndex < users.size()) {
                    users.remove(selectedIndex); // Remove the user at the selected index
                    JOptionPane.showMessageDialog(dialog, "User deleted successfully");
                    dialog.dispose(); // Close the dialog
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select User Index to Delete:"));
        controlPanel.add(indexComboBox);
        controlPanel.add(deleteButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);


        
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return state;
    }

}
