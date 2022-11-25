package fr.icom.info.m1.balleauprisonnier_mvn;


import fr.icom.info.m1.balleauprisonnier_mvn.Controller.Evenements;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.GameVue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
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
        
      //creating menu bar
       // MenuBar menuBar=new MenuBar();
        //creating menu for adding menu items
        //Menu menu=new Menu("Pause");
        //creating menu items
        Font f = Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,20);
        /*Button button = new Button("Redemarrer");
        button.setLayoutX(10);
        button.setLayoutY(10);
        button.resize(60, 20);
        button.setFont(f);
        
        button.setStyle("-fx-background-color: MediumSeaGreen");
		*/
        Text score = new Text();
        /*Text text = new Text();
        text.setFont(f);*/
        /*
        text.setLayoutY(30);
        text.setLayoutX(10);
        text.setFont(f);*/
        //adding menu to the menu bar
        /*menuBar.add(button);*/
        //menuBar.getMenus().add(menu);

        // On cree le terrain de jeu et on l'ajoute a la racine de la scene
		//appeler
        Field gameField = new Field(scene, 600, 600 );
		Game game = new Game(scene, 600, 600, gameField);

		GameVue gv = new GameVue(score, game.getHuman(), game.getIA(), gameField);

		//text.setText("Score: " + gv.getScore());

        root.getChildren().add( gameField );
        //root.getChildren().add(button);
        root.getChildren().add(score);
		root.getChildren().add(game.getHuman()[0].getSprite());
		root.getChildren().add(game.getIA()[0].getSprite());
		root.getChildren().add(game.getIA()[1].getSprite());

		Evenements evenements = new Evenements(gameField, game.getPlayers(), gv);
		
        /*button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                game.startGame();
            }
        });*/
        // On ajoute la scene a la fenetre et on affiche
        stage.setScene( scene );
        stage.show();
	}
	
    public static void main(String[] args) 
    {
    	Application.launch(args);
    }
}
