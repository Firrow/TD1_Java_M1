package fr.icom.info.m1.balleauprisonnier_mvn;

import fr.icom.info.m1.balleauprisonnier_mvn.Model.Human;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.IA;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.Sprite;
import fr.icom.info.m1.balleauprisonnier_mvn.Vue.ProjectileVue;
import fr.icom.info.m1.balleauprisonnier_mvn.Model.Projectile;


import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

//DANS AUCUN DOSSIER
public class Game {
    /** Joueurs */
    Human[] humans = new Human[1];
    IA[] enemies = new IA[2];

    List<Player> players = new ArrayList<>();
    public List<Player> getPlayers() {
        return players;
    }
    public IA[] getIA() {return enemies;}
    public Human[] getHuman() {return humans;}
    public Projectile getProjectile() {return projectile;}

    Field field;
    Projectile projectile;
    private double width;
    private double height;
    private GraphicsContext gc;

    public Game(Scene scene, double w, double h, Field field)
    {
        //super(w, h);
        width = w;
        height = h;
        for(Human human : humans){
            players.add(human);
        }
        for(IA ia : enemies){
            players.add(ia);
        }

        this.field=field;
        this.gc = field.getGraphicsContext2D();
        this.field = new Field(scene, (int)width, (int)height);

        startGame();
    }

    void startGame(){
        Image commonImage = new Image("assets/orc.png");

        Sprite spriteHuman = new Sprite(commonImage, 0,0, Duration.seconds(.2), "bottom");
        spriteHuman.setX(width/2);
        spriteHuman.setY(height-50);

        Sprite spriteIA0 = new Sprite(commonImage, 0,0, Duration.seconds(.2), "top");
        spriteIA0.setX(width/3);
        spriteIA0.setY(20);

        Sprite spriteIA1 = new Sprite(commonImage, 0,0, Duration.seconds(.2), "top");
        spriteIA1.setX(width/2);
        spriteIA1.setY(20);


        /** On initialise le terrain de jeu */
        PlayerVue playerVueHuman = new PlayerVue(spriteHuman, gc, "bottom", 0, width/2, height-50);
        PlayerVue playerVueIA0 = new PlayerVue(spriteIA0, gc, "top",0, width/3, 20);
        PlayerVue playerVueIA1 = new PlayerVue(spriteIA1, gc, "top", 0, width/2, 20);

        humans[0] = new Human(gc, (int)width/2, (int)height-50, playerVueHuman);
        playerVueHuman.display(humans[0]);

        enemies[0] = new IA(gc, (int)width/3, 20, playerVueIA0);
        playerVueIA0.display(enemies[0]);
        enemies[1] = new IA(gc, (int)width/2, 20, playerVueIA1);
        playerVueIA1.display(enemies[1]);
        

    }
}
