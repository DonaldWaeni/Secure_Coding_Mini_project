import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField txtregnumber;
    private JPanel panel1;
    private JPasswordField txtpassword;
    private JButton loginButton;
    private JCheckBox chkshowPasssword;
    private JButton donTHaveAnButton;
    private JButton exitButton;
    public JFrame frame;
    public static String regNumber;//variable that will store the retrieved data from database after successfull login
    public static String name;//variable that will store the retrieved data from database after successfull login
public Login(){
    frame = new JFrame("Login");
    frame .setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(400,300));
    frame.setResizable(false);
    // Now adding panel
    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            user_login();

        }
    });
    chkshowPasssword.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(chkshowPasssword.isSelected()) {
                    txtpassword.setEchoChar((char) 0); // Show password
            }
               else {
                   txtpassword.setEchoChar('$');
            }

        }
    });
    donTHaveAnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Signup();
            frame.setVisible(false);
        }
    });
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Exit();
        }
    });
}

private void Exit() {
        // Add this where you handle the exit logic (e.g., in a button's ActionListener)
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit?", "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); // Close the application
        }

    }

private void user_login() {

            // Database connection details
             final String URL = "jdbc:mysql://localhost:3306/mini_project";
             final String USER = "root";  // Database username
             final String PASSWORD = "";  // Database password (leave empty if none)


            // Login method that checks the entered credentials against the database
                String regnumber = txtregnumber.getText();
                String password = new String(txtpassword.getPassword());  // Convert char array to string
                boolean error = true;
                // Ensure fields are not empty
                if (regnumber.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                    return;
                }else {
                    error=false;
                }

                if(!error){
                                        // Connect to the database
                    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        // SQL query to check if the user exists and if the password matches
                        String sql = "SELECT * FROM users WHERE reg_number = ? AND password = ?";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, regnumber);
                        preparedStatement.setString(2, password);

                        // Execute the query
                        ResultSet resultSet = preparedStatement.executeQuery();

                        // Check if a matching record is found
                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            String sql1 = "SELECT name, reg_number FROM users WHERE reg_number = ? AND password = ?";

                            try (Connection conn1 = DriverManager.getConnection(URL, USER, PASSWORD);
                                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                                pstmt.setString(1, regnumber);
                                pstmt.setString(2, password);

                                ResultSet rs = pstmt.executeQuery();
                                if (rs.next()) {
                                     name = rs.getString("name");
                                    regNumber = rs.getString("reg_number");
                                    new Welcome();
                                    frame.setVisible(false);
                                } else {
                                    // Handle login failure
                                    JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
                                }
                            } catch (SQLException e) {
                                // Handle Database connection failure
                                JOptionPane.showMessageDialog(null, "Failed to connect to the database", "Error", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(frame, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

    }

public static void main(String[] args) {
        new Login();
    }
}
