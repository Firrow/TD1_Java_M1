package fr.icom.info.m1.balleauprisonnier_mvn;


import fr.icom.info.m1.balleauprisonnier_mvn.Controller.Evenements;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.GameVue;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        

        
        Font f = Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,20);
        // Creation du texte indiquant le score
        Text score = new Text();
        score.setLayoutY(30);
        score.setLayoutX(10);
        score.setFont(f);

        // On cree le terrain de jeu et on l'ajoute a la racine de la scene
        Field gameField = new Field(scene, 600, 600 );
        
		Game game = new Game(scene, 600, 600, gameField);
		GameVue gv = new GameVue(score, game.getHuman(), game.getIA(), gameField);

		Evenements evenements = new Evenements(gameField, game.getPlayers(), gv);
		
        root.getChildren().add( gameField );
        root.getChildren().add(score);
		root.getChildren().add(game.getHuman()[0].getSprite());
		root.getChildren().add(game.getIA()[0].getSprite());
		root.getChildren().add(game.getIA()[1].getSprite());


		

        // On ajoute la scene a la fenetre et on affiche
        stage.setScene( scene );
        stage.show();
	}
	
    public static void main(String[] args) 
    {
    	Application.launch(args);
    }
}
