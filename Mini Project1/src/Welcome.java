import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome {
    private JPanel panel1;
    private JLabel lname;
    private JLabel lreg_number;
    private JFrame frame;
    private JLabel iconlabel;
    public JLabel dtname;
    public JLabel dtregnumber;
    private JButton btnlogout;

    public Welcome() {
        // Initialize components
        frame = new JFrame("Welcome");

        // Set frame properties
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setResizable(false);
        // Add panel to the frame
        frame.add(panel1);
        // Pack and set frame location
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        dtname.setText(Login.name);
        dtregnumber.setText(Login.regNumber);


        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.setVisible(false);

           }
        });
    }



    public static void main(String[] args) {
        new Welcome();
    }
}
