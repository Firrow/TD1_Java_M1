package fr.icom.info.m1.balleauprisonnier_mvn;


import fr.icom.info.m1.balleauprisonnier_mvn.Controller.Evenements;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.GameVue;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale de l'application 
 * s'appuie sur javafx pour le rendu
 */
public class App extends Application 
{
	
	/**
	 * En javafx start() lance l'application
	 *
	 * On cree le SceneGraph de l'application ici
	 * @see //http://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception 
	{
		// Nom de la fenetre
        stage.setTitle("BalleAuPrisonnier");

        Group root = new Group();
        Scene scene = new Scene( root );

        // On cree le terrain de jeu et on l'ajoute a la racine de la scene
		//appeler
        Field gameField = new Field(scene, 600, 600 );
		Game game = new Game(scene, 600, 600, gameField);
<<<<<<< HEAD
		GameVue gv = new GameVue(game.getHuman(), gameField.getGraphicsContext2D());
=======
		GameVue gv = new GameVue(game.getHuman(), gameField);
>>>>>>> 215d877b36b41e06a39e4b7776a6520d2fe91537
        root.getChildren().add( gameField );
		root.getChildren().add(game.getHuman()[0].getSprite());
		root.getChildren().add(game.getIA()[0].getSprite());
		root.getChildren().add(game.getIA()[1].getSprite());

		Evenements evenements = new Evenements(gameField, game.getPlayers(), game.getProjectile(), gv);

        // On ajoute la scene a la fenetre et on affiche
        stage.setScene( scene );
        stage.show();
	}
	
    public static void main(String[] args) 
    {
        //System.out.println( "Hello World!" );
    	Application.launch(args);
    }
}
