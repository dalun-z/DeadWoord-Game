package cs345.deadwood;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;


public class BoardView implements MouseListener {

    public JFrame frame;
    private JTextArea comment;
    private JButton moveButton, passButton, actButton, yesButton, noButton;
    private JPanel controlPanel;
    public JPanel buttonPanel;
    private final int VERTICAL_PADDING = 5;
    private final int HORIZONTAL_PADDING = 5;
    // private Deck deck;

    ArrayList<Player> players;
    HashMap<String, Location> locations;
    LocationView locView;
    GameState global;
    boolean awaitingMoveDestination = false;
    boolean awaitingRoleSelection = false;

    public void init(int n) {
        this.global = GameState.getInstance();
        this.players = global.players;
        this.locations = global.locations;
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 930));
        // board img is 1200 x 900. The control panel is 300 x 900, so we want the frame to be 1500 x 900
        // The top bar on the frame is about 30 pixels in height. To account for that, we increase frame height by 30, so 930.

        // Set layout to null, so we can place widgets based on x-y coordinates.
        frame.setLayout(null);

        locView = new LocationView(frame);
        locView.drawLocations();

        String[] diceColor = {"r", "b", "y", "c", "g", "o", "p", "v", "w"};

        for(int i = 1; i <= n; i++){
            if(n == 5){
                players.add(new Player(i, "trailer", 0, 2, diceColor[i], 1));
            }else if(n == 6){
                players.add(new Player(i, "trailer", 0, 4, diceColor[i], 1));
            }else if(n==7 || n==8){
                players.add(new Player(i, "trailer", 0, 0, diceColor[i], 2));
            }else{
                players.add(new Player(i, "trailer", 0, 0, diceColor[i], 1));
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

        comment.append("First Player's turn - please choose an action\n");
    }

    private JPanel createControlPanel() {
        controlPanel = new JPanel();
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
            // controlPanel.add(showPlayerInfo(p));
            controlPanel.add(showPlayerInfo(p), i);
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

    private void updatePlayerInfo(Player player) {
        int compIndex = player.getPlayer() - 1;
        controlPanel.remove(compIndex);
        controlPanel.add(showPlayerInfo(player), compIndex);
        controlPanel.revalidate();
        controlPanel.repaint();
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

        JLabel playerLocation = new JLabel(player.getLocation());
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
        
        buttonPanel = new JPanel();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        
        moveButton = new JButton("Move");
        moveButton.addActionListener(new ButtonListener());
        buttonPanel.add(moveButton);
        
        passButton = new JButton("Pass");
        passButton.addActionListener(new ButtonListener());
        buttonPanel.add(passButton);
        
        actButton = new JButton("Act");
        actButton.addActionListener(new ButtonListener());
        buttonPanel.add(actButton);

        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ButtonListener());
        buttonPanel.add(yesButton);

        noButton = new JButton("No");
        noButton.addActionListener(new ButtonListener());
        buttonPanel.add(noButton);

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

    public void handlePass() {
        comment.setText(null);
        comment.append("Player " + global.currentPlayer.getPlayer() + " chose 'Pass'\n");
        global.action = "pass";
        synchronized(global) {global.notify();}
    }

    public void handleMove() {
        String currentLoc = global.currentPlayer.getLocation();
        comment.setText(null);
        comment.append("Player "+ global.currentPlayer.getPlayer() +", please selection the neighbor area to go to:\n");
        comment.append("Player current location: " + currentLoc + "\n");
        
        System.out.println("All Loaded Locations: " + global.locations.keySet());
        
        comment.append("Possible locations: \n");
        for (String loc : global.locations.get(currentLoc).getNeighbors()) {
            comment.append(loc + ", ");
        }

        global.action = "move";
        awaitingMoveDestination = true;
        synchronized(global) {global.notify();}
        // System.out.println("Global action: " + global.action);
    }

    public void handleMoveSelection(int x, int y) {
        System.out.println("Moving player");
        Player curr = global.currentPlayer;
        boolean validated = global.validateMove(x, y, locations.get(curr.getLocation()).getNeighbors());
        
        if (validated) {
            comment.append("Player " + curr.getPlayer() + " moved to " + curr.getLocation() + "\n");
            updatePlayerInfo(curr);
            
            comment.append("Do you want to take a role?\n");
            awaitingRoleSelection = true;
            
            // global.awaitRoleChoice();
            
            // try{
            //     synchronized(buttonPanel) {
            //         buttonPanel.wait();
            //     }
            // } catch (InterruptedException e) {
            //     System.out.println("Waiting for yes/no interrupted");
            // }
            
            awaitingMoveDestination = false;
            Location dest = locations.get(curr.getLocation());
            if(!dest.isRevealed()) {
                System.out.println("Flipping destination");
                dest.reveal();
                locView.revealLocation(curr.getLocation());
            }
            synchronized(global.boardView) {global.boardView.notify();};
        } else {
            comment.append("Invalid move, please select a valid location's card");
        }
    }
    
    public void handleRoleSelection(int x, int y, Player p) {
        System.out.println("Selecting role...");
        
        boolean roleSelected = global.validateRoleSelection(x, y, p);
    
        if (roleSelected) {
            System.out.println("Role selected: " + p.getRole().getLine());
        }
    }

    public void handleAct() {
        comment.setText(null);
        comment.append("Player "+ global.currentPlayer.getPlayer() +", please select a role\n");
        global.action = "act";
        synchronized(global) {global.notify();}
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == passButton) {
                handlePass();
            } else if (e.getSource() == moveButton) {
                handleMove();
            } else if (e.getSource() == actButton) {
                handleAct();
            } else if (e.getSource() == yesButton) {
                if (awaitingRoleSelection) {
                    comment.append("Please pick a role\n");
                    
                    awaitingRoleSelection = false;
                    synchronized(buttonPanel) {buttonPanel.notify();};
                }
            } else if (e.getSource() == noButton) {
                

                awaitingRoleSelection = false;
                synchronized(buttonPanel) {buttonPanel.notify();};
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // The top bar of the frame is about 30 pixels in height. So to get the x,y values on the board, subtract 30 from the y value.
        int x = e.getX();
        int y = e.getY() - 30;
        
        System.out.println("Mouse clicked at X = " + x + ", Y = " + y);
        comment.setText(null);
        comment.append("XY position " + x + ", " + y + "\n");
        
        if (awaitingMoveDestination) {
            handleMoveSelection(x, y);
        } else if (awaitingRoleSelection) {
            handleRoleSelection(x, y, global.currentPlayer);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
