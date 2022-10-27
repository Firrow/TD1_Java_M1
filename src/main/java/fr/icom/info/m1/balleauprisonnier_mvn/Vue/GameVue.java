package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Game;
import javafx.scene.Group;

import java.util.ArrayList;

public class GameVue extends Group {
    //récupérer input d'Evenement
    //i en argument depuis Evenement
    private Player[] joueurs;

    public GameVue(Player[] player)
    {
        this.joueurs = player;
    }

    public void getInput(int i, ArrayList<String> input){
        System.out.println(input);
        if (i==0 && input.contains("LEFT")) //GameVue (dans une fonction)
        {
            joueurs[i].moveLeft(); //est censé appeler controller qui va appeler model
        }
        if (i==0 && input.contains("RIGHT"))
        {
            joueurs[i].moveRight();
        }
        if (i==0 && input.contains("UP"))
        {
            joueurs[i].turnLeft();
        }
        if (i==0 && input.contains("DOWN"))
        {
            joueurs[i].turnRight();
        }
        if (i==0 && input.contains("ENTER")){
            joueurs[i].shoot();
        }
        if (i==1 && input.contains("Q"))
        {
            joueurs[i].moveLeft();
        }
        if (i==1 && input.contains("D"))
        {
            joueurs[i].moveRight();
        }
        if (i==1 && input.contains("Z"))
        {
            joueurs[i].turnLeft();
        }
        if (i==1 && input.contains("S"))
        {
            joueurs[i].turnRight();
        }
        if (i==1 && input.contains("SPACE")){
            joueurs[i].shoot();
        }

    }
}
