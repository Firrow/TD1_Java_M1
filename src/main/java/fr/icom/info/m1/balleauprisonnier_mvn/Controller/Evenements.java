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
    
	public Evenements(Field field, List<Player> joueurs, Projectile projectile, GameVue gameVue) {
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
					//si joueur a projectile
					//va appeler Touched qui va appeler al methode supprimer du model de Player
					if(gameVue.Touched(projectile, joueurs.get(i).getSprite())){
						joueurs.get(i).killPlayer();
						playerToRemove.add(joueurs.get(i));
					}
	    	    }
//<<<<<<< HEAD
//				for(Player player : playerToRemove){
//					joueurs.remove(player);
//				}
//				playerToRemove.clear();
	        	projectile.getVue().display(projectile);

				removeDeadPlayers();
//=======
	        	//if((projectile)!=null)
	        			//projectile.getVue().display(projectile);
//>>>>>>> 215d877b36b41e06a39e4b7776a6520d2fe91537
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


	//TODO : LE JOUEUR 1 DISPARAIT INSTANTANÉMENT CAR IL EST TOUCHÉ PAR LA BALLE
	//CRÉER UNE VARIABLE "POSSÈDE LA BALLE", L'INITIALISER À FALSE SAUF POUR LE JOUEUR 1 AU DÉBUT
	//SI LE JOUEUR NE POSSEDE PAS LA BALLE ET QU'IL SE FAIT TOUCHÉ : DISPARAIT
	//SINON POSSÈDE LA BALLE
}

