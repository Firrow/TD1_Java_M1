package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Game;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Controller.ProjectileController;
import javafx.scene.Group;

import java.util.ArrayList;

public class GameVue extends Group {
    //récupérer input d'Evenement
    //i en argument depuis Evenement
    private Player[] joueurs;
    private Projectile projectile;
    private ProjectileController projectileController;
    private Field field;

    public GameVue(Player[] player, Field field)
    {
        this.joueurs = player;
        this.field=field;
        this.projectile = Projectile.getInstance();
        this.projectileController = new ProjectileController();
    }

    public void getInput(int i, ArrayList<String> input){
//        System.out.println(input);
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
            //if(!projectile.getMoving()) { projectile.setDirection(-joueurs[i].getAngle());}
            projectileController.startProjectile(projectile, joueurs[i], joueurs[i].getAngle(), field.getGraphicsContext2D());
            
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

        projectileController.throwProjectile(projectile, projectile.getVue());

    }
}
