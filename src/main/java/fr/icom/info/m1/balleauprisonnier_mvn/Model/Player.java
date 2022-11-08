package fr.icom.info.m1.balleauprisonnier_mvn.Model;

import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import javafx.scene.canvas.GraphicsContext;


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


	public PlayerVue getPlayerVue() {
		return playerVue;
	}
	public boolean isAlive() {
		return alive;
	}


	public Player(GraphicsContext gc, int xInit, int yInit, PlayerVue playerVue)
	{
	// Tous les joueurs commencent au centre du canvas,
	x = xInit;
	y = yInit;
	gc = gc;
	alive = true;

	this.playerVue = playerVue; //accéder au PlayerVue

	angle = 0;
	step = 1;
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
}
