package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Game;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Controller.ProjectileController;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameVue extends Group {
    private GraphicsContext gc;
    //récupérer input d'Evenement
    //i en argument depuis Evenement
    private Player[] joueurs;
    private Projectile projectile;
    private ProjectileController projectileController;
    private Field field;

    public GameVue(Player[] player, Field field){

        this.joueurs = player;
        this.field=field;
        this.projectile = Projectile.getInstance();

        this.gc = field.getGraphicsContext2D();

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
            if(projectile.getMoving()) { //permet de ne pas recréer une balle quand une balle est en mouvement
                projectileController.startProjectile(projectile, joueurs[i], joueurs[i].getAngle(), field.getGraphicsContext2D());
            }
            //TODO if(projectile.getMoving() && SI UN DES JOUEURS A LA BALLE)
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

    public boolean Touched(Projectile balle, Sprite p){
        gc.setFill(Color.BLUE);
        BoundingBox bb = balle.getBoundingBox();
        System.out.println(bb);
        gc.fillRect(bb.getMinX(), bb.getMinY(), bb.getWidth(), bb.getHeight());
        Bounds pb = p.getBoundsInParent();
        gc.fillRect(pb.getMinX(), pb.getMinY(), pb.getWidth(), pb.getHeight());
        return balle.getBoundingBox().intersects(p.getBoundsInParent());
    }
}
