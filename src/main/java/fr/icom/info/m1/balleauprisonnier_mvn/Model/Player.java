package fr.icom.info.m1.balleauprisonnier_mvn.Model;

import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;

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


	private double angle;
	private boolean alive;
	private boolean takeBall;

/*
 * Accesseurs et mutateurs
 */
	public boolean isTakeBall() {return takeBall;}
	public void setTakeBall(boolean takeBall) {this.takeBall = takeBall;}
	public double getX() {return this.x;}
	public double getY() {return this.y;}
	public Sprite getSprite() {return this.playerVue.getSprite();}
	public double getAngle() { return angle;}
	public PlayerVue getPlayerVue() {return playerVue;}
	public boolean isAlive() {return alive;}


	public Player(int xInit, int yInit, PlayerVue playerVue, boolean takeBall)
	{
	// Tous les joueurs commencent au centre du canvas,
	x = xInit;
	y = yInit;

	alive = true;
	this.takeBall = takeBall;

	this.playerVue = playerVue; //acceder au PlayerVue

	angle = 0;
	step = 1;
	}



	public void moveLeft()
	{
		if (x > 10)
		{
			x -= step;
			playerVue.spriteAnimate(this);
		}
	}

	public void moveRight()
	{
		if (x < 520)
		{
			x += step;
			playerVue.spriteAnimate(this);
		}
	}

	public void turnLeft()
	{
	  angle += 1;
	}

	public void turnRight()
	{
	  angle -= 1;
	}


	public void shoot(){
		playerVue.getSprite().playShoot();
	}


	/*
	 * M�thode qui supprime le joueur touch� de la liste de joueur
	 */
	public void killPlayer() {
		alive = false;
	}

	/*
	 * M�thode qui permet au joueur de r�cuperer la balle
	 */
	public void takeBall(ArrayList<String> input) {
		if(input.contains("P")){
			setTakeBall(true);
		}
	}
}
