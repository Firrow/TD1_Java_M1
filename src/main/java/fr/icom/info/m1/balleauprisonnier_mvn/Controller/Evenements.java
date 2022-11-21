package fr.icom.info.m1.balleauprisonnier_mvn.Controller;

import fr.icom.info.m1.balleauprisonnier_mvn.Game;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.GameVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class Evenements extends Canvas {

	
	/** Tableau tracant les evenements */
    ArrayList<String> input = new ArrayList<String>();
	private GameVue gv;
	private List<Player> joueurs;

	private Projectile projectile;
    
	public Evenements(Field field, List<Player> joueurs, GameVue gameVue) {
		this.gv = gameVue;
		this.joueurs = joueurs;

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
				List<Player> playerToRemove = new ArrayList<>();
	            // Deplacement et affichage des joueurs


	        	for (int i = 0; i < joueurs.size(); i++)
	    	    {
					gameVue.getInput(i, input);
	        		joueurs.get(i).getPlayerVue().display(joueurs.get(i));

					projectile=Projectile.getInstance();
					System.out.println("joueur " + i + " : " + joueurs.get(i).isTake_ball());
					//System.out.println(projectile.getMoving());

					if(projectile != null && projectile.getMoving()){
						//System.out.println("PROJECTILE !");
						if(gameVue.Touched(projectile, joueurs.get(i).getSprite())){
							joueurs.get(i).TakeBall(input);	//appelé joueurs.get(i).prendBalle
							if(!joueurs.get(i).isTake_ball()){
								//System.out.println(joueurs.get(i).isTake_ball());
								joueurs.get(i).killPlayer();
								playerToRemove.add(joueurs.get(i));
							}
							else{
								projectile.setMoving(false);
								//TODO : FAIRE EN SORTE QUE LA BALLE S'ARRETE SUR LA LIGNE DES JOUEURS
							}
						}
					}

					else if(projectile.getVue() != null && !projectile.getMoving()){
						if(gameVue.Touched(projectile, joueurs.get(i).getSprite()) && joueurs.get(i).isAlive() && !joueurs.get(i).isTake_ball()){
							//projectile.setDirection(projectile.getDirection()+180);
							joueurs.get(i).setTake_ball(true);
							}
							
					}
	    	    
	    	    }

				removeDeadPlayers();
	    	}
	     }.start(); // On lance la boucle de rafraichissement
	}
	public void removeDeadPlayers(){
		for (Player player : joueurs) {
			if(!player.isAlive()){
				player.getSprite().setVisible(false);
			}
		}
	}
}

