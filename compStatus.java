import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class compStatus implements ActionListener {
    private JDialog win;
    private compFile cfile;
    private int compNum;
    private JTextField tfCompNum;
    private JTextArea taStatus;

    public compStatus(compFile cfile) {
        win = new JDialog();
        win.setModalityType(ModalityType.APPLICATION_MODAL);
        win.setTitle("Complaint Status");
        win.setSize(500, 300);
        win.setLayout(new GridLayout(2, 1));
        this.cfile = cfile;

        // Set the background color to peach (you can replace Color.PEACH with the desired peach color)
        win.getContentPane().setBackground(new Color(255, 218, 185));

        tfCompNum = new JTextField(20);
        tfCompNum.setBackground(new Color(255, 240, 220)); // Light Peach color
        tfCompNum.addActionListener(this);

        taStatus = new JTextArea(5, 40);
        taStatus.setBackground(new Color(255, 240, 220)); // Light Peach color

        taStatus.setEditable(false);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(255, 218, 185)); // Peach color
        panel1.add(new JLabel("Enter Complaint No. : "));
        panel1.add(tfCompNum);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(255, 218, 185)); // Peach color
        panel2.add(new JLabel("Status "));
        panel2.add(new JScrollPane(taStatus));

        win.add(panel1);
        win.add(panel2);

        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.compNum = Integer.parseInt(tfCompNum.getText());
            String status = cfile.getSoln(compNum);
            if (status == null) {
                status = "Invalid Complaint No.";
            } else if (status.isEmpty()) {
                status = "No Solution found for given complaint number";
            }
            taStatus.setText(status);
        } catch (Exception exc) {
            taStatus.setText("Invalid Complaint No.");
        }
    }
}
