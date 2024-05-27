package GUI;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import Users.*;
import ExceptionError.ExceptionError;

import java.util.ArrayList;




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
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
        return state;
    }


    public String view_rides(User user) {
        state = "main_menu";
        JDialog dialog = new JDialog((Frame) null, "View Ride Details", true); // Create a modal JDialog
    
        String[][] data = user.view_request();

        if(data.length == 0){
            String msg;
            if(user instanceof Customer) msg = "Currently not on a ride";
            else msg = "The Rider haven't made any ride yet.";
            JOptionPane.showMessageDialog(dialog, msg);
            System.out.println(msg);
        }

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
                state = "cancel_request";
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
        JDialog dialog = new JDialog((Frame) null, "Edit User details", true); // Create a modal JDialog
    
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
                try{
                    User u;
                    if (user instanceof Rider)
                        u = new Rider(name, contactNumber, email);
                    else if (user instanceof Customer)
                        u = new Customer(name, contactNumber, email);
                    else
                        u = null;

                    user.edit_request(u);
                    JOptionPane.showMessageDialog(dialog, "User have been edited.");
                }
                catch(ExceptionError e1){
                    System.out.println(e1.getMessage());
                    JOptionPane.showMessageDialog(dialog, e1.getMessage());
                }
                    
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }

    
    public void request_rider(Customer customer, ArrayList<Rider> riders) {
        JDialog dialog = new JDialog((Frame) null, "Request Rider", true); // Create a modal JDialog
    
        // Load the map image
        ImageIcon mapIcon = new ImageIcon("pic\\map.png");
        JLabel mapLabel = new JLabel(mapIcon); // Create a JLabel to hold the image
    
        JLabel fromLabel = new JLabel("From Location: ");
        String[] locations = {"Gaza", "West Bank", "Rafah", "Al Aqsa","Haifa"};
        JComboBox<String> comboBoxFrom = new JComboBox<>(locations); // Dropdown list for from location
    
        JLabel toLabel = new JLabel("To Location: ");
        JComboBox<String> comboBoxTo = new JComboBox<>(locations); // Dropdown list for to location
    
        JButton button = new JButton("Request Rider");
    
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Position the map label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(mapLabel, gbc);
    
        // Position the "From Location" label and dropdown list
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset to one column
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(fromLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(comboBoxFrom, gbc);
    
        // Position the "To Location" label and dropdown list
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(toLabel, gbc);
    
        gbc.gridx = 1;
        panel.add(comboBoxTo, gbc);
    
        // Position the button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.insets = new Insets(20, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(button, gbc);
    
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromLocation = (String) comboBoxFrom.getSelectedItem();
                String toLocation = (String) comboBoxTo.getSelectedItem();
                
                try {
                    JOptionPane.showMessageDialog(dialog,"Searching for a rider...");
                    customer.request_ride(riders, fromLocation, toLocation);
                    JOptionPane.showMessageDialog(dialog, "Found a ride. you can view the details at view ride section.");
                } catch (ExceptionError error) {
                    System.out.println(error.getMessage());
                    JOptionPane.showMessageDialog(dialog, error.getMessage());
                }
                
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 600); // Adjusted size to fit the map image
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }

    public void view_profile(User user) {
        JDialog dialog = new JDialog((Frame) null, "View Profile", true); // Create a modal JDialog
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
    
        // Load the appropriate image
        ImageIcon imageIcon;
        if (user instanceof Rider) {
            imageIcon = new ImageIcon("pic\\rider_avt.png"); // Provide the correct path to the rider photo
        } else {
            imageIcon = new ImageIcon("pic\\\\customer_avt.png"); // Provide the correct path to the customer photo
        }
    
        // Add the photo label
        JLabel photoLabel = new JLabel(imageIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(photoLabel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth
    
        // Add user details
        JLabel nameLabel = new JLabel("Name: " + user.getName());
        JLabel contactLabel = new JLabel("Contact: " + user.getContactNumber());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
    
        JLabel locationLabel = null;
        if (user instanceof Rider) {
            Rider rider = (Rider) user;
            locationLabel = new JLabel("Current Location: " + rider.getLocation());
        }
    
        // Position the name label
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dialog.add(nameLabel, gbc);
    
        // Position the contact label
        gbc.gridy = 2;
        dialog.add(contactLabel, gbc);
    
        // Position the email label
        gbc.gridy = 3;
        dialog.add(emailLabel, gbc);
    
        // Position the location label if the user is a Rider
        if (locationLabel != null) {
            gbc.gridy = 4;
            dialog.add(locationLabel, gbc);
        }
    
        // Position the close button
        JButton closeButton = new JButton("Back");
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(closeButton, gbc);
    
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the dialog
            }
        });
    
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true); // Show the dialog and block until it's dismissed
    }


    public User login(ArrayList<? extends User> users) {
        staticUser = null;
        JDialog dialog = new JDialog((Frame) null, "Login User", true); // Create a modal JDialog
            
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
        JDialog dialog = new JDialog((Frame) null, "Home", true); // Create a modal JDialog
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
        dialog.setSize(600, 400);
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

                if(staticCustomer != null) JOptionPane.showMessageDialog(dialog, "Customer created successfully");
                else JOptionPane.showMessageDialog(dialog, "Couldn't create customer. Please check all information are provided.");
                
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

                if(staticRider != null) JOptionPane.showMessageDialog(dialog, "Rider created successfully");
                else JOptionPane.showMessageDialog(dialog, "Couldn't create Rider. Please check all information are provided.");
    
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
        if(users.size() == 0){
            JOptionPane.showMessageDialog(null, "No user to delete");
            return state;
        }
        JDialog dialog = new JDialog((Frame) null, "Delete User", true); // Create a modal JDialog
    
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
