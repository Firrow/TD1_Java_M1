package fr.icom.info.m1.balleauprisonnier_mvn;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Human;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.Field;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

//DANS AUCUN DOSSIER
public class Game {
    /** Joueurs */
    Human[] joueurs = new Human[1];
    IA[] ennemis = new IA[2];

    public IA[] getIA() {return ennemis;}
    public Human[] getHuman() {return joueurs;}

    Field field = new Field;
    private int width;
    private int height;
    private GraphicsContext gc;

    public Game(Scene scene, int w, int h)
    {
        //super(w, h);
        width = w;
        height = h;

        this.gc = field.getGc();
    }

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
}
