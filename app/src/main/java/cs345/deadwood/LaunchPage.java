package cs345.deadwood;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

public class LaunchPage implements ActionListener{
    JFrame frame = new JFrame("Enter player number");
    JButton button = new JButton("Enter");
    JLabel label = new JLabel("Number of Player");
    JTextField enterNum = new JTextField(20);

    ArrayList<Player> players;
    int input;

    LaunchPage(ArrayList<Player> players){
        this.players = players;

        button.setBounds(100, 160, 200, 40);
        button.setFocusable(false);
        button.addActionListener(this);

        label.setLabelFor(enterNum);
        enterNum.setSize(150, 150);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.add(enterNum);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == button){
            input = Integer.parseInt(enterNum.getText());
            frame.dispose();
            BoardView boardView = new BoardView();
            boardView.init(input, players);
        }
    }
}