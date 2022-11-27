package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Controller.ProjectileController;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameVue extends Group {
	
    private Player[] joueurs;
    private IA[] ennemi;
    private Projectile projectile;
    private ProjectileController projectileController;
    private Field field;
    private int score;
    private Text text;
    public int getScore() {
    	return this.score;
    }
    public GameVue(Text text, Player[] player, IA[] ia, Field field)

    {
        this.joueurs = player;
        this.ennemi = ia;
        this.field=field;
        this.projectile = Projectile.getInstance();
        this.score=0;
        this.projectileController = new ProjectileController();
        this.text=text;
        
        text.setText("Score: " + this.score);

    }

    //Gestion des inputs clavier
    public void getInput(int i, ArrayList<String> input){
        if (i==0 && input.contains("LEFT"))
        {
            joueurs[i].moveLeft();
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
        if (i==0 && input.contains("P")){ //Verification de la touche P + remise en place de la balle
            if(joueurs[i].isTake_ball()){
                projectile.setX(joueurs[i].getX());
                projectile.setY(joueurs[i].getY()-55);
            }
        }
        if (i==0 && input.contains("ENTER")){ //Tir du joueur humain
            
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
        if (i==0 && input.contains("P")){ //Verification de la touche P + remise en place de la balle
            if(ennemi[i].isTake_ball()){
                projectile.setX(ennemi[i].getX());
                projectile.setY(ennemi[i].getY()+65);
            }
        }
        if (i==0 && input.contains("SPACE")){
            if(!projectile.getMoving() && ennemi[i].isTake_ball()) {
                ennemi[i].shoot();
                projectileController.throwProjectile(projectile, ennemi[i].getAngle()+180);
                ennemi[i].setTake_ball(false);
            }
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
        if (i==1 && input.contains("P")){ //Verification de la touche P + remise en place de la balle
            if(ennemi[i].isTake_ball()){
                projectile.setX(ennemi[i].getX());
                projectile.setY(ennemi[i].getY()+65);
            }
        }
        if (i==1 && input.contains("SHIFT")){
            if(!projectile.getMoving() && ennemi[i].isTake_ball()) {
                ennemi[i].shoot();
                projectileController.throwProjectile(projectile, ennemi[i].getAngle()+180);
                ennemi[i].setTake_ball(false);
            }
        }

        //Gestion des collisions entre projectile et bord du terrain
		if(projectile.getVue()!=null && projectile.getY()<=ennemi[0].getY() || projectile.getY()>= joueurs[0].getY()) {
			projectile.setMoving(false);
		}
		if(projectile.getX()<=0) {
			projectile.setDirection(projectile.getDirection()+90);
		}
		if(projectile.getVue()!=null && projectile.getX()+projectile.getVue().getBoundsInLocal().getWidth()>=field.width) {
			projectile.setDirection(projectile.getDirection()-90);
		}

		//Deplacement du projectile
        projectileController.moveProjectile(projectile, projectile.getVue());
        //Mise a jour du score
        text.setText("Score: " + this.score);
        //Verification de fin de jeu
        getFinJeu();
    }
    
    /*
     * Affiche un message de fin de partie
     */
    public int getFinJeu() {
    	if(!joueurs[0].isAlive()) {
    		text.setX(field.width/2-60);
    		text.setY(field.height/2);
    		text.setText("Game over");
    		return 1;
    	}
    	else if(!ennemi[0].isAlive() && !ennemi[1].isAlive()) {
    		text.setX(field.width/2-50);
    		text.setY(field.height/2);    		
    		text.setText("You win !");
    		return 2;
    	}
    	return 0;
    }

	public void scoreUp() {
		this.score+=1;
	}
	
	/*
	 * Verifie l'interscetion d'un joueur avec le projectile
	 */
    public boolean Touched(Projectile balle, Sprite p){
        return balle.getBoundingBox().intersects(p.getBoundsInParent());
    }
}
