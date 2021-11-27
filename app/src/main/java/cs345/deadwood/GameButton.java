package cs345.deadwood;

import javax.swing.JButton;

public class GameButton extends JButton{
    private String buttonName;
    private ButtonType buttonType;

    public GameButton(String buttonName, ButtonType buttonType){
        this.buttonName = buttonName;
        this.buttonType = buttonType;
    }

    public String getButtonName(){
        return buttonName;
    }

    public ButtonType getButtonType(){
        return buttonType;
    }
}