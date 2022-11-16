package fr.icom.info.m1.balleauprisonnier_mvn.Model;

import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;


/**
 * 
 * Classe gerant un joueur
 *
 */
public class Player 
{
	private double x;       // position horizontale du joueur
	private double y;		// position verticale du joueur
	private double step;    // pas d'un joueur
	private PlayerVue playerVue;


	private GraphicsContext gc;
	private double angle;
	private boolean alive;
	private boolean take_ball;


	public boolean isTake_ball() {
		return take_ball;
	}
	public void setTake_ball(boolean take_ball) {
		this.take_ball = take_ball;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public Sprite getSprite() {
		return this.playerVue.getSprite();
	}
	public double getAngle() { return angle; }
	public PlayerVue getPlayerVue() {
		return playerVue;
	}
	public boolean isAlive() {
		return alive;
	}


	public Player(GraphicsContext gc, int xInit, int yInit, PlayerVue playerVue, boolean take_ball)
	{
	// Tous les joueurs commencent au centre du canvas,
	x = xInit;
	y = yInit;
	gc = gc;
	alive = true;
	this.take_ball = take_ball;

	this.playerVue = playerVue; //accéder au PlayerVue

	angle = 0;
	step = 1;
	}



	public void moveLeft()
	{
		if (x > 10)
		{
			x -= step;
			playerVue.spriteAnimate(this);
			playerVue.display(this);
		}
	}

	public void moveRight()
	{
		if (x < 520)
		{
			x += step;
			playerVue.spriteAnimate(this);
			playerVue.display(this);
		}
	}

	public void turnLeft()
	{
	  angle += 1;
	  playerVue.display(this);
	}

	public void turnRight()
	{
	  angle -= 1;
	  playerVue.display(this);
	}


	public void shoot(){
		playerVue.getSprite().playShoot();
		//création
	}

	public void boost()
	{
		x += step*2;
		playerVue.spriteAnimate(this);
	}

	//Méthode qui supprime le joueur touché de la liste de joueur
	public void killPlayer() {
		alive = false;
	}

	//Méthode qui permet au joueur de récupérer la balle
	public void TakeBall(ArrayList<String> input) {
		if(input.contains("P")){
			take_ball = true;
		}
	}
}
