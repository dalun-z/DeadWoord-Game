package cs345.deadwood;
import java.util.Scanner;

public class test{

    public static void main(String[] args) {

        Scanner userNum = new Scanner(System.in);
        System.out.print("Player choose the number of players : ");
        int playerNum = userNum.nextInt();
        System.out.println("Player number are : " + playerNum);

        // * using the playerNum value to setup the starting credit and game days.
        

        int[] playerArr = new int[playerNum];
        for(int i = 0; i < playerNum; i++){
            playerArr[i] = i+1;
        }
        System.out.println("Player(s)' name are : " );
        for(int i : playerArr){
            System.out.println("Player " + i);
        }


        System.out.println("Game Start!");
        System.out.println("...........");
        while(true){
            for(int i = 0; i < playerNum; i++){
                System.out.println("Player " + playerArr[i] + " select move/pass/take a role");
                Scanner userinput = new Scanner(System.in);
                String userSelection = userinput.nextLine();

    
                /*
                    * interaction panel
                    * switch would be press different button
                    * ask player what they want to do

                    ? On which step should implement role dice action?
                    ? should we calculate the score in this switch statement?
                    ? How to parse JPanel.butoon function to this class?
    
                    TODO : Maybe add another switch inside specific case
                    TODO : Replace each case with JPanel.button feature
                */
                switch(userSelection){
                    // * m for move
                    // * call move / press move button
                    case "m" : break;

                    // * p for pass
                    // * call pass / press pass button
                    // * do nothing & jump to next player
                    case "p" : break;

                    // * t for take a role
                    // * call role / press role button
                    case "t" : 
                        /*
                            TODO : call Role class
                        */
                        break;

                    // * don't need this anymore after plug-in button feature
                    default :
                        System.out.println("Invaild input");
                        break;
                }
            }
        }
    }
}