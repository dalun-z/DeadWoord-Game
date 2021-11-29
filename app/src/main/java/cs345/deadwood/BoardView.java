package cs345.deadwood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;


public class BoardView implements MouseListener {

    private JFrame frame;
    private JTextArea comment;
    private JButton moveButton, passButton, actButton;
    private final int VERTICAL_PADDING = 5;
    private final int HORIZONTAL_PADDING = 5;
    // private Deck deck;

    ArrayList<Player> players;
    HashMap<String, Location> locations;
    
    public void init(int n, ArrayList<Player> players, HashMap<String, Location> locations) {
        this.players = players;
        this.locations = locations;
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 930));
        // board img is 1200 x 900. The control panel is 300 x 900, so we want the frame to be 1500 x 900
        // The top bar on the frame is about 30 pixels in height. To account for that, we increase frame height by 30, so 930.

        // Set layout to null, so we can place widgets based on x-y coordinates.
        frame.setLayout(null);

        // // TODO: set locations for players programmatically (instead of this)
        // SetView trainStation = new SetView(frame);
        // trainStation.drawSet();

        LocationView locView = new LocationView(frame, locations);
        locView.drawLocations();

        String[] diceColor = {"r", "b", "y", "c", "g", "o", "p", "v", "w"};

        for(int i = 1; i <= n; i++){
            if(n == 5){
                players.add(new Player(i, "Trailer", 0, 2, diceColor[i], 1));
            }else if(n == 6){
                players.add(new Player(i, "Trailer", 0, 4, diceColor[i], 1));
            }else if(n==7 || n==8){
                players.add(new Player(i, "Trailer", 0, 0, diceColor[i], 2));
            }else{
                players.add(new Player(i, "Trailer", 0, 0, diceColor[i], 1));
            }
        }

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
        for(int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            controlPanel.add(showPlayerInfo(p));
            System.out.println("Showing player " + i);
        }

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

    private JPanel showPlayerInfo(Player player) {

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
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        moveButton = new JButton("Move");
        moveButton.addActionListener(new ButtonListener());
        buttonPanel.add(moveButton);
        
        passButton = new JButton("Pass");
        passButton.addActionListener(new ButtonListener());
        buttonPanel.add(passButton);
        
        actButton = new JButton("Act");
        actButton.addActionListener(new ButtonListener());
        buttonPanel.add(actButton);

        movePanel.add(buttonPanel);

        return movePanel;

    }


    private JPanel miscInteraction() {
        // free space to use for comments or any game related stuff. E.g., show rolling die or show game log.

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 250));

        JLabel panelTitle = new JLabel("Game Log");
        panelTitle.setFont(new Font("TimesRoman", Font.BOLD, 18));
        panel.add(panelTitle);


        comment = new JTextArea();
        comment.setLineWrap(true);
        comment.setPreferredSize(panel.getPreferredSize());
        panel.add(comment);
        return panel;
    }

    public void update() {
        
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == passButton) {
                comment.append("Player choose 'Pass'\n");
            } else if (e.getSource() == moveButton) {
                comment.append("Player, please selection the neighbor area to go to\n");
            } else if (e.getSource() == actButton) {
                comment.append("Player, please select a role\n");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // The top bar of the frame is about 30 pixels in height. So to get the x,y values on the board, subtract 30 from the y value.
        System.out.println("Mouse clicked at X = " + e.getX() + ", Y = " + (e.getY() - 30));
        comment.append("XY position " + e.getX() + ", " + e.getY() + "\n");
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
