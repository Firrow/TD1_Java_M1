package fr.icom.info.m1.balleauprisonnier_mvn.Model;

import fr.icom.info.m1.balleauprisonnier_mvn.Vue.PlayerVue;
import javafx.scene.canvas.GraphicsContext;

public class IA extends Player {

    /**
     * Constructeur du Joueur
     *
     * @param gc    ContextGraphic dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param xInit
     * @param yInit position verticale
     * @param side
     */
    public IA(GraphicsContext gc, String color, int xInit, int yInit, String side, PlayerVue playerVue) {
        super(gc, color, xInit, yInit, side, playerVue);
    }
}
