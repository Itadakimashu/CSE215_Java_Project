package GUI;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame implements ActionListener{
    static JFrame f;
    // create a textfield
    static JTextField l;
    // store operator and operands
    String s0, s1, s2;
    // declare the button b0 as a class variable
    static JButton b0;

    // default constructor
    public Gui() {
        s0 = s1 = s2 = "";
    }

    @SuppressWarnings("deprecation")
    public void view_show(String[][] data,String user) {
        f = new JFrame("view Ride details");
        // create a table with 4 columns and 1 row
        String[] columnNames = {"No.", user, "From", "To", "Fare","Progress"};
        JTable table = new JTable(data, columnNames);

        // create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // create a panel and add the scroll pane to it
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        // add the panel to the frame
        f.add(panel);

        // set the size of the frame
        f.setSize(600, 400);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // show the frame
        f.show();
    }

    @SuppressWarnings("deprecation")
    public void request_ride() {
         f = new JFrame("Show");
        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Gui c = new Gui();

        // create a textfield
        l = new JTextField(16);
        // set the textfield to non editable
        l.setEditable(false);

        // create the button b0
        b0 = new JButton("0");

        // create a panel
        JPanel p = new JPanel();

        b0.addActionListener(c);

        p.add(l);
        p.add(b0);

        // set Background of panel
        p.setBackground(Color.blue);

        // add panel to frame
        f.add(p);

        f.setSize(200, 220);
        f.show();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get the source of the event
        Object source = e.getSource();

        // check if the source is the button b0
        if (source == b0) {
            // get the current text in the text label
            String currentText = l.getText();

            // append the number "0" to the current text
            l.setText(currentText + "0");
        }
    }
}
