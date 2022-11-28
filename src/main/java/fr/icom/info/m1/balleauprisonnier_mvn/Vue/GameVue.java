package fr.icom.info.m1.balleauprisonnier_mvn.Vue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Controller.ProjectileController;
import javafx.scene.Group;
import javafx.scene.text.Text;
import java.util.ArrayList;


public class GameVue extends Group {
	
    private Player[] players;
    private IA[] ennemies;
    private Projectile projectile;
    private ProjectileController projectileController;
    private Field field;
    private int score;
    private Text text;
    

    public GameVue(Text text, Player[] player, IA[] ia, Field field){
        this.players = player;
        this.ennemies = ia;
        this.field=field;
        this.projectile = Projectile.getInstance();
        this.score=0;
        this.projectileController = new ProjectileController();
        this.text=text;
        
        text.setText("Score: " + this.score);

    }
    

    /*
     * Gestion des inputs clavier
     */
    public void getInput(int i, ArrayList<String> input){
        if (i==0 && input.contains("LEFT"))
        {
            players[i].moveLeft();
            projectile.withPlayerMove(players[i], -50);
        }
        if (i==0 && input.contains("RIGHT"))
        {
            players[i].moveRight();
            projectile.withPlayerMove(players[i], -50);
        }
        if (i==0 && input.contains("UP"))
        {
            players[i].turnLeft();
        }
        if (i==0 && input.contains("DOWN"))
        {
            players[i].turnRight();
        }
        if (i==0 && input.contains("P")){ //Verification de la touche P + remise en place de la balle
            if(players[i].isTakeBall()){
                projectile.setX(players[i].getX());
                projectile.setY(players[i].getY()-55);
            }
        }
        if (i==0 && input.contains("ENTER")){ //Tir du joueur humain
            
            if(!projectile.getMoving() && players[i].isTakeBall()) {
            	players[i].shoot();
                projectileController.startProjectile(projectile, players[i], players[i].getAngle(), field.getGraphicsContext2D());
                players[i].setTakeBall(false);
            }
        }

        if (i==0 && input.contains("Q"))
        {
            ennemies[i].moveLeft();
            projectile.withPlayerMove(ennemies[i], 65);
        }
        if (i==0 && input.contains("D"))
        {
            ennemies[i].moveRight();
            projectile.withPlayerMove(ennemies[i], 65);
        }
        if (i==0 && input.contains("Z"))
        {
            ennemies[i].turnLeft();
        }
        if (i==0 && input.contains("S"))
        {
            ennemies[i].turnRight();
        }
        if (i==0 && input.contains("P")){ //Verification de la touche P + remise en place de la balle
            if(ennemies[i].isTakeBall()){
                projectile.setX(ennemies[i].getX());
                projectile.setY(ennemies[i].getY()+65);
            }
        }
        if (i==0 && input.contains("SPACE")){
            if(!projectile.getMoving() && ennemies[i].isTakeBall()) {
                ennemies[i].shoot();
                projectileController.throwProjectile(projectile, ennemies[i].getAngle()+180);
                ennemies[i].setTakeBall(false);
            }
        }

        if (i==1 && input.contains("K"))
        {
            ennemies[i].moveLeft();
            projectile.withPlayerMove(ennemies[i], 65);
        }
        if (i==1 && input.contains("M"))
        {
            ennemies[i].moveRight();
            projectile.withPlayerMove(ennemies[i], 65);
        }
        if (i==1 && input.contains("O"))
        {
            ennemies[i].turnLeft();
        }
        if (i==1 && input.contains("L"))
        {
            ennemies[i].turnRight();
        }
        if (i==1 && input.contains("P")){ //Verification de la touche P + remise en place de la balle
            if(ennemies[i].isTakeBall()){
                projectile.setX(ennemies[i].getX());
                projectile.setY(ennemies[i].getY()+65);
            }
        }
        if (i==1 && input.contains("SHIFT")){
            if(!projectile.getMoving() && ennemies[i].isTakeBall()) {
                ennemies[i].shoot();
                projectileController.throwProjectile(projectile, ennemies[i].getAngle()+180);
                ennemies[i].setTakeBall(false);
            }
        }

        //Gestion des collisions entre projectile et bord du terrain
		if(projectile.getVue()!=null && projectile.getY()<=ennemies[0].getY() || projectile.getY()>= players[0].getY()) {
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
        getEnd();
    }
    
    /*
     * Affiche un message de fin de partie
     */
    public void getEnd() {
    	if(!players[0].isAlive()) {
    		text.setX(field.width/2-60);
    		text.setY(field.height/2);
    		text.setText("Game over");
    	}
    	else if(!ennemies[0].isAlive() && !ennemies[1].isAlive()) {
    		text.setX(field.width/2-50);
    		text.setY(field.height/2);    		
    		text.setText("You win !");
    	}
    }

	public void scoreUp() {
		this.score+=1;
	}
	
	/*
	 * Verifie l'intersection d'un joueur avec le projectile
	 */
    public boolean touched(Projectile balle, Sprite p){
        return balle.getBoundingBox().intersects(p.getBoundsInParent());
    }
}
