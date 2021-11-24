package cs345.deadwood;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener{
    JFrame frame = new JFrame("Enter player number");
    JButton button = new JButton("Enter");
    JLabel label = new JLabel("Number of Player");
    JTextField enterNum = new JTextField(20);

    LaunchPage(){

        button.setBounds(100, 160, 200, 40);
        button.setFocusable(false);
        button.addActionListener(this);

        label.setLabelFor(enterNum);
        

        frame.add(label);
        frame.add(enterNum);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == button){

            frame.dispose();
            BoardView boardView = new BoardView();
            boardView.init();
        }
        
    }
}