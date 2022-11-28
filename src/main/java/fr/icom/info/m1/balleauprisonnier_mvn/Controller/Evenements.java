package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.GameVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class Evenements extends Canvas {

	
	/** Tableau tracant les evenements */
    ArrayList<String> input = new ArrayList<String>();
	private List<Player> players;
	private Projectile projectile;
    
	public Evenements(Field field, List<Player> players, GameVue gameVue) {
		this.players = players;

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
		//A DEPLACER DANS GAME !
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	        	field.display();
				List<Player> playerToRemove = new ArrayList<>();
				
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < players.size(); i++)
	    	    {
	        		//On recupere l'input
					gameVue.getInput(i, input);
					
					//Affichage des fleches de direction si le joueur est en vie
	        		if(players.get(i).isAlive()) players.get(i).getPlayerVue().display(players.get(i));

					projectile=Projectile.getInstance();

					//Gestion des collisions joueurs et du score
					if(projectile != null && projectile.getMoving()){
						//Le joueur en vie est touche
						if(gameVue.touched(projectile, players.get(i).getSprite()) && players.get(i).isAlive()){
							players.get(i).takeBall(input);
							if(!players.get(i).isTakeBall()){
								if(players.get(i) instanceof IA) {
									gameVue.scoreUp();
								}
								
								players.get(i).killPlayer();
								playerToRemove.add(players.get(i));
							}
							
							else{
								projectile.setMoving(false);
							}
						}
					}
					//Le joueur reprend la balle
					else if(projectile.getVue() != null && !projectile.getMoving()){
						if(gameVue.touched(projectile, players.get(i).getSprite()) && players.get(i).isAlive() && !players.get(i).isTakeBall()){

							players.get(i).setTakeBall(true);
						}		
					}
	    	    }
				removeDeadPlayers();
	    	}
	     }.start(); // On lance la boucle de rafraichissement
	}
	
	public void removeDeadPlayers(){
		for (Player player : players) {
			if(!player.isAlive()){
				player.getSprite().setVisible(false);
			}
		}
	}
}

