package fr.icom.info.m1.balleauprisonnier_mvn.Model;


//import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * Classe gerant un joueur
 *
 */
public class Player 
{

	private double x;       // position horizontale du joueur
	private double y;		  // position verticale du joueur
	//double x;
	//final double y; 	  // position verticale du joueur
	private double angle = 90; // rotation du joueur, devrait toujours être en 0 et 180
	private double step;    // pas d'un joueur
	private String playerColor;

	// On une image globale du joueur
	private Image directionArrow;
	private ImageView PlayerDirectionArrow;

	private GraphicsContext graphicsContext;
	private PlayerVue playerVue;


	  Player(GraphicsContext gc, String color, int xInit, int yInit, String side, PlayerVue playerVue)
	  {
		// Tous les joueurs commencent au centre du canvas, 
	    x = xInit;               
	    y = yInit;
	    graphicsContext = gc;
	    playerColor=color;

	    this.playerVue = playerVue; //accéder au PlayerVue

	    angle = 0;

	    // On charge la representation du joueur
        if(side.equals("top")){
        	directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}
        
        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);


        //directionArrow = sprite.getClip().;

		  // Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
		  /*Random randomGenerator = new Random();
		  step = randomGenerator.nextFloat();
		  System.out.print("Vitesse joueur 1 : " + step);*/

        // Pour commencer les joueurs ont une vitesse / un pas fixe
        step = 1;
	    
	  }

	  /**
	   *  Affichage du joueur
	   */
	  public void display()
	  {
		  graphicsContext.save(); // saves the current state on stack, including the current transform
	      rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
		  graphicsContext.drawImage(directionArrow, x, y);
		  graphicsContext.restore(); // back to original state (before rotation)
	  }

	  private void rotate(GraphicsContext gc, double angle, double px, double py) {
		  Rotate r = new Rotate(angle, px, py);
		  gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	  }
	  
	  /**
	   *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	   */
	 
	  public void moveLeft()
	  {	    
	    if (x > 10)
	    {
			playerVue.spriteAnimate(this);
		    x -= step;
	    }
	  }

	  /**
	   *  Deplacement du joueur vers la droite
	   */
	  public void moveRight()
	  {
	    if (x < 520)
	    {
			playerVue.spriteAnimate(this);
		    x += step;
	    }
	  }

	  
	  /**
	   *  Rotation du joueur vers la gauche
	   */
	  public void turnLeft()
	  {
		  angle += 1;
	  }
	  /**
	   *  Rotation du joueur vers la droite
	   */
	  public void turnRight()
	  {
		  angle -= 1;
	  }


	  public void shoot(){
	  	playerVue.getSprite().playShoot();
	  }
	  
	  /**
	   *  Deplacement en mode boost
	   */
	  public void boost()
	  {
	    x += step*2;
		playerVue.spriteAnimate(this);
	  }

	public double getX() {
		  return this.x;
	}

	public double setX() {
		return this.x = x;
	}

	public double getY() {
		  return this.y;
	}

	public double setY() {
		return this.y = y;
	}

	public Sprite getSprite() {
		return this.playerVue.getSprite();
	}

}
