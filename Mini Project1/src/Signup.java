import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;


public class Signup extends Component {
    public JTextField txtreg_number;
    public boolean error;/*this variable ensures that when user makes a mistake like forget to enter 1 field
    for example other methods are not executed unless it is sorted*/
    public JTextField txtname;
    public JPasswordField txtPassword;
    public JPasswordField txtPasswordConfirmation;
    private JButton signupButton;
    private JButton exitButton;
    private JCheckBox chkShowPassword;
    public JFrame frame;
    private JPanel panel1;
    private JButton donTHaveAnButton;
    public String password;
    public boolean error1 = true;/*this variable ensures that when user clicks the password field
     the dialog to choose password or not only shows once*/
    public String name;
    public String reg_number ;
    public String confirmPassword;

    public Signup(){
        frame = new JFrame("Signup");
        frame .setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(420,470));
        frame.setResizable(false);
        // Now adding panel
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Exit();
        }
    });

    chkShowPassword.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0); // Show password
                txtPasswordConfirmation.setEchoChar((char) 0); // Show password
            } else {
                txtPassword.setEchoChar('$'); // Hide password
                txtPasswordConfirmation.setEchoChar('$'); // Hide password
            }
        }
    });

    txtPassword.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            showPasswordOptionsDialog();
        }
    });
    signupButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          checkfields();

        }
    });

        donTHaveAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             new Login();
             frame.setVisible(false);
            }
        });
        txtPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                showPasswordOptionsDialog();
            }
        });
    }

    private void checkfields() {
        name = txtname.getText();
        reg_number = txtreg_number.getText();
        password = txtPassword.getText();
        confirmPassword = txtPasswordConfirmation.getText();

        if (name.trim().isEmpty() ||
                reg_number.trim().isEmpty() ||
                confirmPassword.trim().isEmpty() ||
                password.trim().isEmpty()) {
            // One or more fields are empty
            error = true;
            JOptionPane.showMessageDialog(null, "Please enter all Fields", "Message", JOptionPane.INFORMATION_MESSAGE);

        } else {
            error = false;
            addDataToDatabase();
        }
    }
    private void addDataToDatabase() {

    if (password.length() > 12 ) {
        error = true;
        JOptionPane.showMessageDialog(null, "Please enter a password less than 12 characters", "Message", JOptionPane.INFORMATION_MESSAGE);

    } else if (confirmPassword.length() > 12 ) {
        error = true;
        JOptionPane.showMessageDialog(null, "Please enter a password less than 12 characters", "Message", JOptionPane.INFORMATION_MESSAGE);

    } else if (!password.equalsIgnoreCase(confirmPassword)) {
        error = true;
        JOptionPane.showMessageDialog(null, "Those passwords didn't match", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    if (!error){
           // Database connection details
           final String URL = "jdbc:mysql://localhost:3306/mini_project";
           final String USER = "root"; // MySQL username
           final String PASSWORD = ""; // MySQL password
           try {
               // Register the MySQL JDBC driver
               Class.forName("com.mysql.cj.jdbc.Driver");

               // Establish the connection
               Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

               // SQL query to insert data
               String sql = "INSERT INTO users (name, reg_number, password) VALUES (?, ?, ?)";
               PreparedStatement preparedStatement = conn.prepareStatement(sql);

               // Set values for the placeholders
               preparedStatement.setString(1, name);         // First placeholder (?) for name
               preparedStatement.setString(2, reg_number);   // Second placeholder (?) for registration number
               preparedStatement.setString(3, password);    // Third placeholder (?) for username


               // Execute the SQL query
               int rowsInserted = preparedStatement.executeUpdate();
               if (rowsInserted > 0) {
                   JOptionPane.showMessageDialog(null, "A new user was inserted successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
                   frame.setVisible(false);
                   new Login();
               }

               // Close the connection
               conn.close();

           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Failed to connect to the database", "Error", JOptionPane.INFORMATION_MESSAGE);
           }

       }

    }


    private void showPasswordOptionsDialog() {
        if(error1){
            // Generate a strong password of length 12
            String generatedPassword = generateStrongPassword(12);

            // Create a message that shows the generated password
            String message = "Generated Password: " + generatedPassword + "\n\nChoose an option:";

            // Options for the dialog
            Object[] options = {"Use Generated Password", "Create My Own"};

            // Show option dialog with the generated password displayed
            int choice = JOptionPane.showOptionDialog(this,
                    message,
                    "Password Options",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            error1 = false;
            // If the user chooses to use the generated password
            if (choice == JOptionPane.YES_OPTION) {
                // Set the generated password in both password and confirmation fields
                txtPassword.setText(generatedPassword);
                txtPasswordConfirmation.setText(generatedPassword);
            } else if (choice == JOptionPane.NO_OPTION) {
                // User chose to create their own password, no action needed
            }
        }

    }

    public static String generateStrongPassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4");
        }

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()";

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        // Ensure at least one character from each category
        password.append(upperCase.charAt(random.nextInt(upperCase.length())));
        password.append(lowerCase.charAt(random.nextInt(lowerCase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));

        // Fill the rest of the password length with random characters
        String allChars = upperCase + lowerCase + digits + specialChars;
        for (int i = 4; i < length; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        // Shuffle the characters to make the password more random
        return shuffleString(password.toString());
    }

    public static String shuffleString(String password) {
        char[] passwordArray = password.toCharArray();
        Random random = new Random();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Simple swap
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        return new String(passwordArray);
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

    public static void main(String[] args) {
        new Signup();
    }
}

