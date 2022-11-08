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
    private final GraphicsContext gc;
    //récupérer input d'Evenement
    //i en argument depuis Evenement
    private Player[] joueurs;
    private Projectile projectile;
    private ProjectileController projectileController;
    private Field field;

<<<<<<< HEAD
    public GameVue(Player[] player, GraphicsContext gc)
=======
    public GameVue(Player[] player, Field field)
>>>>>>> 215d877b36b41e06a39e4b7776a6520d2fe91537
    {
        this.joueurs = player;
        this.field=field;
        this.projectile = Projectile.getInstance();
<<<<<<< HEAD
        this.projectileController = ProjectileController.getInstance();
        this.gc = gc;
=======
        this.projectileController = new ProjectileController();
>>>>>>> 215d877b36b41e06a39e4b7776a6520d2fe91537
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
<<<<<<< HEAD
            projectileController.startProjectile(projectile, projectile.getVue(), joueurs[i].getAngle());

=======
            projectileController.startProjectile(projectile, joueurs[i], joueurs[i].getAngle(), field.getGraphicsContext2D());
            
>>>>>>> 215d877b36b41e06a39e4b7776a6520d2fe91537
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
    //pour partie COLLISIONS : créer une méthode touched avec getboundingBox()
    //cette méthode va dire au controller que le joueur/ennemi est touché

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
