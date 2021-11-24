package cs345.deadwood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


public class BoardView implements MouseListener {

    private JFrame frame;
    private final int VERTICAL_PADDING = 5;
    private final int HORIZONTAL_PADDING = 5;

    public void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 930));
        // board img is 1200 x 900. The control panel is 300 x 900, so we want the frame to be 1500 x 900
        // The top bar on the frame is about 30 pixels in height. To account for that, we increase frame height by 30, so 930.

        // Set layout to null, so we can place widgets based on x-y coordinates.
        frame.setLayout(null);

        SetView trainStation = new SetView(frame);
        trainStation.drawSet();


        URL boardImg = getClass().getClassLoader().getResource("img/board.png");
        JLabel board = new JLabel(new ImageIcon(boardImg.getPath().replace("%20", " ")));
        board.setLocation(0, 0);
        board.setSize(1200, 900);
        frame.add(board);

        JPanel controlPanel = createControlPanel();
        controlPanel.setLocation(1200, 0);
        controlPanel.setSize(300, 900);
        frame.add(controlPanel);

        frame.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(300, 900));
        // Set height same as the board image. board image dimensions are 1200 x 900

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding around edges

        JLabel team = new JLabel("Team Name");
        team.setFont(new Font("TimesRoman", Font.BOLD, 20));
        controlPanel.add(team);
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        JLabel playerInfoLabel = new JLabel("Players");
        playerInfoLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
        controlPanel.add(playerInfoLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        // Show players
        controlPanel.add(showPlayerInfo(1, "Train Station", 2, 3, "b"));
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
        controlPanel.add(showPlayerInfo(2, "Trailer", 2, 3, "r"));
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
        controlPanel.add(showPlayerInfo(3, "Trailer", 2, 3, "p"));
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
        controlPanel.add(showPlayerInfo(4, "Trailer", 2, 3, "y"));
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
        controlPanel.add(showPlayerInfo(5, "Trailer", 2, 3, "w"));
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding


        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(getMovePanel());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(miscInteraction());


        return controlPanel;
    }

    private JPanel showPlayerInfo(int i, String area, int cash, int credit, String dice) {
        Player player = new Player(i, area, cash, credit, dice);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        panel.add(new JLabel("Player " + player.getPlayer() + ": "));
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel playerDice= new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + player.getDice()).getPath().replace("%20", " ")));
        panel.add(playerDice);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel playerLocation = new JLabel(player.getArea());
        panel.add(playerLocation);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel money = new JLabel("$" + player.getCash() + " C" + player.getCredit()); // 2 dollars and 3 credits.
        panel.add(money);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        return panel;
    }

    private JPanel getMovePanel() {
        JPanel movePanel = new JPanel();
        movePanel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 200));

        JLabel panelTitle = new JLabel("Move options");
        panelTitle.setFont(new Font("TimesRoman", Font.BOLD, 18));
        movePanel.add(panelTitle);

        JPanel buttonpabel = new JPanel(new FlowLayout());
        
        JButton moveButton = new JButton("Move");
        //moveButton.addActionListener(new clickButtonListener());
        buttonpabel.add(moveButton);
        
        JButton passButton = new JButton("Pass");
        //passButton.addActionListener(new clickButtonListener());
        buttonpabel.add(passButton);
        
        JButton actButton = new JButton("Act");
        //actButton.addActionListener(new clickButtonListener());
        buttonpabel.add(actButton);

        movePanel.add(buttonpabel);

        return movePanel;

    }


    private JPanel miscInteraction() {
        // free space to use for comments or any game related stuff. E.g., show rolling die or show game log.

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 250));

        JLabel panelTitle = new JLabel("Free space");
        panelTitle.setFont(new Font("TimesRoman", Font.BOLD, 18));
        panel.add(panelTitle);

        JTextArea comment = new JTextArea("free space to use for comments or any game related stuff. E.g., show rolling die or show game log.");
        comment.setLineWrap(true);
        comment.setPreferredSize(panel.getPreferredSize());
        panel.add(comment);
        return panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // The top bar of the frame is about 30 pixels in height. So to get the x,y values on the board, subtract 30 from the y value.
        System.out.println("Mouse clicked at X = " + e.getX() + ", Y = " + (e.getY() - 30));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
