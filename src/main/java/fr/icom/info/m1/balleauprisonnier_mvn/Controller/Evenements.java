package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.GameVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class Evenements extends Canvas {

	
	/** Tableau tracant les evenements */
    ArrayList<String> input = new ArrayList<String>();
	private GameVue gv;
    
	public Evenements(Field field, Player[] joueurs, Projectile projectile, GameVue gameVue) {
		this.gv = gameVue;

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    field.setOnKeyPressed( //a mettre dans GameVueue
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
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    field.setOnKeyReleased(
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
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     *
	     */
		//A DÉPLACER DANS GAME !
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	        	field.display();
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.length; i++)
	    	    {
					gameVue.getInput(i, input);
	        		joueurs[i].getPlayerVue().display(joueurs[i]);
					//si joueur a projectile
	    	    }
	        	if((projectile)!=null)
	        			projectile.getVue().display(projectile);
	    	}
	     }.start(); // On lance la boucle de rafraichissement
	}

}

