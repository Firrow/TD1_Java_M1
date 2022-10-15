package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.PlayerVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Field;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
public class Evenements extends Canvas {

	
	/** Tableau tracant les evenements */
    ArrayList<String> input = new ArrayList<String>();

    
	public Evenements(Field field, Player[] joueurs) {

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    field.setOnKeyPressed(
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
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {	 
	        	
	        	/*nettoie_canvas(GraphicsContext gc, int width, int height)*/
	            // On nettoie le canvas a chaque frame
	            /*field.gc.setFill( Color.LIGHTGRAY);
	            field.gc.fillRect(0, 0, width, height);
	        	*/
	        	field.display();
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.length; i++) 
	    	    {
	        		if (i==0 && input.contains("LEFT"))
	        		{
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
	        		if (i==0 && input.contains("ENTER")){
	        			joueurs[i].shoot();
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

	        		
	        		joueurs[i].display();
	    	    }
	    	}
	     }.start(); // On lance la boucle de rafraichissement 
	}

}

