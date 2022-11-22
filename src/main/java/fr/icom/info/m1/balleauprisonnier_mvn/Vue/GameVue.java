package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
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
    private IA[] ennemi;
    private Projectile projectile;
    private ProjectileController projectileController;
    private Field field;


    public GameVue(Player[] player, IA[] ia, Field field)

    {
        this.joueurs = player;
        this.ennemi = ia;
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
            projectile.withPlayerMove(joueurs[i], -50);
        }
        if (i==0 && input.contains("RIGHT"))
        {
            joueurs[i].moveRight();
            projectile.withPlayerMove(joueurs[i], -50);
        }
        if (i==0 && input.contains("UP"))
        {
            joueurs[i].turnLeft();
        }
        if (i==0 && input.contains("DOWN"))
        {
            joueurs[i].turnRight();
        }
        if (i==0 && input.contains("P")){ //Vérification de la touche P + remise en place de la balle
            //ennemi[i].TakeBall();
            if(joueurs[i].isTake_ball()){
                projectile.setX(joueurs[i].getX());
                projectile.setY(joueurs[i].getY()-55);
            }
        }
        if (i==0 && input.contains("ENTER")){
            
            if(!projectile.getMoving() && joueurs[i].isTake_ball()) {
            	joueurs[i].shoot();
                projectileController.startProjectile(projectile, joueurs[i], joueurs[i].getAngle(), field.getGraphicsContext2D());
                joueurs[i].setTake_ball(false);
            }
        }

        if (i==0 && input.contains("Q"))
        {
            ennemi[i].moveLeft();
            projectile.withPlayerMove(ennemi[i], 65);
        }
        if (i==0 && input.contains("D"))
        {
            ennemi[i].moveRight();
            projectile.withPlayerMove(ennemi[i], 65);
        }
        if (i==0 && input.contains("Z"))
        {
            ennemi[i].turnLeft();
        }
        if (i==0 && input.contains("S"))
        {
            ennemi[i].turnRight();
        }
        if (i==0 && input.contains("P")){ //Vérification de la touche P + remise en place de la balle
            if(ennemi[i].isTake_ball()){
                projectile.setX(ennemi[i].getX());
                projectile.setY(ennemi[i].getY()+65);
            }
        }
        if (i==0 && input.contains("SPACE")){
            if(!projectile.getMoving() && ennemi[i].isTake_ball()) {
                System.out.println("SHOOOOOOOOOOOT");
                ennemi[i].shoot();
                //projectileController.startProjectile(projectile, ennemi[i], ennemi[i].getAngle(), field.getGraphicsContext2D());
                projectileController.throwProjectile(projectile, ennemi[i].getAngle()+180);
                ennemi[i].setTake_ball(false);
            }
            //ennemi[i].shoot();
        }

        if (i==1 && input.contains("K"))
        {
            ennemi[i].moveLeft();
            projectile.withPlayerMove(ennemi[i], 65);
        }
        if (i==1 && input.contains("M"))
        {
            ennemi[i].moveRight();
            projectile.withPlayerMove(ennemi[i], 65);
        }
        if (i==1 && input.contains("O"))
        {
            ennemi[i].turnLeft();
        }
        if (i==1 && input.contains("L"))
        {
            ennemi[i].turnRight();
        }
        if (i==1 && input.contains("P")){ //Vérification de la touche P + remise en place de la balle
            if(ennemi[i].isTake_ball()){
                projectile.setX(ennemi[i].getX());
                projectile.setY(ennemi[i].getY()+65);
            }
        }
        if (i==1 && input.contains("SHIFT")){
            if(!projectile.getMoving() && ennemi[i].isTake_ball()) {
                ennemi[i].shoot();
                //projectileController.startProjectile(projectile, ennemi[i], ennemi[i].getAngle(), field.getGraphicsContext2D());
                projectileController.throwProjectile(projectile, ennemi[i].getAngle()+180);
                ennemi[i].setTake_ball(false);
            }
            //ennemi[i].shoot();
        }

        //Arrête balle sur la ligne des ennemis
		if(projectile.getVue()!=null && projectile.getY()<=ennemi[0].getY() || projectile.getY()>= joueurs[0].getY()) {
			projectile.setMoving(false);
		}
		if(projectile.getX()<=0) {
			projectile.setDirection(projectile.getDirection()+90);
		}
		if(projectile.getVue()!=null && projectile.getX()+projectile.getVue().getBoundsInLocal().getWidth()>=field.width) {
			projectile.setDirection(projectile.getDirection()-90);
		}
		//System.out.println(projectile.getDirection() + "," + joueurs[i].getAngle());

        projectileController.moveProjectile(projectile, projectile.getVue());
    }

    public boolean Touched(Projectile balle, Sprite p){
        //gc.setFill(Color.BLUE);
        BoundingBox bb = balle.getBoundingBox();
        //System.out.println(bb);
        //gc.fillRect(bb.getMinX(), bb.getMinY(), bb.getWidth(), bb.getHeight());
        Bounds pb = p.getBoundsInParent();
        //gc.fillRect(pb.getMinX(), pb.getMinY(), pb.getWidth(), pb.getHeight());
        return balle.getBoundingBox().intersects(p.getBoundsInParent());
    }
}
