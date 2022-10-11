package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Human;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */
	Human[] joueurs = new Human[1];
	IA[] ennemis = new IA[2];

	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu à laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();


		Image commonImage = new Image("assets/orc.png");

		Sprite spriteHuman = new Sprite(commonImage, 0,0, Duration.seconds(.2), "bottom");
		spriteHuman.setX(w/2);
		spriteHuman.setY(h-50);

		Sprite spriteIA0 = new Sprite(commonImage, 0,0, Duration.seconds(.2), "top");
		spriteIA0.setX(w/3);
		spriteIA0.setY(20);

		Sprite spriteIA1 = new Sprite(commonImage, 0,0, Duration.seconds(.2), "top");
		spriteIA1.setX(w/2);
		spriteIA1.setY(20);


        /** On initialise le terrain de jeu */
		PlayerVue playerVueHuman = new PlayerVue(spriteHuman, gc, "bottom", 0, w/2, h-50);
		PlayerVue playerVueIA0 = new PlayerVue(spriteIA0, gc, "top",0, w/3, 20);
		PlayerVue playerVueIA1 = new PlayerVue(spriteIA1, gc, "top", 0, w/2, 20);

		joueurs[0] = new Human(gc, w/2, h-50, playerVueHuman);
    	playerVueHuman.display(joueurs[0]);

    	ennemis[0] = new IA(gc, w/3, 20, playerVueIA0);
		playerVueIA0.display(ennemis[0]);
		ennemis[1] = new IA(gc, w/2, 20, playerVueIA1);
		playerVueIA1.display(ennemis[1]);


	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute à la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enlève de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee à chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{ //fonction anonyme
				gc.setFill( Color.LIGHTGRAY);
				gc.fillRect(0, 0, width, height);

				// Deplacement et affichage des joueurs
				for (int i = 0; i < joueurs.length; i++)
				{
					{
						if (i==0 && input.contains("LEFT"))
							joueurs[i].moveLeft();
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
					if (i==0 && input.contains("ENTER")){
						joueurs[i].shoot();
					}

					playerVueHuman.display(joueurs[i]);
				}
			}
		}.start(); // On lance la boucle de rafraichissement
	     
	}

	public Player[] getJoueurs() {
		return joueurs;
	}

	public IA[] getIA() {
		return ennemis;
	}
	//public Human[] getHuman() {return joueurs;}
}
